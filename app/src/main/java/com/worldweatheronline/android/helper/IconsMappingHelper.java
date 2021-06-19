package com.worldweatheronline.android.helper;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class IconsMappingHelper {
    private Map<String,String> mIconsMap;
    @Inject
    public IconsMappingHelper(Map<String,String> iconsMap){
        mIconsMap=iconsMap;
    }

    public String getIconIdFor(String wid){
        return mIconsMap.get(wid);
    }
}
