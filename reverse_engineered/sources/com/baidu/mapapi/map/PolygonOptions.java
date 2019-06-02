package com.baidu.mapapi.map;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;
import java.util.List;

public final class PolygonOptions extends OverlayOptions {
    /* renamed from: a */
    int f3078a;
    /* renamed from: b */
    boolean f3079b = true;
    /* renamed from: c */
    Bundle f3080c;
    /* renamed from: d */
    private Stroke f3081d;
    /* renamed from: e */
    private int f3082e = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: f */
    private List<LatLng> f3083f;

    /* renamed from: a */
    Overlay mo2614a() {
        Overlay polygon = new Polygon();
        polygon.r = this.f3079b;
        polygon.q = this.f3078a;
        polygon.s = this.f3080c;
        if (this.f3083f == null || this.f3083f.size() < 2) {
            throw new IllegalStateException("when you add polyline, you must at least supply 2 points");
        }
        polygon.f3077c = this.f3083f;
        polygon.f3076b = this.f3082e;
        polygon.f3075a = this.f3081d;
        return polygon;
    }

    public PolygonOptions extraInfo(Bundle bundle) {
        this.f3080c = bundle;
        return this;
    }

    public PolygonOptions fillColor(int i) {
        this.f3082e = i;
        return this;
    }

    public Bundle getExtraInfo() {
        return this.f3080c;
    }

    public int getFillColor() {
        return this.f3082e;
    }

    public List<LatLng> getPoints() {
        return this.f3083f;
    }

    public Stroke getStroke() {
        return this.f3081d;
    }

    public int getZIndex() {
        return this.f3078a;
    }

    public boolean isVisible() {
        return this.f3079b;
    }

    public PolygonOptions points(List<LatLng> list) {
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
            this.f3083f = list;
            return this;
        }
    }

    public PolygonOptions stroke(Stroke stroke) {
        this.f3081d = stroke;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.f3079b = z;
        return this;
    }

    public PolygonOptions zIndex(int i) {
        this.f3078a = i;
        return this;
    }
}
