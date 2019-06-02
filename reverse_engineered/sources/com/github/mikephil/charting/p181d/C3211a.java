package com.github.mikephil.charting.p181d;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.C3225c;
import com.github.mikephil.charting.p089e.p090b.C3229a;
import com.github.mikephil.charting.p089e.p128a.C2010a;
import com.github.mikephil.charting.p183g.C3277d;

/* compiled from: BarHighlighter */
/* renamed from: com.github.mikephil.charting.d.a */
public class C3211a extends C3210b<C2010a> {
    public C3211a(C2010a c2010a) {
        super(c2010a);
    }

    /* renamed from: a */
    public C3213d mo3906a(float f, float f2) {
        C3213d a = super.mo3906a(f, f2);
        if (a == null) {
            return null;
        }
        C3277d b = m15415b(f, f2);
        C3229a c3229a = (C3229a) ((C2010a) this.a).getBarData().mo3993a(a.m15431f());
        if (c3229a.mo3963b()) {
            return m15420a(a, c3229a, (float) b.f14196a, (float) b.f14197b);
        }
        C3277d.m15893a(b);
        return a;
    }

    /* renamed from: a */
    public C3213d m15420a(C3213d c3213d, C3229a c3229a, float f, float f2) {
        BarEntry barEntry = (BarEntry) c3229a.mo3945b(f, f2);
        if (barEntry == null) {
            return null;
        }
        if (barEntry.m15451a() == null) {
            return c3213d;
        }
        C3218j[] c = barEntry.m15453c();
        if (c.length <= 0) {
            return null;
        }
        int a = m15418a(c, f2);
        C3277d b = ((C2010a) this.a).mo3343a(c3229a.mo3913A()).m15923b(c3213d.m15423a(), c[a].f13980b);
        C3213d c3213d2 = new C3213d(barEntry.m15450i(), barEntry.mo3912b(), (float) b.f14196a, (float) b.f14197b, c3213d.m15431f(), a, c3213d.m15433h());
        C3277d.m15893a(b);
        return c3213d2;
    }

    /* renamed from: a */
    protected int m15418a(C3218j[] c3218jArr, float f) {
        if (c3218jArr == null || c3218jArr.length == 0) {
            return 0;
        }
        int length = c3218jArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            if (c3218jArr[i].m15444a(f)) {
                return i2;
            }
            i++;
            i2++;
        }
        i2 = Math.max(c3218jArr.length - 1, 0);
        return f <= c3218jArr[i2].f13980b ? 0 : i2;
    }

    /* renamed from: a */
    protected float mo3907a(float f, float f2, float f3, float f4) {
        return Math.abs(f - f3);
    }

    /* renamed from: a */
    protected C3225c mo3908a() {
        return ((C2010a) this.a).getBarData();
    }
}
