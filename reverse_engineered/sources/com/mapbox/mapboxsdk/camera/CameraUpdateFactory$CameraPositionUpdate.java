package com.mapbox.mapboxsdk.camera;

import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.camera.CameraPosition.Builder;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;

final class CameraUpdateFactory$CameraPositionUpdate implements CameraUpdate {
    private final double bearing;
    private final LatLng target;
    private final double tilt;
    private final double zoom;

    CameraUpdateFactory$CameraPositionUpdate(double d, LatLng latLng, double d2, double d3) {
        this.bearing = d;
        this.target = latLng;
        this.tilt = d2;
        this.zoom = d3;
    }

    public LatLng getTarget() {
        return this.target;
    }

    public double getBearing() {
        return this.bearing;
    }

    public double getTilt() {
        return this.tilt;
    }

    public double getZoom() {
        return this.zoom;
    }

    public CameraPosition getCameraPosition(@NonNull MapboxMap mapboxMap) {
        CameraPosition cameraPosition = mapboxMap.getCameraPosition();
        if (this.target == null) {
            return new Builder().tilt(this.tilt).zoom(this.zoom).bearing(this.bearing).target(cameraPosition.target).build();
        }
        return new Builder(this).build();
    }
}
