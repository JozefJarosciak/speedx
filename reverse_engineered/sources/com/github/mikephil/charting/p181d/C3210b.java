package com.github.mikephil.charting.p181d;

import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.C3225c;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p128a.C2008b;
import com.github.mikephil.charting.p183g.C3277d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ChartHighlighter */
/* renamed from: com.github.mikephil.charting.d.b */
public class C3210b<T extends C2008b> implements C3209f {
    /* renamed from: a */
    protected T f13964a;
    /* renamed from: b */
    protected List<C3213d> f13965b = new ArrayList();

    public C3210b(T t) {
        this.f13964a = t;
    }

    /* renamed from: a */
    public C3213d mo3906a(float f, float f2) {
        C3277d b = m15415b(f, f2);
        float f3 = (float) b.f14196a;
        C3277d.m15893a(b);
        return m15411a(f3, f, f2);
    }

    /* renamed from: b */
    protected C3277d m15415b(float f, float f2) {
        return this.f13964a.mo3343a(AxisDependency.LEFT).m15911a(f, f2);
    }

    /* renamed from: a */
    protected C3213d m15411a(float f, float f2, float f3) {
        List b = mo3909b(f, f2, f3);
        if (b.isEmpty()) {
            return null;
        }
        return m15412a(b, f2, f3, m15409a(b, f3, AxisDependency.LEFT) < m15409a(b, f3, AxisDependency.RIGHT) ? AxisDependency.LEFT : AxisDependency.RIGHT, this.f13964a.getMaxHighlightDistance());
    }

    /* renamed from: a */
    protected float m15409a(List<C3213d> list, float f, AxisDependency axisDependency) {
        float f2 = Float.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            C3213d c3213d = (C3213d) list.get(i);
            if (c3213d.m15433h() == axisDependency) {
                float abs = Math.abs(m15408a(c3213d) - f);
                if (abs < f2) {
                    f2 = abs;
                }
            }
        }
        return f2;
    }

    /* renamed from: a */
    protected float m15408a(C3213d c3213d) {
        return c3213d.m15429d();
    }

    /* renamed from: b */
    protected List<C3213d> mo3909b(float f, float f2, float f3) {
        this.f13965b.clear();
        C3225c a = mo3908a();
        if (a == null) {
            return this.f13965b;
        }
        int d = a.m15590d();
        for (int i = 0; i < d; i++) {
            C3220e a2 = a.mo3993a(i);
            if (a2.mo3924n()) {
                this.f13965b.addAll(mo3910a(a2, i, f, Rounding.CLOSEST));
            }
        }
        return this.f13965b;
    }

    /* renamed from: a */
    protected List<C3213d> mo3910a(C3220e c3220e, int i, float f, Rounding rounding) {
        ArrayList arrayList = new ArrayList();
        List d = c3220e.mo3947d(f);
        if (d.size() == 0) {
            Entry a = c3220e.mo3943a(f, Float.NaN, rounding);
            if (a != null) {
                d = c3220e.mo3947d(a.m15450i());
            }
        }
        if (r2.size() == 0) {
            return arrayList;
        }
        for (Entry entry : r2) {
            C3277d b = this.f13964a.mo3343a(c3220e.mo3913A()).m15923b(entry.m15450i(), entry.mo3912b());
            arrayList.add(new C3213d(entry.m15450i(), entry.mo3912b(), (float) b.f14196a, (float) b.f14197b, i, c3220e.mo3913A()));
        }
        return arrayList;
    }

    /* renamed from: a */
    public C3213d m15412a(List<C3213d> list, float f, float f2, AxisDependency axisDependency, float f3) {
        C3213d c3213d = null;
        int i = 0;
        float f4 = f3;
        while (i < list.size()) {
            C3213d c3213d2;
            float f5;
            C3213d c3213d3 = (C3213d) list.get(i);
            if (axisDependency == null || c3213d3.m15433h() == axisDependency) {
                float a = mo3907a(f, f2, c3213d3.m15428c(), c3213d3.m15429d());
                if (a < f4) {
                    c3213d2 = c3213d3;
                    f5 = a;
                    i++;
                    c3213d = c3213d2;
                    f4 = f5;
                }
            }
            f5 = f4;
            c3213d2 = c3213d;
            i++;
            c3213d = c3213d2;
            f4 = f5;
        }
        return c3213d;
    }

    /* renamed from: a */
    protected float mo3907a(float f, float f2, float f3, float f4) {
        return (float) Math.hypot((double) (f - f3), (double) (f2 - f4));
    }

    /* renamed from: a */
    protected C3225c mo3908a() {
        return this.f13964a.getData();
    }
}
