package com.tapia.mji.demo.Providers.Interfaces;

import android.location.Address;

/**
 * Created by Sami on 07-Jul-16.
 */
public interface GeocodeProvider {

    String LOCAL = "(local)";

    interface GeocodeListener {
        void onLocationFinished(Address result);
    }

    void getLocalLocation(GeocodeListener geocodeListener);

    void getCityLocation(String cityStr, GeocodeListener geocodeListener);
}
