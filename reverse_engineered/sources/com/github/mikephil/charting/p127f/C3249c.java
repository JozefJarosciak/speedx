package com.github.mikephil.charting.p127f;

import com.github.mikephil.charting.data.DataSet.Rounding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3227b;
import com.github.mikephil.charting.p089e.p128a.C2008b;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p183g.C3275j;

/* compiled from: BarLineScatterCandleBubbleRenderer */
/* renamed from: com.github.mikephil.charting.f.c */
public abstract class C3249c extends C3248g {
    /* renamed from: f */
    protected C3251a f14073f = new C3251a(this);

    /* compiled from: BarLineScatterCandleBubbleRenderer */
    /* renamed from: com.github.mikephil.charting.f.c$a */
    protected class C3251a {
        /* renamed from: a */
        public int f14080a;
        /* renamed from: b */
        public int f14081b;
        /* renamed from: c */
        public int f14082c;
        /* renamed from: d */
        final /* synthetic */ C3249c f14083d;

        protected C3251a(C3249c c3249c) {
            this.f14083d = c3249c;
        }

        /* renamed from: a */
        public void m15726a(C2008b c2008b, C3227b c3227b) {
            int i = 0;
            float max = Math.max(0.0f, Math.min(1.0f, this.f14083d.g.m15293b()));
            float lowestVisibleX = c2008b.getLowestVisibleX();
            float highestVisibleX = c2008b.getHighestVisibleX();
            Entry a = c3227b.mo3943a(lowestVisibleX, Float.NaN, Rounding.DOWN);
            Entry a2 = c3227b.mo3943a(highestVisibleX, Float.NaN, Rounding.UP);
            this.f14080a = a == null ? 0 : c3227b.mo3946d(a);
            if (a2 != null) {
                i = c3227b.mo3946d(a2);
            }
            this.f14081b = i;
            this.f14082c = (int) (((float) (this.f14081b - this.f14080a)) * max);
        }
    }

    public C3249c(C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
    }

    /* renamed from: a */
    protected boolean m15717a(C3220e c3220e) {
        return c3220e.mo3936z() && (c3220e.mo3933w() || c3220e.mo3934x());
    }

    /* renamed from: a */
    protected boolean m15716a(Entry entry, C3227b c3227b) {
        if (entry == null) {
            return false;
        }
        float d = (float) c3227b.mo3946d(entry);
        if (entry == null || d >= ((float) c3227b.mo3938F()) * this.g.m15293b()) {
            return false;
        }
        return true;
    }
}
