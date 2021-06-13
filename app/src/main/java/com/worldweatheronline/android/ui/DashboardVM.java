package com.worldweatheronline.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.worldweatheronline.android.data.model.entities.City;
import com.worldweatheronline.android.data.model.entities.CityWeather;
import com.worldweatheronline.android.data.repository.WWORepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

@HiltViewModel
public class DashboardVM extends ViewModel {
    private WWORepository mRepository;
    private MutableLiveData<CityWeather> mCityWeather=new MutableLiveData<>();
    private MutableLiveData<List<City>> mCitiesList=new MutableLiveData<>();
    @Inject
    DashboardVM(WWORepository repository){
        this.mRepository=repository;
    }

    public LiveData<CityWeather> getCityWeather() {
        return mCityWeather;
    }
    public LiveData<List<City>> getCities() {
        mRepository.getCities().addChangeListener(cities -> mCitiesList.postValue(cities));
        //another approach would be to return Realm Observable itself
        return mCitiesList;
    }
}