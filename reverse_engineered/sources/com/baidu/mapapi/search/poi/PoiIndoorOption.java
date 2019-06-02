package com.baidu.mapapi.search.poi;

public class PoiIndoorOption {
    public String bid;
    public int currentPage = 0;
    public String floor;
    public int pageSize = 10;
    public String wd;

    public PoiIndoorOption poiCurrentPage(int i) {
        this.currentPage = i;
        return this;
    }

    public PoiIndoorOption poiFloor(String str) {
        this.floor = str;
        return this;
    }

    public PoiIndoorOption poiIndoorBid(String str) {
        this.bid = str;
        return this;
    }

    public PoiIndoorOption poiIndoorWd(String str) {
        this.wd = str;
        return this;
    }

    public PoiIndoorOption poiPageSize(int i) {
        this.pageSize = i;
        return this;
    }
}
