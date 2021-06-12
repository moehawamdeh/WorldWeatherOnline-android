package com.worldweatheronline.android.data.remote.services;

import com.worldweatheronline.android.data.remote.WeatherApiResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    /**
     * Returns current condition and hourly weather forecast (Today only)
     * @param query  Pass US Zip code, UK Postcode, Canada Postalcode, IP address, Latitude/Longitude (decimal degree) or city name
     * @return Observable of ApiResponse
     * default query params: (tp=1 => hourly), (date=today => no weather data)
     */
    @GET("weather.ashx?tp=1&date=today")
    Observable<WeatherApiResponse> getCurrentSummary(@Query("q") String query);

    /**
     * Returns weather for 14 days, with daily 4-hours interval averages
     * @param query  Pass US Zip code, UK Postcode, Canada Postalcode, IP address, Latitude/Longitude (decimal degree) or city name
     * @return Observable of ApiResponse
     * default query params: (tp=4 => 4hours intervals), (cc=no => remove current condition)
     */
    @GET("weather.ashx?tp=4&cc=no&&num_of_days=14")
    Observable<WeatherApiResponse> getTwoWeeksForecast(@Query("q") String query);


}
