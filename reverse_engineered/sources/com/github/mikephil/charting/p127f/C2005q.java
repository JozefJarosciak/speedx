package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
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

/* compiled from: XAxisRenderer */
/* renamed from: com.github.mikephil.charting.f.q */
public class C2005q extends C2004a {
    /* renamed from: a */
    private Path f9027a = new Path();
    /* renamed from: h */
    protected XAxis f9028h;
    /* renamed from: i */
    protected Path f9029i = new Path();
    /* renamed from: j */
    protected float[] f9030j = new float[2];
    /* renamed from: k */
    protected RectF f9031k = new RectF();
    /* renamed from: l */
    protected float[] f9032l = new float[2];
    /* renamed from: m */
    protected RectF f9033m = new RectF();
    /* renamed from: n */
    float[] f9034n = new float[4];

    public C2005q(C3275j c3275j, XAxis xAxis, C3281g c3281g) {
        super(c3275j, c3281g, xAxis);
        this.f9028h = xAxis;
        this.e.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.e.setTextAlign(Align.CENTER);
        this.e.setTextSize(C3283i.m15928a(10.0f));
    }

    /* renamed from: b */
    protected void m10344b() {
        this.d.setColor(this.f9028h.d());
        this.d.setStrokeWidth(this.f9028h.f());
        this.d.setPathEffect(this.f9028h.r());
    }

