package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.C3232h;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3235d;
import com.github.mikephil.charting.p089e.p128a.C3194d;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3277d;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: CandleStickChartRenderer */
/* renamed from: com.github.mikephil.charting.f.e */
public class C3254e extends C3253l {
    /* renamed from: a */
    protected C3194d f14089a;
    /* renamed from: b */
    private float[] f14090b = new float[8];
    /* renamed from: c */
    private float[] f14091c = new float[4];
    /* renamed from: d */
    private float[] f14092d = new float[4];
    /* renamed from: e */
    private float[] f14093e = new float[4];
    /* renamed from: l */
    private float[] f14094l = new float[4];

    public C3254e(C3194d c3194d, C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
        this.f14089a = c3194d;
    }

    /* renamed from: a */
    public void mo3994a() {
    }

    /* renamed from: a */
    public void mo3995a(Canvas canvas) {
        for (C3235d c3235d : this.f14089a.getCandleData().m15595i()) {
            if (c3235d.mo3936z()) {
                m15737a(canvas, c3235d);
            }
        }
    }

    /* renamed from: a */
    protected void m15737a(Canvas canvas, C3235d c3235d) {
        C3281g a = this.f14089a.mo3343a(c3235d.mo3913A());
        float a2 = this.g.m15292a();
        float a3 = c3235d.mo3976a();
        boolean c = c3235d.mo3979c();
        this.f.m15726a(this.f14089a, c3235d);
        this.h.setStrokeWidth(c3235d.mo3977b());
        for (int i = this.f.f14080a; i <= this.f.f14082c + this.f.f14080a; i++) {
            CandleEntry candleEntry = (CandleEntry) c3235d.mo3948g(i);
            if (candleEntry != null) {
                float i2 = candleEntry.m15450i();
                float e = candleEntry.m15462e();
                float d = candleEntry.m15461d();
                float a4 = candleEntry.m15458a();
                float c2 = candleEntry.m15460c();
                int b;
                if (c) {
                    this.f14090b[0] = i2;
                    this.f14090b[2] = i2;
                    this.f14090b[4] = i2;
                    this.f14090b[6] = i2;
                    if (e > d) {
                        this.f14090b[1] = a4 * a2;
                        this.f14090b[3] = e * a2;
                        this.f14090b[5] = c2 * a2;
                        this.f14090b[7] = d * a2;
                    } else if (e < d) {
                        this.f14090b[1] = a4 * a2;
                        this.f14090b[3] = d * a2;
                        this.f14090b[5] = c2 * a2;
                        this.f14090b[7] = e * a2;
                    } else {
                        this.f14090b[1] = a4 * a2;
                        this.f14090b[3] = e * a2;
                        this.f14090b[5] = c2 * a2;
                        this.f14090b[7] = this.f14090b[3];
                    }
                    a.m15918a(this.f14090b);
                    Paint paint;
                    if (!c3235d.mo3975D()) {
                        paint = this.h;
                        if (c3235d.mo3974C() == 1122867) {
                            b = c3235d.mo3917b(i);
                        } else {
                            b = c3235d.mo3974C();
                        }
                        paint.setColor(b);
                    } else if (e > d) {
                        paint = this.h;
                        if (c3235d.mo3982f() == 1122867) {
                            b = c3235d.mo3917b(i);
                        } else {
                            b = c3235d.mo3982f();
                        }
                        paint.setColor(b);
                    } else if (e < d) {
                        paint = this.h;
                        if (c3235d.mo3981e() == 1122867) {
                            b = c3235d.mo3917b(i);
                        } else {
                            b = c3235d.mo3981e();
                        }
                        paint.setColor(b);
                    } else {
                        paint = this.h;
                        if (c3235d.mo3980d() == 1122867) {
                            b = c3235d.mo3917b(i);
                        } else {
                            b = c3235d.mo3980d();
                        }
                        paint.setColor(b);
                    }
                    this.h.setStyle(Style.STROKE);
                    canvas.drawLines(this.f14090b, this.h);
                    this.f14091c[0] = (i2 - 0.5f) + a3;
                    this.f14091c[1] = d * a2;
                    this.f14091c[2] = (i2 + 0.5f) - a3;
                    this.f14091c[3] = e * a2;
                    a.m15918a(this.f14091c);
                    if (e > d) {
                        if (c3235d.mo3982f() == 1122867) {
                            this.h.setColor(c3235d.mo3917b(i));
                        } else {
                            this.h.setColor(c3235d.mo3982f());
                        }
                        this.h.setStyle(c3235d.mo3973B());
                        canvas.drawRect(this.f14091c[0], this.f14091c[3], this.f14091c[2], this.f14091c[1], this.h);
                    } else if (e < d) {
                        if (c3235d.mo3981e() == 1122867) {
                            this.h.setColor(c3235d.mo3917b(i));
                        } else {
                            this.h.setColor(c3235d.mo3981e());
                        }
                        this.h.setStyle(c3235d.mo3983g());
                        canvas.drawRect(this.f14091c[0], this.f14091c[1], this.f14091c[2], this.f14091c[3], this.h);
                    } else {
                        if (c3235d.mo3980d() == 1122867) {
                            this.h.setColor(c3235d.mo3917b(i));
                        } else {
                            this.h.setColor(c3235d.mo3980d());
                        }
                        canvas.drawLine(this.f14091c[0], this.f14091c[1], this.f14091c[2], this.f14091c[3], this.h);
                    }
                } else {
                    this.f14092d[0] = i2;
                    this.f14092d[1] = a4 * a2;
                    this.f14092d[2] = i2;
                    this.f14092d[3] = c2 * a2;
                    this.f14093e[0] = (i2 - 0.5f) + a3;
                    this.f14093e[1] = e * a2;
                    this.f14093e[2] = i2;
                    this.f14093e[3] = e * a2;
                    this.f14094l[0] = (0.5f + i2) - a3;
                    this.f14094l[1] = d * a2;
                    this.f14094l[2] = i2;
                    this.f14094l[3] = d * a2;
                    a.m15918a(this.f14092d);
                    a.m15918a(this.f14093e);
                    a.m15918a(this.f14094l);
                    if (e > d) {
                        if (c3235d.mo3982f() == 1122867) {
                            b = c3235d.mo3917b(i);
                        } else {
                            b = c3235d.mo3982f();
                        }
                    } else if (e < d) {
                        if (c3235d.mo3981e() == 1122867) {
                            b = c3235d.mo3917b(i);
                        } else {
                            b = c3235d.mo3981e();
                        }
                    } else if (c3235d.mo3980d() == 1122867) {
                        b = c3235d.mo3917b(i);
                    } else {
                        b = c3235d.mo3980d();
                    }
                    this.h.setColor(b);
                    canvas.drawLine(this.f14092d[0], this.f14092d[1], this.f14092d[2], this.f14092d[3], this.h);
                    canvas.drawLine(this.f14093e[0], this.f14093e[1], this.f14093e[2], this.f14093e[3], this.h);
                    canvas.drawLine(this.f14094l[0], this.f14094l[1], this.f14094l[2], this.f14094l[3], this.h);
                }
            }
        }
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        if (mo4002a(this.f14089a)) {
            List i = this.f14089a.getCandleData().m15595i();
            for (int i2 = 0; i2 < i.size(); i2++) {
                C3235d c3235d = (C3235d) i.get(i2);
                if (m15717a(c3235d)) {
                    m15714b((C3220e) c3235d);
                    C3281g a = this.f14089a.mo3343a(c3235d.mo3913A());
                    this.f.m15726a(this.f14089a, c3235d);
                    float[] a2 = a.m15920a(c3235d, this.g.m15293b(), this.g.m15292a(), this.f.f14080a, this.f.f14081b);
                    float a3 = C3283i.m15928a(5.0f);
                    C3279e a4 = C3279e.m15898a(c3235d.mo3935y());
                    a4.f14200a = C3283i.m15928a(a4.f14200a);
                    a4.f14201b = C3283i.m15928a(a4.f14201b);
                    for (int i3 = 0; i3 < a2.length; i3 += 2) {
                        float f = a2[i3];
                        float f2 = a2[i3 + 1];
                        if (!this.o.m15869h(f)) {
                            break;
                        }
                        if (this.o.m15867g(f) && this.o.m15865f(f2)) {
                            CandleEntry candleEntry = (CandleEntry) c3235d.mo3948g((i3 / 2) + this.f.f14080a);
                            if (c3235d.mo3933w()) {
                                m15710a(canvas, c3235d.mo3925o(), candleEntry.m15458a(), candleEntry, i2, f, f2 - a3, c3235d.mo3920e(i3 / 2));
                            }
                            if (candleEntry.m15448g() != null && c3235d.mo3934x()) {
                                Drawable g = candleEntry.m15448g();
                                C3283i.m15935a(canvas, g, (int) (a4.f14200a + f), (int) (a4.f14201b + f2), g.getIntrinsicWidth(), g.getIntrinsicHeight());
                            }
                        }
                    }
                    C3279e.m15900b(a4);
                }
            }
        }
    }

    /* renamed from: c */
    public void mo3998c(Canvas canvas) {
    }

    /* renamed from: a */
    public void mo3996a(Canvas canvas, C3213d[] c3213dArr) {
        C3232h candleData = this.f14089a.getCandleData();
        for (C3213d c3213d : c3213dArr) {
            C3235d c3235d = (C3235d) candleData.mo3993a(c3213d.m15431f());
            if (c3235d != null && c3235d.mo3924n()) {
                CandleEntry candleEntry = (CandleEntry) c3235d.mo3945b(c3213d.m15423a(), c3213d.m15427b());
                if (m15716a(candleEntry, c3235d)) {
                    C3277d b = this.f14089a.mo3343a(c3235d.mo3913A()).m15923b(candleEntry.m15450i(), ((candleEntry.m15460c() * this.g.m15292a()) + (candleEntry.m15458a() * this.g.m15292a())) / 2.0f);
                    c3213d.m15424a((float) b.f14196a, (float) b.f14197b);
                    m15734a(canvas, (float) b.f14196a, (float) b.f14197b, c3235d);
                }
            }
        }
    }
}
