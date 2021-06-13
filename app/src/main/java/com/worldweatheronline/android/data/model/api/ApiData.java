package com.worldweatheronline.android.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiData {

    @SerializedName("current_condition")
    @Expose
    private List<WeatherCondition> mCurrentConditions;
    @SerializedName("error")
    @Expose
    private List<ApiError> mErrors;
    @SerializedName("weather")
    @Expose
    private List<Weather> mWeather;


    public List<WeatherCondition> getCurrentConditions() {
        return mCurrentConditions;
    }

    public void setCurrentConditions(List<WeatherCondition> currentConditions) {
        mCurrentConditions = currentConditions;
    }
    public List<Weather> getWeather() {
        return mWeather;
    }

    public void setWeather(List<Weather> weather) {
        mWeather = weather;
    }

    public List<ApiError> getErrors() {
        return mErrors;
    }

    public void setErrors(List<ApiError> errors) {
        mErrors = errors;
    }
}
