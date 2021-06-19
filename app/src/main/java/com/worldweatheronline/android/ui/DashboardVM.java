package com.worldweatheronline.android.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.worldweatheronline.android.data.model.api.Weather;
import com.worldweatheronline.android.data.model.entities.City;
import com.worldweatheronline.android.data.model.entities.CityTwoWeeksForecast;
import com.worldweatheronline.android.data.model.entities.CityWeather;
import com.worldweatheronline.android.data.repository.WWORepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DashboardVM extends ViewModel {
    private final WWORepository mRepository;
    private final MutableLiveData<CityWeather> mCityWeather=new MutableLiveData<>();
    private final MutableLiveData<List<City>> mCitiesList=new MutableLiveData<>();
    private final MutableLiveData<List<Weather>> mTwoWeeksList=new MutableLiveData<>();
    private final MutableLiveData<Boolean>mLoading = new MutableLiveData<>();
    @Inject
    DashboardVM(WWORepository repository){
        this.mRepository=repository;
    }

    public LiveData<CityWeather> getCityWeather() {
        return mCityWeather;
    }
    public LiveData<List<City>> getCities() {
        mRepository.getCities().addChangeListener(cities -> {
            mCitiesList.setValue(cities);
                    if(mCityWeather.getValue()==null)
                        selectCity(0);
        });
        //another approach would be to return Realm Observable itself
        return mCitiesList;
    }

    public void searchCities(String query) {
        mRepository.getTop5AutoCompletes(query);

    }
     LiveData<Boolean> getLoading(){
        return mLoading;
    }


    public void selectCity(int position) {

        if(mCitiesList.getValue()==null || mCitiesList.getValue().isEmpty())
            return;
        City city=mCitiesList.getValue().get(position);
        mRepository.
                getWeatherSummary(city)
                .addChangeListener(results -> {
                    if(results.size()>0) {
                        mCityWeather.postValue(results.get(0));
                        CityTwoWeeksForecast forecast=mRepository
                                .getTwoWeeksForecast(
                                        city
                                );
                        if(forecast!=null)
                                forecast.addChangeListener((data) -> {
                                    CityTwoWeeksForecast cityWeather=(CityTwoWeeksForecast)data;
                                    mTwoWeeksList.postValue(cityWeather.getTwoWeeksWeather());
                                });
                    }});

    }

    public MutableLiveData<List<Weather>> getTwoWeeksList() {
        return mTwoWeeksList;
    }

        public void selectCity(double longitude, double latitude) {
            String queryLatLon=latitude+", "+longitude;
        searchCities(queryLatLon);

    }
}