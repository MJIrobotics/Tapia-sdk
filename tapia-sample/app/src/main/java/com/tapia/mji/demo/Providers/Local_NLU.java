package com.tapia.mji.demo.Providers;

import static com.tapia.mji.demo.Actions.MyAction.MyActionType.GIVE_TIME;

import android.content.Context;

import com.tapia.mji.demo.Actions.MyAction;
import com.tapia.mji.demo.Actions.Rotate;
import com.tapia.mji.demo.Languages.Japanese;
import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Actions.Action;
import com.tapia.mji.tapialib.Languages.Language;
import com.tapia.mji.tapialib.Providers.Interfaces.OfflineNLUProvider;
import com.tapia.mji.tapialib.Utils.LevenshteinDistance;
import com.tapia.mji.tapialib.Utils.TapiaRobotManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sami on 07-Jul-16.
 */
public class Local_NLU implements OfflineNLUProvider {

    Language.LanguageID language;

    ArrayList<Keyword> myKeywords = new ArrayList<>();
    OnAnalyseCompleteListener onAnalyseCompleteListener;
    static Local_NLU myInstance = null;

    static public Local_NLU getInstance(Context context, Language.LanguageID language) {
        if (myInstance == null || !language.equals(myInstance.language)) {
            myInstance = new Local_NLU(context, language);
        }
        return myInstance;
    }

    class Keyword {
        String[] keywordArray;
        Action.ActionType actionType;

        Keyword(String[] keywordArray, Action.ActionType actionType) {
            this.keywordArray = keywordArray;
            this.actionType = actionType;
        }
    }

    Context context;

    public Local_NLU(Context context, Language.LanguageID language) {
        this.language = language;
        this.context = context;
        switch (language) {
            case ENGLISH_US:
            case ENGLISH_UK:
                myKeywords.add(new Keyword(new String[]{"what", "time"}, GIVE_TIME));
                myKeywords.add(new Keyword(new String[]{"rotate", "degree"}, MyAction.MyActionType.ROTATE));
                break;
            case JAPANESE:
                myKeywords.add(new Keyword(new String[]{"何時"}, MyAction.MyActionType.GIVE_TIME));
                myKeywords.add(new Keyword(new String[]{"何曜日"}, MyAction.MyActionType.GIVE_DATE));
                myKeywords.add(new Keyword(new String[]{"何日"}, MyAction.MyActionType.GIVE_DATE));
                myKeywords.add(new Keyword(new String[]{"回転", "°"}, MyAction.MyActionType.ROTATE));
                break;
        }
    }

    @Override
    public void analyseText(final List<String> sentences, final List<Action> actionToListen) {
        ((Runnable) () -> {

            ArrayList<Keyword> listToAnalyse = new ArrayList<Keyword>();
            for (Action act : actionToListen) {
                for (Keyword keyword : myKeywords) {
                    if (keyword.actionType == act.getType()) {
                        listToAnalyse.add(keyword);
                    }
                }
            }
            Keyword keywordGroup = getSimpleBestKeywordsGroup(listToAnalyse, sentences);
            Action resultAction = null;
            if (keywordGroup != null) {
                switch ((MyAction.MyActionType) keywordGroup.actionType) {
                    case ROTATE:
                        Rotate rotate = (Rotate) Action.queryAction(actionToListen, MyAction.MyActionType.ROTATE);
                        if (rotate != null) {
                            for (String sentence : sentences) {
                                if (sentence.contains(context.getString(R.string.direction_left0)))
                                    rotate.setOrientation(TapiaRobotManager.Direction.LEFT);
                                else if (sentence.contains(context.getString(R.string.direction_right0)))
                                    rotate.setOrientation(TapiaRobotManager.Direction.RIGHT);
                                else if (sentence.contains(context.getString(R.string.direction_up0)))
                                    rotate.setOrientation(TapiaRobotManager.Direction.UP);
                                else if (sentence.contains(context.getString(R.string.direction_down0)))
                                    rotate.setOrientation(TapiaRobotManager.Direction.DOWN);
                            }
                            int degrees = -1;
                            for (String sentence : sentences) {
                                try {
                                    if (language.equals(Language.LanguageID.JAPANESE)) {
                                        sentence = Japanese.convertFullWidthNumberToHalfWidthNumber(sentence);
                                    }

                                    degrees = Integer.parseInt(sentence.replaceAll("[\\D]", ""));
                                    rotate.setDegree(degrees);
                                    break;
                                } catch (Exception e) {
                                    degrees = -1;
                                }
                            }
                            if (degrees != -1)
                                resultAction = rotate;
                            else
                                resultAction = null;
                        }
                        break;
                    default:
                        resultAction = Action.queryAction(actionToListen, keywordGroup.actionType);
                        break;
                }
            }
            if (resultAction != null) {
                resultAction.onAction();
            }
            if (onAnalyseCompleteListener != null) {
                onAnalyseCompleteListener.OnAnalyseComplete(resultAction);
            }
        }).run();
    }

