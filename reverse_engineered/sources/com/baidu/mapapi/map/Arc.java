package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;
import com.mapbox.mapboxsdk.style.layers.Property;
import java.util.ArrayList;
import java.util.List;

public final class Arc extends Overlay {
    /* renamed from: f */
    private static final String f2808f = Arc.class.getSimpleName();
    /* renamed from: a */
    int f2809a;
    /* renamed from: b */
    int f2810b;
    /* renamed from: c */
    LatLng f2811c;
    /* renamed from: d */
    LatLng f2812d;
    /* renamed from: e */
    LatLng f2813e;

    Arc() {
        this.type = C1252h.arc;
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        super.mo2613a(bundle);
        List arrayList = new ArrayList();
        arrayList.clear();
        arrayList.add(this.f2811c);
        arrayList.add(this.f2812d);
        arrayList.add(this.f2813e);
        GeoPoint ll2mc = CoordUtil.ll2mc((LatLng) arrayList.get(0));
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt(Property.ICON_TEXT_FIT_WIDTH, this.f2810b);
        Overlay.m4046a(arrayList, bundle);
        Overlay.m4045a(this.f2809a, bundle);
        return bundle;
    }

    public int getColor() {
        return this.f2809a;
    }

    public LatLng getEndPoint() {
        return this.f2813e;
    }

    public LatLng getMiddlePoint() {
        return this.f2812d;
    }

    public LatLng getStartPoint() {
        return this.f2811c;
    }

    public int getWidth() {
        return this.f2810b;
    }

    public void setColor(int i) {
        this.f2809a = i;
        this.listener.mo2624b(this);
    }

    public void setPoints(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng == null || latLng2 == null || latLng3 == null) {
            throw new IllegalArgumentException("start and middle and end points can not be null");
        } else if (latLng == latLng2 || latLng == latLng3 || latLng2 == latLng3) {
            throw new IllegalArgumentException("start and middle and end points can not be same");
        } else {
            this.f2811c = latLng;
            this.f2812d = latLng2;
            this.f2813e = latLng3;
            this.listener.mo2624b(this);
        }
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f2810b = i;
            this.listener.mo2624b(this);
        }
    }
}
