package com.tapia.mji.demo.Languages;

import android.content.res.Configuration;

import com.tapia.mji.demo.Providers.Local_NLU;
import com.tapia.mji.demo.R;
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

    Configuration configuration;


    public Japanese(){
        Locale jaLocale = new Locale("ja");
        Locale.setDefault(jaLocale);
        configuration = TapiaApp.appContext.getResources().getConfiguration();
        configuration.locale =jaLocale;

        onlineNLUProvider          = null;
        offlineNLUProvider         = Local_NLU.class;
        onlineSTTProvider          = null;
        offLineSTTProvider         = null;
        ttsProvider                = Hoya.class;
        geocodeProvider            = null;

    }

    @Override
    public String getDateString(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);

        return gregorianCalendar.get(Calendar.YEAR)+"年"
                + TapiaCalendar.getStrMonth(TapiaApp.appContext,gregorianCalendar.get(Calendar.MONTH))
                + gregorianCalendar.get(Calendar.DAY_OF_MONTH)+"日"
                + TapiaCalendar.getStrDay(TapiaApp.appContext,gregorianCalendar.get(Calendar.DAY_OF_WEEK))
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

}
