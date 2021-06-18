package com.worldweatheronline.android.data.model.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmField;

public class City extends RealmObject {
    @PrimaryKey
    @RealmField(name = "location_id")
    private String mLocationID;
    private String mCountry;
    private String mRegion;
    private String mAreaName;
    private Location mLocation;
    public String getLocationID(){
        return mLocationID;
    }

    public void setLocationID() {
        StringBuilder builder=new StringBuilder();
        //Id = ($latitude, $longitude)
        mLocationID=
                builder
                .append(mLocation.getLatitude())
                .append(", ")
                .append(mLocation.getLongitude())
                .toString();
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getRegion() {
        return mRegion;
    }

    public void setRegion(String region) {
        mRegion = region;
    }

    public String getAreaName() {
        return mAreaName;
    }

    public void setAreaName(String areaName) {
        mAreaName = areaName;
    }

    public void setLocation(Location location) {
        mLocation = location;
    }
    public void setLocation( String latitude, String longitude) {
        mLocation = new Location();
        mLocation.setLatitude(latitude);
        mLocation.setLongitude(longitude);
    }
}
