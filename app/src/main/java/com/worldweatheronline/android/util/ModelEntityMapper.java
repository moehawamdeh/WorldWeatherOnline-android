package com.worldweatheronline.android.util;

import com.worldweatheronline.android.data.model.api.Weather;
import com.worldweatheronline.android.data.model.api.WeatherData;
import com.worldweatheronline.android.data.model.api.AutoCompleteApiResponse;
import com.worldweatheronline.android.data.model.entities.City;
import com.worldweatheronline.android.data.model.entities.CityTwoWeeksForecast;
import com.worldweatheronline.android.data.model.entities.CityWeather;

import java.util.List;

import io.realm.RealmList;

public class ModelEntityMapper {
    public static City mapResultToCity(AutoCompleteApiResponse response){
        if(response==null || response.getResult()==null)
            return null;
        AutoCompleteApiResponse.ResultData resultData=response.getResult();
        List<AutoCompleteApiResponse.Result> resultInner=resultData.getResult();
        AutoCompleteApiResponse.Result result = resultInner.get(0);
                if(result==null)
                    return null;
        City city = new City();
        city.setAreaName(result.getAreaName().get(0).getValue());
        city.setCountry(result.getCountry().get(0).getValue());
        city.setRegion(result.getRegion().get(0).getValue());
        city.setLocation(result.getLatitude(),result.getLongitude());
        city.setLocationID();
        return city;
    }
    public static City mapResultToCity(AutoCompleteApiResponse.Result result){


        if(result==null)
            return null;
        City city = new City();
        city.setAreaName(result.getAreaName().get(0).getValue());
        city.setCountry(result.getCountry().get(0).getValue());
        city.setRegion(result.getRegion().get(0).getValue());
        city.setLocation(result.getLatitude(),result.getLongitude());
        city.setLocationID();
        return city;
    }
    public static CityWeather mapWeatherDataToCityWeather(WeatherData data,City city){
        CityWeather weather = new CityWeather();
        weather.setCurrentCondition(data.getCurrentConditions().get(0));
        weather.setHourly(data.getWeather().get(0).getHourlyList());
        weather.setLocationID(city.getLocationID());
        weather.setCity(city);
        return weather;
    }
    public static CityTwoWeeksForecast mapWeatherDataToListofWeather(WeatherData data,String locationId){
        CityTwoWeeksForecast forecast=new CityTwoWeeksForecast();
        forecast.setLocationID(locationId);
        forecast.setTwoWeeksWeather(data.getWeather());
        return forecast;
    }

}
