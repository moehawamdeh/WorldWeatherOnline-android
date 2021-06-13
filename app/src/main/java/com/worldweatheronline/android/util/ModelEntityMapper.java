package com.worldweatheronline.android.util;

import com.worldweatheronline.android.data.model.api.AutoCompleteApiResponse;
import com.worldweatheronline.android.data.model.entities.City;

public class ModelEntityMapper {
    public static City mapResultToCity(AutoCompleteApiResponse.Result result){
        City city = new City();
        city.setAreaName(result.areaName.get(0).getValue());
        city.setCountry(result.country.get(0).getValue());
        city.setRegion(result.region.get(0).getValue());
        city.setLocation(result.latitude,result.longitude);
        city.setLocationID();
        return city;
    }
}
