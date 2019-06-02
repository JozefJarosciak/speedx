package com.mapbox.mapboxsdk.camera;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.camera.CameraPosition.Builder;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.UiSettings;

final class CameraUpdateFactory$CameraMoveUpdate implements CameraUpdate {
    /* renamed from: x */
    private float f14836x;
    /* renamed from: y */
    private float f14837y;

    CameraUpdateFactory$CameraMoveUpdate(float f, float f2) {
        this.f14836x = f;
        this.f14837y = f2;
    }

    public CameraPosition getCameraPosition(@NonNull MapboxMap mapboxMap) {
        UiSettings uiSettings = mapboxMap.getUiSettings();
        LatLng fromScreenLocation = mapboxMap.getProjection().fromScreenLocation(new PointF((uiSettings.getWidth() / 2.0f) + this.f14836x, (uiSettings.getHeight() / 2.0f) + this.f14837y));
        CameraPosition cameraPosition = mapboxMap.getCameraPosition();
        Builder builder = new Builder();
        if (fromScreenLocation == null) {
            fromScreenLocation = cameraPosition.target;
        }
        return builder.target(fromScreenLocation).zoom(cameraPosition.zoom).tilt(cameraPosition.tilt).bearing(cameraPosition.bearing).build();
    }
}
