package com.github.mikephil.charting.p127f;

import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3277d;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: AxisRenderer */
/* renamed from: com.github.mikephil.charting.f.a */
public abstract class C2004a extends C2003o {
    /* renamed from: b */
    protected C1476a f9021b;
    /* renamed from: c */
    protected C3281g f9022c;
    /* renamed from: d */
    protected Paint f9023d;
    /* renamed from: e */
    protected Paint f9024e;
    /* renamed from: f */
    protected Paint f9025f;
    /* renamed from: g */
    protected Paint f9026g;

    public C2004a(C3275j c3275j, C3281g c3281g, C1476a c1476a) {
        super(c3275j);
        this.f9022c = c3281g;
        this.f9021b = c1476a;
        if (this.o != null) {
            this.f9024e = new Paint(1);
            this.f9023d = new Paint();
            this.f9023d.setColor(-7829368);
            this.f9023d.setStrokeWidth(1.0f);
            this.f9023d.setStyle(Style.STROKE);
            this.f9023d.setAlpha(90);
            this.f9025f = new Paint();
            this.f9025f.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.f9025f.setStrokeWidth(1.0f);
            this.f9025f.setStyle(Style.STROKE);
            this.f9026g = new Paint(1);
            this.f9026g.setStyle(Style.STROKE);
        }
    }

    /* renamed from: a */
    public Paint m10333a() {
        return this.f9024e;
    }

    /* renamed from: a */
    public void mo3340a(float f, float f2, boolean z) {
        if (!(this.o == null || this.o.m15870i() <= 10.0f || this.o.m15885t())) {
            float f3;
            float f4;
            C3277d a = this.f9022c.m15911a(this.o.m15864f(), this.o.m15862e());
            C3277d a2 = this.f9022c.m15911a(this.o.m15864f(), this.o.m15868h());
            if (z) {
                f3 = (float) a.f14197b;
                f4 = (float) a2.f14197b;
            } else {
                f3 = (float) a2.f14197b;
                f4 = (float) a.f14197b;
            }
            C3277d.m15893a(a);
            C3277d.m15893a(a2);
            f2 = f4;
            f = f3;
        }
        mo3339a(f, f2);
    }

    /* renamed from: a */
    protected void mo3339a(float f, float f2) {
        int j = this.f9021b.j();
        double abs = (double) Math.abs(f2 - f);
        if (j == 0 || abs <= 0.0d || Double.isInfinite(abs)) {
            this.f9021b.f6976b = new float[0];
            this.f9021b.f6977c = new float[0];
            this.f9021b.f6978d = 0;
            return;
        }
        double a = (double) C3283i.m15927a(abs / ((double) j));
        if (this.f9021b.k() && a < ((double) this.f9021b.l())) {
            a = (double) this.f9021b.l();
        }
        double a2 = (double) C3283i.m15927a(Math.pow(10.0d, (double) ((int) Math.log10(a))));
        if (((int) (a / a2)) > 5) {
            a = Math.floor(10.0d * a2);
        }
        int i = this.f9021b.c() ? 1 : 0;
        if (this.f9021b.i()) {
            a = (double) (((float) abs) / ((float) (j - 1)));
            this.f9021b.f6978d = j;
            if (this.f9021b.f6976b.length < j) {
                this.f9021b.f6976b = new float[j];
            }
            for (i = 0; i < j; i++) {
                this.f9021b.f6976b[i] = f;
                f = (float) (((double) f) + a);
            }
            i = j;
        } else {
            double d;
            abs = a == 0.0d ? 0.0d : Math.ceil(((double) f) / a) * a;
            if (this.f9021b.c()) {
                abs -= a;
            }
            if (a == 0.0d) {
                d = 0.0d;
            } else {
                d = C3283i.m15941b(Math.floor(((double) f2) / a) * a);
            }
            if (a != 0.0d) {
                a2 = abs;
                while (a2 <= d) {
                    a2 += a;
                    i++;
                }
            }
            this.f9021b.f6978d = i;
            if (this.f9021b.f6976b.length < i) {
                this.f9021b.f6976b = new float[i];
            }
            for (j = 0; j < i; j++) {
                if (abs == 0.0d) {
                    abs = 0.0d;
                }
                this.f9021b.f6976b[j] = (float) abs;
                abs += a;
            }
        }
        if (a < 1.0d) {
            this.f9021b.f6979e = (int) Math.ceil(-Math.log10(a));
        } else {
            this.f9021b.f6979e = 0;
        }
        if (this.f9021b.c()) {
            if (this.f9021b.f6977c.length < i) {
                this.f9021b.f6977c = new float[i];
            }
            float f3 = ((float) a) / 2.0f;
            for (int i2 = 0; i2 < i; i2++) {
                this.f9021b.f6977c[i2] = this.f9021b.f6976b[i2] + f3;
            }
        }
    }
}
