package com.github.mikephil.charting.p183g;

import com.github.mikephil.charting.p183g.C3280f.C3273a;

/* compiled from: MPPointD */
/* renamed from: com.github.mikephil.charting.g.d */
public class C3277d extends C3273a {
    /* renamed from: c */
    private static C3280f<C3277d> f14195c = C3280f.m15903a(64, new C3277d(0.0d, 0.0d));
    /* renamed from: a */
    public double f14196a;
    /* renamed from: b */
    public double f14197b;

    static {
        f14195c.m15908a(0.5f);
    }

    /* renamed from: a */
    public static C3277d m15892a(double d, double d2) {
        C3277d c3277d = (C3277d) f14195c.m15907a();
        c3277d.f14196a = d;
        c3277d.f14197b = d2;
        return c3277d;
    }

    /* renamed from: a */
    public static void m15893a(C3277d c3277d) {
        f14195c.m15909a((C3273a) c3277d);
    }

    /* renamed from: a */
    protected C3273a mo4018a() {
        return new C3277d(0.0d, 0.0d);
    }

    private C3277d(double d, double d2) {
        this.f14196a = d;
        this.f14197b = d2;
    }

    public String toString() {
        return "MPPointD, x: " + this.f14196a + ", y: " + this.f14197b;
    }
}
