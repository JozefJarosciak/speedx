package com.github.mikephil.charting.p181d;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.C3241o;
import com.github.mikephil.charting.p089e.p090b.C3223i;

/* compiled from: PieHighlighter */
/* renamed from: com.github.mikephil.charting.d.g */
public class C3216g extends C3215h<PieChart> {
    public C3216g(PieChart pieChart) {
        super(pieChart);
    }

    /* renamed from: a */
    protected C3213d mo3911a(int i, float f, float f2) {
        C3223i a = ((C3241o) ((PieChart) this.a).getData()).m15691a();
        return new C3213d((float) i, a.mo3948g(i).mo3912b(), f, f2, 0, a.mo3913A());
    }
}
