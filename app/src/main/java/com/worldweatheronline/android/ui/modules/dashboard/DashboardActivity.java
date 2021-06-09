package com.worldweatheronline.android.ui.modules.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.worldweatheronline.android.BuildConfig;
import com.worldweatheronline.android.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ((TextView)findViewById(R.id.testText)).setText(BuildConfig.WWO_API_KEY);

    }
}