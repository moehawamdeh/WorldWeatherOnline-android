package com.worldweatheronline.android.data.repository;

import com.worldweatheronline.android.data.model.api.AutoCompleteApiResponse;
import com.worldweatheronline.android.data.model.entities.City;
import com.worldweatheronline.android.data.remote.services.AutoCompleteService;
import com.worldweatheronline.android.data.remote.services.WeatherService;
import com.worldweatheronline.android.util.ModelEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.worldweatheronline.android.Constants.defaultCities;


public class WWORepository {
    @Inject
    WeatherService mWeatherService;
    AutoCompleteService mAutoCompleteService;
    Realm mRealm;
    @Inject
    WWORepository(Realm realm, AutoCompleteService autoCompleteService){
        //initialize cities list
        mRealm=realm;
        mAutoCompleteService=autoCompleteService;
        final long count = mRealm.where(City.class)
                .count();
        //Check if we have at least 3 cities.
        if(count >= defaultCities.length)
            return;
        //No cities, the defaults
        Observable.fromArray(defaultCities)
                .flatMap(city -> mAutoCompleteService.getCity(city))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> {
                    List<City> cities= results.stream().map(response -> {
                        AutoCompleteApiResponse.Result result=response.getResult().mResult.get(0);
                        return ModelEntityMapper.mapResultToCity(result);
                    }).collect(Collectors.toList());
                    //if one fails, all fai
                    realm.beginTransaction();
                    realm.insert(cities);
                    realm.commitTransaction();

                    realm.close();

                });


    }

    public RealmResults<City> getCities() {
        Realm realm= Realm.getDefaultInstance();
        return realm
                .where(City.class)
                .findAllAsync();
    }
}
