package com.tapia.mji.demo.Activities;


import android.os.Bundle;
import android.widget.ImageView;

import com.tapia.mji.demo.Actions.GiveDate;
import com.tapia.mji.demo.Actions.GiveTime;
import com.tapia.mji.demo.Actions.Rotate;
import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Actions.Action;
import com.tapia.mji.tapialib.Activities.TapiaActivity;
import com.tapia.mji.tapialib.Exceptions.LanguageNotSupportedException;
import com.tapia.mji.tapialib.Languages.Language;
import com.tapia.mji.tapialib.Providers.Interfaces.STTProvider;
import com.tapia.mji.tapialib.Providers.Interfaces.TTSProvider;
import com.tapia.mji.tapialib.TapiaApp;
import com.tapia.mji.tapialib.Utils.TapiaAnimation;
import com.tapia.mji.tapialib.Utils.TapiaCalendar;
import com.tapia.mji.tapialib.Utils.TapiaResources;
import com.tapia.mji.tapialib.Utils.TapiaRobot;
import com.tapia.mji.tapialib.Utils.TapiaRobotManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Sami on 06-Jul-16.
 */
public class TalkActivity extends TapiaActivity {

    TapiaAnimation tapiaAnimation;
    List<Action> actions = new ArrayList<>();
    boolean isFirstTime = true;
    TTSProvider.OnStateChangeListener onTTSstateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eyes_layout);
        ImageView tapiaEyes = findViewById(R.id.eyes);
        tapiaAnimation = new TapiaAnimation(this, tapiaEyes);
        tapiaEyes.setOnLongClickListener(v -> {
            finish();
            return false;
        });
        sttProvider = TapiaApp.currentLanguage.getOnlineSTTProvider();
        ttsProvider = TapiaApp.currentLanguage.getTTSProvider();
        onlineNLUProvider = TapiaApp.currentLanguage.getOnlineNLUProvider();
        offlineNLUProvider = TapiaApp.currentLanguage.getOfflineNLUProvider();


        onTTSstateListener = newState -> {
            switch (newState) {
                case IDLE:
                    if (sttProvider.getSTTState() == STTProvider.State.IDLE)
                        tapiaAnimation.setBackground(R.drawable.gradient_aqua);
                    break;
                case SPEAKING:
                    tapiaAnimation.setBackground(R.drawable.gradient_yellow);
                    break;
            }
        };

        actions.add(new GiveDate(new GiveDate.OnGiveDateListener() {
            @Override
            public void onGiveDate(Date date) {
                try {
                    ttsProvider.ask(String.format(getString(R.string.givedate_sentence0), TapiaApp.currentLanguage.getDateString(new Date())), sttProvider);
                } catch (LanguageNotSupportedException e) {
                    e.printStackTrace();
                }

            }
        }));

        actions.add(new GiveTime(new GiveTime.OnGiveTimeListener() {
            @Override
            public void onGiveTime(Date time) {
                try {
                    ttsProvider.ask(String.format(getString(R.string.givetime_sentence0), TapiaCalendar.strHour(), TapiaCalendar.strMinute()), sttProvider);
                } catch (LanguageNotSupportedException e) {
                    e.printStackTrace();
                }

            }
        }));

//        actions.add(new Actions.TakePhoto(new SimpleAction.OnSimpleActionListener() {
//            @Override
//            public void onSimpleAction() {
//                Intent takePhotoIntent = new Intent(activity, PhotoTakeActivity.class);
//                startActivity(takePhotoIntent);
//            }
//        }));
//
//
//
//        actions.add(new MySimpleAction.ShowPhoto(new SimpleAction.OnSimpleActionListener() {
//            @Override
//            public void onSimpleAction() {
//                Intent showPhotoIntent = new Intent(activity, PhotoShowActivity.class);
//                startActivity(showPhotoIntent);
//            }
//        }));


        TapiaRobotManager manager = TapiaRobot.getManager(this);
        actions.add(new Rotate(new Rotate.OnRotateListener() {
            @Override
            public void onRotate(TapiaRobotManager.Direction orientation, int degree) {
                String speech = getString(R.string.rotate_sentence0);
                String direction;
                switch (orientation) {
                    case LEFT:
                        direction = getString(R.string.direction_left0);
                        break;
                    case RIGHT:
                        direction = getString(R.string.direction_right0);
                        break;
                    case UP:
                        direction = getString(R.string.direction_up0);
                        break;
                    case DOWN:
                        direction = getString(R.string.direction_down0);
                        break;
                    default:
                        direction = "";
                        break;
                }
                try {
                    ttsProvider.ask(String.format(speech, direction), sttProvider);
                } catch (LanguageNotSupportedException e) {
                    e.printStackTrace();
                }
                manager.rotate(orientation, degree, null);
            }
        }));


    }


    @Override
    protected void onResume() {
        super.onResume();
        final String startString;
        ttsProvider.setOnStateChangeListener(onTTSstateListener);
        sttProvider.setOnStateChangeListener(newState -> {
            switch (newState) {
                case IDLE:
                    if (ttsProvider.getTTSState() == TTSProvider.State.IDLE)
                        tapiaAnimation.setBackground(0);
                    break;
                case LISTENING:
                    tapiaAnimation.setBackground(R.drawable.gradient_aqua);
                    break;
                case PROCESSING:
                    tapiaAnimation.setBackground(R.drawable.gradient_defult);
                    break;
            }
        });
        sttProvider.setOnRecognitionCompleteListener(results -> {
            offlineNLUProvider.setOnAnalyseCompleteListener(action -> {
                if (action == null) {
                    tapiaAnimation.stopAnimation();
                    tapiaAnimation.startAtFrameEndAtFrame(TapiaAnimation.CONFUSED, true, 26, 89);
                    ttsProvider.setOnStateChangeListener(null);
                    tapiaAnimation.setBackground(R.drawable.gradient_pink);
                    try {
                        ttsProvider.ask(getString(R.string.general_dont_understand0), sttProvider);
                    } catch (LanguageNotSupportedException e) {
                        e.printStackTrace();
                    }
                    ttsProvider.setOnSpeechCompleteListener(() -> {
                        tapiaAnimation.startAnimation(TapiaAnimation.PLAIN, true);
                        tapiaAnimation.setBackground(0);
                        ttsProvider.setOnStateChangeListener(onTTSstateListener);
                    });
                }
            });
            offlineNLUProvider.analyseText(results, actions);
        });
        sttProvider.setOnTimeOutListener(() -> finish());
        if (isFirstTime) {
            startString = TapiaResources.getRandStr(this, "service_hello", 0);
            isFirstTime = false;
            tapiaAnimation.reverseStartAtFrame(TapiaAnimation.TRANSITION1, false, 16);
            tapiaAnimation.setOnAnimationEndListener(() -> {
                tapiaAnimation.startAnimation(TapiaAnimation.PLAIN, true);
                tapiaAnimation.setBackground(R.drawable.gradient_aqua);
                try {
                    ttsProvider.ask(startString, sttProvider);
                } catch (LanguageNotSupportedException e) {
                    e.printStackTrace();
                }
            });
        } else {
            startString = TapiaResources.getRandStr(this, "service_offerHelp", 1);
            try {

                ttsProvider.sayIn("hello", Language.LanguageID.ENGLISH_US);
                ttsProvider.ask(startString, sttProvider);
            } catch (LanguageNotSupportedException e) {
                e.printStackTrace();
            }
            tapiaAnimation.startAnimation(TapiaAnimation.PLAIN, true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        tapiaAnimation.stopAnimation();
    }


}
