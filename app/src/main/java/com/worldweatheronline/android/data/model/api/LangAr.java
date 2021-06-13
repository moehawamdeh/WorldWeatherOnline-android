package com.worldweatheronline.android.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class LangAr extends RealmObject {
    @SerializedName("value")
    @Expose
    private String mValue;

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }
}
