package com.worldweatheronline.android.data.remote.services;

import com.worldweatheronline.android.data.model.api.AutoCompleteApiResponse;
import com.worldweatheronline.android.data.model.api.WeatherApiResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AutoCompleteService {
    /**
     * Search for worldwide city/town/etc, return top 5 results
     * @param query  Pass US Zip code, UK Postcode, Canada Postalcode, IP address, Latitude/Longitude (decimal degree) or city name
     * @return Observable of ApiResponse
     *
     */
    @GET("search.ashx?num_of_results=5")
    Observable<WeatherApiResponse> getTop5Suggestions(@Query("q") String query);

    /**
     * Search for worldwide city/town/etc
     * @param query  Pass US Zip code, UK Postcode, Canada Postalcode, IP address, Latitude/Longitude (decimal degree) or city name
     * @return Observable of ApiResponse
     *
     */
    @GET("search.ashx?num_of_results=1")
    Observable<AutoCompleteApiResponse> getCity(@Query("q") String query);



}
