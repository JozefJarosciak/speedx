package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14921;

class OfflineRegion$1$2 implements Runnable {
    final /* synthetic */ C14921 this$1;
    final /* synthetic */ OfflineRegionError val$error;

    OfflineRegion$1$2(C14921 c14921, OfflineRegionError offlineRegionError) {
        this.this$1 = c14921;
        this.val$error = offlineRegionError;
    }

    public void run() {
        this.this$1.val$observer.onError(this.val$error);
    }
}
