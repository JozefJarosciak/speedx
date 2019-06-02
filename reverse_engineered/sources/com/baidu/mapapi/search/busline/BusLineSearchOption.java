package com.baidu.mapapi.search.busline;

public class BusLineSearchOption {
    public String mCity = null;
    public String mUid = null;

    public BusLineSearchOption city(String str) {
        this.mCity = str;
        return this;
    }

    public BusLineSearchOption uid(String str) {
        this.mUid = str;
        return this;
    }
}
