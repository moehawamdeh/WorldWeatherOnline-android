package com.worldweatheronline.android.ui.adapters.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.worldweatheronline.android.Constants;
import com.worldweatheronline.android.R;
import com.worldweatheronline.android.data.model.api.Weather;
import com.worldweatheronline.android.data.model.api.WeatherCondition;
import com.worldweatheronline.android.helper.IconsMappingHelper;
import com.worldweatheronline.android.util.FormatsUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class DayForecastHolder extends RecyclerView.ViewHolder {
    IconsMappingHelper mIconsMapper;
    private final TextView tempText;
    private final TextView dateText;
    private final TextView descText;

    public DayForecastHolder(View view, IconsMappingHelper mapper) {
        super(view);
        // Define click listener for the ViewHolder's View
        mIconsMapper=mapper;
        tempText = view.findViewById(R.id.text_day_temp);
        dateText = view.findViewById(R.id.text_date);
        descText = view.findViewById(R.id.text_day_desc);

    }

    private void setIcon(String code){
        if(mIconsMapper !=null) {
            ImageView img = itemView.findViewById(R.id.image_condition_icon);
            String name = mIconsMapper.getIconIdFor(code);
            int drawableResourceId = itemView.getResources().getIdentifier(name, "drawable", itemView.getContext().getPackageName());
            Glide.with(itemView)
                    .load(drawableResourceId)
                    .dontTransform()
                    .into(img);
        }
    }
    private void setDate(String str){
        //TODO extract util
        try {
            SimpleDateFormat inFormat = new SimpleDateFormat(Constants.API_WEATHER_DATE_FORMAT);
            Date date = null;
            date = inFormat.parse(str);
            SimpleDateFormat outFormat = new SimpleDateFormat(Constants.COMPACT_DATE_FORMAT);
            String day = outFormat.format(date);
            dateText.setText(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public void setWeather(Weather weather) {
        WeatherCondition condition=weather.getHourlyList().get(0);
        setIcon(condition.getWeatherCode());
        setDate(weather.getDate());
        descText.setText(condition.getWeatherDesc().get(0).value);
        tempText.setText(FormatsUtils.tempAsCelecius(condition.getTempC()));
    }

}