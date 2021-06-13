package com.worldweatheronline.android.ui.modules.dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.TextView;

import com.worldweatheronline.android.BuildConfig;
import com.worldweatheronline.android.R;
import com.worldweatheronline.android.data.model.entities.City;
import com.worldweatheronline.android.data.model.entities.CityWeather;
import com.worldweatheronline.android.ui.DashboardVM;

import java.util.List;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardActivity extends AppCompatActivity {
    private DashboardVM mDashboardVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mDashboardVM= new ViewModelProvider(this).get(DashboardVM.class);

        ((TextView)findViewById(R.id.testText)).setText(BuildConfig.WWO_API_KEY);



        mDashboardVM.getCities().observe(this, cities -> {
            List<String> s=cities.stream().map(City::getLocationID).collect(Collectors.toList());
            StringBuilder builder=new StringBuilder();
                    for(String a :s )
                        builder.append(a).append(' ');
            ((TextView)findViewById(R.id.testText)).setText(builder.toString());
        });
    }
}