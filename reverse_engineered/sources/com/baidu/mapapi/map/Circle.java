package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;

public final class Circle extends Overlay {
    /* renamed from: a */
    LatLng f2883a;
    /* renamed from: b */
    int f2884b;
    /* renamed from: c */
    int f2885c;
    /* renamed from: d */
    Stroke f2886d;

    Circle() {
        this.type = C1252h.circle;
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        super.mo2613a(bundle);
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f2883a);
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt("radius", CoordUtil.getMCDistanceByOneLatLngAndRadius(this.f2883a, this.f2885c));
        Overlay.m4045a(this.f2884b, bundle);
        if (this.f2886d == null) {
            bundle.putInt("has_stroke", 0);
        } else {
            bundle.putInt("has_stroke", 1);
            bundle.putBundle("stroke", this.f2886d.m4169a(new Bundle()));
        }
        return bundle;
    }

    public LatLng getCenter() {
        return this.f2883a;
    }

    public int getFillColor() {
        return this.f2884b;
    }

    public int getRadius() {
        return this.f2885c;
    }

    public Stroke getStroke() {
        return this.f2886d;
    }

    public void setCenter(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("circle center can not be null");
        }
        this.f2883a = latLng;
        this.listener.mo2624b(this);
    }

    public void setFillColor(int i) {
        this.f2884b = i;
        this.listener.mo2624b(this);
    }

    public void setRadius(int i) {
        this.f2885c = i;
        this.listener.mo2624b(this);
    }

    public void setStroke(Stroke stroke) {
        this.f2886d = stroke;
        this.listener.mo2624b(this);
    }
}
