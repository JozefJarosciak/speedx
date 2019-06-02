package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.C3226a;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3229a;
import com.github.mikephil.charting.p089e.p128a.C2007e;
import com.github.mikephil.charting.p089e.p128a.C2010a;
import com.github.mikephil.charting.p121c.C3192f;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p180b.C3187b;
import com.github.mikephil.charting.p180b.C3188c;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: HorizontalBarChartRenderer */
/* renamed from: com.github.mikephil.charting.f.h */
public class C3257h extends C3250b {
    /* renamed from: l */
    private RectF f14099l = new RectF();

    public C3257h(C2010a c2010a, C3185a c3185a, C3275j c3275j) {
        super(c2010a, c3185a, c3275j);
        this.k.setTextAlign(Align.LEFT);
    }

    /* renamed from: a */
    public void mo3994a() {
        C3226a barData = this.a.getBarData();
        this.c = new C3188c[barData.m15590d()];
        for (int i = 0; i < this.c.length; i++) {
            C3229a c3229a = (C3229a) barData.mo3993a(i);
            this.c[i] = new C3188c((c3229a.mo3963b() ? c3229a.mo3962a() : 1) * (c3229a.mo3938F() * 4), barData.m15590d(), c3229a.mo3963b());
        }
    }

    /* renamed from: a */
    protected void mo4000a(Canvas canvas, C3229a c3229a, int i) {
        C3281g a = this.a.mo3343a(c3229a.mo3913A());
        this.e.setColor(c3229a.mo3966e());
        this.e.setStrokeWidth(C3283i.m15928a(c3229a.mo3965d()));
        Object obj = c3229a.mo3965d() > 0.0f ? 1 : null;
        float b = this.g.m15293b();
        float a2 = this.g.m15292a();
        if (this.a.mo3353d()) {
            this.d.setColor(c3229a.mo3964c());
            float a3 = this.a.getBarData().m15599a() / 2.0f;
            int min = Math.min((int) Math.ceil((double) (((float) c3229a.mo3938F()) * b)), c3229a.mo3938F());
            for (int i2 = 0; i2 < min; i2++) {
                float i3 = ((BarEntry) c3229a.mo3948g(i2)).m15450i();
                this.f14099l.top = i3 - a3;
                this.f14099l.bottom = i3 + a3;
                a.m15915a(this.f14099l);
                if (this.o.m15871i(this.f14099l.bottom)) {
                    if (!this.o.m15873j(this.f14099l.top)) {
                        break;
                    }
                    this.f14099l.left = this.o.m15864f();
                    this.f14099l.right = this.o.m15866g();
                    canvas.drawRect(this.f14099l, this.d);
                }
            }
        }
        C3187b c3187b = this.c[i];
        c3187b.m15295a(b, a2);
        c3187b.m15299a(i);
        c3187b.m15301a(this.a.mo3344c(c3229a.mo3913A()));
        c3187b.m15297a(this.a.getBarData().m15599a());
        c3187b.mo3884a(c3229a);
        a.m15918a(c3187b.b);
        Object obj2 = c3229a.mo3921j().size() == 1 ? 1 : null;
        if (obj2 != null) {
            this.h.setColor(c3229a.mo3922k());
        }
        int i4 = 0;
        while (i4 < c3187b.m15296b() && this.o.m15871i(c3187b.b[i4 + 3])) {
            if (this.o.m15873j(c3187b.b[i4 + 1])) {
                if (obj2 == null) {
                    this.h.setColor(c3229a.mo3917b(i4 / 4));
                }
                canvas.drawRect(c3187b.b[i4], c3187b.b[i4 + 1], c3187b.b[i4 + 2], c3187b.b[i4 + 3], this.h);
                if (obj != null) {
                    canvas.drawRect(c3187b.b[i4], c3187b.b[i4 + 1], c3187b.b[i4 + 2], c3187b.b[i4 + 3], this.e);
                }
            }
            i4 += 4;
        }
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        if (mo4002a(this.a)) {
            List i = this.a.getBarData().m15595i();
            float a = C3283i.m15928a(5.0f);
            boolean c = this.a.mo3352c();
            for (int i2 = 0; i2 < this.a.getBarData().m15590d(); i2++) {
                C3229a c3229a = (C3229a) i.get(i2);
                if (m15717a(c3229a)) {
                    boolean c2 = this.a.mo3344c(c3229a.mo3913A());
                    m15714b((C3220e) c3229a);
                    float b = ((float) C3283i.m15946b(this.k, "10")) / 2.0f;
                    C3192f o = c3229a.mo3925o();
                    C3187b c3187b = this.c[i2];
                    float a2 = this.g.m15292a();
                    C3279e a3 = C3279e.m15898a(c3229a.mo3935y());
                    a3.f14200a = C3283i.m15928a(a3.f14200a);
                    a3.f14201b = C3283i.m15928a(a3.f14201b);
                    String a4;
                    float a5;
                    float f;
                    float f2;
                    Drawable g;
                    if (c3229a.mo3963b()) {
                        C3281g a6 = this.a.mo3343a(c3229a.mo3913A());
                        int i3 = 0;
                        int i4 = 0;
                        while (((float) i3) < ((float) c3229a.mo3938F()) * this.g.m15293b()) {
                            BarEntry barEntry = (BarEntry) c3229a.mo3948g(i3);
                            int e = c3229a.mo3920e(i3);
                            float[] a7 = barEntry.m15451a();
                            float f3;
                            if (a7 == null) {
                                if (!this.o.m15871i(c3187b.b[i4 + 1])) {
                                    break;
                                } else if (this.o.m15863e(c3187b.b[i4]) && this.o.m15873j(c3187b.b[i4 + 1])) {
                                    float f4;
                                    a4 = o.mo3886a(barEntry.mo3912b(), barEntry, i2, this.o);
                                    a5 = (float) C3283i.m15931a(this.k, a4);
                                    f = c ? a : -(a5 + a);
                                    if (c) {
                                        f2 = -(a5 + a);
                                    } else {
                                        f2 = a;
                                    }
                                    if (c2) {
                                        f3 = (-f2) - a5;
                                        f4 = (-f) - a5;
                                    } else {
                                        f3 = f2;
                                        f4 = f;
                                    }
                                    if (c3229a.mo3933w()) {
                                        f = c3187b.b[i4 + 2];
                                        if (barEntry.mo3912b() >= 0.0f) {
                                            f2 = f4;
                                        } else {
                                            f2 = f3;
                                        }
                                        m15750a(canvas, a4, f + f2, c3187b.b[i4 + 1] + b, e);
                                    }
                                    if (barEntry.m15448g() != null && c3229a.mo3934x()) {
                                        g = barEntry.m15448g();
                                        f2 = c3187b.b[i4 + 2];
                                        if (barEntry.mo3912b() < 0.0f) {
                                            f4 = f3;
                                        }
                                        C3283i.m15935a(canvas, g, (int) ((f2 + f4) + a3.f14200a), (int) (a3.f14201b + c3187b.b[i4 + 1]), g.getIntrinsicWidth(), g.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                float[] fArr = new float[(a7.length * 2)];
                                int i5 = 0;
                                int i6 = 0;
                                float f5 = -barEntry.m15456f();
                                float f6 = 0.0f;
                                while (i5 < fArr.length) {
                                    float f7 = a7[i6];
                                    float f8;
                                    if (f7 == 0.0f && (f6 == 0.0f || f5 == 0.0f)) {
                                        a5 = f6;
                                        f8 = f5;
                                        f5 = f7;
                                        f7 = f8;
                                    } else if (f7 >= 0.0f) {
                                        f7 += f6;
                                        a5 = f7;
                                        f8 = f5;
                                        f5 = f7;
                                        f7 = f8;
                                    } else {
                                        f7 = f5 - f7;
                                        a5 = f6;
                                    }
                                    fArr[i5] = f5 * a2;
                                    i5 += 2;
                                    i6++;
                                    f5 = f7;
                                    f6 = a5;
                                }
                                a6.m15918a(fArr);
                                for (int i7 = 0; i7 < fArr.length; i7 += 2) {
                                    f2 = a7[i7 / 2];
                                    a4 = o.mo3886a(f2, barEntry, i2, this.o);
                                    float a8 = (float) C3283i.m15931a(this.k, a4);
                                    a5 = c ? a : -(a8 + a);
                                    if (c) {
                                        f = -(a8 + a);
                                    } else {
                                        f = a;
                                    }
                                    if (c2) {
                                        a5 = (-a5) - a8;
                                        f = (-f) - a8;
                                    }
                                    Object obj = (!(f2 == 0.0f && f5 == 0.0f && f6 > 0.0f) && f2 >= 0.0f) ? null : 1;
                                    a8 = fArr[i7];
                                    if (obj != null) {
                                        f2 = f;
                                    } else {
                                        f2 = a5;
                                    }
                                    a5 = a8 + f2;
                                    f3 = (c3187b.b[i4 + 1] + c3187b.b[i4 + 3]) / 2.0f;
                                    if (!this.o.m15871i(f3)) {
                                        break;
                                    }
                                    if (this.o.m15863e(a5) && this.o.m15873j(f3)) {
                                        if (c3229a.mo3933w()) {
                                            m15750a(canvas, a4, a5, f3 + b, e);
                                        }
                                        if (barEntry.m15448g() != null && c3229a.mo3934x()) {
                                            Drawable g2 = barEntry.m15448g();
                                            C3283i.m15935a(canvas, g2, (int) (a3.f14200a + a5), (int) (a3.f14201b + f3), g2.getIntrinsicWidth(), g2.getIntrinsicHeight());
                                        }
                                    }
                                }
                            }
                            i3++;
                            i4 = a7 == null ? i4 + 4 : i4 + (a7.length * 4);
                        }
                    } else {
                        int i8 = 0;
                        while (((float) i8) < ((float) c3187b.b.length) * this.g.m15293b()) {
                            float f9 = (c3187b.b[i8 + 1] + c3187b.b[i8 + 3]) / 2.0f;
                            if (!this.o.m15871i(c3187b.b[i8 + 1])) {
                                break;
                            }
                            if (this.o.m15863e(c3187b.b[i8]) && this.o.m15873j(c3187b.b[i8 + 1])) {
                                float f10;
                                float f11;
                                BarEntry barEntry2 = (BarEntry) c3229a.mo3948g(i8 / 4);
                                float b2 = barEntry2.mo3912b();
                                a4 = o.mo3886a(b2, barEntry2, i2, this.o);
                                a5 = (float) C3283i.m15931a(this.k, a4);
                                f = c ? a : -(a5 + a);
                                if (c) {
                                    f2 = -(a5 + a);
                                } else {
                                    f2 = a;
                                }
                                if (c2) {
                                    f10 = (-f2) - a5;
                                    f11 = (-f) - a5;
                                } else {
                                    f10 = f2;
                                    f11 = f;
                                }
                                if (c3229a.mo3933w()) {
                                    f = c3187b.b[i8 + 2];
                                    if (b2 >= 0.0f) {
                                        f2 = f11;
                                    } else {
                                        f2 = f10;
                                    }
                                    m15750a(canvas, a4, f + f2, f9 + b, c3229a.mo3920e(i8 / 2));
                                }
                                if (barEntry2.m15448g() != null && c3229a.mo3934x()) {
                                    g = barEntry2.m15448g();
                                    f2 = c3187b.b[i8 + 2];
                                    if (b2 < 0.0f) {
                                        f11 = f10;
                                    }
                                    C3283i.m15935a(canvas, g, (int) ((f2 + f11) + a3.f14200a), (int) (f9 + a3.f14201b), g.getIntrinsicWidth(), g.getIntrinsicHeight());
                                }
                            }
                            i8 += 4;
                        }
                    }
                    C3279e.m15900b(a3);
                }
            }
        }
    }

    /* renamed from: a */
    protected void m15750a(Canvas canvas, String str, float f, float f2, int i) {
        this.k.setColor(i);
        canvas.drawText(str, f, f2, this.k);
    }

    /* renamed from: a */
    protected void mo3999a(float f, float f2, float f3, float f4, C3281g c3281g) {
        this.b.set(f2, f - f4, f3, f + f4);
        c3281g.m15924b(this.b, this.g.m15292a());
    }

    /* renamed from: a */
    protected void mo4001a(C3213d c3213d, RectF rectF) {
        c3213d.m15424a(rectF.centerY(), rectF.right);
    }

    /* renamed from: a */
    protected boolean mo4002a(C2007e c2007e) {
        return ((float) c2007e.getData().m15597k()) < ((float) c2007e.getMaxVisibleCount()) * this.o.m15883r();
    }
}
