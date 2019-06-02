package com.baidu.mapapi.map;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import com.baidu.mapapi.model.LatLng;

public final class TextOptions extends OverlayOptions {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;
    /* renamed from: a */
    int f3139a;
    /* renamed from: b */
    boolean f3140b = true;
    /* renamed from: c */
    Bundle f3141c;
    /* renamed from: d */
    private String f3142d;
    /* renamed from: e */
    private LatLng f3143e;
    /* renamed from: f */
    private int f3144f;
    /* renamed from: g */
    private int f3145g = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: h */
    private int f3146h = 12;
    /* renamed from: i */
    private Typeface f3147i;
    /* renamed from: j */
    private int f3148j = 4;
    /* renamed from: k */
    private int f3149k = 32;
    /* renamed from: l */
    private float f3150l;

    /* renamed from: a */
    Overlay mo2614a() {
        Overlay text = new Text();
        text.r = this.f3140b;
        text.q = this.f3139a;
        text.s = this.f3141c;
        text.f3129a = this.f3142d;
        text.f3130b = this.f3143e;
        text.f3131c = this.f3144f;
        text.f3132d = this.f3145g;
        text.f3133e = this.f3146h;
        text.f3134f = this.f3147i;
        text.f3135g = this.f3148j;
        text.f3136h = this.f3149k;
        text.f3137i = this.f3150l;
        return text;
    }

    public TextOptions align(int i, int i2) {
        this.f3148j = i;
        this.f3149k = i2;
        return this;
    }

    public TextOptions bgColor(int i) {
        this.f3144f = i;
        return this;
    }

    public TextOptions extraInfo(Bundle bundle) {
        this.f3141c = bundle;
        return this;
    }

    public TextOptions fontColor(int i) {
        this.f3145g = i;
        return this;
    }

    public TextOptions fontSize(int i) {
        this.f3146h = i;
        return this;
    }

    public float getAlignX() {
        return (float) this.f3148j;
    }

    public float getAlignY() {
        return (float) this.f3149k;
    }

    public int getBgColor() {
        return this.f3144f;
    }

    public Bundle getExtraInfo() {
        return this.f3141c;
    }

    public int getFontColor() {
        return this.f3145g;
    }

    public int getFontSize() {
        return this.f3146h;
    }

    public LatLng getPosition() {
        return this.f3143e;
    }

    public float getRotate() {
        return this.f3150l;
    }

    public String getText() {
        return this.f3142d;
    }

    public Typeface getTypeface() {
        return this.f3147i;
    }

    public int getZIndex() {
        return this.f3139a;
    }

    public boolean isVisible() {
        return this.f3140b;
    }

    public TextOptions position(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("position can not be null");
        }
        this.f3143e = latLng;
        return this;
    }

    public TextOptions rotate(float f) {
        this.f3150l = f;
        return this;
    }

    public TextOptions text(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("text can not be null or empty");
        }
        this.f3142d = str;
        return this;
    }

    public TextOptions typeface(Typeface typeface) {
        this.f3147i = typeface;
        return this;
    }

    public TextOptions visible(boolean z) {
        this.f3140b = z;
        return this;
    }

    public TextOptions zIndex(int i) {
        this.f3139a = i;
        return this;
    }
}
