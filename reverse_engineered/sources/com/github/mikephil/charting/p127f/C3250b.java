package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.C3226a;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3229a;
import com.github.mikephil.charting.p089e.p128a.C2010a;
import com.github.mikephil.charting.p121c.C3192f;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p180b.C3187b;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p181d.C3218j;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: BarChartRenderer */
/* renamed from: com.github.mikephil.charting.f.b */
public class C3250b extends C3249c {
    /* renamed from: a */
    protected C2010a f14074a;
    /* renamed from: b */
    protected RectF f14075b = new RectF();
    /* renamed from: c */
    protected C3187b[] f14076c;
    /* renamed from: d */
    protected Paint f14077d;
    /* renamed from: e */
    protected Paint f14078e;
    /* renamed from: l */
    private RectF f14079l = new RectF();

    public C3250b(C2010a c2010a, C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
        this.f14074a = c2010a;
        this.i = new Paint(1);
        this.i.setStyle(Style.FILL);
        this.i.setColor(Color.rgb(0, 0, 0));
        this.i.setAlpha(120);
        this.f14077d = new Paint(1);
        this.f14077d.setStyle(Style.FILL);
        this.f14078e = new Paint(1);
        this.f14078e.setStyle(Style.STROKE);
    }

    /* renamed from: a */
    public void mo3994a() {
        C3226a barData = this.f14074a.getBarData();
        this.f14076c = new C3187b[barData.m15590d()];
        for (int i = 0; i < this.f14076c.length; i++) {
            C3229a c3229a = (C3229a) barData.mo3993a(i);
            this.f14076c[i] = new C3187b((c3229a.mo3963b() ? c3229a.mo3962a() : 1) * (c3229a.mo3938F() * 4), barData.m15590d(), c3229a.mo3963b());
        }
    }

    /* renamed from: a */
    public void mo3995a(Canvas canvas) {
        C3226a barData = this.f14074a.getBarData();
        for (int i = 0; i < barData.m15590d(); i++) {
            C3229a c3229a = (C3229a) barData.mo3993a(i);
            if (c3229a.mo3936z()) {
                mo4000a(canvas, c3229a, i);
            }
        }
    }

