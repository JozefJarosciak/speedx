package com.mapbox.mapboxsdk.offline;

import com.mapbox.mapboxsdk.offline.OfflineRegion.C14954;

class OfflineRegion$4$1 implements Runnable {
    final /* synthetic */ C14954 this$1;
    final /* synthetic */ byte[] val$metadata;

    OfflineRegion$4$1(C14954 c14954, byte[] bArr) {
        this.this$1 = c14954;
        this.val$metadata = bArr;
    }

    public void run() {
        OfflineRegion.access$202(this.this$1.this$0, this.val$metadata);
        this.this$1.val$callback.onUpdate(this.val$metadata);
    }
}
