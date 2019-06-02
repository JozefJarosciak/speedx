package com.baidu.mapapi.search.share;

public interface OnGetShareUrlResultListener {
    void onGetLocationShareUrlResult(ShareUrlResult shareUrlResult);

    void onGetPoiDetailShareUrlResult(ShareUrlResult shareUrlResult);

    void onGetRouteShareUrlResult(ShareUrlResult shareUrlResult);
}
