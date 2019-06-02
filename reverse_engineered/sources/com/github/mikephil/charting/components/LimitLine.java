package com.github.mikephil.charting.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint.Style;
import com.github.mikephil.charting.p183g.C3283i;

public class LimitLine extends C3206b {
    /* renamed from: a */
    private float f13929a = 0.0f;
    /* renamed from: b */
    private float f13930b = 2.0f;
    /* renamed from: c */
    private int f13931c = Color.rgb(237, 91, 91);
    /* renamed from: d */
    private Style f13932d = Style.FILL_AND_STROKE;
    /* renamed from: e */
    private String f13933e = "";
    /* renamed from: f */
    private DashPathEffect f13934f = null;
    /* renamed from: g */
    private LimitLabelPosition f13935g = LimitLabelPosition.RIGHT_TOP;

    public enum LimitLabelPosition {
        LEFT_TOP,
        LEFT_BOTTOM,
        RIGHT_TOP,
        RIGHT_BOTTOM
    }

    public LimitLine(float f) {
        this.f13929a = f;
    }

    /* renamed from: a */
    public float m15370a() {
        return this.f13929a;
    }

    /* renamed from: a */
    public void m15371a(float f) {
        float f2 = 12.0f;
        float f3 = 0.2f;
        if (f >= 0.2f) {
            f3 = f;
        }
        if (f3 <= 12.0f) {
            f2 = f3;
        }
        this.f13930b = C3283i.m15928a(f2);
    }

    /* renamed from: b */
    public float m15375b() {
        return this.f13930b;
    }

    /* renamed from: a */
    public void m15373a(int i) {
        this.f13931c = i;
    }

    /* renamed from: c */
    public int m15376c() {
        return this.f13931c;
    }

    /* renamed from: a */
    public void m15372a(float f, float f2, float f3) {
        this.f13934f = new DashPathEffect(new float[]{f, f2}, f3);
    }

    /* renamed from: d */
    public DashPathEffect m15377d() {
        return this.f13934f;
    }

    /* renamed from: e */
    public Style m15378e() {
        return this.f13932d;
    }

    /* renamed from: a */
    public void m15374a(LimitLabelPosition limitLabelPosition) {
        this.f13935g = limitLabelPosition;
    }

    /* renamed from: f */
    public LimitLabelPosition m15379f() {
        return this.f13935g;
    }

    /* renamed from: g */
    public String m15380g() {
        return this.f13933e;
    }
}
