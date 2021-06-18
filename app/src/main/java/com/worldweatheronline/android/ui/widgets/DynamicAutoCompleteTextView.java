package com.worldweatheronline.android.ui.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DynamicAutoCompleteTextView extends androidx.appcompat.widget.AppCompatAutoCompleteTextView {
    public void onNewDataLoaded(){
        performFiltering(getText(),0);
    }

    public DynamicAutoCompleteTextView(@NonNull Context context) {
        super(context);
    }

    public DynamicAutoCompleteTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicAutoCompleteTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
