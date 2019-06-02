package com.github.mikephil.charting.p181d;

import com.github.mikephil.charting.components.YAxis.AxisDependency;

/* compiled from: Highlight */
/* renamed from: com.github.mikephil.charting.d.d */
public class C3213d {
    /* renamed from: a */
    private float f13967a;
    /* renamed from: b */
    private float f13968b;
    /* renamed from: c */
    private float f13969c;
    /* renamed from: d */
    private float f13970d;
    /* renamed from: e */
    private int f13971e;
    /* renamed from: f */
    private int f13972f;
    /* renamed from: g */
    private int f13973g;
    /* renamed from: h */
    private AxisDependency f13974h;
    /* renamed from: i */
    private float f13975i;
    /* renamed from: j */
    private float f13976j;

    public C3213d(float f, float f2, int i) {
        this.f13967a = Float.NaN;
        this.f13968b = Float.NaN;
        this.f13971e = -1;
        this.f13973g = -1;
        this.f13967a = f;
        this.f13968b = f2;
        this.f13972f = i;
    }

    public C3213d(float f, float f2, float f3, float f4, int i, AxisDependency axisDependency) {
        this.f13967a = Float.NaN;
        this.f13968b = Float.NaN;
        this.f13971e = -1;
        this.f13973g = -1;
        this.f13967a = f;
        this.f13968b = f2;
        this.f13969c = f3;
        this.f13970d = f4;
        this.f13972f = i;
        this.f13974h = axisDependency;
    }

    public C3213d(float f, float f2, float f3, float f4, int i, int i2, AxisDependency axisDependency) {
        this(f, f2, f3, f4, i, axisDependency);
        this.f13973g = i2;
    }

    /* renamed from: a */
    public float m15423a() {
        return this.f13967a;
    }

    /* renamed from: b */
    public float m15427b() {
        return this.f13968b;
    }

    /* renamed from: c */
    public float m15428c() {
        return this.f13969c;
    }

    /* renamed from: d */
    public float m15429d() {
        return this.f13970d;
    }

    /* renamed from: e */
    public int m15430e() {
        return this.f13971e;
    }

    /* renamed from: a */
    public void m15425a(int i) {
        this.f13971e = i;
    }

    /* renamed from: f */
    public int m15431f() {
        return this.f13972f;
    }

    /* renamed from: g */
    public int m15432g() {
        return this.f13973g;
    }

    /* renamed from: h */
    public AxisDependency m15433h() {
        return this.f13974h;
    }

    /* renamed from: a */
    public void m15424a(float f, float f2) {
        this.f13975i = f;
        this.f13976j = f2;
    }

    /* renamed from: i */
    public float m15434i() {
        return this.f13975i;
    }

    /* renamed from: j */
    public float m15435j() {
        return this.f13976j;
    }

    /* renamed from: a */
    public boolean m15426a(C3213d c3213d) {
        if (c3213d != null && this.f13972f == c3213d.f13972f && this.f13967a == c3213d.f13967a && this.f13973g == c3213d.f13973g && this.f13971e == c3213d.f13971e) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Highlight, x: " + this.f13967a + ", y: " + this.f13968b + ", dataSetIndex: " + this.f13972f + ", stackIndex (only stacked barentry): " + this.f13973g;
    }
}
