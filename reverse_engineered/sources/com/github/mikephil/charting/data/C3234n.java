package com.github.mikephil.charting.data;

import android.graphics.DashPathEffect;
import com.github.mikephil.charting.p089e.p090b.C3233h;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: LineScatterCandleRadarDataSet */
/* renamed from: com.github.mikephil.charting.data.n */
public abstract class C3234n<T extends Entry> extends C3228d<T> implements C3233h<T> {
    /* renamed from: A */
    protected DashPathEffect f14044A;
    /* renamed from: x */
    protected boolean f14045x;
    /* renamed from: y */
    protected boolean f14046y;
    /* renamed from: z */
    protected float f14047z;

    public C3234n(List<T> list, String str) {
        super(list, str);
        this.f14045x = true;
        this.f14046y = true;
        this.f14047z = 0.5f;
        this.f14044A = null;
        this.f14047z = C3283i.m15928a(0.5f);
    }

    /* renamed from: f */
    public void m15631f(boolean z) {
        this.f14046y = z;
    }

    /* renamed from: g */
    public void m15632g(boolean z) {
        this.f14045x = z;
    }

    /* renamed from: T */
    public boolean mo3969T() {
        return this.f14045x;
    }

    /* renamed from: U */
    public boolean mo3970U() {
        return this.f14046y;
    }

    /* renamed from: f */
    public void m15630f(float f) {
        this.f14047z = C3283i.m15928a(f);
    }

    /* renamed from: V */
    public float mo3971V() {
        return this.f14047z;
    }

    /* renamed from: W */
    public DashPathEffect mo3972W() {
        return this.f14044A;
    }
}
