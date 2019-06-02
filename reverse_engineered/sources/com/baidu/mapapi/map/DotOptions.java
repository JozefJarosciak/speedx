package com.baidu.mapapi.map;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

public final class DotOptions extends OverlayOptions {
    /* renamed from: a */
    int f2898a;
    /* renamed from: b */
    boolean f2899b = true;
    /* renamed from: c */
    Bundle f2900c;
    /* renamed from: d */
    private LatLng f2901d;
    /* renamed from: e */
    private int f2902e = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: f */
    private int f2903f = 5;

    /* renamed from: a */
    Overlay mo2614a() {
        Overlay dot = new Dot();
        dot.r = this.f2899b;
        dot.q = this.f2898a;
        dot.s = this.f2900c;
        dot.f2896b = this.f2902e;
        dot.f2895a = this.f2901d;
        dot.f2897c = this.f2903f;
        return dot;
    }

    public DotOptions center(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("dot center can not be null");
        }
        this.f2901d = latLng;
        return this;
    }

    public DotOptions color(int i) {
        this.f2902e = i;
        return this;
    }

    public DotOptions extraInfo(Bundle bundle) {
        this.f2900c = bundle;
        return this;
    }

    public LatLng getCenter() {
        return this.f2901d;
    }

    public int getColor() {
        return this.f2902e;
    }

    public Bundle getExtraInfo() {
        return this.f2900c;
    }

    public int getRadius() {
        return this.f2903f;
    }

    public int getZIndex() {
        return this.f2898a;
    }

    public boolean isVisible() {
        return this.f2899b;
    }

    public DotOptions radius(int i) {
        if (i > 0) {
            this.f2903f = i;
        }
        return this;
    }

    public DotOptions visible(boolean z) {
        this.f2899b = z;
        return this;
    }

    public DotOptions zIndex(int i) {
        this.f2898a = i;
        return this;
    }
}
