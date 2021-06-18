package com.worldweatheronline.android.ui.modules.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.worldweatheronline.android.R;
import com.worldweatheronline.android.data.model.api.Weather;
import com.worldweatheronline.android.data.model.entities.City;
import com.worldweatheronline.android.data.model.entities.CityWeather;
import com.worldweatheronline.android.ui.DashboardVM;
import com.worldweatheronline.android.ui.adapters.DayForecastAdapter;
import com.worldweatheronline.android.ui.widgets.DynamicAutoCompleteTextView;
import com.worldweatheronline.android.util.LocalParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardActivity extends AppCompatActivity {
    //TODO use databinding
    private DashboardVM mDashboardVM;
    ArrayAdapter<String> mAdapter;
    ArrayList<String> mLabels=new ArrayList<>();
    TextInputLayout mTextInputLayout;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mDashboardVM= new ViewModelProvider(this).get(DashboardVM.class);
        mAdapter= new ArrayAdapter<>(DashboardActivity.this, R.layout.search_list_item, mLabels);
        mTextInputLayout=findViewById(R.id.auto_complete_menu);
        mTextInputLayout.setEndIconOnClickListener(v -> Objects.requireNonNull(mTextInputLayout.getEditText()).setText(""));
        mRecyclerView=findViewById(R.id.recycler_list_week);
        mLayoutManager=new LinearLayoutManager(DashboardActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DynamicAutoCompleteTextView autocompleteView = findViewById(R.id.auto_complete_cities);

        autocompleteView.setOnItemClickListener((parent, view, position, id) -> {
            mDashboardVM.selectCity(position);
            autocompleteView.setText(mAdapter.getItem(position),false);
            InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
            mDashboardVM.selectCity(position);



        });
        //After 300ms of text changes
        RxTextView.textChanges(autocompleteView)
                .filter(charSequence -> charSequence.length() > 2)// if 3 or more, search for new results.
                .map(CharSequence::toString)
                .debounce(300, TimeUnit.MILLISECONDS)
                .subscribe(searchText -> {
                    //show loading
                    if(mAdapter.getPosition(searchText)<0)
                    mDashboardVM.searchCities(searchText);
                });

        mDashboardVM.getCities().observe(this, this::onCitiesListChanged);
        mDashboardVM.getCityWeather().observe(this,this::onWeatherChanged);
        mDashboardVM.getTwoWeeksList().observe(this,this::onTwoWeekWeatherChanged);
        mDashboardVM.selectCity(0);
        ConstraintLayout constraintLayout = findViewById(R.id.layout_dashboard);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

    }

    private void onTwoWeekWeatherChanged(List<Weather> weathers) {

        if(weathers!=null && weathers.size()>0)
        {
            DayForecastAdapter adapter = new DayForecastAdapter(weathers);
            mRecyclerView.setAdapter(adapter);
        }
    }


    private void onWeatherChanged(CityWeather cityWeather) {
        if(cityWeather.getCurrentCondition()!=null) {
            ((TextView) findViewById(R.id.text_temprature)).setText(cityWeather.getCurrentCondition().getTempC());
            ((TextView) findViewById(R.id.text_city_name)).setText(cityWeather.getCity().getAreaName());
            ImageView imageView = (ImageView) findViewById(R.id.image_weather_icon);
            //TODO extract utility
            Gson gson = new Gson();
            Map map = gson.fromJson(LocalParser.loadJSONFromAsset(this), Map.class);
            String a= (String) map.get(cityWeather.getCurrentCondition().getWeatherCode());
            int drawableResourceId = getResources().getIdentifier(a, "drawable", DashboardActivity.this.getBaseContext().getPackageName());
            String b= String.valueOf(drawableResourceId);
            ((MotionLayout)findViewById(R.id.layout_dashboard)).transitionToStart();
            Glide.with(this)
                    .load(drawableResourceId)
                    .dontTransform()
                    .into(imageView);
        }

    }

    private void onCitiesListChanged(List<City> cities) {

            List<String> s = cities.stream().filter(Objects::nonNull).map(city -> city.getAreaName() + ' ' + city.getRegion() + ' ' + city.getCountry()).collect(Collectors.toList());
        if(cities.size()>0) {
            DynamicAutoCompleteTextView autocompleteView = findViewById(R.id.auto_complete_cities);
            mAdapter = new ArrayAdapter<String>(DashboardActivity.this, R.layout.search_list_item, s);
            autocompleteView.setAdapter(mAdapter);
            autocompleteView.onNewDataLoaded();
        }

     }
}