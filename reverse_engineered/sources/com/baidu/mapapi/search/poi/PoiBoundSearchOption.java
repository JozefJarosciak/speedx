package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.model.LatLngBounds;

public class PoiBoundSearchOption {
    public LatLngBounds mBound = null;
    public String mKeyword = null;
    public int mPageCapacity = 10;
    public int mPageNum = 0;

    public PoiBoundSearchOption bound(LatLngBounds latLngBounds) {
        this.mBound = latLngBounds;
        return this;
    }

    public PoiBoundSearchOption keyword(String str) {
        this.mKeyword = str;
        return this;
    }

    public PoiBoundSearchOption pageCapacity(int i) {
        this.mPageCapacity = i;
        return this;
    }

    public PoiBoundSearchOption pageNum(int i) {
        this.mPageNum = i;
        return this;
    }
}
