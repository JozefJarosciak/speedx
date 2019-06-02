package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14943;

class OfflineRegion$3$1 implements Runnable {
    final /* synthetic */ C14943 this$1;

    OfflineRegion$3$1(C14943 c14943) {
        this.this$1 = c14943;
    }

    public void run() {
        this.this$1.val$callback.onDelete();
        this.this$1.this$0.finalize();
    }
}
