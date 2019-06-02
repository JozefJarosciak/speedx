package com.github.mikephil.charting.p183g;

import com.github.mikephil.charting.p183g.C3280f.C3273a;

/* compiled from: FSize */
/* renamed from: com.github.mikephil.charting.g.b */
public final class C3274b extends C3273a {
    /* renamed from: c */
    private static C3280f<C3274b> f14175c = C3280f.m15903a(256, new C3274b(0.0f, 0.0f));
    /* renamed from: a */
    public float f14176a;
    /* renamed from: b */
    public float f14177b;

    static {
        f14175c.m15908a(0.5f);
    }

    /* renamed from: a */
    protected C3273a mo4018a() {
        return new C3274b(0.0f, 0.0f);
    }

    /* renamed from: a */
    public static C3274b m15844a(float f, float f2) {
        C3274b c3274b = (C3274b) f14175c.m15907a();
        c3274b.f14176a = f;
        c3274b.f14177b = f2;
        return c3274b;
    }

    /* renamed from: a */
    public static void m15845a(C3274b c3274b) {
        f14175c.m15909a((C3273a) c3274b);
    }

    public C3274b(float f, float f2) {
        this.f14176a = f;
        this.f14177b = f2;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3274b)) {
            return false;
        }
        C3274b c3274b = (C3274b) obj;
        if (!(this.f14176a == c3274b.f14176a && this.f14177b == c3274b.f14177b)) {
            z = false;
        }
        return z;
    }

    public String toString() {
        return this.f14176a + "x" + this.f14177b;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.f14176a) ^ Float.floatToIntBits(this.f14177b);
    }
}
