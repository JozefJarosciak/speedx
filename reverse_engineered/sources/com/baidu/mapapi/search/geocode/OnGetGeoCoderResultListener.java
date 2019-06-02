package com.baidu.mapapi.search.geocode;

public interface OnGetGeoCoderResultListener {
    void onGetGeoCodeResult(GeoCodeResult geoCodeResult);

    void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult);
}
