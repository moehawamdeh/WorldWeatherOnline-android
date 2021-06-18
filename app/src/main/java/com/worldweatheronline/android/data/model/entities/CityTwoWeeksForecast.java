package com.worldweatheronline.android.data.model.entities;

import com.worldweatheronline.android.data.model.api.Weather;
import com.worldweatheronline.android.data.model.api.WeatherCondition;

import io.reactivex.rxjava3.annotations.NonNull;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmField;

public class CityTwoWeeksForecast extends RealmObject {
    @PrimaryKey
    @RealmField(name = "location_id")
    private String mLocationID;
    private RealmList<Weather> mTwoWeeksWeather;

    public CityTwoWeeksForecast(){}
    public CityTwoWeeksForecast(@NonNull String locationID){
        mLocationID=locationID;
    }
    public String getLocationID() {
        return mLocationID;
    }


    public void setLocationID(String locationID) {
        mLocationID = locationID;
    }


    public RealmList<Weather> getTwoWeeksWeather() {
        return mTwoWeeksWeather;
    }

    public void setTwoWeeksWeather(RealmList<Weather> twoWeeksWeather) {
        mTwoWeeksWeather = twoWeeksWeather;
    }
}

