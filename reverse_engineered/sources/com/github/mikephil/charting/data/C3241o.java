package com.github.mikephil.charting.data;

import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3223i;
import com.github.mikephil.charting.p181d.C3213d;

/* compiled from: PieData */
/* renamed from: com.github.mikephil.charting.data.o */
public class C3241o extends C3224j<C3223i> {
    /* renamed from: a */
    public /* synthetic */ C3220e mo3993a(int i) {
        return m15692c(i);
    }

    /* renamed from: a */
    public C3223i m15691a() {
        return (C3223i) this.i.get(0);
    }

    /* renamed from: c */
    public C3223i m15692c(int i) {
        return i == 0 ? m15691a() : null;
    }

    /* renamed from: a */
    public Entry mo3984a(C3213d c3213d) {
        return m15691a().mo3948g((int) c3213d.m15423a());
    }

    /* renamed from: m */
    public float m15693m() {
        float f = 0.0f;
        for (int i = 0; i < m15691a().mo3938F(); i++) {
            f += ((PieEntry) m15691a().mo3948g(i)).b();
        }
        return f;
    }
}
