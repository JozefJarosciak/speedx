package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;

public final class GroundOverlay extends Overlay {
    /* renamed from: j */
    private static final String f2911j = GroundOverlay.class.getSimpleName();
    /* renamed from: a */
    int f2912a;
    /* renamed from: b */
    BitmapDescriptor f2913b;
    /* renamed from: c */
    LatLng f2914c;
    /* renamed from: d */
    double f2915d;
    /* renamed from: e */
    double f2916e;
    /* renamed from: f */
    float f2917f;
    /* renamed from: g */
    float f2918g;
    /* renamed from: h */
    LatLngBounds f2919h;
    /* renamed from: i */
    float f2920i;

    GroundOverlay() {
        this.type = C1252h.ground;
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        super.mo2613a(bundle);
        bundle.putBundle("image_info", this.f2913b.m4093b());
        if (this.f2912a == 1) {
            GeoPoint ll2mc = CoordUtil.ll2mc(this.f2919h.southwest);
            double longitudeE6 = ll2mc.getLongitudeE6();
            double latitudeE6 = ll2mc.getLatitudeE6();
            GeoPoint ll2mc2 = CoordUtil.ll2mc(this.f2919h.northeast);
            double longitudeE62 = ll2mc2.getLongitudeE6();
            double latitudeE62 = ll2mc2.getLatitudeE6();
            this.f2915d = longitudeE62 - longitudeE6;
            this.f2916e = latitudeE62 - latitudeE6;
            this.f2914c = CoordUtil.mc2ll(new GeoPoint(latitudeE6 + (this.f2916e / 2.0d), longitudeE6 + (this.f2915d / 2.0d)));
            this.f2917f = 0.5f;
            this.f2918g = 0.5f;
        }
        if (this.f2915d <= 0.0d || this.f2916e <= 0.0d) {
            throw new IllegalStateException("when you add ground overlay, the width and height must greater than 0");
        }
        bundle.putDouble("x_distance", this.f2915d);
        if (this.f2916e == 2.147483647E9d) {
            this.f2916e = (double) ((int) ((this.f2915d * ((double) this.f2913b.f2880a.getHeight())) / ((double) ((float) this.f2913b.f2880a.getWidth()))));
        }
        bundle.putDouble("y_distance", this.f2916e);
        ll2mc = CoordUtil.ll2mc(this.f2914c);
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putFloat("anchor_x", this.f2917f);
        bundle.putFloat("anchor_y", this.f2918g);
        bundle.putFloat("transparency", this.f2920i);
        return bundle;
    }

    public float getAnchorX() {
        return this.f2917f;
    }

    public float getAnchorY() {
        return this.f2918g;
    }

    public LatLngBounds getBounds() {
        return this.f2919h;
    }

    public double getHeight() {
        return this.f2916e;
    }

    public BitmapDescriptor getImage() {
        return this.f2913b;
    }

    public LatLng getPosition() {
        return this.f2914c;
    }

    public float getTransparency() {
        return this.f2920i;
    }

    public double getWidth() {
        return this.f2915d;
    }

    public void setAnchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f2917f = f;
            this.f2918g = f2;
            this.listener.mo2624b(this);
        }
    }

    public void setDimensions(int i) {
        this.f2915d = (double) i;
        this.f2916e = 2.147483647E9d;
        this.listener.mo2624b(this);
    }

    public void setDimensions(int i, int i2) {
        this.f2915d = (double) i;
        this.f2916e = (double) i2;
        this.listener.mo2624b(this);
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("image can not be null");
        }
        this.f2913b = bitmapDescriptor;
        this.listener.mo2624b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("position can not be null");
        }
        this.f2912a = 2;
        this.f2914c = latLng;
        this.listener.mo2624b(this);
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("bounds can not be null");
        }
        this.f2912a = 1;
        this.f2919h = latLngBounds;
        this.listener.mo2624b(this);
    }

    public void setTransparency(float f) {
        if (f <= 1.0f && f >= 0.0f) {
            this.f2920i = f;
            this.listener.mo2624b(this);
        }
    }
}
