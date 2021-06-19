package com.worldweatheronline.android.data.model.entities;

import com.worldweatheronline.android.data.model.api.Weather;
import com.worldweatheronline.android.data.model.api.WeatherCondition;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmField;

public class CityWeather extends RealmObject {
    @PrimaryKey
    @RealmField(name = "location_id")
    private String mLocationID;
    private WeatherCondition mCurrentCondition;
    private RealmList<WeatherCondition> mHourly;
    private RealmList<Weather>mTwoWeeksWeather;
    private City mCity;

    //    private RealmList<Weather> mTwoWeeksWeatherList;
    public String getLocationID(){
        return mLocationID;
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



    public void setLocationID(String locationID) {
        mLocationID = locationID;
    }

    public City getCity() {
        return mCity;
    }

    public void setCity(City city) {
        mCity = city;
    }

    public RealmList<Weather> getTwoWeeksWeather() {
        return mTwoWeeksWeather;
    }

    public void setTwoWeeksWeather(RealmList<Weather> twoWeeksWeather) {
        mTwoWeeksWeather = twoWeeksWeather;
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
