package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;
import java.util.List;

public final class Polygon extends Overlay {
    /* renamed from: a */
    Stroke f3075a;
    /* renamed from: b */
    int f3076b;
    /* renamed from: c */
    List<LatLng> f3077c;

    Polygon() {
        this.type = C1252h.polygon;
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        super.mo2613a(bundle);
        GeoPoint ll2mc = CoordUtil.ll2mc((LatLng) this.f3077c.get(0));
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        Overlay.m4046a(this.f3077c, bundle);
        Overlay.m4045a(this.f3076b, bundle);
        if (this.f3075a == null) {
            bundle.putInt("has_stroke", 0);
        } else {
            bundle.putInt("has_stroke", 1);
            bundle.putBundle("stroke", this.f3075a.m4169a(new Bundle()));
        }
        return bundle;
    }

    public int getFillColor() {
        return this.f3076b;
    }

    public List<LatLng> getPoints() {
        return this.f3077c;
    }

    public Stroke getStroke() {
        return this.f3075a;
    }

    public void setFillColor(int i) {
        this.f3076b = i;
        this.listener.mo2624b(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("points list can not be null");
        } else if (list.size() <= 2) {
            throw new IllegalArgumentException("points count can not less than three");
        } else if (list.contains(null)) {
            throw new IllegalArgumentException("points list can not contains null");
        } else {
            for (int i = 0; i < list.size(); i++) {
                for (int i2 = i + 1; i2 < list.size(); i2++) {
                    if (((LatLng) list.get(i)) == ((LatLng) list.get(i2))) {
                        throw new IllegalArgumentException("points list can not has same points");
                    }
                }
            }
            this.f3077c = list;
            this.listener.mo2624b(this);
        }
    }

    public void setStroke(Stroke stroke) {
        this.f3075a = stroke;
        this.listener.mo2624b(this);
    }
}
