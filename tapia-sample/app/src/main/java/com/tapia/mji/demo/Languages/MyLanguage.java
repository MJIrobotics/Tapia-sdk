package com.tapia.mji.demo.Languages;

import android.content.Context;


import com.tapia.mji.demo.Providers.Interfaces.WeatherProvider;
import com.tapia.mji.tapialib.Languages.Language;
import com.tapia.mji.tapialib.TapiaApp;


/**
 * Created by Sami on 06-Jul-16.
 */
public abstract class MyLanguage extends Language {


    public Class newsProvider;
    public Class weatherProvider;


    public WeatherProvider getWeatherProvider() {
        if(weatherProvider != null){
            try {
                return (WeatherProvider)weatherProvider.getMethod("getInstance",Context.class,LanguageID.class).invoke(null, TapiaApp.appContext,getID());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else{
            return null;
        }
    }
}
