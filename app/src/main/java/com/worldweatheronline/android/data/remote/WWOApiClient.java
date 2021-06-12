package com.worldweatheronline.android.data.remote;

import com.worldweatheronline.android.data.remote.services.WeatherService;

import javax.inject.Inject;
import javax.inject.Singleton;

/***
 * World Weather Online Api Client
 * TODO: add documentation
 */
@Singleton
public class WWOApiClient  {
    private final static String BASE_URL= "https://api.worldweatheronline.com/premium/v1/weather.ashx";
    @Inject
    WeatherService mWeatherService;



    //package private
    @Inject
    WWOApiClient() {

    }

}
