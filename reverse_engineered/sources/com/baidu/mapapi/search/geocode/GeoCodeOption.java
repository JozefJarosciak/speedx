package com.baidu.mapapi.search.geocode;

public class GeoCodeOption {
    public String mAddress = null;
    public String mCity = null;

    public GeoCodeOption address(String str) {
        this.mAddress = str;
        return this;
    }

    public GeoCodeOption city(String str) {
        this.mCity = str;
        return this;
    }
}
