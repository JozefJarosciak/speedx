package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.map.C1127l.C1112a;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

public class WeightedLatLng extends C1112a {
    public static final double DEFAULT_INTENSITY = 1.0d;
    /* renamed from: a */
    private Point f3238a;
    public final double intensity;
    public final LatLng latLng;

    public WeightedLatLng(LatLng latLng) {
        this(latLng, 1.0d);
    }

    public WeightedLatLng(LatLng latLng, double d) {
        if (latLng == null) {
            throw new IllegalArgumentException("latLng can not be null");
        }
        this.latLng = latLng;
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        this.f3238a = new Point((int) ll2mc.getLongitudeE6(), (int) ll2mc.getLatitudeE6());
        if (d > 0.0d) {
            this.intensity = d;
        } else {
            this.intensity = 1.0d;
        }
    }

    /* renamed from: a */
    Point mo2622a() {
        return this.f3238a;
    }
}
