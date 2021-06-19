package com.worldweatheronline.android.ui.modules.dashboard;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.worldweatheronline.android.R;
import com.worldweatheronline.android.data.model.api.Weather;
import com.worldweatheronline.android.data.model.entities.City;
import com.worldweatheronline.android.data.model.entities.CityWeather;
import com.worldweatheronline.android.helper.IconsMappingHelper;
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

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardActivity extends AppCompatActivity {
    //TODO use databinding instead of members
    private DashboardVM mDashboardVM;
    ArrayAdapter<String> mAdapter;
    ArrayList<String> mLabels=new ArrayList<>();
    TextInputLayout mTextInputLayout;
    TextView mTextTemp;
    TextView mTextDescription;
    TextView mTextCity;
    ImageView mWeatherIcon;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    LocationManager mLocationManager;
    @Inject
            @Nullable
    IconsMappingHelper mIconsMapper;

    private ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    getLocation();
                } else {
                    Toast.makeText(DashboardActivity.this, getString(R.string.whatever),Toast.LENGTH_SHORT).show();
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mTextTemp= findViewById(R.id.text_temprature);
        mTextCity= findViewById(R.id.text_city_name);
        mTextDescription= findViewById(R.id.text_weather_descs);
        mWeatherIcon = findViewById(R.id.image_weather_icon);
        mDashboardVM= new ViewModelProvider(this).get(DashboardVM.class);
        mAdapter= new ArrayAdapter<>(DashboardActivity.this, R.layout.search_list_item, mLabels);
        mTextInputLayout=findViewById(R.id.auto_complete_menu);
        mTextInputLayout.setEndIconOnClickListener(v -> Objects.requireNonNull(mTextInputLayout.getEditText()).setText(""));
        mRecyclerView=findViewById(R.id.recycler_list_week);
        mLayoutManager=new LinearLayoutManager(DashboardActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DynamicAutoCompleteTextView autocompleteView = findViewById(R.id.auto_complete_cities);
        ImageButton imageButton= findViewById(R.id.button_location
        );
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        DashboardActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
                   getLocation();
                } else {
                    requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        }});
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
    void getLocation() {
        try {
            mLocationManager = (LocationManager)getApplicationContext().getSystemService(LOCATION_SERVICE);
            String provider = mLocationManager.getBestProvider(new Criteria(),true);
            List<String> providers = mLocationManager.getProviders(true);
            Location location =mLocationManager.getLastKnownLocation(provider);

            if(location==null) {
                //TODO stop loading and show error msg
                return;
            }
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            mDashboardVM.selectCity(longitude,latitude);


        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    private void onTwoWeekWeatherChanged(List<Weather> weathers) {

        if(weathers!=null && weathers.size()>0)
        {
            DayForecastAdapter adapter = new DayForecastAdapter(weathers,mIconsMapper);
            mRecyclerView.setAdapter(adapter);
        }
    }


    private void onWeatherChanged(CityWeather cityWeather) {
        if(cityWeather.getCurrentCondition()!=null) {
            mTextTemp.setText(cityWeather.getCurrentCondition().getTempC());
            mTextCity.setText(cityWeather.getCity().getAreaName());
            mTextDescription.setText(cityWeather.getCurrentCondition().getWeatherDesc().get(0).value);

            //TODO extract utility
            String name=mIconsMapper.getIconIdFor(cityWeather.getCurrentCondition().getWeatherCode());
            int drawableResourceId = getResources().getIdentifier(name, "drawable", DashboardActivity.this.getBaseContext().getPackageName());
            String b= String.valueOf(drawableResourceId);
            ((MotionLayout)findViewById(R.id.layout_dashboard)).transitionToStart();
            Glide.with(this)
                    .load(drawableResourceId)
                    .dontTransform()
                    .into(mWeatherIcon);
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