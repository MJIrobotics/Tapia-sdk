package com.tapia.mji.demo.Providers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.tapia.mji.demo.Providers.Interfaces.GeocodeProvider;
import com.tapia.mji.tapialib.Languages.Language;
import com.tapia.mji.tapialib.Utils.TapiaNetwork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * Created by Sami on 06-Jul-16.
 */
public class Google implements GeocodeProvider {

    Language.LanguageID language;

    LocationManager locationManager;
    Context context;
    String city = "Tokyo";
    Geocoder geocoder;
    GeocodeListener geocodeListener;

    static Google myInstance = null;

    public static Google getInstance(Context context, Language.LanguageID language) {
        if (myInstance == null || !language.equals(myInstance.language)) {
            myInstance = new Google(context, language);
        }
        return myInstance;
    }

    boolean isStopped = true;

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            List<Address> addresses = null;
            try {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                geocodeListener.onLocationFinished(addresses.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public Google(Context context, final Language.LanguageID language) {
        this.context = context;
        this.language = language;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        geocoder = new Geocoder(context);
    }

    @Override
    public void getLocalLocation(final GeocodeListener geocodeListener) {
        Location loc = null;
        try {
//            TapiaNetwork.getJson("http://ip-api.com/json", new TapiaNetwork.OnJSONListener() {
//                @Override
//                public void onJSON(JSONObject jsonObject) {
//                    Address myAddress = new Address(Locale.ENGLISH);
//                    try {
//                        myAddress.setCountryName(jsonObject.getString("country"));
//                        myAddress.setCountryCode(jsonObject.getString("countryCode"));
//                        myAddress.setLatitude(jsonObject.getDouble("lat"));
//                        myAddress.setLongitude(jsonObject.getDouble("lon"));
//                        myAddress.setAdminArea(jsonObject.getString("city"));
//                        geocodeListener.onLocationFinished(myAddress);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });

            TapiaNetwork.getJson("http://ipapi.co/json", new TapiaNetwork.OnJSONListener() {
                @Override
                public void onJSON(JSONObject jsonObject) {
                    Address myAddress = new Address(Locale.ENGLISH);
                    try {
//                        myAddress.setCountryName(jsonObject.getString("country"));
                        myAddress.setCountryCode(jsonObject.getString("country"));
                        myAddress.setLatitude(jsonObject.getDouble("latitude"));
                        myAddress.setLongitude(jsonObject.getDouble("longitude"));
                        myAddress.setAdminArea(jsonObject.getString("city"));
                        geocodeListener.onLocationFinished(myAddress);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
//            loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            List<Address> addresses = null;
//            if (loc != null) {
//                try {
//                    addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
//                    geocodeListener.onLocationFinished(addresses.get(0));
//                }
//                catch (Exception e){
//                    e.printStackTrace();
//                    //if geocoder fail we can at least get the coordinate
//                    Address add = new Address(Locale.getDefault());
//                    add.setLatitude(loc.getLatitude());
//                    add.setLongitude(loc.getLongitude());
//                    geocodeListener.onLocationFinished(add);
//                }
//
//            } else {
//                this.geocodeListener = geocodeListener;
//                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCityLocation(String cityStr, final GeocodeListener geocodeListener) {
        Address cityLocation = null;
        TapiaNetwork.getJson("https://maps.googleapis.com/maps/api/geocode/json?&address=" + cityStr.replace(" ", "%20"), new TapiaNetwork.OnJSONListener() {
            @Override
            public void onJSON(JSONObject jsonObject) {
                try {
                    if (jsonObject.getString("status").equals("OK")) {
                        Address cityAddress = new Address(Locale.ENGLISH);
                        JSONArray results = jsonObject.getJSONArray("results");
                        JSONObject firstResult = results.getJSONObject(0);
                        JSONObject location = firstResult.getJSONObject("geometry").getJSONObject("location");
                        cityAddress.setLongitude(location.getDouble("lng"));
                        cityAddress.setLatitude(location.getDouble("lat"));
                        geocodeListener.onLocationFinished(cityAddress);
                    } else {
                        //error
                        geocodeListener.onLocationFinished(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
//        try {
//            List<Address> addresses = geocoder.getFromLocationName(cityStr, 1);
//
//            if (addresses.size() > 0) {
//                double latitude1= addresses.get(0).getLatitude();
//                double longitude1= addresses.get(0).getLongitude();
//                cityLocation = addresses.get(0);
//                //latitude = Double.toString(latitude1);
//                //longitude =  Double.toString(longitude1);
//            }
//        }
//        catch (IOException e){
//            //couldn't find the location
//        }
//        return cityLocation;
    }
}
