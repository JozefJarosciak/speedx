package com.github.mikephil.charting.p181d;

import com.github.mikephil.charting.charts.C3204f;
import com.github.mikephil.charting.data.C3242p;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: RadarHighlighter */
/* renamed from: com.github.mikephil.charting.d.i */
public class C3217i extends C3215h<C3204f> {
    public C3217i(C3204f c3204f) {
        super(c3204f);
    }

    /* renamed from: a */
    protected C3213d mo3911a(int i, float f, float f2) {
        List a = m15443a(i);
        float d = ((C3204f) this.a).m15328d(f, f2) / ((C3204f) this.a).getFactor();
        C3213d c3213d = null;
        float f3 = Float.MAX_VALUE;
        int i2 = 0;
        while (i2 < a.size()) {
            C3213d c3213d2;
            float f4;
            C3213d c3213d3 = (C3213d) a.get(i2);
            float abs = Math.abs(c3213d3.m15427b() - d);
            if (abs < f3) {
                float f5 = abs;
                c3213d2 = c3213d3;
                f4 = f5;
            } else {
                f4 = f3;
                c3213d2 = c3213d;
            }
            i2++;
            c3213d = c3213d2;
            f3 = f4;
        }
        return c3213d;
    }

    /* renamed from: a */
    protected List<C3213d> m15443a(int i) {
        this.b.clear();
        float b = ((C3204f) this.a).getAnimator().m15293b();
        float a = ((C3204f) this.a).getAnimator().m15292a();
        float sliceAngle = ((C3204f) this.a).getSliceAngle();
        float factor = ((C3204f) this.a).getFactor();
        C3279e a2 = C3279e.m15897a(0.0f, 0.0f);
        for (int i2 = 0; i2 < ((C3242p) ((C3204f) this.a).getData()).m15590d(); i2++) {
            C3220e a3 = ((C3242p) ((C3204f) this.a).getData()).mo3993a(i2);
            Entry g = a3.mo3948g(i);
            float b2 = g.mo3912b() - ((C3204f) this.a).getYChartMin();
            C3283i.m15940a(((C3204f) this.a).getCenterOffsets(), (b2 * factor) * a, ((C3204f) this.a).getRotationAngle() + ((((float) i) * sliceAngle) * b), a2);
            this.b.add(new C3213d((float) i, g.mo3912b(), a2.f14200a, a2.f14201b, i2, a3.mo3913A()));
        }
        return this.b;
    }
}
