package com.beastbikes.android.ble.ui;

import com.mapbox.mapboxsdk.maps.MapView.OnMapChangedListener;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.UiSettings;

class OfflineMapSelectActivity$1 implements OnMapReadyCallback {
    /* renamed from: a */
    final /* synthetic */ OfflineMapSelectActivity f7637a;

    /* renamed from: com.beastbikes.android.ble.ui.OfflineMapSelectActivity$1$1 */
    class C16891 implements OnMapChangedListener {
        /* renamed from: a */
        final /* synthetic */ OfflineMapSelectActivity$1 f7636a;

        C16891(OfflineMapSelectActivity$1 offlineMapSelectActivity$1) {
            this.f7636a = offlineMapSelectActivity$1;
        }

        public void onMapChanged(int i) {
            if (i == 3 || i == 4) {
                OfflineMapSelectActivity.e(this.f7636a.f7637a);
                OfflineMapSelectActivity.d(this.f7636a.f7637a).removeOnMapChangedListener(this);
            }
        }
    }

    OfflineMapSelectActivity$1(OfflineMapSelectActivity offlineMapSelectActivity) {
        this.f7637a = offlineMapSelectActivity;
    }

    public void onMapReady(MapboxMap mapboxMap) {
        OfflineMapSelectActivity.a(this.f7637a, mapboxMap);
        UiSettings uiSettings = OfflineMapSelectActivity.a(this.f7637a).getUiSettings();
        uiSettings.setLogoEnabled(false);
        uiSettings.setAttributionEnabled(false);
        uiSettings.setCompassEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setTiltGesturesEnabled(false);
        OfflineMapSelectActivity.a(this.f7637a).getMyLocationViewSettings().setEnabled(true);
        OfflineMapSelectActivity.a(this.f7637a).getMyLocationViewSettings().setAccuracyAlpha(0);
        OfflineMapSelectActivity.b(this.f7637a);
        OfflineMapSelectActivity.a(this.f7637a).setOnCameraChangeListener(this.f7637a);
        this.f7637a.onCameraChange(null);
        OfflineMapSelectActivity.c(this.f7637a);
        OfflineMapSelectActivity.d(this.f7637a).setOnTouchListener(this.f7637a);
        OfflineMapSelectActivity.d(this.f7637a).addOnMapChangedListener(new C16891(this));
    }
}
