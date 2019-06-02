package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.model.LatLng;

public class ReverseGeoCodeOption {
    public LatLng mLocation = null;

    public ReverseGeoCodeOption location(LatLng latLng) {
        this.mLocation = latLng;
        return this;
    }
}
