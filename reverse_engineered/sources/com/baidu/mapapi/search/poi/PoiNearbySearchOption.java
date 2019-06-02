package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.model.LatLng;

public class PoiNearbySearchOption {
    public String mKeyword = null;
    public LatLng mLocation = null;
    public int mPageCapacity = 10;
    public int mPageNum = 0;
    public int mRadius = -1;
    public PoiSortType sortType = PoiSortType.comprehensive;

    public PoiNearbySearchOption keyword(String str) {
        this.mKeyword = str;
        return this;
    }

    public PoiNearbySearchOption location(LatLng latLng) {
        this.mLocation = latLng;
        return this;
    }

    public PoiNearbySearchOption pageCapacity(int i) {
        this.mPageCapacity = i;
        return this;
    }

    public PoiNearbySearchOption pageNum(int i) {
        this.mPageNum = i;
        return this;
    }

    public PoiNearbySearchOption radius(int i) {
        this.mRadius = i;
        return this;
    }

    public PoiNearbySearchOption sortType(PoiSortType poiSortType) {
        if (poiSortType != null) {
            this.sortType = poiSortType;
        }
        return this;
    }
}
