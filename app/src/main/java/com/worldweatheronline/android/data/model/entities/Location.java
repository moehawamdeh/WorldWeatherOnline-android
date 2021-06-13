package com.worldweatheronline.android.data.model.entities;

import io.realm.RealmObject;


public class Location extends RealmObject {
    private String mLatitude;
    private String mLongitude;

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }
}
