package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.charts.C2011a;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.p183g.C3274b;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3277d;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: XAxisRendererHorizontalBarChart */
/* renamed from: com.github.mikephil.charting.f.r */
public class C3267r extends C2005q {
    /* renamed from: a */
    protected C2011a f14149a;
    /* renamed from: p */
    protected Path f14150p = new Path();

    public C3267r(C3275j c3275j, XAxis xAxis, C3281g c3281g, C2011a c2011a) {
        super(c3275j, xAxis, c3281g);
        this.f14149a = c2011a;
    }

    /* renamed from: a */
    public void mo3340a(float f, float f2, boolean z) {
        if (this.o.m15870i() > 10.0f && !this.o.m15885t()) {
            float f3;
            float f4;
            C3277d a = this.c.m15911a(this.o.m15864f(), this.o.m15868h());
            C3277d a2 = this.c.m15911a(this.o.m15864f(), this.o.m15862e());
            if (z) {
                f3 = (float) a2.f14197b;
                f4 = (float) a.f14197b;
            } else {
                f3 = (float) a.f14197b;
                f4 = (float) a2.f14197b;
            }
            C3277d.m15893a(a);
            C3277d.m15893a(a2);
            f2 = f4;
            f = f3;
        }
        mo3339a(f, f2);
    }

    /* renamed from: c */
    protected void mo4007c() {
        this.e.setTypeface(this.h.x());
        this.e.setTextSize(this.h.y());
        C3274b c = C3283i.m15950c(this.e, this.h.p());
        float v = (float) ((int) (c.f14176a + (this.h.v() * 3.5f)));
        float f = c.f14177b;
        c = C3283i.m15933a(c.f14176a, f, this.h.m15382C());
        this.h.f13936B = Math.round(v);
        this.h.f13937C = Math.round(f);
        this.h.f13938D = (int) (c.f14176a + (this.h.v() * 3.5f));
        this.h.f13939E = Math.round(c.f14177b);
        C3274b.m15845a(c);
    }

    /* renamed from: a */
    public void mo4004a(Canvas canvas) {
        if (this.h.A() && this.h.h()) {
            float v = this.h.v();
            this.e.setTypeface(this.h.x());
            this.e.setTextSize(this.h.y());
            this.e.setColor(this.h.z());
            C3279e a = C3279e.m15897a(0.0f, 0.0f);
            if (this.h.m15381B() == XAxisPosition.TOP) {
                a.f14200a = 0.0f;
                a.f14201b = 0.5f;
                mo3341a(canvas, v + this.o.m15866g(), a);
            } else if (this.h.m15381B() == XAxisPosition.TOP_INSIDE) {
                a.f14200a = 1.0f;
                a.f14201b = 0.5f;
                mo3341a(canvas, this.o.m15866g() - v, a);
            } else if (this.h.m15381B() == XAxisPosition.BOTTOM) {
                a.f14200a = 1.0f;
                a.f14201b = 0.5f;
                mo3341a(canvas, this.o.m15864f() - v, a);
            } else if (this.h.m15381B() == XAxisPosition.BOTTOM_INSIDE) {
                a.f14200a = 1.0f;
                a.f14201b = 0.5f;
                mo3341a(canvas, v + this.o.m15864f(), a);
            } else {
                a.f14200a = 0.0f;
                a.f14201b = 0.5f;
                mo3341a(canvas, this.o.m15866g() + v, a);
                a.f14200a = 1.0f;
                a.f14201b = 0.5f;
                mo3341a(canvas, this.o.m15864f() - v, a);
            }
            C3279e.m15900b(a);
        }
    }

