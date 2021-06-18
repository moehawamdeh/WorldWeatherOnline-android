package com.worldweatheronline.android.ui.adapters.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.worldweatheronline.android.R;


public class DayForecastHolder extends RecyclerView.ViewHolder {
    private final TextView textView;
    private String mTemp;
    public DayForecastHolder(View view) {
        super(view);
        // Define click listener for the ViewHolder's View

        textView = (TextView) view.findViewById(R.id.text_day_temp);

    }

    public TextView getTextView() {
        return textView;
    }

    public String getTemp() {
        return mTemp;
    }

    public void setTemp(String temp) {
        mTemp = temp;
        textView.setText(temp);
    }
}