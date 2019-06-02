package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14943;

class OfflineRegion$3$2 implements Runnable {
    final /* synthetic */ C14943 this$1;
    final /* synthetic */ String val$error;

    OfflineRegion$3$2(C14943 c14943, String str) {
        this.this$1 = c14943;
        this.val$error = str;
    }

    public void run() {
        this.this$1.val$callback.onError(this.val$error);
    }
}