    /* renamed from: a */
    protected void mo4000a(Canvas canvas, C3229a c3229a, int i) {
        C3281g a = this.f14074a.mo3343a(c3229a.mo3913A());
        this.f14078e.setColor(c3229a.mo3966e());
        this.f14078e.setStrokeWidth(C3283i.m15928a(c3229a.mo3965d()));
        Object obj = c3229a.mo3965d() > 0.0f ? 1 : null;
        float b = this.g.m15293b();
        float a2 = this.g.m15292a();
        if (this.f14074a.mo3353d()) {
            this.f14077d.setColor(c3229a.mo3964c());
            float a3 = this.f14074a.getBarData().m15599a() / 2.0f;
            int min = Math.min((int) Math.ceil((double) (((float) c3229a.mo3938F()) * b)), c3229a.mo3938F());
            for (int i2 = 0; i2 < min; i2++) {
                float i3 = ((BarEntry) c3229a.mo3948g(i2)).m15450i();
                this.f14079l.left = i3 - a3;
                this.f14079l.right = i3 + a3;
                a.m15915a(this.f14079l);
                if (this.o.m15867g(this.f14079l.right)) {
                    if (!this.o.m15869h(this.f14079l.left)) {
                        break;
                    }
                    this.f14079l.top = this.o.m15862e();
                    this.f14079l.bottom = this.o.m15868h();
                    canvas.drawRect(this.f14079l, this.f14077d);
                }
            }
        }
        C3187b c3187b = this.f14076c[i];
        c3187b.m15295a(b, a2);
        c3187b.m15299a(i);
        c3187b.m15301a(this.f14074a.mo3344c(c3229a.mo3913A()));
        c3187b.m15297a(this.f14074a.getBarData().m15599a());
        c3187b.mo3884a(c3229a);
        a.m15918a(c3187b.b);
        Object obj2 = c3229a.mo3921j().size() == 1 ? 1 : null;
        if (obj2 != null) {
            this.h.setColor(c3229a.mo3922k());
        }
        for (int i4 = 0; i4 < c3187b.m15296b(); i4 += 4) {
            if (this.o.m15867g(c3187b.b[i4 + 2])) {
                if (this.o.m15869h(c3187b.b[i4])) {
                    if (obj2 == null) {
                        this.h.setColor(c3229a.mo3917b(i4 / 4));
                    }
                    canvas.drawRect(c3187b.b[i4], c3187b.b[i4 + 1], c3187b.b[i4 + 2], c3187b.b[i4 + 3], this.h);
                    if (obj != null) {
                        canvas.drawRect(c3187b.b[i4], c3187b.b[i4 + 1], c3187b.b[i4 + 2], c3187b.b[i4 + 3], this.f14078e);
                    }
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    protected void mo3999a(float f, float f2, float f3, float f4, C3281g c3281g) {
        this.f14075b.set(f - f4, f2, f + f4, f3);
        c3281g.m15916a(this.f14075b, this.g.m15292a());
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        if (mo4002a(this.f14074a)) {
            List i = this.f14074a.getBarData().m15595i();
            float a = C3283i.m15928a(4.5f);
            boolean c = this.f14074a.mo3352c();
            for (int i2 = 0; i2 < this.f14074a.getBarData().m15590d(); i2++) {
                C3229a c3229a = (C3229a) i.get(i2);
                if (m15717a(c3229a)) {
                    float f;
                    float f2;
                    float f3;
                    m15714b((C3220e) c3229a);
                    boolean c2 = this.f14074a.mo3344c(c3229a.mo3913A());
                    float b = (float) C3283i.m15946b(this.k, "8");
                    float f4 = c ? -a : b + a;
                    if (c) {
                        f = b + a;
                    } else {
                        f = -a;
                    }
                    if (c2) {
                        f2 = (-f) - b;
                        f3 = (-f4) - b;
                    } else {
                        f2 = f;
                        f3 = f4;
                    }
                    C3187b c3187b = this.f14076c[i2];
                    float a2 = this.g.m15292a();
                    C3279e a3 = C3279e.m15898a(c3229a.mo3935y());
                    a3.f14200a = C3283i.m15928a(a3.f14200a);
                    a3.f14201b = C3283i.m15928a(a3.f14201b);
                    BarEntry barEntry;
                    float f5;
                    Drawable g;
                    float f6;
                    if (c3229a.mo3963b()) {
                        C3281g a4 = this.f14074a.mo3343a(c3229a.mo3913A());
                        int i3 = 0;
                        int i4 = 0;
                        while (((float) i3) < ((float) c3229a.mo3938F()) * this.g.m15293b()) {
                            barEntry = (BarEntry) c3229a.mo3948g(i3);
                            float[] a5 = barEntry.m15451a();
                            f5 = (c3187b.b[i4] + c3187b.b[i4 + 2]) / 2.0f;
                            int e = c3229a.mo3920e(i3);
                            if (a5 == null) {
                                if (!this.o.m15869h(f5)) {
                                    break;
                                } else if (this.o.m15865f(c3187b.b[i4 + 1]) && this.o.m15867g(f5)) {
                                    if (c3229a.mo3933w()) {
                                        m15710a(canvas, c3229a.mo3925o(), barEntry.mo3912b(), barEntry, i2, f5, c3187b.b[i4 + 1] + (barEntry.mo3912b() >= 0.0f ? f3 : f2), e);
                                    }
                                    if (barEntry.m15448g() != null && c3229a.mo3934x()) {
                                        g = barEntry.m15448g();
                                        f4 = c3187b.b[i4 + 1];
                                        if (barEntry.mo3912b() >= 0.0f) {
                                            f = f3;
                                        } else {
                                            f = f2;
                                        }
                                        C3283i.m15935a(canvas, g, (int) (a3.f14200a + f5), (int) ((f + f4) + a3.f14201b), g.getIntrinsicWidth(), g.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                float[] fArr = new float[(a5.length * 2)];
                                int i5 = 0;
                                int i6 = 0;
                                float f7 = -barEntry.m15456f();
                                float f8 = 0.0f;
                                while (i5 < fArr.length) {
                                    float f9 = a5[i6];
                                    float f10;
                                    if (f9 == 0.0f && (f8 == 0.0f || f7 == 0.0f)) {
                                        b = f8;
                                        f10 = f7;
                                        f7 = f9;
                                        f9 = f10;
                                    } else if (f9 >= 0.0f) {
                                        f9 += f8;
                                        b = f9;
                                        f10 = f7;
                                        f7 = f9;
                                        f9 = f10;
                                    } else {
                                        f9 = f7 - f9;
                                        b = f8;
                                    }
                                    fArr[i5 + 1] = f7 * a2;
                                    i5 += 2;
                                    i6++;
                                    f7 = f9;
                                    f8 = b;
                                }
                                a4.m15918a(fArr);
                                for (int i7 = 0; i7 < fArr.length; i7 += 2) {
                                    f = a5[i7 / 2];
                                    Object obj = (!(f == 0.0f && f7 == 0.0f && f8 > 0.0f) && f >= 0.0f) ? null : 1;
                                    f4 = fArr[i7 + 1];
                                    if (obj != null) {
                                        f = f2;
                                    } else {
                                        f = f3;
                                    }
                                    f6 = f4 + f;
                                    if (!this.o.m15869h(f5)) {
                                        break;
                                    }
                                    if (this.o.m15865f(f6) && this.o.m15867g(f5)) {
                                        if (c3229a.mo3933w()) {
                                            m15710a(canvas, c3229a.mo3925o(), a5[i7 / 2], barEntry, i2, f5, f6, e);
                                        }
                                        if (barEntry.m15448g() != null && c3229a.mo3934x()) {
                                            Drawable g2 = barEntry.m15448g();
                                            C3283i.m15935a(canvas, g2, (int) (a3.f14200a + f5), (int) (a3.f14201b + f6), g2.getIntrinsicWidth(), g2.getIntrinsicHeight());
                                        }
                                    }
                                }
                            }
                            i3++;
                            i4 = a5 == null ? i4 + 4 : i4 + (a5.length * 4);
                        }
                    } else {
                        for (int i8 = 0; ((float) i8) < ((float) c3187b.b.length) * this.g.m15293b(); i8 += 4) {
                            f5 = (c3187b.b[i8] + c3187b.b[i8 + 2]) / 2.0f;
                            if (!this.o.m15869h(f5)) {
                                break;
                            }
                            if (this.o.m15865f(c3187b.b[i8 + 1]) && this.o.m15867g(f5)) {
                                barEntry = (BarEntry) c3229a.mo3948g(i8 / 4);
                                b = barEntry.mo3912b();
                                if (c3229a.mo3933w()) {
                                    C3192f o = c3229a.mo3925o();
                                    if (b >= 0.0f) {
                                        f6 = c3187b.b[i8 + 1] + f3;
                                    } else {
                                        f6 = c3187b.b[i8 + 3] + f2;
                                    }
                                    m15710a(canvas, o, b, barEntry, i2, f5, f6, c3229a.mo3920e(i8 / 4));
                                }
                                if (barEntry.m15448g() != null && c3229a.mo3934x()) {
                                    g = barEntry.m15448g();
                                    if (b >= 0.0f) {
                                        f = c3187b.b[i8 + 1] + f3;
                                    } else {
                                        f = c3187b.b[i8 + 3] + f2;
                                    }
                                    C3283i.m15935a(canvas, g, (int) (a3.f14200a + f5), (int) (f + a3.f14201b), g.getIntrinsicWidth(), g.getIntrinsicHeight());
                                }
                            }
                        }
                    }
                    C3279e.m15900b(a3);
                }
            }
        }
    }

    /* renamed from: a */
    public void mo3996a(Canvas canvas, C3213d[] c3213dArr) {
        C3226a barData = this.f14074a.getBarData();
        for (C3213d c3213d : c3213dArr) {
            C3229a c3229a = (C3229a) barData.mo3993a(c3213d.m15431f());
            if (c3229a != null && c3229a.mo3924n()) {
                BarEntry barEntry = (BarEntry) c3229a.mo3945b(c3213d.m15423a(), c3213d.m15427b());
                if (m15716a(barEntry, c3229a)) {
                    float b;
                    float f;
                    C3281g a = this.f14074a.mo3343a(c3229a.mo3913A());
                    this.i.setColor(c3229a.mo3961h());
                    this.i.setAlpha(c3229a.mo3967f());
                    Object obj = (c3213d.m15432g() < 0 || !barEntry.m15454d()) ? null : 1;
                    if (obj == null) {
                        b = barEntry.mo3912b();
                        f = 0.0f;
                    } else if (this.f14074a.mo3354e()) {
                        b = barEntry.m15455e();
                        f = -barEntry.m15456f();
                    } else {
                        C3218j c3218j = barEntry.m15453c()[c3213d.m15432g()];
                        b = c3218j.f13979a;
                        f = c3218j.f13980b;
                    }
                    mo3999a(barEntry.m15450i(), b, f, barData.m15599a() / 2.0f, a);
                    mo4001a(c3213d, this.f14075b);
                    canvas.drawRect(this.f14075b, this.i);
                }
            }
        }
    }

    /* renamed from: a */
    protected void mo4001a(C3213d c3213d, RectF rectF) {
        c3213d.m15424a(rectF.centerX(), rectF.top);
    }

    /* renamed from: c */
    public void mo3998c(Canvas canvas) {
    }
}
