package com.worldweatheronline.android.data.repository;

import android.util.Log;

import com.worldweatheronline.android.data.model.api.AutoCompleteApiResponse;
import com.worldweatheronline.android.data.model.entities.City;
import com.worldweatheronline.android.data.model.entities.CityTwoWeeksForecast;
import com.worldweatheronline.android.data.model.entities.CityWeather;
import com.worldweatheronline.android.data.remote.services.AutoCompleteService;
import com.worldweatheronline.android.data.remote.services.WeatherService;
import com.worldweatheronline.android.util.ModelEntityMapper;

import java.util.List;
import java.util.Objects;
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
    WWORepository(Realm realm, AutoCompleteService autoCompleteService) {
        //initialize cities list
        mRealm = realm;
        mAutoCompleteService = autoCompleteService;
        final long count = mRealm.where(City.class)
                .count();
        //Check if we have at least 3 cities.
        if (count >= defaultCities.length)
            return;
//        No cities, fetch and store default cities
        Observable.fromArray(defaultCities)
                .flatMap(city -> mAutoCompleteService.getCity(city))
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    Log.i("REPO_INIT", "Error", throwable);
                })
                .subscribe(results -> {
                    List<City> cities = results.stream().map(ModelEntityMapper::mapResultToCity).collect(Collectors.toList());
                    //if one fails, all fai
                    realm.beginTransaction();
                    realm.insert(cities);
                    realm.commitTransaction();

                    //create forecast for them
                    List<CityTwoWeeksForecast>forecasts=
                            cities
                                    .stream()
                                    .map(city->
                                            new CityTwoWeeksForecast(city.getLocationID()))
                                    .collect(Collectors.toList());
                    realm.beginTransaction();
                    realm.insert(forecasts);
                    realm.commitTransaction();


                });




    }

    public RealmResults<City> getCities() {
        Realm realm = Realm.getDefaultInstance();
        return realm
                .where(City.class)
                .findAllAsync();
    }

    public void getTop5AutoCompletes(String query) {

        mAutoCompleteService.getTop5Suggestions(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {

                    List<City> newCities = response
                            .getResult()
                            .getResult()
                            .stream()
                            .map(ModelEntityMapper::mapResultToCity)
                            .collect(Collectors.toList());
                    mRealm.beginTransaction();
                    mRealm.where(City.class).findAll().deleteAllFromRealm();
                    mRealm.insert(newCities);
                    //create empty city weather object too
                    List<CityWeather> list = newCities.stream().map(city -> {
                        CityWeather weather = new CityWeather();
                        weather.setLocationID(city.getLocationID());
                        return weather;
                    }).collect(Collectors.toList());

                    mRealm.insertOrUpdate(list);
                    mRealm.commitTransaction();

                }, error -> {
                    Log.i("REPO_INIT", "Error", error);
                });


    }

    public RealmResults<CityWeather> getWeatherSummary(City city) {
        mWeatherService.getCurrentSummary(city.getLocationID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                            CityWeather weather = ModelEntityMapper.mapWeatherDataToCityWeather(res.getData(), city);
                            mRealm.beginTransaction();
                            mRealm.insertOrUpdate(weather);
                            mRealm.commitTransaction();

                        }
                        , error -> Log.e("WEATHER_FETCH", "Error fetching weather for " + city.getLocationID(), error));
        return mRealm.where(CityWeather.class).equalTo("mLocationID", city.getLocationID()).limit(1).findAll();

    }

    public void getWeatherSummary(String cityID) {
        getWeatherSummary(Objects.requireNonNull(mRealm.where(City.class).equalTo("mLocationID", cityID).findFirst()));

    }

    public CityTwoWeeksForecast getTwoWeeksForecast(City city) {
        mWeatherService.getTwoWeeksForecast(city.getLocationID())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(res -> {
                            CityTwoWeeksForecast forecast = ModelEntityMapper.mapWeatherDataToListofWeather(res.getData(),city.getLocationID());
                            mRealm.beginTransaction();
                            mRealm.insertOrUpdate(forecast);
                            mRealm.commitTransaction();
                        }
                        , error -> Log.e("WEATHER_FETCH", "Error fetching weather for " + city.getLocationID(), error));

        return mRealm.where(CityTwoWeeksForecast.class)
                .equalTo("mLocationID", city.getLocationID())
                .findFirst();
    }



}
