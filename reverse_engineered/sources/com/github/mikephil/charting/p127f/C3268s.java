package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import com.github.mikephil.charting.charts.C3204f;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.C3242p;
import com.github.mikephil.charting.p089e.p090b.C3245j;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: XAxisRendererRadarChart */
/* renamed from: com.github.mikephil.charting.f.s */
public class C3268s extends C2005q {
    /* renamed from: a */
    private C3204f f14151a;

    public C3268s(C3275j c3275j, XAxis xAxis, C3204f c3204f) {
        super(c3275j, xAxis, null);
        this.f14151a = c3204f;
    }

    /* renamed from: a */
    public void mo4004a(Canvas canvas) {
        if (this.h.A() && this.h.h()) {
            float C = this.h.m15382C();
            C3279e a = C3279e.m15897a(0.5f, 0.25f);
            this.e.setTypeface(this.h.x());
            this.e.setTextSize(this.h.y());
            this.e.setColor(this.h.z());
            float sliceAngle = this.f14151a.getSliceAngle();
            float factor = this.f14151a.getFactor();
            C3279e centerOffsets = this.f14151a.getCenterOffsets();
            C3279e a2 = C3279e.m15897a(0.0f, 0.0f);
            for (int i = 0; i < ((C3245j) ((C3242p) this.f14151a.getData()).m15598l()).mo3938F(); i++) {
                String a3 = this.h.q().mo3334a((float) i, this.h);
                C3283i.m15940a(centerOffsets, (this.f14151a.getYRange() * factor) + (((float) this.h.f13938D) / 2.0f), ((((float) i) * sliceAngle) + this.f14151a.getRotationAngle()) % 360.0f, a2);
                m10343a(canvas, a3, a2.f14200a, a2.f14201b - (((float) this.h.f13939E) / 2.0f), a, C);
            }
            C3279e.m15900b(centerOffsets);
            C3279e.m15900b(a2);
            C3279e.m15900b(a);
        }
    }

    /* renamed from: d */
    public void mo4009d(Canvas canvas) {
    }
}
