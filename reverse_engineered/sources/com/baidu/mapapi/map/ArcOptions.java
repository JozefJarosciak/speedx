package com.baidu.mapapi.map;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

public final class ArcOptions extends OverlayOptions {
    /* renamed from: d */
    private static final String f2814d = ArcOptions.class.getSimpleName();
    /* renamed from: a */
    int f2815a;
    /* renamed from: b */
    boolean f2816b = true;
    /* renamed from: c */
    Bundle f2817c;
    /* renamed from: e */
    private int f2818e = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: f */
    private int f2819f = 5;
    /* renamed from: g */
    private LatLng f2820g;
    /* renamed from: h */
    private LatLng f2821h;
    /* renamed from: i */
    private LatLng f2822i;

    /* renamed from: a */
    Overlay mo2614a() {
        Overlay arc = new Arc();
        arc.r = this.f2816b;
        arc.q = this.f2815a;
        arc.s = this.f2817c;
        arc.f2809a = this.f2818e;
        arc.f2810b = this.f2819f;
        arc.f2811c = this.f2820g;
        arc.f2812d = this.f2821h;
        arc.f2813e = this.f2822i;
        return arc;
    }

    public ArcOptions color(int i) {
        this.f2818e = i;
        return this;
    }

    public ArcOptions extraInfo(Bundle bundle) {
        this.f2817c = bundle;
        return this;
    }

    public int getColor() {
        return this.f2818e;
    }

    public LatLng getEndPoint() {
        return this.f2822i;
    }

    public Bundle getExtraInfo() {
        return this.f2817c;
    }

    public LatLng getMiddlePoint() {
        return this.f2821h;
    }

    public LatLng getStartPoint() {
        return this.f2820g;
    }

    public int getWidth() {
        return this.f2819f;
    }

    public int getZIndex() {
        return this.f2815a;
    }

    public boolean isVisible() {
        return this.f2816b;
    }

    public ArcOptions points(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        if (latLng == null || latLng2 == null || latLng3 == null) {
            throw new IllegalArgumentException("start and middle and end points can not be null");
        } else if (latLng == latLng2 || latLng == latLng3 || latLng2 == latLng3) {
            throw new IllegalArgumentException("start and middle and end points can not be same");
        } else {
            this.f2820g = latLng;
            this.f2821h = latLng2;
            this.f2822i = latLng3;
            return this;
        }
    }

    public ArcOptions visible(boolean z) {
        this.f2816b = z;
        return this;
    }

    public ArcOptions width(int i) {
        if (i > 0) {
            this.f2819f = i;
        }
        return this;
    }

    public ArcOptions zIndex(int i) {
        this.f2815a = i;
        return this;
    }
}
