package com.baidu.mapapi.map;

import android.graphics.Point;
import android.graphics.PointF;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1235E;
import com.baidu.platform.comapi.map.C1235E.C1234a;
import com.baidu.platform.comapi.map.C1249e;

public final class Projection {
    /* renamed from: a */
    private C1249e f3107a;

    Projection(C1249e c1249e) {
        this.f3107a = c1249e;
    }

    public LatLng fromScreenLocation(Point point) {
        return (point == null || this.f3107a == null) ? null : CoordUtil.mc2ll(this.f3107a.m4696b(point.x, point.y));
    }

    public float metersToEquatorPixels(float f) {
        return f <= 0.0f ? 0.0f : (float) (((double) f) / this.f3107a.m4665I());
    }

    public PointF toOpenGLLocation(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        C1235E c1235e = mapStatus.f2981a;
        return new PointF((float) ((ll2mc.getLongitudeE6() - c1235e.f3681d) / c1235e.f3691n), (float) ((ll2mc.getLatitudeE6() - c1235e.f3682e) / c1235e.f3691n));
    }

    public PointF toOpenGLNormalization(LatLng latLng, MapStatus mapStatus) {
        if (latLng == null || mapStatus == null) {
            return null;
        }
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        C1234a c1234a = mapStatus.f2981a.f3688k;
        return new PointF((float) (((2.0d * (ll2mc.getLongitudeE6() - ((double) c1234a.f3668a))) / ((double) Math.abs(c1234a.f3669b - c1234a.f3668a))) - 1.0d), (float) ((((ll2mc.getLatitudeE6() - ((double) c1234a.f3671d)) * 2.0d) / ((double) Math.abs(c1234a.f3670c - c1234a.f3671d))) - 1.0d));
    }

    public Point toScreenLocation(LatLng latLng) {
        if (latLng == null || this.f3107a == null) {
            return null;
        }
        return this.f3107a.m4673a(CoordUtil.ll2mc(latLng));
    }
}
