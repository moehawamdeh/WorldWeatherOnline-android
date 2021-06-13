package com.worldweatheronline.android.data.model.api;

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
    private ResultData mResultData;



    public ApiData getData() {
        return mData;
    }

    public void setData(ApiData data) {
        mData = data;
    }

    public ResultData getResult() {
        return mResultData;
    }

    public void setResult(ResultData result) {
        mResultData = result;
    }

    public static class ResultData{
        @SerializedName("result")
        @Expose
        public List<Result> mResult;
    }

    public static class Result{
        @SerializedName("areaName")
        @Expose
        public List<ValueField> areaName;
        @SerializedName("country")
        @Expose
        public List<ValueField> country;
        @SerializedName("region")
        @Expose
        public List<ValueField> region;
        @SerializedName("latitude")
        @Expose
        public String latitude;
        @SerializedName("longitude")
        @Expose
        public String longitude;
    }

}
