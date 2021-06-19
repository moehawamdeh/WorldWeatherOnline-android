package com.worldweatheronline.android.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Request extends RealmObject {
    @SerializedName("type")
    @Expose
    private String mType;
    @SerializedName("query")
    @Expose
    private String mQuery;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getQuery() {
        return mQuery;
    }

    public void setQuery(String query) {
        mQuery = query;
    }
}
