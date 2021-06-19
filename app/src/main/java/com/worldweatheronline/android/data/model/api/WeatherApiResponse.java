package com.worldweatheronline.android.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Generated with https://codebeautify.org/json-to-java-converter
 */
public class WeatherApiResponse {
    @SerializedName("data")
    @Expose
    private WeatherData mData;

    @SerializedName("request")
    @Expose
    private Request mRequest;

    public WeatherData getData() {
        return mData;
    }

    public void setData(WeatherData data) {
        mData = data;
    }

    public Request getRequest() {
        return mRequest;
    }

    public void setRequest(Request request) {
        mRequest = request;
    }
}
