package com.tapia.mji.demo.Languages;

import android.content.res.Configuration;

import com.tapia.mji.demo.Providers.Local_NLU;
import com.tapia.mji.demo.R;
import com.tapia.mji.tapialib.Providers.DosmonoOnlineSTTProvider;
import com.tapia.mji.tapialib.Providers.Hoya;
import com.tapia.mji.tapialib.TapiaApp;
import com.tapia.mji.tapialib.Utils.TapiaCalendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by KUBO-PC on 2017/12/25.
 */

public class English_US extends MyLanguage {


    public English_US() {
        Locale jaLocale = new Locale("en");
        Locale.setDefault(jaLocale);
        configuration = new Configuration(TapiaApp.getAppContext().getResources().getConfiguration());
        configuration.locale = jaLocale;

        onlineNLUProvider = null;
        offlineNLUProvider = Local_NLU.class;
        onlineSTTProvider = DosmonoOnlineSTTProvider.class;
        offLineSTTProvider = null;
        ttsProvider = Hoya.class;
        geocodeProvider = null;

    }

    @Override
    public String getDateString(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);

        return gregorianCalendar.get(Calendar.YEAR) + "year"
                + TapiaCalendar.getStrMonth(TapiaApp.getAppContext(), gregorianCalendar.get(Calendar.MONTH))
                + gregorianCalendar.get(Calendar.DAY_OF_MONTH) + "day"
                + TapiaCalendar.getStrDay(TapiaApp.getAppContext(), gregorianCalendar.get(Calendar.DAY_OF_WEEK))
                ;
    }

    @Override
    public LanguageID getID() {
        return LanguageID.ENGLISH_US;
    }

    @Override
    public int getFlagRessource() {
        return R.drawable.usa;
    }


}
