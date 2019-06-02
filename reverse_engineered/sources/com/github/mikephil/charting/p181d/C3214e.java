package com.github.mikephil.charting.p181d;

import com.github.mikephil.charting.data.C3226a;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3229a;
import com.github.mikephil.charting.p089e.p128a.C2010a;
import com.github.mikephil.charting.p183g.C3277d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: HorizontalBarHighlighter */
/* renamed from: com.github.mikephil.charting.d.e */
public class C3214e extends C3211a {
    public C3214e(C2010a c2010a) {
        super(c2010a);
    }

    /* renamed from: a */
    public C3213d mo3906a(float f, float f2) {
        C3226a barData = ((C2010a) this.a).getBarData();
        C3277d b = m15415b(f2, f);
        C3213d a = m15411a((float) b.f14197b, f2, f);
        if (a == null) {
            return null;
        }
        C3229a c3229a = (C3229a) barData.mo3993a(a.m15431f());
        if (c3229a.mo3963b()) {
            return m15420a(a, c3229a, (float) b.f14197b, (float) b.f14196a);
        }
        C3277d.m15893a(b);
        return a;
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
            C3277d b = ((C2010a) this.a).mo3343a(c3220e.mo3913A()).m15923b(entry.mo3912b(), entry.m15450i());
            arrayList.add(new C3213d(entry.m15450i(), entry.mo3912b(), (float) b.f14196a, (float) b.f14197b, i, c3220e.mo3913A()));
        }
        return arrayList;
    }

    /* renamed from: a */
    protected float mo3907a(float f, float f2, float f3, float f4) {
        return Math.abs(f2 - f4);
    }
}
