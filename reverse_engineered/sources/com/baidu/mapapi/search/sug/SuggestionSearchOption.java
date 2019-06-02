package com.baidu.mapapi.search.sug;

import com.baidu.mapapi.model.LatLng;

public class SuggestionSearchOption {
    public String mCity = null;
    public Boolean mCityLimit = Boolean.valueOf(false);
    public String mKeyword = null;
    public LatLng mLocation = null;

    public SuggestionSearchOption city(String str) {
        this.mCity = str;
        return this;
    }

    public SuggestionSearchOption citylimit(Boolean bool) {
        this.mCityLimit = bool;
        return this;
    }

    public SuggestionSearchOption keyword(String str) {
        this.mKeyword = str;
        return this;
    }

    public SuggestionSearchOption location(LatLng latLng) {
        this.mLocation = latLng;
        return this;
    }
}
