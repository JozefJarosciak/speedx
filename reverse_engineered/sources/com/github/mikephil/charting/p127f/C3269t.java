package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
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

/* compiled from: YAxisRenderer */
/* renamed from: com.github.mikephil.charting.f.t */
public class C3269t extends C2004a {
    /* renamed from: a */
    protected YAxis f14152a;
    /* renamed from: h */
    protected Paint f14153h;
    /* renamed from: i */
    protected Path f14154i = new Path();
    /* renamed from: j */
    protected RectF f14155j = new RectF();
    /* renamed from: k */
    protected float[] f14156k = new float[2];
    /* renamed from: l */
    protected Path f14157l = new Path();
    /* renamed from: m */
    protected RectF f14158m = new RectF();
    /* renamed from: n */
    protected Path f14159n = new Path();
    /* renamed from: p */
    protected float[] f14160p = new float[2];
    /* renamed from: q */
    protected RectF f14161q = new RectF();

    public C3269t(C3275j c3275j, YAxis yAxis, C3281g c3281g) {
        super(c3275j, c3281g, yAxis);
        this.f14152a = yAxis;
        if (this.o != null) {
            this.e.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.e.setTextSize(C3283i.m15928a(10.0f));
            this.f14153h = new Paint(1);
            this.f14153h.setColor(-7829368);
            this.f14153h.setStrokeWidth(1.0f);
            this.f14153h.setStyle(Style.STROKE);
        }
    }

    /* renamed from: a */
    public void mo4011a(Canvas canvas) {
        if (this.f14152a.A() && this.f14152a.h()) {
            float[] c = mo4015c();
            this.e.setTypeface(this.f14152a.x());
            this.e.setTextSize(this.f14152a.y());
            this.e.setColor(this.f14152a.z());
            float v = this.f14152a.v();
            float b = (((float) C3283i.m15946b(this.e, "A")) / 2.5f) + this.f14152a.w();
            AxisDependency B = this.f14152a.m15385B();
            YAxisLabelPosition E = this.f14152a.m15388E();
            if (B == AxisDependency.LEFT) {
                if (E == YAxisLabelPosition.OUTSIDE_CHART) {
                    this.e.setTextAlign(Align.RIGHT);
                    v = this.o.m15847a() - v;
                } else {
                    this.e.setTextAlign(Align.LEFT);
                    v += this.o.m15847a();
                }
            } else if (E == YAxisLabelPosition.OUTSIDE_CHART) {
                this.e.setTextAlign(Align.LEFT);
                v += this.o.m15866g();
            } else {
                this.e.setTextAlign(Align.RIGHT);
                v = this.o.m15866g() - v;
            }
            mo4012a(canvas, v, c, b);
        }
    }

    /* renamed from: b */
    public void mo4014b(Canvas canvas) {
        if (this.f14152a.A() && this.f14152a.b()) {
            this.f.setColor(this.f14152a.g());
            this.f.setStrokeWidth(this.f14152a.e());
            if (this.f14152a.m15385B() == AxisDependency.LEFT) {
                canvas.drawLine(this.o.m15864f(), this.o.m15862e(), this.o.m15864f(), this.o.m15868h(), this.f);
                return;
            }
            canvas.drawLine(this.o.m15866g(), this.o.m15862e(), this.o.m15866g(), this.o.m15868h(), this.f);
        }
    }

    /* renamed from: a */
    protected void mo4012a(Canvas canvas, float f, float[] fArr, float f2) {
        int i;
        int i2 = this.f14152a.m15390G() ? 0 : 1;
        if (this.f14152a.m15389F()) {
            i = this.f14152a.d;
        } else {
            i = this.f14152a.d - 1;
        }
        while (i2 < i) {
            canvas.drawText(this.f14152a.d(i2), f, fArr[(i2 * 2) + 1] + f2, this.e);
            i2++;
        }
    }

    /* renamed from: c */
    public void m15824c(Canvas canvas) {
        if (this.f14152a.A()) {
            if (this.f14152a.a()) {
                int save = canvas.save();
                canvas.clipRect(mo4013b());
                float[] c = mo4015c();
                this.d.setColor(this.f14152a.d());
                this.d.setStrokeWidth(this.f14152a.f());
                this.d.setPathEffect(this.f14152a.r());
                Path path = this.f14154i;
                path.reset();
                for (int i = 0; i < c.length; i += 2) {
                    canvas.drawPath(mo4010a(path, i, c), this.d);
                    path.reset();
                }
                canvas.restoreToCount(save);
            }
            if (this.f14152a.m15394K()) {
                mo4016d(canvas);
            }
        }
    }

    /* renamed from: b */
    public RectF mo4013b() {
        this.f14155j.set(this.o.m15874k());
        this.f14155j.inset(0.0f, -this.b.f());
        return this.f14155j;
    }

    /* renamed from: a */
    protected Path mo4010a(Path path, int i, float[] fArr) {
        path.moveTo(this.o.m15847a(), fArr[i + 1]);
        path.lineTo(this.o.m15866g(), fArr[i + 1]);
        return path;
    }

    /* renamed from: c */
    protected float[] mo4015c() {
        if (this.f14156k.length != this.f14152a.d * 2) {
            this.f14156k = new float[(this.f14152a.d * 2)];
        }
        float[] fArr = this.f14156k;
        for (int i = 0; i < fArr.length; i += 2) {
            fArr[i + 1] = this.f14152a.b[i / 2];
        }
        this.c.m15918a(fArr);
        return fArr;
    }

    /* renamed from: d */
    protected void mo4016d(Canvas canvas) {
        int save = canvas.save();
        this.f14158m.set(this.o.m15874k());
        this.f14158m.inset(0.0f, -this.f14152a.m15396M());
        canvas.clipRect(this.f14158m);
        C3277d b = this.c.m15923b(0.0f, 0.0f);
        this.f14153h.setColor(this.f14152a.m15395L());
        this.f14153h.setStrokeWidth(this.f14152a.m15396M());
        Path path = this.f14157l;
        path.reset();
        path.moveTo(this.o.m15864f(), (float) b.f14197b);
        path.lineTo(this.o.m15866g(), (float) b.f14197b);
        canvas.drawPath(path, this.f14153h);
        canvas.restoreToCount(save);
    }

    /* renamed from: e */
    public void mo4017e(Canvas canvas) {
        List n = this.f14152a.n();
        if (n != null && n.size() > 0) {
            float[] fArr = this.f14160p;
            fArr[0] = 0.0f;
            fArr[1] = 0.0f;
            Path path = this.f14159n;
            path.reset();
            for (int i = 0; i < n.size(); i++) {
                LimitLine limitLine = (LimitLine) n.get(i);
                if (limitLine.m15336A()) {
                    int save = canvas.save();
                    this.f14161q.set(this.o.m15874k());
                    this.f14161q.inset(0.0f, -limitLine.m15375b());
                    canvas.clipRect(this.f14161q);
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
                        this.g.setTypeface(limitLine.m15343x());
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
