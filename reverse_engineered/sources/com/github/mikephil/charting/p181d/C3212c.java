package com.github.mikephil.charting.p181d;

import com.github.mikephil.charting.data.C3224j;
import com.github.mikephil.charting.data.C3225c;
import com.github.mikephil.charting.data.C3226a;
import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p128a.C2010a;
import com.github.mikephil.charting.p089e.p128a.C3198f;
import java.util.List;

/* compiled from: CombinedHighlighter */
/* renamed from: com.github.mikephil.charting.d.c */
public class C3212c extends C3210b<C3198f> implements C3209f {
    /* renamed from: c */
    protected C3211a f13966c;

    public C3212c(C3198f c3198f, C2010a c2010a) {
        super(c3198f);
        this.f13966c = c2010a.getBarData() == null ? null : new C3211a(c2010a);
    }

    /* renamed from: b */
    protected List<C3213d> mo3909b(float f, float f2, float f3) {
        this.b.clear();
        List q = ((C3198f) this.a).getCombinedData().m15673q();
        for (int i = 0; i < q.size(); i++) {
            C3224j c3224j = (C3224j) q.get(i);
            C3213d a;
            if (this.f13966c == null || !(c3224j instanceof C3226a)) {
                int d = c3224j.m15590d();
                for (int i2 = 0; i2 < d; i2++) {
                    C3220e a2 = ((C3225c) q.get(i)).mo3993a(i2);
                    if (a2.mo3924n()) {
                        for (C3213d a3 : mo3910a(a2, i2, f, Rounding.CLOSEST)) {
                            a3.m15425a(i);
                            this.b.add(a3);
                        }
                    }
                }
            } else {
                a3 = this.f13966c.mo3906a(f2, f3);
                if (a3 != null) {
                    a3.m15425a(i);
                    this.b.add(a3);
                }
            }
        }
        return this.b;
    }
}
