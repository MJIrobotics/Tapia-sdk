package com.tapia.mji.demo.Languages;

import android.content.res.Configuration;

import com.tapia.mji.demo.Providers.Local_NLU;
import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Providers.Fuetrek;
import com.tapia.mji.tapialib.Providers.Hoya;
import com.tapia.mji.tapialib.TapiaApp;
import com.tapia.mji.tapialib.Utils.TapiaCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Sami on 21-Jul-16.
 */
public class Japanese extends MyLanguage {

    public Japanese(){
        Locale jaLocale = new Locale("ja");
        Locale.setDefault(jaLocale);
        configuration = new Configuration(TapiaApp.getAppContext().getResources().getConfiguration());
        configuration.locale =jaLocale;

        onlineNLUProvider          = null;
        offlineNLUProvider         = Local_NLU.class;
        onlineSTTProvider          = Fuetrek.class;
        offLineSTTProvider         = null;
        ttsProvider                = Hoya.class;
        geocodeProvider            = null;

    }

    @Override
    public String getDateString(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);

        return gregorianCalendar.get(Calendar.YEAR)+"年"
                + TapiaCalendar.getStrMonth(TapiaApp.getAppContext(),gregorianCalendar.get(Calendar.MONTH))
                + gregorianCalendar.get(Calendar.DAY_OF_MONTH)+"日"
                + TapiaCalendar.getStrDay(TapiaApp.getAppContext(),gregorianCalendar.get(Calendar.DAY_OF_WEEK))
                + "です。";
    }

    @Override
    public LanguageID getID() {
        return LanguageID.JAPANESE;
    }

    @Override
    public int getFlagRessource() {
        return R.drawable.japan;
    }

    static public String convertFullWidthNumberToHalfWidthNumber(String fullWidthString){
        String halfWidthNumberString = fullWidthString;
        halfWidthNumberString.replace("０","0");
        halfWidthNumberString.replace("１","1");
        halfWidthNumberString.replace("２","2");
        halfWidthNumberString.replace("３","3");
        halfWidthNumberString.replace("４","4");
        halfWidthNumberString.replace("５","5");
        halfWidthNumberString.replace("６","6");
        halfWidthNumberString.replace("７","7");
        halfWidthNumberString.replace("８","8");
        halfWidthNumberString.replace("９","9");
        return halfWidthNumberString;
    }

}
