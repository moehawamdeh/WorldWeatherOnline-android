package com.worldweatheronline.android.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

public class WeatherData extends RealmObject {

    @SerializedName("current_condition")
    @Expose
    private RealmList<WeatherCondition> mCurrentConditions;
    @SerializedName("error")
    @Expose
    @Ignore
    private List<ApiError> mErrors;
    @SerializedName("weather")
    @Expose
    private RealmList<Weather> mWeather;


    public RealmList<WeatherCondition> getCurrentConditions() {
        return mCurrentConditions;
    }

    public void setCurrentConditions(RealmList<WeatherCondition> currentConditions) {
        mCurrentConditions = currentConditions;
    }
    public RealmList<Weather> getWeather() {
        return mWeather;
    }

    public void setWeather(RealmList<Weather> weather) {
        mWeather = weather;
    }

    public List<ApiError> getErrors() {
        return mErrors;
    }

    public void setErrors(RealmList<ApiError> errors) {
        mErrors = errors;
    }
}
