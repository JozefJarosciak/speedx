package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;

public final class MarkerOptions extends OverlayOptions {
    /* renamed from: a */
    int f3051a;
    /* renamed from: b */
    boolean f3052b = true;
    /* renamed from: c */
    Bundle f3053c;
    /* renamed from: d */
    private LatLng f3054d;
    /* renamed from: e */
    private BitmapDescriptor f3055e;
    /* renamed from: f */
    private float f3056f = 0.5f;
    /* renamed from: g */
    private float f3057g = 1.0f;
    /* renamed from: h */
    private boolean f3058h = true;
    /* renamed from: i */
    private boolean f3059i = false;
    /* renamed from: j */
    private float f3060j;
    /* renamed from: k */
    private String f3061k;
    /* renamed from: l */
    private int f3062l;
    /* renamed from: m */
    private boolean f3063m = false;
    /* renamed from: n */
    private ArrayList<BitmapDescriptor> f3064n;
    /* renamed from: o */
    private int f3065o = 20;
    /* renamed from: p */
    private float f3066p = 1.0f;
    /* renamed from: q */
    private int f3067q = MarkerAnimateType.none.ordinal();

    public enum MarkerAnimateType {
        none,
        drop,
        grow
    }

    /* renamed from: a */
    MarkerOptions m4156a(int i) {
        this.f3062l = i;
        return this;
    }

    /* renamed from: a */
    Overlay mo2614a() {
        Overlay marker = new Marker();
        marker.r = this.f3052b;
        marker.q = this.f3051a;
        marker.s = this.f3053c;
        if (this.f3054d == null) {
            throw new IllegalStateException("when you add marker, you must set the position");
        }
        marker.f3035a = this.f3054d;
        if (this.f3055e == null && this.f3064n == null) {
            throw new IllegalStateException("when you add marker, you must set the icon or icons");
        }
        marker.f3036b = this.f3055e;
        marker.f3037c = this.f3056f;
        marker.f3038d = this.f3057g;
        marker.f3039e = this.f3058h;
        marker.f3040f = this.f3059i;
        marker.f3041g = this.f3060j;
        marker.f3042h = this.f3061k;
        marker.f3043i = this.f3062l;
        marker.f3044j = this.f3063m;
        marker.f3048n = this.f3064n;
        marker.f3049o = this.f3065o;
        marker.f3046l = this.f3066p;
        marker.f3047m = this.f3067q;
        return marker;
    }

    public MarkerOptions alpha(float f) {
        if (f < 0.0f || f > 1.0f) {
            this.f3066p = 1.0f;
        } else {
            this.f3066p = f;
        }
        return this;
    }

    public MarkerOptions anchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f3056f = f;
            this.f3057g = f2;
        }
        return this;
    }

    public MarkerOptions animateType(MarkerAnimateType markerAnimateType) {
        if (markerAnimateType == null) {
            markerAnimateType = MarkerAnimateType.none;
        }
        this.f3067q = markerAnimateType.ordinal();
        return this;
    }

    public MarkerOptions draggable(boolean z) {
        this.f3059i = z;
        return this;
    }

    public MarkerOptions extraInfo(Bundle bundle) {
        this.f3053c = bundle;
        return this;
    }

    public MarkerOptions flat(boolean z) {
        this.f3063m = z;
        return this;
    }

    public float getAlpha() {
        return this.f3066p;
    }

    public float getAnchorX() {
        return this.f3056f;
    }

    public float getAnchorY() {
        return this.f3057g;
    }

    public MarkerAnimateType getAnimateType() {
        switch (this.f3067q) {
            case 1:
                return MarkerAnimateType.drop;
            case 2:
                return MarkerAnimateType.grow;
            default:
                return MarkerAnimateType.none;
        }
    }

    public Bundle getExtraInfo() {
        return this.f3053c;
    }

    public BitmapDescriptor getIcon() {
        return this.f3055e;
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return this.f3064n;
    }

    public int getPeriod() {
        return this.f3065o;
    }

    public LatLng getPosition() {
        return this.f3054d;
    }

    public float getRotate() {
        return this.f3060j;
    }

    public String getTitle() {
        return this.f3061k;
    }

    public int getZIndex() {
        return this.f3051a;
    }

    public MarkerOptions icon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("marker's icon can not be null");
        }
        this.f3055e = bitmapDescriptor;
        return this;
    }

    public MarkerOptions icons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList == null) {
            throw new IllegalArgumentException("marker's icons can not be null");
        }
        if (arrayList.size() != 0) {
            int i = 0;
            while (i < arrayList.size()) {
                if (arrayList.get(i) == null || ((BitmapDescriptor) arrayList.get(i)).f2880a == null) {
                    break;
                }
                i++;
            }
            this.f3064n = arrayList;
        }
        return this;
    }

    public boolean isDraggable() {
        return this.f3059i;
    }

    public boolean isFlat() {
        return this.f3063m;
    }

    public boolean isPerspective() {
        return this.f3058h;
    }

    public boolean isVisible() {
        return this.f3052b;
    }

    public MarkerOptions period(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("marker's period must be greater than zero ");
        }
        this.f3065o = i;
        return this;
    }

    public MarkerOptions perspective(boolean z) {
        this.f3058h = z;
        return this;
    }

    public MarkerOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("marker's position can not be null");
        }
        this.f3054d = latLng;
        return this;
    }

    public MarkerOptions rotate(float f) {
        while (f < 0.0f) {
            f += 360.0f;
        }
        this.f3060j = f % 360.0f;
        return this;
    }

    public MarkerOptions title(String str) {
        this.f3061k = str;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.f3052b = z;
        return this;
    }

    public MarkerOptions zIndex(int i) {
        this.f3051a = i;
        return this;
    }
}
