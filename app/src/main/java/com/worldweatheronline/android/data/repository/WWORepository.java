package com.worldweatheronline.android.data.repository;

import com.worldweatheronline.android.data.remote.WeatherApiResponse;
import com.worldweatheronline.android.data.remote.services.WeatherService;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;


public class WWORepository {
    @Inject
    WeatherService mWeatherService;
    @Inject
    WWORepository(){

    }

    public Observable<WeatherApiResponse> getTest() {

        return mWeatherService
                .getCurrentSummary("Amman");

    }
}
