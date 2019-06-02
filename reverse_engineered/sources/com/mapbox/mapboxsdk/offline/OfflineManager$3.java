package com.mapbox.mapboxsdk.offline;

class OfflineManager$3 implements OfflineManager$CreateOfflineRegionCallback {
    final /* synthetic */ OfflineManager this$0;
    final /* synthetic */ OfflineManager$CreateOfflineRegionCallback val$callback;

    OfflineManager$3(OfflineManager offlineManager, OfflineManager$CreateOfflineRegionCallback offlineManager$CreateOfflineRegionCallback) {
        this.this$0 = offlineManager;
        this.val$callback = offlineManager$CreateOfflineRegionCallback;
    }

    public void onCreate(final OfflineRegion offlineRegion) {
        OfflineManager.access$000(this.this$0).post(new Runnable() {
            public void run() {
                OfflineManager$3.this.val$callback.onCreate(offlineRegion);
            }
        });
    }

    public void onError(final String str) {
        OfflineManager.access$000(this.this$0).post(new Runnable() {
            public void run() {
                OfflineManager$3.this.val$callback.onError(str);
            }
        });
    }
}
