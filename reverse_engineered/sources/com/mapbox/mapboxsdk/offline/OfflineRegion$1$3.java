package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14921;

class OfflineRegion$1$3 implements Runnable {
    final /* synthetic */ C14921 this$1;
    final /* synthetic */ long val$limit;

    OfflineRegion$1$3(C14921 c14921, long j) {
        this.this$1 = c14921;
        this.val$limit = j;
    }

    public void run() {
        this.this$1.val$observer.mapboxTileCountLimitExceeded(this.val$limit);
    }
}
