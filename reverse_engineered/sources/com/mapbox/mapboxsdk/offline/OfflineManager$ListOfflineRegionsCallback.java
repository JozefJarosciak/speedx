package com.mapbox.mapboxsdk.offline;

public interface OfflineManager$ListOfflineRegionsCallback {
    void onError(String str);

    void onList(OfflineRegion[] offlineRegionArr);
}
