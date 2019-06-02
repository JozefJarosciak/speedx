package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.model.LatLng;

public class PoiCitySearchOption {
    /* renamed from: a */
    LatLng f3378a = null;
    public String mCity = null;
    public boolean mIsReturnAddr = true;
    public String mKeyword = null;
    public int mPageCapacity = 10;
    public int mPageNum = 0;

    public PoiCitySearchOption city(String str) {
        this.mCity = str;
        return this;
    }

    public PoiCitySearchOption isReturnAddr(boolean z) {
        this.mIsReturnAddr = z;
        return this;
    }

    public PoiCitySearchOption keyword(String str) {
        this.mKeyword = str;
        return this;
    }

    public PoiCitySearchOption pageCapacity(int i) {
        this.mPageCapacity = i;
        return this;
    }

    public PoiCitySearchOption pageNum(int i) {
        this.mPageNum = i;
        return this;
    }
}
