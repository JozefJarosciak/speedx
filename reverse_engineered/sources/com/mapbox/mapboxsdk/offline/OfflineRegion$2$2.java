package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14932;

class OfflineRegion$2$2 implements Runnable {
    final /* synthetic */ C14932 this$1;
    final /* synthetic */ String val$error;

    OfflineRegion$2$2(C14932 c14932, String str) {
        this.this$1 = c14932;
        this.val$error = str;
    }

    public void run() {
        this.this$1.val$callback.onError(this.val$error);
    }
}
