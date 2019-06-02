package com.github.mikephil.charting.data;

import android.annotation.SuppressLint;
import com.github.mikephil.charting.p181d.C3218j;

@SuppressLint({"ParcelCreator"})
public class BarEntry extends Entry {
    /* renamed from: a */
    private float[] f13985a;
    /* renamed from: b */
    private C3218j[] f13986b;
    /* renamed from: c */
    private float f13987c;
    /* renamed from: d */
    private float f13988d;

    public BarEntry(float f, float f2, Object obj) {
        super(f, f2, obj);
    }

    /* renamed from: a */
    public float[] m15451a() {
        return this.f13985a;
    }

    /* renamed from: b */
    public float mo3912b() {
        return super.mo3912b();
    }

    /* renamed from: c */
    public C3218j[] m15453c() {
        return this.f13986b;
    }

    /* renamed from: d */
    public boolean m15454d() {
        return this.f13985a != null;
    }

    /* renamed from: e */
    public float m15455e() {
        return this.f13988d;
    }

    /* renamed from: f */
    public float m15456f() {
        return this.f13987c;
    }
}
