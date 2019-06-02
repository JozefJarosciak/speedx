package com.github.mikephil.charting.data;

import android.graphics.Paint.Style;
import com.github.mikephil.charting.p089e.p090b.C3235d;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: CandleDataSet */
/* renamed from: com.github.mikephil.charting.data.i */
public class C3236i extends C3234n<CandleEntry> implements C3235d {
    /* renamed from: B */
    private float f14048B = 3.0f;
    /* renamed from: C */
    private boolean f14049C = true;
    /* renamed from: D */
    private float f14050D = 0.1f;
    /* renamed from: E */
    private boolean f14051E = false;
    /* renamed from: m */
    protected Style f14052m = Style.STROKE;
    /* renamed from: n */
    protected Style f14053n = Style.FILL;
    /* renamed from: o */
    protected int f14054o = 1122868;
    /* renamed from: p */
    protected int f14055p = 1122868;
    /* renamed from: q */
    protected int f14056q = 1122868;
    /* renamed from: r */
    protected int f14057r = 1122868;

    public C3236i(List<CandleEntry> list, String str) {
        super(list, str);
    }

    /* renamed from: a */
    protected void m15647a(CandleEntry candleEntry) {
        if (candleEntry.m15460c() < this.u) {
            this.u = candleEntry.m15460c();
        }
        if (candleEntry.m15458a() > this.t) {
            this.t = candleEntry.m15458a();
        }
        m15543c(candleEntry);
    }

    /* renamed from: b */
    protected void m15651b(CandleEntry candleEntry) {
        if (candleEntry.m15458a() < this.u) {
            this.u = candleEntry.m15458a();
        }
        if (candleEntry.m15458a() > this.t) {
            this.t = candleEntry.m15458a();
        }
        if (candleEntry.m15460c() < this.u) {
            this.u = candleEntry.m15460c();
        }
        if (candleEntry.m15460c() > this.t) {
            this.t = candleEntry.m15460c();
        }
    }

    /* renamed from: b */
    public void m15650b(float f) {
        float f2 = 0.45f;
        float f3 = 0.0f;
        if (f >= 0.0f) {
            f3 = f;
        }
        if (f3 <= 0.45f) {
            f2 = f3;
        }
        this.f14050D = f2;
    }

    /* renamed from: a */
    public float mo3976a() {
        return this.f14050D;
    }

    /* renamed from: c */
    public void m15653c(float f) {
        this.f14048B = C3283i.m15928a(f);
    }

    /* renamed from: b */
    public float mo3977b() {
        return this.f14048B;
    }

    /* renamed from: c */
    public void m15654c(boolean z) {
        this.f14049C = z;
    }

    /* renamed from: c */
    public boolean mo3979c() {
        return this.f14049C;
    }

    /* renamed from: d */
    public int mo3980d() {
        return this.f14054o;
    }

    /* renamed from: e */
    public int mo3981e() {
        return this.f14055p;
    }

    /* renamed from: f */
    public void m15659f(int i) {
        this.f14056q = i;
    }

    /* renamed from: f */
    public int mo3982f() {
        return this.f14056q;
    }

    /* renamed from: g */
    public Style mo3983g() {
        return this.f14052m;
    }

    /* renamed from: B */
    public Style mo3973B() {
        return this.f14053n;
    }

    /* renamed from: C */
    public int mo3974C() {
        return this.f14057r;
    }

    /* renamed from: D */
    public boolean mo3975D() {
        return this.f14051E;
    }
}
