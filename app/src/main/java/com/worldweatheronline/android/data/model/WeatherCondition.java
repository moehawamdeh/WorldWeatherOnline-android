package com.worldweatheronline.android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherCondition {
    @SerializedName(value="time", alternate={"observation_time"})
    @Expose
    private String mTime;
    @SerializedName("temp_C")
    @Expose
    private String mTempC;
    @SerializedName("temp_F")
    @Expose
    private String mTempF;
    @SerializedName("weatherCode")
    @Expose
    private String mWeatherCode;
    @SerializedName("weatherIconUrl")
    @Expose
    private List<WeatherIconUrl> mWeatherIconUrl;
    @SerializedName("weatherDesc")
    @Expose
    private List<WeatherDesc> mWeatherDesc;
    @SerializedName("lang_ar")
    @Expose
    private List<LangAr> mArabicDesc;

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getTempC() {
        return mTempC;
    }

    public void setTempC(String tempC) {
        mTempC = tempC;
    }

    public String getTempF() {
        return mTempF;
    }

    public void setTempF(String tempF) {
        mTempF = tempF;
    }

    public String getWeatherCode() {
        return mWeatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        mWeatherCode = weatherCode;
    }

    public List<WeatherIconUrl> getWeatherIconUrl() {
        return mWeatherIconUrl;
    }

    public void setWeatherIconUrl(List<WeatherIconUrl> weatherIconUrl) {
        mWeatherIconUrl = weatherIconUrl;
    }

    public List<WeatherDesc> getWeatherDesc() {
        return mWeatherDesc;
    }

    public void setWeatherDesc(List<WeatherDesc> weatherDesc) {
        mWeatherDesc = weatherDesc;
    }

    public List<LangAr> getArabicDesc() {
        return mArabicDesc;
    }

    public void setArabicDesc(List<LangAr> arabicDesc) {
        mArabicDesc = arabicDesc;
    }
}
