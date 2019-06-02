package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;

public final class Dot extends Overlay {
    /* renamed from: a */
    LatLng f2895a;
    /* renamed from: b */
    int f2896b;
    /* renamed from: c */
    int f2897c;

    Dot() {
        this.type = C1252h.dot;
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        super.mo2613a(bundle);
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f2895a);
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt("radius", this.f2897c);
        Overlay.m4045a(this.f2896b, bundle);
        return bundle;
    }

    public LatLng getCenter() {
        return this.f2895a;
    }

    public int getColor() {
        return this.f2896b;
    }

    public int getRadius() {
        return this.f2897c;
    }

    public void setCenter(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("dot center can not be null");
        }
        this.f2895a = latLng;
        this.listener.mo2624b(this);
    }

    public void setColor(int i) {
        this.f2896b = i;
        this.listener.mo2624b(this);
    }

    public void setRadius(int i) {
        if (i > 0) {
            this.f2897c = i;
            this.listener.mo2624b(this);
        }
    }
}
