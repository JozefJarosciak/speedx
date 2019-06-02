package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1249e;

public final class MapStatusUpdate {
    /* renamed from: l */
    private static final String f2984l = MapStatusUpdate.class.getSimpleName();
    /* renamed from: a */
    int f2985a;
    /* renamed from: b */
    MapStatus f2986b;
    /* renamed from: c */
    LatLng f2987c;
    /* renamed from: d */
    LatLngBounds f2988d;
    /* renamed from: e */
    int f2989e;
    /* renamed from: f */
    int f2990f;
    /* renamed from: g */
    float f2991g;
    /* renamed from: h */
    int f2992h;
    /* renamed from: i */
    int f2993i;
    /* renamed from: j */
    float f2994j;
    /* renamed from: k */
    Point f2995k;

    MapStatusUpdate() {
    }

    MapStatusUpdate(int i) {
        this.f2985a = i;
    }

    /* renamed from: a */
    MapStatus m4138a(C1249e c1249e, MapStatus mapStatus) {
        GeoPoint ll2mc;
        GeoPoint ll2mc2;
        float a;
        switch (this.f2985a) {
            case 1:
                return this.f2986b;
            case 2:
                return new MapStatus(mapStatus.rotate, this.f2987c, mapStatus.overlook, mapStatus.zoom, mapStatus.targetScreen, null);
            case 3:
                ll2mc = CoordUtil.ll2mc(this.f2988d.southwest);
                ll2mc2 = CoordUtil.ll2mc(this.f2988d.northeast);
                double longitudeE6 = ll2mc.getLongitudeE6();
                double latitudeE6 = ll2mc2.getLatitudeE6();
                double longitudeE62 = ll2mc2.getLongitudeE6();
                a = c1249e.m4670a((int) longitudeE6, (int) latitudeE6, (int) longitudeE62, (int) ll2mc.getLatitudeE6(), mapStatus.f2981a.f3687j.right - mapStatus.f2981a.f3687j.left, mapStatus.f2981a.f3687j.bottom - mapStatus.f2981a.f3687j.top);
                return new MapStatus(mapStatus.rotate, this.f2988d.getCenter(), mapStatus.overlook, a, mapStatus.targetScreen, null);
            case 4:
                return new MapStatus(mapStatus.rotate, this.f2987c, mapStatus.overlook, this.f2991g, mapStatus.targetScreen, null);
            case 5:
                c1249e.m4662F();
                GeoPoint b = c1249e.m4696b((c1249e.m4662F() / 2) + this.f2992h, (c1249e.m4663G() / 2) + this.f2993i);
                return new MapStatus(mapStatus.rotate, CoordUtil.mc2ll(b), mapStatus.overlook, mapStatus.zoom, mapStatus.targetScreen, b.getLongitudeE6(), b.getLatitudeE6(), null);
            case 6:
                return new MapStatus(mapStatus.rotate, mapStatus.target, mapStatus.overlook, mapStatus.zoom + this.f2994j, mapStatus.targetScreen, mapStatus.m4134a(), mapStatus.m4135b(), null);
            case 7:
                return new MapStatus(mapStatus.rotate, CoordUtil.mc2ll(c1249e.m4696b(this.f2995k.x, this.f2995k.y)), mapStatus.overlook, mapStatus.zoom + this.f2994j, this.f2995k, null);
            case 8:
                return new MapStatus(mapStatus.rotate, mapStatus.target, mapStatus.overlook, this.f2991g, mapStatus.targetScreen, mapStatus.m4134a(), mapStatus.m4135b(), null);
            case 9:
                ll2mc = CoordUtil.ll2mc(this.f2988d.southwest);
                ll2mc2 = CoordUtil.ll2mc(this.f2988d.northeast);
                C1249e c1249e2 = c1249e;
                a = c1249e2.m4670a((int) ll2mc.getLongitudeE6(), (int) ll2mc2.getLatitudeE6(), (int) ll2mc2.getLongitudeE6(), (int) ll2mc.getLatitudeE6(), this.f2989e, this.f2990f);
                return new MapStatus(mapStatus.rotate, this.f2988d.getCenter(), mapStatus.overlook, a, mapStatus.targetScreen, null);
            default:
                return null;
        }
    }
}
