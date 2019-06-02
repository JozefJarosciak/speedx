package com.github.mikephil.charting.data;

import com.github.mikephil.charting.p089e.p090b.C3223i;

public class PieDataSet extends DataSet<PieEntry> implements C3223i {
    /* renamed from: A */
    private boolean f14015A;
    /* renamed from: a */
    private float f14016a;
    /* renamed from: m */
    private boolean f14017m;
    /* renamed from: n */
    private float f14018n;
    /* renamed from: o */
    private ValuePosition f14019o;
    /* renamed from: p */
    private ValuePosition f14020p;
    /* renamed from: q */
    private int f14021q;
    /* renamed from: r */
    private float f14022r;
    /* renamed from: x */
    private float f14023x;
    /* renamed from: y */
    private float f14024y;
    /* renamed from: z */
    private float f14025z;

    public enum ValuePosition {
        INSIDE_SLICE,
        OUTSIDE_SLICE
    }

    /* renamed from: a */
    protected void m15565a(PieEntry pieEntry) {
        if (pieEntry != null) {
            mo3978b((Entry) pieEntry);
        }
    }

    /* renamed from: a */
    public float mo3952a() {
        return this.f14016a;
    }

    /* renamed from: b */
    public boolean mo3954b() {
        return this.f14017m;
    }

    /* renamed from: c */
    public float mo3955c() {
        return this.f14018n;
    }

    /* renamed from: d */
    public ValuePosition mo3956d() {
        return this.f14019o;
    }

    /* renamed from: e */
    public ValuePosition mo3957e() {
        return this.f14020p;
    }

    /* renamed from: f */
    public int mo3958f() {
        return this.f14021q;
    }

    /* renamed from: g */
    public float mo3959g() {
        return this.f14022r;
    }

    /* renamed from: h */
    public float mo3960h() {
        return this.f14023x;
    }

    /* renamed from: B */
    public float mo3949B() {
        return this.f14024y;
    }

    /* renamed from: C */
    public float mo3950C() {
        return this.f14025z;
    }

    /* renamed from: D */
    public boolean mo3951D() {
        return this.f14015A;
    }
}
