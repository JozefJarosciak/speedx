package com.mapbox.mapboxsdk.offline;

class OfflineManager$2 implements OfflineManager$ListOfflineRegionsCallback {
    final /* synthetic */ OfflineManager this$0;
    final /* synthetic */ OfflineManager$ListOfflineRegionsCallback val$callback;

    OfflineManager$2(OfflineManager offlineManager, OfflineManager$ListOfflineRegionsCallback offlineManager$ListOfflineRegionsCallback) {
        this.this$0 = offlineManager;
        this.val$callback = offlineManager$ListOfflineRegionsCallback;
    }

    public void onList(final OfflineRegion[] offlineRegionArr) {
        OfflineManager.access$000(this.this$0).post(new Runnable() {
            public void run() {
                OfflineManager$2.this.val$callback.onList(offlineRegionArr);
            }
        });
    }

    public void onError(final String str) {
        OfflineManager.access$000(this.this$0).post(new Runnable() {
            public void run() {
                OfflineManager$2.this.val$callback.onError(str);
            }
        });
    }
}
