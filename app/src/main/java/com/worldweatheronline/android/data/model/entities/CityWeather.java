package com.worldweatheronline.android.data.model.entities;

import com.worldweatheronline.android.data.model.api.WeatherCondition;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CityWeather extends RealmObject {
    @PrimaryKey
    private String mLocationID;
    private City mCity;
    private WeatherCondition mCurrentCondition;
    private RealmList<WeatherCondition> mHourly;
//    private RealmList<Weather> mTwoWeeksWeatherList;
    public String getLocationID(){
        return mCity.getLocationID();
    }

    public City getCity(){
        return mCity;
    }
    public WeatherCondition getCurrentCondition() {
        return mCurrentCondition;
    }

    public void setCurrentCondition(WeatherCondition currentCondition) {
        mCurrentCondition = currentCondition;
    }

    public RealmList<WeatherCondition> getHourly() {
        return mHourly;
    }

    public void setHourly(RealmList<WeatherCondition> hourly) {
        mHourly = hourly;
    }
//
//    public List<Weather> getTwoWeeksWeatherList() {
//        return mTwoWeeksWeatherList;
//    }
//
//    public void setTwoWeeksWeatherList(RealmList<Weather> twoWeeksWeatherList) {
//        mTwoWeeksWeatherList = twoWeeksWeatherList;
//    }
}
