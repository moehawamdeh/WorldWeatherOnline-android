package com.worldweatheronline.android.ui.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.worldweatheronline.android.R;
import com.worldweatheronline.android.data.model.api.Weather;
import com.worldweatheronline.android.data.model.entities.CityTwoWeeksForecast;
import com.worldweatheronline.android.helper.IconsMappingHelper;
import com.worldweatheronline.android.ui.adapters.viewholder.DayForecastHolder;

import java.util.List;

import javax.inject.Inject;


public class DayForecastAdapter extends RecyclerView.Adapter<DayForecastHolder> {
    IconsMappingHelper mIconsMapper;
    private List<Weather> localDataSet;

    public DayForecastAdapter(List<Weather> dataSet,IconsMappingHelper helper) {
        mIconsMapper=helper;
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DayForecastHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.day_forecast_list_item, viewGroup, false);

        return new DayForecastHolder(view, mIconsMapper);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(DayForecastHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.setWeather(localDataSet.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
