package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.LatLng;

public class AreaUtil {
    public static double calculateArea(LatLng latLng, LatLng latLng2) {
        if (latLng == null || latLng2 == null) {
            return 0.0d;
        }
        LatLng latLng3 = new LatLng(latLng.latitude, latLng2.longitude);
        double distance = DistanceUtil.getDistance(latLng3, latLng2);
        double distance2 = DistanceUtil.getDistance(latLng, latLng3);
        return (distance == 0.0d || distance2 == 0.0d) ? 0.0d : distance * distance2;
    }
}
