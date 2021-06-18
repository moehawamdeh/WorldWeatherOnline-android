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
    private WeatherData mData;

    @SerializedName("search_api")
    @Expose
    private ResultData mResultData;



    public WeatherData getData() {
        return mData;
    }

    public void setData(WeatherData data) {
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
        private List<Result> mResult;

        public List<Result> getResult() {
            return mResult;
        }

        public void setResult(List<Result> result) {
            mResult = result;
        }
    }

    public static class Result{
        @SerializedName("areaName")
        @Expose
        private List<ValueField> areaName;
        @SerializedName("country")
        @Expose
        private List<ValueField> country;
        @SerializedName("region")
        @Expose
        private List<ValueField> region;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longitude")
        @Expose
        private String longitude;

        public List<ValueField> getAreaName() {
            return areaName;
        }

        public void setAreaName(List<ValueField> areaName) {
            this.areaName = areaName;
        }

        public List<ValueField> getCountry() {
            return country;
        }

        public void setCountry(List<ValueField> country) {
            this.country = country;
        }

        public List<ValueField> getRegion() {
            return region;
        }

        public void setRegion(List<ValueField> region) {
            this.region = region;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }

}
