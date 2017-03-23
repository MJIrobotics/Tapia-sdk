package com.tapia.mji.demo.Providers.Interfaces;


import com.tapia.mji.tapialib.Providers.Interfaces.GeocodeProvider;

import java.util.Date;
import java.util.List;

/**
 * Created by Sami on 06-Jul-16.
 */
public interface WeatherProvider {


    void getWeather(Date time, String place, GeocodeProvider geocodeProvider);

    void getWeekWeather(String place, GeocodeProvider geocodeProvider, OnWeekWeatherCompleteListener onWeekWeatherCompleteListener);


    void setOnWeatherCompleteListener(OnWeatherCompleteListener onWeatherCompleteListener);

    interface OnWeatherCompleteListener{
        void onWeatherComplete(WeatherObject weatherObject);
    }

    interface OnWeekWeatherCompleteListener{
        void onWeekWeatherComplete(List<WeatherObject> weatherList);
    }


    class WeatherObject{
        public Weather weather;
        public double maxTemp;
        public double minTemp;
        public String summary;
        public int precipitation;
    }

    enum Weather{
        CLEAR,
        RAIN,
        STORM,
        HAZE,
        CLOUDY,
        SNOW,
        BLIZZARD
    }


}
