package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

public final class GroundOverlayOptions extends OverlayOptions {
    /* renamed from: a */
    int f2921a;
    /* renamed from: b */
    boolean f2922b = true;
    /* renamed from: c */
    Bundle f2923c;
    /* renamed from: d */
    private BitmapDescriptor f2924d;
    /* renamed from: e */
    private LatLng f2925e;
    /* renamed from: f */
    private int f2926f;
    /* renamed from: g */
    private int f2927g;
    /* renamed from: h */
    private float f2928h = 0.5f;
    /* renamed from: i */
    private float f2929i = 0.5f;
    /* renamed from: j */
    private LatLngBounds f2930j;
    /* renamed from: k */
    private float f2931k = 1.0f;

    /* renamed from: a */
    Overlay mo2614a() {
        Overlay groundOverlay = new GroundOverlay();
        groundOverlay.r = this.f2922b;
        groundOverlay.q = this.f2921a;
        groundOverlay.s = this.f2923c;
        if (this.f2924d == null) {
            throw new IllegalStateException("when you add ground overlay, you must set the image");
        }
        groundOverlay.f2913b = this.f2924d;
        if (this.f2930j != null || this.f2925e == null) {
            if (this.f2925e != null || this.f2930j == null) {
                throw new IllegalStateException("when you add ground overlay, you must set one of position or bounds");
            }
            groundOverlay.f2919h = this.f2930j;
            groundOverlay.f2912a = 1;
        } else if (this.f2926f <= 0 || this.f2927g <= 0) {
            throw new IllegalArgumentException("when you add ground overlay, the width and height must greater than 0");
        } else {
            groundOverlay.f2914c = this.f2925e;
            groundOverlay.f2917f = this.f2928h;
            groundOverlay.f2918g = this.f2929i;
            groundOverlay.f2915d = (double) this.f2926f;
            groundOverlay.f2916e = (double) this.f2927g;
            groundOverlay.f2912a = 2;
        }
        groundOverlay.f2920i = this.f2931k;
        return groundOverlay;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        if (f >= 0.0f && f <= 1.0f && f2 >= 0.0f && f2 <= 1.0f) {
            this.f2928h = f;
            this.f2929i = f2;
        }
        return this;
    }

    public GroundOverlayOptions dimensions(int i) {
        this.f2926f = i;
        this.f2927g = Integer.MAX_VALUE;
        return this;
    }

    public GroundOverlayOptions dimensions(int i, int i2) {
        this.f2926f = i;
        this.f2927g = i2;
        return this;
    }

    public GroundOverlayOptions extraInfo(Bundle bundle) {
        this.f2923c = bundle;
        return this;
    }

    public float getAnchorX() {
        return this.f2928h;
    }

    public float getAnchorY() {
        return this.f2929i;
    }

    public LatLngBounds getBounds() {
        return this.f2930j;
    }

    public Bundle getExtraInfo() {
        return this.f2923c;
    }

    public int getHeight() {
        return this.f2927g == Integer.MAX_VALUE ? (int) (((float) (this.f2926f * this.f2924d.f2880a.getHeight())) / ((float) this.f2924d.f2880a.getWidth())) : this.f2927g;
    }

    public BitmapDescriptor getImage() {
        return this.f2924d;
    }

    public LatLng getPosition() {
        return this.f2925e;
    }

    public float getTransparency() {
        return this.f2931k;
    }

    public int getWidth() {
        return this.f2926f;
    }

    public int getZIndex() {
        return this.f2921a;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor == null) {
            throw new IllegalArgumentException("image can not be null");
        }
        this.f2924d = bitmapDescriptor;
        return this;
    }

    public boolean isVisible() {
        return this.f2922b;
    }

    public GroundOverlayOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("position can not be null");
        }
        this.f2925e = latLng;
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            throw new IllegalArgumentException("bounds can not be null");
        }
        this.f2930j = latLngBounds;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        if (f <= 1.0f && f >= 0.0f) {
            this.f2931k = f;
        }
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.f2922b = z;
        return this;
    }

    public GroundOverlayOptions zIndex(int i) {
        this.f2921a = i;
        return this;
    }
}