    /* renamed from: a */
    public void mo3340a(float f, float f2, boolean z) {
        if (this.o.m15870i() > 10.0f && !this.o.m15886u()) {
            float f3;
            float f4;
            C3277d a = this.c.m15911a(this.o.m15864f(), this.o.m15862e());
            C3277d a2 = this.c.m15911a(this.o.m15866g(), this.o.m15862e());
            if (z) {
                f3 = (float) a2.f14196a;
                f4 = (float) a.f14196a;
            } else {
                f3 = (float) a.f14196a;
                f4 = (float) a2.f14196a;
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
        super.mo3339a(f, f2);
        mo4007c();
    }

    /* renamed from: c */
    protected void mo4007c() {
        String p = this.f9028h.p();
        this.e.setTypeface(this.f9028h.x());
        this.e.setTextSize(this.f9028h.y());
        C3274b c = C3283i.m15950c(this.e, p);
        float f = c.f14176a;
        float b = (float) C3283i.m15946b(this.e, "Q");
        C3274b a = C3283i.m15933a(f, b, this.f9028h.m15382C());
        this.f9028h.f13936B = Math.round(f);
        this.f9028h.f13937C = Math.round(b);
        this.f9028h.f13938D = Math.round(a.f14176a);
        this.f9028h.f13939E = Math.round(a.f14177b);
        C3274b.m15845a(a);
        C3274b.m15845a(c);
    }

    /* renamed from: a */
    public void mo4004a(Canvas canvas) {
        if (this.f9028h.A() && this.f9028h.h()) {
            float w = this.f9028h.w();
            this.e.setTypeface(this.f9028h.x());
            this.e.setTextSize(this.f9028h.y());
            this.e.setColor(this.f9028h.z());
            C3279e a = C3279e.m15897a(0.0f, 0.0f);
            if (this.f9028h.m15381B() == XAxisPosition.TOP) {
                a.f14200a = 0.5f;
                a.f14201b = 1.0f;
                mo3341a(canvas, this.o.m15862e() - w, a);
            } else if (this.f9028h.m15381B() == XAxisPosition.TOP_INSIDE) {
                a.f14200a = 0.5f;
                a.f14201b = 1.0f;
                mo3341a(canvas, (w + this.o.m15862e()) + ((float) this.f9028h.f13939E), a);
            } else if (this.f9028h.m15381B() == XAxisPosition.BOTTOM) {
                a.f14200a = 0.5f;
                a.f14201b = 0.0f;
                mo3341a(canvas, w + this.o.m15868h(), a);
            } else if (this.f9028h.m15381B() == XAxisPosition.BOTTOM_INSIDE) {
                a.f14200a = 0.5f;
                a.f14201b = 0.0f;
                mo3341a(canvas, (this.o.m15868h() - w) - ((float) this.f9028h.f13939E), a);
            } else {
                a.f14200a = 0.5f;
                a.f14201b = 1.0f;
                mo3341a(canvas, this.o.m15862e() - w, a);
                a.f14200a = 0.5f;
                a.f14201b = 0.0f;
                mo3341a(canvas, w + this.o.m15868h(), a);
            }
            C3279e.m15900b(a);
        }
    }

    /* renamed from: b */
    public void mo4006b(Canvas canvas) {
        if (this.f9028h.b() && this.f9028h.A()) {
            this.f.setColor(this.f9028h.g());
            this.f.setStrokeWidth(this.f9028h.e());
            this.f.setPathEffect(this.f9028h.t());
            if (this.f9028h.m15381B() == XAxisPosition.TOP || this.f9028h.m15381B() == XAxisPosition.TOP_INSIDE || this.f9028h.m15381B() == XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.o.m15864f(), this.o.m15862e(), this.o.m15866g(), this.o.m15862e(), this.f);
            }
            if (this.f9028h.m15381B() == XAxisPosition.BOTTOM || this.f9028h.m15381B() == XAxisPosition.BOTTOM_INSIDE || this.f9028h.m15381B() == XAxisPosition.BOTH_SIDED) {
                canvas.drawLine(this.o.m15864f(), this.o.m15868h(), this.o.m15866g(), this.o.m15868h(), this.f);
            }
        }
    }

    /* renamed from: a */
    protected void mo3341a(Canvas canvas, float f, C3279e c3279e) {
        float C = this.f9028h.m15382C();
        boolean c = this.f9028h.c();
        float[] fArr = new float[(this.f9028h.d * 2)];
        for (int i = 0; i < fArr.length; i += 2) {
            if (c) {
                fArr[i] = this.f9028h.c[i / 2];
            } else {
                fArr[i] = this.f9028h.b[i / 2];
            }
        }
        this.c.m15918a(fArr);
        for (int i2 = 0; i2 < fArr.length; i2 += 2) {
            float f2 = fArr[i2];
            if (this.o.m15863e(f2)) {
                float f3;
                String a = this.f9028h.q().mo3334a(this.f9028h.b[i2 / 2], this.f9028h);
                if (this.f9028h.m15383D()) {
                    if (i2 == this.f9028h.d - 1 && this.f9028h.d > 1) {
                        float a2 = (float) C3283i.m15931a(this.e, a);
                        if (a2 > this.o.m15855b() * 2.0f && f2 + a2 > this.o.m15879n()) {
                            f2 -= a2 / 2.0f;
                        }
                        f3 = f2;
                        m10343a(canvas, a, f3, f, c3279e, C);
                    } else if (i2 == 0) {
                        f3 = f2 + (((float) C3283i.m15931a(this.e, a)) / 2.0f);
                        m10343a(canvas, a, f3, f, c3279e, C);
                    }
                }
                f3 = f2;
                m10343a(canvas, a, f3, f, c3279e, C);
            }
        }
    }

    /* renamed from: a */
    protected void m10343a(Canvas canvas, String str, float f, float f2, C3279e c3279e, float f3) {
        C3283i.m15936a(canvas, str, f, f2, this.e, c3279e, f3);
    }

    /* renamed from: c */
    public void m10347c(Canvas canvas) {
        int i = 0;
        if (this.f9028h.a() && this.f9028h.A()) {
            int save = canvas.save();
            canvas.clipRect(mo4008d());
            if (this.f9030j.length != this.b.f6978d * 2) {
                this.f9030j = new float[(this.f9028h.d * 2)];
            }
            float[] fArr = this.f9030j;
            for (int i2 = 0; i2 < fArr.length; i2 += 2) {
                fArr[i2] = this.f9028h.b[i2 / 2];
                fArr[i2 + 1] = this.f9028h.b[i2 / 2];
            }
            this.c.m15918a(fArr);
            m10344b();
            Path path = this.f9029i;
            path.reset();
            while (i < fArr.length) {
                mo4005a(canvas, fArr[i], fArr[i + 1], path);
                i += 2;
            }
            canvas.restoreToCount(save);
        }
    }

    /* renamed from: d */
    public RectF mo4008d() {
        this.f9031k.set(this.o.m15874k());
        this.f9031k.inset(-this.b.f(), 0.0f);
        return this.f9031k;
    }

    /* renamed from: a */
    protected void mo4005a(Canvas canvas, float f, float f2, Path path) {
        path.moveTo(f, this.o.m15868h());
        path.lineTo(f, this.o.m15862e());
        canvas.drawPath(path, this.d);
        path.reset();
    }

    /* renamed from: d */
    public void mo4009d(Canvas canvas) {
        List n = this.f9028h.n();
        if (n != null && n.size() > 0) {
            float[] fArr = this.f9032l;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            for (int i = 0; i < n.size(); i++) {
                LimitLine limitLine = (LimitLine) n.get(i);
                if (limitLine.m15336A()) {
                    int save = canvas.save();
                    this.f9033m.set(this.o.m15874k());
                    this.f9033m.inset(-limitLine.m15375b(), 0.0f);
                    canvas.clipRect(this.f9033m);
                    fArr[0] = limitLine.m15370a();
                    fArr[1] = 0.0f;
                    this.c.m15918a(fArr);
                    m10341a(canvas, limitLine, fArr);
                    m10342a(canvas, limitLine, fArr, 2.0f + limitLine.m15342w());
                    canvas.restoreToCount(save);
                }
            }
        }
    }

    /* renamed from: a */
    public void m10341a(Canvas canvas, LimitLine limitLine, float[] fArr) {
        this.f9034n[0] = fArr[0];
        this.f9034n[1] = this.o.m15862e();
        this.f9034n[2] = fArr[0];
        this.f9034n[3] = this.o.m15868h();
        this.f9027a.reset();
        this.f9027a.moveTo(this.f9034n[0], this.f9034n[1]);
        this.f9027a.lineTo(this.f9034n[2], this.f9034n[3]);
        this.g.setStyle(Style.STROKE);
        this.g.setColor(limitLine.m15376c());
        this.g.setStrokeWidth(limitLine.m15375b());
        this.g.setPathEffect(limitLine.m15377d());
        canvas.drawPath(this.f9027a, this.g);
    }

    /* renamed from: a */
    public void m10342a(Canvas canvas, LimitLine limitLine, float[] fArr, float f) {
        String g = limitLine.m15380g();
        if (g != null && !g.equals("")) {
            this.g.setStyle(limitLine.m15378e());
            this.g.setPathEffect(null);
            this.g.setColor(limitLine.m15345z());
            this.g.setStrokeWidth(0.5f);
            this.g.setTextSize(limitLine.m15344y());
            float b = limitLine.m15375b() + limitLine.m15341v();
            LimitLabelPosition f2 = limitLine.m15379f();
            if (f2 == LimitLabelPosition.RIGHT_TOP) {
                float b2 = (float) C3283i.m15946b(this.g, g);
                this.g.setTextAlign(Align.LEFT);
                canvas.drawText(g, b + fArr[0], b2 + (this.o.m15862e() + f), this.g);
            } else if (f2 == LimitLabelPosition.RIGHT_BOTTOM) {
                this.g.setTextAlign(Align.LEFT);
                canvas.drawText(g, b + fArr[0], this.o.m15868h() - f, this.g);
            } else if (f2 == LimitLabelPosition.LEFT_TOP) {
                this.g.setTextAlign(Align.RIGHT);
                canvas.drawText(g, fArr[0] - b, ((float) C3283i.m15946b(this.g, g)) + (this.o.m15862e() + f), this.g);
            } else {
                this.g.setTextAlign(Align.RIGHT);
                canvas.drawText(g, fArr[0] - b, this.o.m15868h() - f, this.g);
            }
        }
    }
}
