package com.mapbox.mapboxsdk.camera;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.camera.CameraPosition.Builder;
import com.mapbox.mapboxsdk.maps.MapboxMap;

final class CameraUpdateFactory$ZoomUpdate implements CameraUpdate {
    static final int ZOOM_BY = 2;
    static final int ZOOM_IN = 0;
    static final int ZOOM_OUT = 1;
    static final int ZOOM_TO = 3;
    static final int ZOOM_TO_POINT = 4;
    private final int type;
    /* renamed from: x */
    private float f14838x;
    /* renamed from: y */
    private float f14839y;
    private final double zoom;

    CameraUpdateFactory$ZoomUpdate(int i) {
        this.type = i;
        this.zoom = 0.0d;
    }

    CameraUpdateFactory$ZoomUpdate(int i, float f) {
        this.type = i;
        this.zoom = (double) f;
    }

    CameraUpdateFactory$ZoomUpdate(float f, float f2, float f3) {
        this.type = 4;
        this.zoom = (double) f;
        this.f14838x = f2;
        this.f14839y = f3;
    }

    public double getZoom() {
        return this.zoom;
    }

    public int getType() {
        return this.type;
    }

    public float getX() {
        return this.f14838x;
    }

    public float getY() {
        return this.f14839y;
    }

    double transformZoom(double d) {
        switch (getType()) {
            case 0:
                return d + 1.0d;
            case 1:
                d -= 1.0d;
                if (d < 0.0d) {
                    return 0.0d;
                }
                return d;
            case 2:
                return d + getZoom();
            case 3:
                return getZoom();
            case 4:
                return d + getZoom();
            default:
                return d;
        }
    }

    public CameraPosition getCameraPosition(@NonNull MapboxMap mapboxMap) {
        CameraPosition cameraPosition = mapboxMap.getCameraPosition();
        if (getType() != 4) {
            return new Builder(cameraPosition).zoom(transformZoom(cameraPosition.zoom)).build();
        }
        return new Builder(cameraPosition).zoom(transformZoom(cameraPosition.zoom)).target(mapboxMap.getProjection().fromScreenLocation(new PointF(getX(), getY()))).build();
    }
}