    /* renamed from: a */
    protected void mo3341a(Canvas canvas, float f, C3279e c3279e) {
        float C = this.h.m15382C();
        boolean c = this.h.c();
        float[] fArr = new float[(this.h.d * 2)];
        for (int i = 0; i < fArr.length; i += 2) {
            if (c) {
                fArr[i + 1] = this.h.c[i / 2];
            } else {
                fArr[i + 1] = this.h.b[i / 2];
            }
        }
        this.c.m15918a(fArr);
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            float f2 = fArr[i2 + 1];
            if (this.o.m15865f(f2)) {
                m10343a(canvas, this.h.q().mo3334a(this.h.b[i2 / 2], this.h), f, f2, c3279e, C);
            }
        }
    }

    /* renamed from: d */
    public RectF mo4008d() {
        this.k.set(this.o.m15874k());
        this.k.inset(0.0f, -this.b.f());
        return this.k;
    }

    /* renamed from: a */
    protected void mo4005a(Canvas canvas, float f, float f2, Path path) {
        path.moveTo(this.o.m15866g(), f2);
        path.lineTo(this.o.m15864f(), f2);
        canvas.drawPath(path, this.d);
        path.reset();
    }

    /* renamed from: b */
    public void mo4006b(Canvas canvas) {
        if (this.h.b() && this.h.A()) {
            this.f.setColor(this.h.g());
            this.f.setStrokeWidth(this.h.e());
            if (this.h.m15381B() == XAxisPosition.TOP || this.h.m15381B() == XAxisPosition.TOP_INSIDE || this.h.m15381B() == XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.o.m15866g(), this.o.m15862e(), this.o.m15866g(), this.o.m15868h(), this.f);
            }
            if (this.h.m15381B() == XAxisPosition.BOTTOM || this.h.m15381B() == XAxisPosition.BOTTOM_INSIDE || this.h.m15381B() == XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.o.m15864f(), this.o.m15862e(), this.o.m15864f(), this.o.m15868h(), this.f);
            }
        }
    }

    /* renamed from: d */
    public void mo4009d(Canvas canvas) {
        List n = this.h.n();
        if (n != null && n.size() > 0) {
            float[] fArr = this.l;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            Path path = this.f14150p;
            path.reset();
            for (int i = 0; i < n.size(); i++) {
                LimitLine limitLine = (LimitLine) n.get(i);
                if (limitLine.m15336A()) {
                    int save = canvas.save();
                    this.m.set(this.o.m15874k());
                    this.m.inset(0.0f, -limitLine.m15375b());
                    canvas.clipRect(this.m);
                    this.g.setStyle(Style.STROKE);
                    this.g.setColor(limitLine.m15376c());
                    this.g.setStrokeWidth(limitLine.m15375b());
                    this.g.setPathEffect(limitLine.m15377d());
                    fArr[1] = limitLine.m15370a();
                    this.c.m15918a(fArr);
                    path.moveTo(this.o.m15864f(), fArr[1]);
                    path.lineTo(this.o.m15866g(), fArr[1]);
                    canvas.drawPath(path, this.g);
                    path.reset();
                    String g = limitLine.m15380g();
                    if (!(g == null || g.equals(""))) {
                        this.g.setStyle(limitLine.m15378e());
                        this.g.setPathEffect(null);
                        this.g.setColor(limitLine.m15345z());
                        this.g.setStrokeWidth(0.5f);
                        this.g.setTextSize(limitLine.m15344y());
                        float b = (float) C3283i.m15946b(this.g, g);
                        float a = C3283i.m15928a(4.0f) + limitLine.m15341v();
                        float b2 = (limitLine.m15375b() + b) + limitLine.m15342w();
                        LimitLabelPosition f = limitLine.m15379f();
                        if (f == LimitLabelPosition.RIGHT_TOP) {
                            this.g.setTextAlign(Align.RIGHT);
                            canvas.drawText(g, this.o.m15866g() - a, b + (fArr[1] - b2), this.g);
                        } else if (f == LimitLabelPosition.RIGHT_BOTTOM) {
                            this.g.setTextAlign(Align.RIGHT);
                            canvas.drawText(g, this.o.m15866g() - a, fArr[1] + b2, this.g);
                        } else if (f == LimitLabelPosition.LEFT_TOP) {
                            this.g.setTextAlign(Align.LEFT);
                            canvas.drawText(g, this.o.m15864f() + a, b + (fArr[1] - b2), this.g);
                        } else {
                            this.g.setTextAlign(Align.LEFT);
                            canvas.drawText(g, this.o.m15847a() + a, fArr[1] + b2, this.g);
                        }
                    }
                    canvas.restoreToCount(save);
                }
            }
        }
    }
}
