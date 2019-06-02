package com.github.mikephil.charting.p180b;

/* compiled from: AbstractBuffer */
/* renamed from: com.github.mikephil.charting.b.a */
public abstract class C3186a<T> {
    /* renamed from: a */
    protected int f13854a;
    /* renamed from: b */
    public final float[] f13855b;
    /* renamed from: c */
    protected float f13856c;
    /* renamed from: d */
    protected float f13857d;
    /* renamed from: e */
    protected int f13858e;
    /* renamed from: f */
    protected int f13859f;

    public C3186a(int i) {
        this.f13854a = 0;
        this.f13856c = 1.0f;
        this.f13857d = 1.0f;
        this.f13858e = 0;
        this.f13859f = 0;
        this.f13854a = 0;
        this.f13855b = new float[i];
    }

    /* renamed from: a */
    public void m15294a() {
        this.f13854a = 0;
    }

    /* renamed from: b */
    public int m15296b() {
        return this.f13855b.length;
    }

    /* renamed from: a */
    public void m15295a(float f, float f2) {
        this.f13856c = f;
        this.f13857d = f2;
    }
}
