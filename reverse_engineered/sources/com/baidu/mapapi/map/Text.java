package com.baidu.mapapi.map;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import vi.com.gdi.bgl.android.java.EnvDrawText;

public final class Text extends Overlay {
    /* renamed from: k */
    private static final String f3128k = Text.class.getSimpleName();
    /* renamed from: a */
    String f3129a;
    /* renamed from: b */
    LatLng f3130b;
    /* renamed from: c */
    int f3131c;
    /* renamed from: d */
    int f3132d;
    /* renamed from: e */
    int f3133e;
    /* renamed from: f */
    Typeface f3134f;
    /* renamed from: g */
    int f3135g;
    /* renamed from: h */
    int f3136h;
    /* renamed from: i */
    float f3137i;
    /* renamed from: j */
    int f3138j;

    Text() {
        this.type = C1252h.f3782e;
    }

    /* renamed from: a */
    Bundle mo2621a() {
        if (this.f3134f != null) {
            EnvDrawText.removeFontCache(this.f3134f.hashCode());
        }
        return super.mo2621a();
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        float f = 0.5f;
        super.mo2613a(bundle);
        if (this.f3130b == null) {
            throw new IllegalStateException("when you add a text overlay, you must provide text and the position info.");
        }
        float f2;
        bundle.putString(DirectionsCriteria.INSTRUCTIONS_TEXT, this.f3129a);
        GeoPoint ll2mc = CoordUtil.ll2mc(this.f3130b);
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        int i = (this.f3132d >> 8) & 255;
        bundle.putInt("font_color", Color.argb(this.f3132d >>> 24, this.f3132d & 255, i, (this.f3132d >> 16) & 255));
        i = (this.f3131c >> 8) & 255;
        bundle.putInt("bg_color", Color.argb(this.f3131c >>> 24, this.f3131c & 255, i, (this.f3131c >> 16) & 255));
        bundle.putInt("font_size", this.f3133e);
        if (this.f3134f != null) {
            EnvDrawText.registFontCache(this.f3134f.hashCode(), this.f3134f);
            bundle.putInt("type_face", this.f3134f.hashCode());
        }
        switch (this.f3135g) {
            case 1:
                f2 = 0.0f;
                break;
            case 2:
                f2 = 1.0f;
                break;
            case 4:
                f2 = 0.5f;
                break;
            default:
                f2 = 0.5f;
                break;
        }
        bundle.putFloat("align_x", f2);
        switch (this.f3136h) {
            case 8:
                f = 0.0f;
                break;
            case 16:
                f = 1.0f;
                break;
        }
        bundle.putFloat("align_y", f);
        bundle.putFloat("rotate", this.f3137i);
        bundle.putInt("update", this.f3138j);
        return bundle;
    }

    public float getAlignX() {
        return (float) this.f3135g;
    }

    public float getAlignY() {
        return (float) this.f3136h;
    }

    public int getBgColor() {
        return this.f3131c;
    }

    public int getFontColor() {
        return this.f3132d;
    }

    public int getFontSize() {
        return this.f3133e;
    }

    public LatLng getPosition() {
        return this.f3130b;
    }

    public float getRotate() {
        return this.f3137i;
    }

    public String getText() {
        return this.f3129a;
    }

    public Typeface getTypeface() {
        return this.f3134f;
    }

    public void setAlign(int i, int i2) {
        this.f3135g = i;
        this.f3136h = i2;
        this.f3138j = 1;
        this.listener.mo2624b(this);
    }

    public void setBgColor(int i) {
        this.f3131c = i;
        this.f3138j = 1;
        this.listener.mo2624b(this);
    }

    public void setFontColor(int i) {
        this.f3132d = i;
        this.f3138j = 1;
        this.listener.mo2624b(this);
    }

    public void setFontSize(int i) {
        this.f3133e = i;
        this.f3138j = 1;
        this.listener.mo2624b(this);
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("position can not be null");
        }
        this.f3130b = latLng;
        this.f3138j = 1;
        this.listener.mo2624b(this);
    }

    public void setRotate(float f) {
        this.f3137i = f;
        this.f3138j = 1;
        this.listener.mo2624b(this);
    }

    public void setText(String str) {
        if (str == null || str.equals("")) {
            throw new IllegalArgumentException("text can not be null or empty");
        }
        this.f3129a = str;
        this.f3138j = 1;
        this.listener.mo2624b(this);
    }

    public void setTypeface(Typeface typeface) {
        this.f3134f = typeface;
        this.f3138j = 1;
        this.listener.mo2624b(this);
    }
}
