package com.beastbikes.android.ble.ui;

import com.beastbikes.android.C1373R;
import com.mapbox.mapboxsdk.offline.OfflineManager$CreateOfflineRegionCallback;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegion.OfflineRegionStatusCallback;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;

class OfflineMapSelectActivity$3 implements OfflineManager$CreateOfflineRegionCallback {
    /* renamed from: a */
    final /* synthetic */ OfflineMapSelectActivity f7640a;

    /* renamed from: com.beastbikes.android.ble.ui.OfflineMapSelectActivity$3$1 */
    class C16901 implements OfflineRegionStatusCallback {
        /* renamed from: a */
        final /* synthetic */ OfflineMapSelectActivity$3 f7639a;

        C16901(OfflineMapSelectActivity$3 offlineMapSelectActivity$3) {
            this.f7639a = offlineMapSelectActivity$3;
        }

        public void onStatus(OfflineRegionStatus offlineRegionStatus) {
            long requiredResourceCount = offlineRegionStatus.getRequiredResourceCount();
            OfflineMapSelectActivity.d().info("resourceCount: " + requiredResourceCount);
            requiredResourceCount *= 55;
            OfflineMapSelectActivity.g(this.f7639a.f7640a).setText(String.format(this.f7639a.f7640a.getString(C1373R.string.str_offline_map_choose_description), new Object[]{Double.valueOf(((double) requiredResourceCount) / 1024.0d)}));
        }

        public void onError(String str) {
            OfflineMapSelectActivity.d().error("Get resource count error: " + str);
        }
    }

    OfflineMapSelectActivity$3(OfflineMapSelectActivity offlineMapSelectActivity) {
        this.f7640a = offlineMapSelectActivity;
    }

    public void onCreate(OfflineRegion offlineRegion) {
        offlineRegion.getStatus(new C16901(this));
    }

    public void onError(String str) {
        OfflineMapSelectActivity.d().error("Create offline region error: " + str);
    }
}
