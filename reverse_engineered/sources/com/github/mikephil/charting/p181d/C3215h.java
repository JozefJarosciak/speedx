package com.github.mikephil.charting.p181d;

import com.github.mikephil.charting.charts.C3203e;
import com.github.mikephil.charting.charts.PieChart;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PieRadarHighlighter */
/* renamed from: com.github.mikephil.charting.d.h */
public abstract class C3215h<T extends C3203e> implements C3209f {
    /* renamed from: a */
    protected T f13977a;
    /* renamed from: b */
    protected List<C3213d> f13978b = new ArrayList();

    /* renamed from: a */
    protected abstract C3213d mo3911a(int i, float f, float f2);

    public C3215h(T t) {
        this.f13977a = t;
    }

    /* renamed from: a */
    public C3213d mo3906a(float f, float f2) {
        if (this.f13977a.m15328d(f, f2) > this.f13977a.getRadius()) {
            return null;
        }
        float b = this.f13977a.m15326b(f, f2);
        if (this.f13977a instanceof PieChart) {
            b /= this.f13977a.getAnimator().m15292a();
        }
        int a = this.f13977a.mo3897a(b);
        if (a < 0 || a >= this.f13977a.getData().m15598l().mo3938F()) {
            return null;
        }
        return mo3911a(a, f, f2);
    }
}
