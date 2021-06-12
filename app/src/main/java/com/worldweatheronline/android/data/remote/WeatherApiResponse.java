package com.worldweatheronline.android.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Generated with https://codebeautify.org/json-to-java-converter
 */
public class WeatherApiResponse {
    @SerializedName("data")
    @Expose
    private ApiData mData;

    @SerializedName("request")
    @Expose
    private Request mRequest;


    public ApiData getData() {
        return mData;
    }

    public void setData(ApiData data) {
        mData = data;
    }

    public Request getRequest() {
        return mRequest;
    }

    public void setRequest(Request request) {
        mRequest = request;
    }
}
