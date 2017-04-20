package com.tapia.mji.demo.Languages;

import android.content.Context;

import com.tapia.mji.demo.Providers.Interfaces.GeocodeProvider;
import com.tapia.mji.tapialib.Languages.Language;
import com.tapia.mji.tapialib.TapiaApp;


/**
 * Abstract class extending Language.
 *
 * To add support for another language to Tapia, implement an extension of MyLanguage for the language.
 *
 * Created by Sami on 06-Jul-16.
 */
public abstract class MyLanguage extends Language {

    //Fields
    public Class geocodeProvider;

    /**
     * Get the GeocodeProvider object associated with the MyLanguage.
     *
     * @return the GeocodeProvider object associated with the MyLanguage.
     */
    public GeocodeProvider getGeocodeProvider() {
        if(geocodeProvider != null){
            try {
                return (GeocodeProvider) geocodeProvider.getMethod("getInstance",Context.class,LanguageID.class).invoke(null, TapiaApp.getAppContext(),getID());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        else{
            return null;
        }    }
}
