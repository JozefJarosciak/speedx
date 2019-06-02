package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14932;

class OfflineRegion$2$1 implements Runnable {
    final /* synthetic */ C14932 this$1;
    final /* synthetic */ OfflineRegionStatus val$status;

    OfflineRegion$2$1(C14932 c14932, OfflineRegionStatus offlineRegionStatus) {
        this.this$1 = c14932;
        this.val$status = offlineRegionStatus;
    }

    public void run() {
        this.this$1.val$callback.onStatus(this.val$status);
    }
}
