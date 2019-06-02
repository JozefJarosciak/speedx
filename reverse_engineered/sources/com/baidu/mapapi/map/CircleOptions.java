package com.baidu.mapapi.map;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

public final class CircleOptions extends OverlayOptions {
    /* renamed from: d */
    private static final String f2887d = CircleOptions.class.getSimpleName();
    /* renamed from: a */
    int f2888a;
    /* renamed from: b */
    boolean f2889b = true;
    /* renamed from: c */
    Bundle f2890c;
    /* renamed from: e */
    private LatLng f2891e;
    /* renamed from: f */
    private int f2892f = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: g */
    private int f2893g;
    /* renamed from: h */
    private Stroke f2894h;

    /* renamed from: a */
    Overlay mo2614a() {
        Overlay circle = new Circle();
        circle.r = this.f2889b;
        circle.q = this.f2888a;
        circle.s = this.f2890c;
        circle.f2884b = this.f2892f;
        circle.f2883a = this.f2891e;
        circle.f2885c = this.f2893g;
        circle.f2886d = this.f2894h;
        return circle;
    }

    public CircleOptions center(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("circle center can not be null");
        }
        this.f2891e = latLng;
        return this;
    }

    public CircleOptions extraInfo(Bundle bundle) {
        this.f2890c = bundle;
        return this;
    }

    public CircleOptions fillColor(int i) {
        this.f2892f = i;
        return this;
    }

    public LatLng getCenter() {
        return this.f2891e;
    }

    public Bundle getExtraInfo() {
        return this.f2890c;
    }

    public int getFillColor() {
        return this.f2892f;
    }

    public int getRadius() {
        return this.f2893g;
    }

    public Stroke getStroke() {
        return this.f2894h;
    }

    public int getZIndex() {
        return this.f2888a;
    }

    public boolean isVisible() {
        return this.f2889b;
    }

    public CircleOptions radius(int i) {
        this.f2893g = i;
        return this;
    }

    public CircleOptions stroke(Stroke stroke) {
        this.f2894h = stroke;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.f2889b = z;
        return this;
    }

    public CircleOptions zIndex(int i) {
        this.f2888a = i;
        return this;
    }
}
