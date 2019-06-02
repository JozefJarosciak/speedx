package com.mapbox.mapboxsdk.camera;

import android.graphics.Point;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;

public final class CameraUpdateFactory {
    public static CameraUpdate newCameraPosition(@NonNull CameraPosition cameraPosition) {
        return new CameraUpdateFactory$CameraPositionUpdate(cameraPosition.bearing, cameraPosition.target, cameraPosition.tilt, cameraPosition.zoom);
    }

    public static CameraUpdate newLatLng(@NonNull LatLng latLng) {
        return new CameraUpdateFactory$CameraPositionUpdate(-1.0d, latLng, -1.0d, -1.0d);
    }

    public static CameraUpdate newLatLngBounds(@NonNull LatLngBounds latLngBounds, int i) {
        return newLatLngBounds(latLngBounds, i, i, i, i);
    }

    public static CameraUpdate newLatLngBounds(@NonNull LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        return new CameraUpdateFactory$CameraBoundsUpdate(latLngBounds, i, i2, i3, i4);
    }

    public static CameraUpdate newLatLngZoom(@NonNull LatLng latLng, float f) {
        return new CameraUpdateFactory$CameraPositionUpdate(-1.0d, latLng, -1.0d, (double) f);
    }

    public static CameraUpdate scrollBy(float f, float f2) {
        return new CameraUpdateFactory$CameraMoveUpdate(f, f2);
    }

    public static CameraUpdate zoomBy(float f, Point point) {
        return new CameraUpdateFactory$ZoomUpdate(f, (float) point.x, (float) point.y);
    }

    public static CameraUpdate zoomBy(float f) {
        return new CameraUpdateFactory$ZoomUpdate(2, f);
    }

    public static CameraUpdate zoomIn() {
        return new CameraUpdateFactory$ZoomUpdate(0);
    }

    public static CameraUpdate zoomOut() {
        return new CameraUpdateFactory$ZoomUpdate(1);
    }

    public static CameraUpdate zoomTo(float f) {
        return new CameraUpdateFactory$ZoomUpdate(3, f);
    }
}
