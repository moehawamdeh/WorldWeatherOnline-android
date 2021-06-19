package com.worldweatheronline.android.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;

public class Weather extends RealmObject {

    @SerializedName("date")
    @Expose
    private String mDate;

    @SerializedName("hourly")
    @Expose
    private RealmList<WeatherCondition> mHourlyList;

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public RealmList<WeatherCondition> getHourlyList() {
        return mHourlyList;
    }

    public void setHourlyList(RealmList<WeatherCondition> hourlyList) {
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
