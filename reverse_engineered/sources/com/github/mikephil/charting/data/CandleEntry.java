package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;

@SuppressLint({"ParcelCreator"})
public class CandleEntry extends Entry {
    /* renamed from: a */
    private float f13990a = 0.0f;
    /* renamed from: b */
    private float f13991b = 0.0f;
    /* renamed from: c */
    private float f13992c = 0.0f;
    /* renamed from: d */
    private float f13993d = 0.0f;

    public CandleEntry(float f, float f2, float f3, float f4, float f5) {
        super(f, (f2 + f3) / 2.0f);
        this.f13990a = f2;
        this.f13991b = f3;
        this.f13993d = f4;
        this.f13992c = f5;
    }

    /* renamed from: b */
    public float mo3912b() {
        return super.mo3912b();
    }

    /* renamed from: a */
    public float m15458a() {
        return this.f13990a;
    }

    /* renamed from: c */
    public float m15460c() {
        return this.f13991b;
    }

    /* renamed from: d */
    public float m15461d() {
        return this.f13992c;
    }

    /* renamed from: e */
    public float m15462e() {
        return this.f13993d;
    }
}
