package com.mapzen.p091a.p092a.p093a;

/* compiled from: LocationRequest */
/* renamed from: com.mapzen.a.a.a.d */
public final class C4209d {
    /* renamed from: a */
    private long f14842a = 3600000;
    /* renamed from: b */
    private long f14843b = 600000;
    /* renamed from: c */
    private float f14844c = 0.0f;
    /* renamed from: d */
    private int f14845d = 102;

    private C4209d() {
    }

    /* renamed from: a */
    public static C4209d m16710a() {
        return new C4209d();
    }

    /* renamed from: b */
    public long m16714b() {
        return this.f14843b;
    }

    /* renamed from: a */
    public C4209d m16713a(long j) {
        this.f14843b = j;
        return this;
    }

    /* renamed from: c */
    public float m16715c() {
        return this.f14844c;
    }

    /* renamed from: a */
    public C4209d m16711a(float f) {
        this.f14844c = f;
        return this;
    }

    /* renamed from: d */
    public int m16716d() {
        return this.f14845d;
    }

    /* renamed from: a */
    public C4209d m16712a(int i) {
        if (i == 100 || i == 102 || i == 104 || i == 105) {
            this.f14845d = i;
            return this;
        }
        throw new IllegalArgumentException("Invalid priority: " + i);
    }
}
