package com.worldweatheronline.android.data.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Generated with https://codebeautify.org/json-to-java-converter
 */
public class AutoCompleteApiResponse {
    //only returned in case of errors exist
    @SerializedName("data")
    @Expose
    private ApiData mData;

    @SerializedName("search_api")
    @Expose
    private Result mResult;



    public ApiData getData() {
        return mData;
    }

    public void setData(ApiData data) {
        mData = data;
    }

    public Result getResult() {
        return mResult;
    }

    public void setResult(Result result) {
        mResult = result;
    }


    public static class Result{
        public List<ValueField> areaNames;
        public List<ValueField> countries;
        public List<ValueField> regions;
        public String latitude;
        public String longitude;
        public String population;
        public List<ValueField> WeatherUrls;
    }

}
