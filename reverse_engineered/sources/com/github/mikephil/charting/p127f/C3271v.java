package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.charts.C3204f;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.C3242p;
import com.github.mikephil.charting.p089e.p090b.C3245j;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: YAxisRendererRadarChart */
/* renamed from: com.github.mikephil.charting.f.v */
public class C3271v extends C3269t {
    /* renamed from: r */
    private C3204f f14165r;
    /* renamed from: s */
    private Path f14166s = new Path();

    public C3271v(C3275j c3275j, YAxis yAxis, C3204f c3204f) {
        super(c3275j, yAxis, null);
        this.f14165r = c3204f;
    }

    /* renamed from: a */
    protected void mo3339a(float f, float f2) {
        int j = this.b.j();
        double abs = (double) Math.abs(f2 - f);
        if (j == 0 || abs <= 0.0d || Double.isInfinite(abs)) {
            this.b.f6976b = new float[0];
            this.b.f6977c = new float[0];
            this.b.f6978d = 0;
            return;
        }
        double a = (double) C3283i.m15927a(abs / ((double) j));
        if (this.b.k() && a < ((double) this.b.l())) {
            a = (double) this.b.l();
        }
        double a2 = (double) C3283i.m15927a(Math.pow(10.0d, (double) ((int) Math.log10(a))));
        if (((int) (a / a2)) > 5) {
            a = Math.floor(10.0d * a2);
        }
        boolean c = this.b.c();
        int i = c ? 1 : 0;
        if (this.b.i()) {
            float f3 = ((float) abs) / ((float) (j - 1));
            this.b.f6978d = j;
            if (this.b.f6976b.length < j) {
                this.b.f6976b = new float[j];
            }
            for (i = 0; i < j; i++) {
                this.b.f6976b[i] = f;
                f += f3;
            }
            i = j;
        } else {
            double d;
            a2 = a == 0.0d ? 0.0d : Math.ceil(((double) f) / a) * a;
            if (c) {
                a2 -= a;
            }
            if (a == 0.0d) {
                d = 0.0d;
            } else {
                d = C3283i.m15941b(Math.floor(((double) f2) / a) * a);
            }
            if (a != 0.0d) {
                abs = a2;
                while (abs <= d) {
                    abs += a;
                    i++;
                }
            }
            int i2 = i + 1;
            this.b.f6978d = i2;
            if (this.b.f6976b.length < i2) {
                this.b.f6976b = new float[i2];
            }
            int i3 = 0;
            double d2 = a2;
            while (i3 < i2) {
                if (d2 == 0.0d) {
                    d2 = 0.0d;
                }
                this.b.f6976b[i3] = (float) d2;
                i3++;
                d2 += a;
            }
            i = i2;
        }
        if (a < 1.0d) {
            this.b.f6979e = (int) Math.ceil(-Math.log10(a));
        } else {
            this.b.f6979e = 0;
        }
        if (c) {
            if (this.b.f6977c.length < i) {
                this.b.f6977c = new float[i];
            }
            float f4 = (this.b.f6976b[1] - this.b.f6976b[0]) / 2.0f;
            for (int i4 = 0; i4 < i; i4++) {
                this.b.f6977c[i4] = this.b.f6976b[i4] + f4;
            }
        }
        this.b.f6994t = this.b.f6976b[0];
        this.b.f6993s = this.b.f6976b[i - 1];
        this.b.f6995u = Math.abs(this.b.f6993s - this.b.f6994t);
    }

    /* renamed from: a */
    public void mo4011a(Canvas canvas) {
        if (this.a.A() && this.a.h()) {
            int i;
            this.e.setTypeface(this.a.x());
            this.e.setTextSize(this.a.y());
            this.e.setColor(this.a.z());
            C3279e centerOffsets = this.f14165r.getCenterOffsets();
            C3279e a = C3279e.m15897a(0.0f, 0.0f);
            float factor = this.f14165r.getFactor();
            int i2 = this.a.m15390G() ? 0 : 1;
            if (this.a.m15389F()) {
                i = this.a.d;
            } else {
                i = this.a.d - 1;
            }
            while (i2 < i) {
                C3283i.m15940a(centerOffsets, (this.a.b[i2] - this.a.t) * factor, this.f14165r.getRotationAngle(), a);
                canvas.drawText(this.a.d(i2), a.f14200a + 10.0f, a.f14201b, this.e);
                i2++;
            }
            C3279e.m15900b(centerOffsets);
            C3279e.m15900b(a);
        }
    }

    /* renamed from: e */
    public void mo4017e(Canvas canvas) {
        List n = this.a.n();
        if (n != null) {
            float sliceAngle = this.f14165r.getSliceAngle();
            float factor = this.f14165r.getFactor();
            C3279e centerOffsets = this.f14165r.getCenterOffsets();
            C3279e a = C3279e.m15897a(0.0f, 0.0f);
            for (int i = 0; i < n.size(); i++) {
                LimitLine limitLine = (LimitLine) n.get(i);
                if (limitLine.m15336A()) {
                    this.g.setColor(limitLine.m15376c());
                    this.g.setPathEffect(limitLine.m15377d());
                    this.g.setStrokeWidth(limitLine.m15375b());
                    float a2 = (limitLine.m15370a() - this.f14165r.getYChartMin()) * factor;
                    Path path = this.f14166s;
                    path.reset();
                    for (int i2 = 0; i2 < ((C3245j) ((C3242p) this.f14165r.getData()).m15598l()).mo3938F(); i2++) {
                        C3283i.m15940a(centerOffsets, a2, (((float) i2) * sliceAngle) + this.f14165r.getRotationAngle(), a);
                        if (i2 == 0) {
                            path.moveTo(a.f14200a, a.f14201b);
                        } else {
                            path.lineTo(a.f14200a, a.f14201b);
                        }
                    }
                    path.close();
                    canvas.drawPath(path, this.g);
                }
            }
            C3279e.m15900b(centerOffsets);
            C3279e.m15900b(a);
        }
    }
}
