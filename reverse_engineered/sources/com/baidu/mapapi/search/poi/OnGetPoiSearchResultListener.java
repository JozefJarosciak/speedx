package com.baidu.mapapi.search.poi;

public interface OnGetPoiSearchResultListener {
    void onGetPoiDetailResult(PoiDetailResult poiDetailResult);

    void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult);

    void onGetPoiResult(PoiResult poiResult);
}
