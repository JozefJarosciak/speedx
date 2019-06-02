package com.beastbikes.android.ble.ui;

import android.location.Location;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationListener;

class OfflineMapSelectActivity$2 implements LocationListener {
    /* renamed from: a */
    final /* synthetic */ OfflineMapSelectActivity f7638a;

    OfflineMapSelectActivity$2(OfflineMapSelectActivity offlineMapSelectActivity) {
        this.f7638a = offlineMapSelectActivity;
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
            OfflineMapSelectActivity.d().info("onLocationChanged is called!");
            OfflineMapSelectActivity.a(this.f7638a).moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location), 11.0f));
            OfflineMapSelectActivity.f(this.f7638a).removeLocationListener(this);
        }
    }
}
