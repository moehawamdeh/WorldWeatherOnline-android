package com.worldweatheronline.android.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
public class WeatherCondition extends RealmObject {
    @SerializedName(value="time", alternate={"observation_time"})
    @Expose
    private String mTime;
    @SerializedName(value = "temp_C", alternate = {"tempC"})
    @Expose
    private String mTempC;
    @SerializedName(value= "temp_F", alternate = {"tempF"})
    @Expose
    private String mTempF;
    @SerializedName("weatherCode")
    @Expose
    private String mWeatherCode;
    @SerializedName("weatherIconUrl")
    @Expose
    private RealmList<WeatherIconUrl> mWeatherIconUrl;
    @SerializedName("weatherDesc")
    @Expose
    private RealmList<WeatherDesc> mWeatherDesc;
    @SerializedName("lang_ar")
    @Expose
    private RealmList<LangAr> mArabicDesc;

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

    public void setWeatherIconUrl(RealmList<WeatherIconUrl> weatherIconUrl) {
        mWeatherIconUrl = weatherIconUrl;
    }

    public List<WeatherDesc> getWeatherDesc() {
        return mWeatherDesc;
    }

    public void setWeatherDesc(RealmList<WeatherDesc> weatherDesc) {
        mWeatherDesc = weatherDesc;
    }

    public List<LangAr> getArabicDesc() {
        return mArabicDesc;
    }

    public void setArabicDesc(RealmList<LangAr> arabicDesc) {
        mArabicDesc = arabicDesc;
    }
}