    @Override
    public void setOnAnalyseCompleteListener(OnAnalyseCompleteListener onAnalyseCompleteListener) {
        this.onAnalyseCompleteListener = onAnalyseCompleteListener;
    }

    public Keyword getSimpleBestKeywordsGroup(ArrayList<Keyword> keywords, List<String> sentences) {
        Keyword bestKeywords = null;
        for (int k = sentences.size() - 1; k >= 0; k--) {
            for (Keyword kw : keywords) {
                boolean isMatch = true;
                for (String word : kw.keywordArray) {
                    if (!sentences.get(k).toLowerCase().contains(word.toLowerCase()))
                        isMatch = false;
                }
                if (isMatch)
                    bestKeywords = kw;
            }
        }

        return bestKeywords;
    }

    public String[] getBestKeywordsGroup(ArrayList<String[]> keywords, List<String> sentences) {
        int[] weights = new int[keywords.size()];

        for (int k = 0; k < keywords.size(); k++) {
            String[] keyword = keywords.get(k);
            for (int l = 0; l < keyword.length; l++) {
                String keywordOnlyWords = keyword[l].replace(",", "").replace(".", "");
                List<String> keywordWords = Arrays.asList(keywordOnlyWords.split(" "));
                for (int i = 0; i < sentences.size(); i++) {
                    String resultOnlyWords = sentences.get(i).replace(",", "").replace(".", "");
                    List<String> resultWords = Arrays.asList(resultOnlyWords.split(" "));
                    if (keywordOnlyWords.length() < 8 && keywordWords.size() > 1) {
                        if (resultOnlyWords.contains(keywordOnlyWords))
                            weights[k] += (sentences.size() + 1 - i) * keywordWords.size();
                    } else {
                        for (String resultWord : resultWords) {
                            for (String keywordWord : keywordWords) {
                                if (keywordWord.length() <= 4 && keywordWord.length() > 1)
                                    if (LevenshteinDistance.computeLevenshteinDistance(keywordWord.toLowerCase(), resultWord.toLowerCase()) == 0) {
                                        weights[k] += sentences.size() + 1 - i;
                                    }
                                if (keywordWord.length() > 4 && keywordWord.length() < 8) {
                                    if (LevenshteinDistance.computeLevenshteinDistance(keywordWord.toLowerCase(), resultWord.toLowerCase()) <= 1)
                                        weights[k] += sentences.size() + 2 - i - LevenshteinDistance.computeLevenshteinDistance(keywordWord.toLowerCase(), resultWord.toLowerCase());
                                }
                                if (keywordWord.length() >= 8) {
                                    if (LevenshteinDistance.computeLevenshteinDistance(keywordWord.toLowerCase(), resultWord.toLowerCase()) <= 2)
                                        weights[k] += sentences.size() + 3 - i - LevenshteinDistance.computeLevenshteinDistance(keywordWord.toLowerCase(), resultWord.toLowerCase());
                                }
                            }
                        }
                    }
                }
                if (l == 0 && weights[k] == 0)
                    break;
            }
        }
        int bestWeightPos = 0;
        int bestWeightValue = 0;
        for (int j = 0; j < weights.length; j++) {
            if (bestWeightValue < weights[j]) {
                bestWeightValue = weights[j];
                bestWeightPos = j;
            }
        }
        //no keyword found
        if (bestWeightValue <= 20)
            return null;
        else {
            int drawNumber = 0;
            for (int weight : weights) {
                if (weight == weights[bestWeightPos])
                    drawNumber++;
            }
            if (drawNumber > 1)
                return null;

            return keywords.get(bestWeightPos);
        }
    }
}
