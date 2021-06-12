package com.worldweatheronline.android.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {

    @SerializedName("date")
    @Expose
    private String mDate;

    @SerializedName("hourly")
    @Expose
    private List<WeatherCondition> mHourlyList;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public List<WeatherCondition> getHourlyList() {
        return mHourlyList;
    }

    public void setHourlyList(List<WeatherCondition> hourlyList) {
        mHourlyList = hourlyList;
    }


    //    public List<Astronomy> astronomy;
//    public String maxtempC;
//    public String maxtempF;
//    public String mintempC;
//    public String mintempF;
//    public String avgtempC;
//    public String avgtempF;
//    public String totalSnow_cm;
//    public String sunHour;
//    public String uvIndex;
}
