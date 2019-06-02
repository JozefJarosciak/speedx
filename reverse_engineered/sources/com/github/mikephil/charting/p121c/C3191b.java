package com.github.mikephil.charting.p121c;

import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.p089e.p090b.C1477f;
import com.github.mikephil.charting.p089e.p128a.C3196g;

/* compiled from: DefaultFillFormatter */
/* renamed from: com.github.mikephil.charting.c.b */
public class C3191b implements C3190e {
    /* renamed from: a */
    public float mo3885a(C1477f c1477f, C3196g c3196g) {
        float yChartMax = c3196g.getYChartMax();
        float yChartMin = c3196g.getYChartMin();
        C3238l lineData = c3196g.getLineData();
        if (c1477f.I() > 0.0f && c1477f.H() < 0.0f) {
            return 0.0f;
        }
        if (lineData.m15592f() > 0.0f) {
            yChartMax = 0.0f;
        }
        if (lineData.m15591e() < 0.0f) {
            yChartMin = 0.0f;
        }
        if (c1477f.H() < 0.0f) {
            yChartMin = yChartMax;
        }
        return yChartMin;
    }
}
