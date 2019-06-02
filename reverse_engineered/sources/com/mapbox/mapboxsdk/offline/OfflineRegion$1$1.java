package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14921;

class OfflineRegion$1$1 implements Runnable {
    final /* synthetic */ C14921 this$1;
    final /* synthetic */ OfflineRegionStatus val$status;

    OfflineRegion$1$1(C14921 c14921, OfflineRegionStatus offlineRegionStatus) {
        this.this$1 = c14921;
        this.val$status = offlineRegionStatus;
    }

    public void run() {
        this.this$1.val$observer.onStatusChanged(this.val$status);
    }
}
