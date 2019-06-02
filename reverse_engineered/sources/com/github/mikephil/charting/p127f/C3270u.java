package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3277d;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: YAxisRendererHorizontalBarChart */
/* renamed from: com.github.mikephil.charting.f.u */
public class C3270u extends C3269t {
    /* renamed from: r */
    protected Path f14162r = new Path();
    /* renamed from: s */
    protected Path f14163s = new Path();
    /* renamed from: t */
    protected float[] f14164t = new float[4];

    public C3270u(C3275j c3275j, YAxis yAxis, C3281g c3281g) {
        super(c3275j, yAxis, c3281g);
        this.g.setTextAlign(Align.LEFT);
    }

    /* renamed from: a */
    public void mo3340a(float f, float f2, boolean z) {
        if (this.o.m15872j() > 10.0f && !this.o.m15886u()) {
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
    public void mo4011a(Canvas canvas) {
        if (this.a.A() && this.a.h()) {
            float[] c = mo4015c();
            this.e.setTypeface(this.a.x());
            this.e.setTextSize(this.a.y());
            this.e.setColor(this.a.z());
            this.e.setTextAlign(Align.CENTER);
            float a = C3283i.m15928a(2.5f);
            float b = (float) C3283i.m15946b(this.e, "Q");
            AxisDependency B = this.a.m15385B();
            YAxisLabelPosition E = this.a.m15388E();
            if (B == AxisDependency.LEFT) {
                if (E == YAxisLabelPosition.OUTSIDE_CHART) {
                    a = this.o.m15862e() - a;
                } else {
                    a = this.o.m15862e() - a;
                }
            } else if (E == YAxisLabelPosition.OUTSIDE_CHART) {
                a += b + this.o.m15868h();
            } else {
                a += b + this.o.m15868h();
            }
            mo4012a(canvas, a, c, this.a.w());
        }
    }

    /* renamed from: b */
    public void mo4014b(Canvas canvas) {
        if (this.a.A() && this.a.b()) {
            this.f.setColor(this.a.g());
            this.f.setStrokeWidth(this.a.e());
            if (this.a.m15385B() == AxisDependency.LEFT) {
                canvas.drawLine(this.o.m15864f(), this.o.m15862e(), this.o.m15866g(), this.o.m15862e(), this.f);
                return;
            }
            canvas.drawLine(this.o.m15864f(), this.o.m15868h(), this.o.m15866g(), this.o.m15868h(), this.f);
        }
    }

    /* renamed from: a */
    protected void mo4012a(Canvas canvas, float f, float[] fArr, float f2) {
        this.e.setTypeface(this.a.x());
        this.e.setTextSize(this.a.y());
        this.e.setColor(this.a.z());
        int i = this.a.m15390G() ? 0 : 1;
        int i2 = this.a.m15389F() ? this.a.d : this.a.d - 1;
        while (i < i2) {
            canvas.drawText(this.a.d(i), fArr[i * 2], f - f2, this.e);
            i++;
        }
    }

    /* renamed from: c */
    protected float[] mo4015c() {
        if (this.k.length != this.a.d * 2) {
            this.k = new float[(this.a.d * 2)];
        }
        float[] fArr = this.k;
        for (int i = 0; i < fArr.length; i += 2) {
            fArr[i] = this.a.b[i / 2];
        }
        this.c.m15918a(fArr);
        return fArr;
    }

    /* renamed from: b */
    public RectF mo4013b() {
        this.j.set(this.o.m15874k());
        this.j.inset(-this.b.f(), 0.0f);
        return this.j;
    }

    /* renamed from: a */
    protected Path mo4010a(Path path, int i, float[] fArr) {
        path.moveTo(fArr[i], this.o.m15862e());
        path.lineTo(fArr[i], this.o.m15868h());
        return path;
    }

    /* renamed from: d */
    protected void mo4016d(Canvas canvas) {
        int save = canvas.save();
        this.m.set(this.o.m15874k());
        this.m.inset(-this.a.m15396M(), 0.0f);
        canvas.clipRect(this.q);
        C3277d b = this.c.m15923b(0.0f, 0.0f);
        this.h.setColor(this.a.m15395L());
        this.h.setStrokeWidth(this.a.m15396M());
        Path path = this.f14162r;
        path.reset();
        path.moveTo(((float) b.f14196a) - 1.0f, this.o.m15862e());
        path.lineTo(((float) b.f14196a) - 1.0f, this.o.m15868h());
        canvas.drawPath(path, this.h);
        canvas.restoreToCount(save);
    }

    /* renamed from: e */
    public void mo4017e(Canvas canvas) {
        List n = this.a.n();
        if (n != null && n.size() > 0) {
            float[] fArr = this.f14164t;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            Path path = this.f14163s;
            path.reset();
            for (int i = 0; i < n.size(); i++) {
                LimitLine limitLine = (LimitLine) n.get(i);
                if (limitLine.m15336A()) {
                    int save = canvas.save();
                    this.q.set(this.o.m15874k());
                    this.q.inset(-limitLine.m15375b(), 0.0f);
                    canvas.clipRect(this.q);
                    fArr[0] = limitLine.m15370a();
                    fArr[2] = limitLine.m15370a();
                    this.c.m15918a(fArr);
                    fArr[1] = this.o.m15862e();
                    fArr[3] = this.o.m15868h();
                    path.moveTo(fArr[0], fArr[1]);
                    path.lineTo(fArr[2], fArr[3]);
                    this.g.setStyle(Style.STROKE);
                    this.g.setColor(limitLine.m15376c());
                    this.g.setPathEffect(limitLine.m15377d());
                    this.g.setStrokeWidth(limitLine.m15375b());
                    canvas.drawPath(path, this.g);
                    path.reset();
                    String g = limitLine.m15380g();
                    if (!(g == null || g.equals(""))) {
                        this.g.setStyle(limitLine.m15378e());
                        this.g.setPathEffect(null);
                        this.g.setColor(limitLine.m15345z());
                        this.g.setTypeface(limitLine.m15343x());
                        this.g.setStrokeWidth(0.5f);
                        this.g.setTextSize(limitLine.m15344y());
                        float b = limitLine.m15375b() + limitLine.m15341v();
                        float a = C3283i.m15928a(2.0f) + limitLine.m15342w();
                        LimitLabelPosition f = limitLine.m15379f();
                        if (f == LimitLabelPosition.RIGHT_TOP) {
                            float b2 = (float) C3283i.m15946b(this.g, g);
                            this.g.setTextAlign(Align.LEFT);
                            canvas.drawText(g, b + fArr[0], b2 + (a + this.o.m15862e()), this.g);
                        } else if (f == LimitLabelPosition.RIGHT_BOTTOM) {
                            this.g.setTextAlign(Align.LEFT);
                            canvas.drawText(g, fArr[0] + b, this.o.m15868h() - a, this.g);
                        } else if (f == LimitLabelPosition.LEFT_TOP) {
                            this.g.setTextAlign(Align.RIGHT);
                            canvas.drawText(g, fArr[0] - b, ((float) C3283i.m15946b(this.g, g)) + (a + this.o.m15862e()), this.g);
                        } else {
                            this.g.setTextAlign(Align.RIGHT);
                            canvas.drawText(g, fArr[0] - b, this.o.m15868h() - a, this.g);
                        }
                    }
                    canvas.restoreToCount(save);
                }
            }
        }
    }
}
