package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14954;

class OfflineRegion$4$2 implements Runnable {
    final /* synthetic */ C14954 this$1;
    final /* synthetic */ String val$error;

    OfflineRegion$4$2(C14954 c14954, String str) {
        this.this$1 = c14954;
        this.val$error = str;
    }

    public void run() {
        this.this$1.val$callback.onError(this.val$error);
    }
}
