package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Drawable;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import com.github.mikephil.charting.charts.C3204f;
import com.github.mikephil.charting.data.C3242p;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3245j;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3272a;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: RadarChartRenderer */
/* renamed from: com.github.mikephil.charting.f.n */
public class C3265n extends C3262k {
    /* renamed from: a */
    protected C3204f f14142a;
    /* renamed from: b */
    protected Paint f14143b;
    /* renamed from: c */
    protected Paint f14144c;
    /* renamed from: d */
    protected Path f14145d = new Path();
    /* renamed from: e */
    protected Path f14146e = new Path();

    public C3265n(C3204f c3204f, C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
        this.f14142a = c3204f;
        this.i = new Paint(1);
        this.i.setStyle(Style.STROKE);
        this.i.setStrokeWidth(2.0f);
        this.i.setColor(Color.rgb(255, Opcodes.NEW, AVException.PUSH_MISCONFIGURED));
        this.f14143b = new Paint(1);
        this.f14143b.setStyle(Style.STROKE);
        this.f14144c = new Paint(1);
    }

    /* renamed from: a */
    public void mo3994a() {
    }

    /* renamed from: a */
    public void mo3995a(Canvas canvas) {
        C3242p c3242p = (C3242p) this.f14142a.getData();
        int F = ((C3245j) c3242p.m15598l()).mo3938F();
        for (C3245j c3245j : c3242p.m15595i()) {
            if (c3245j.mo3936z()) {
                m15797a(canvas, c3245j, F);
            }
        }
    }

    /* renamed from: a */
    protected void m15797a(Canvas canvas, C3245j c3245j, int i) {
        float b = this.g.m15293b();
        float a = this.g.m15292a();
        float sliceAngle = this.f14142a.getSliceAngle();
        float factor = this.f14142a.getFactor();
        C3279e centerOffsets = this.f14142a.getCenterOffsets();
        C3279e a2 = C3279e.m15897a(0.0f, 0.0f);
        Path path = this.f14145d;
        path.reset();
        Object obj = null;
        for (int i2 = 0; i2 < c3245j.mo3938F(); i2++) {
            this.h.setColor(c3245j.mo3917b(i2));
            C3283i.m15940a(centerOffsets, ((((RadarEntry) c3245j.mo3948g(i2)).b() - this.f14142a.getYChartMin()) * factor) * a, ((((float) i2) * sliceAngle) * b) + this.f14142a.getRotationAngle(), a2);
            if (!Float.isNaN(a2.f14200a)) {
                if (obj == null) {
                    path.moveTo(a2.f14200a, a2.f14201b);
                    obj = 1;
                } else {
                    path.lineTo(a2.f14200a, a2.f14201b);
                }
            }
        }
        if (c3245j.mo3938F() > i) {
            path.lineTo(centerOffsets.f14200a, centerOffsets.f14201b);
        }
        path.close();
        if (c3245j.mo3992S()) {
            Drawable P = c3245j.mo3989P();
            if (P != null) {
                m15764a(canvas, path, P);
            } else {
                m15763a(canvas, path, c3245j.mo3988O(), c3245j.mo3990Q());
            }
        }
        this.h.setStrokeWidth(c3245j.mo3991R());
        this.h.setStyle(Style.STROKE);
        if (!c3245j.mo3992S() || c3245j.mo3990Q() < 255) {
            canvas.drawPath(path, this.h);
        }
        C3279e.m15900b(centerOffsets);
        C3279e.m15900b(a2);
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        float b = this.g.m15293b();
        float a = this.g.m15292a();
        float sliceAngle = this.f14142a.getSliceAngle();
        float factor = this.f14142a.getFactor();
        C3279e centerOffsets = this.f14142a.getCenterOffsets();
        C3279e a2 = C3279e.m15897a(0.0f, 0.0f);
        C3279e a3 = C3279e.m15897a(0.0f, 0.0f);
        float a4 = C3283i.m15928a(5.0f);
        for (int i = 0; i < ((C3242p) this.f14142a.getData()).m15590d(); i++) {
            C3245j c3245j = (C3245j) ((C3242p) this.f14142a.getData()).mo3993a(i);
            if (m15717a(c3245j)) {
                m15714b((C3220e) c3245j);
                C3279e a5 = C3279e.m15898a(c3245j.mo3935y());
                a5.f14200a = C3283i.m15928a(a5.f14200a);
                a5.f14201b = C3283i.m15928a(a5.f14201b);
                for (int i2 = 0; i2 < c3245j.mo3938F(); i2++) {
                    RadarEntry radarEntry = (RadarEntry) c3245j.mo3948g(i2);
                    C3283i.m15940a(centerOffsets, ((radarEntry.b() - this.f14142a.getYChartMin()) * factor) * a, ((((float) i2) * sliceAngle) * b) + this.f14142a.getRotationAngle(), a2);
                    if (c3245j.mo3933w()) {
                        m15710a(canvas, c3245j.mo3925o(), radarEntry.b(), radarEntry, i, a2.f14200a, a2.f14201b - a4, c3245j.mo3920e(i2));
                    }
                    if (radarEntry.g() != null && c3245j.mo3934x()) {
                        Drawable g = radarEntry.g();
                        C3283i.m15940a(centerOffsets, ((radarEntry.b() * factor) * a) + a5.f14201b, ((((float) i2) * sliceAngle) * b) + this.f14142a.getRotationAngle(), a3);
                        a3.f14201b += a5.f14200a;
                        C3283i.m15935a(canvas, g, (int) a3.f14200a, (int) a3.f14201b, g.getIntrinsicWidth(), g.getIntrinsicHeight());
                    }
                }
                C3279e.m15900b(a5);
            }
        }
        C3279e.m15900b(centerOffsets);
        C3279e.m15900b(a2);
        C3279e.m15900b(a3);
    }

    /* renamed from: c */
    public void mo3998c(Canvas canvas) {
        m15802d(canvas);
    }

    /* renamed from: d */
    protected void m15802d(Canvas canvas) {
        int i;
        float sliceAngle = this.f14142a.getSliceAngle();
        float factor = this.f14142a.getFactor();
        float rotationAngle = this.f14142a.getRotationAngle();
        C3279e centerOffsets = this.f14142a.getCenterOffsets();
        this.f14143b.setStrokeWidth(this.f14142a.getWebLineWidth());
        this.f14143b.setColor(this.f14142a.getWebColor());
        this.f14143b.setAlpha(this.f14142a.getWebAlpha());
        int skipWebLineCount = this.f14142a.getSkipWebLineCount() + 1;
        int F = ((C3245j) ((C3242p) this.f14142a.getData()).m15598l()).mo3938F();
        C3279e a = C3279e.m15897a(0.0f, 0.0f);
        for (i = 0; i < F; i += skipWebLineCount) {
            C3283i.m15940a(centerOffsets, this.f14142a.getYRange() * factor, (((float) i) * sliceAngle) + rotationAngle, a);
            canvas.drawLine(centerOffsets.f14200a, centerOffsets.f14201b, a.f14200a, a.f14201b, this.f14143b);
        }
        C3279e.m15900b(a);
        this.f14143b.setStrokeWidth(this.f14142a.getWebLineWidthInner());
        this.f14143b.setColor(this.f14142a.getWebColorInner());
        this.f14143b.setAlpha(this.f14142a.getWebAlpha());
        F = this.f14142a.getYAxis().d;
        a = C3279e.m15897a(0.0f, 0.0f);
        C3279e a2 = C3279e.m15897a(0.0f, 0.0f);
        for (skipWebLineCount = 0; skipWebLineCount < F; skipWebLineCount++) {
            for (i = 0; i < ((C3242p) this.f14142a.getData()).m15597k(); i++) {
                float yChartMin = (this.f14142a.getYAxis().b[skipWebLineCount] - this.f14142a.getYChartMin()) * factor;
                C3283i.m15940a(centerOffsets, yChartMin, (((float) i) * sliceAngle) + rotationAngle, a);
                C3283i.m15940a(centerOffsets, yChartMin, (((float) (i + 1)) * sliceAngle) + rotationAngle, a2);
                canvas.drawLine(a.f14200a, a.f14201b, a2.f14200a, a2.f14201b, this.f14143b);
            }
        }
        C3279e.m15900b(a);
        C3279e.m15900b(a2);
    }

    /* renamed from: a */
    public void mo3996a(Canvas canvas, C3213d[] c3213dArr) {
        float sliceAngle = this.f14142a.getSliceAngle();
        float factor = this.f14142a.getFactor();
        C3279e centerOffsets = this.f14142a.getCenterOffsets();
        C3279e a = C3279e.m15897a(0.0f, 0.0f);
        C3242p c3242p = (C3242p) this.f14142a.getData();
        for (C3213d c3213d : c3213dArr) {
            C3245j c3245j = (C3245j) c3242p.mo3993a(c3213d.m15431f());
            if (c3245j != null && c3245j.mo3924n()) {
                RadarEntry radarEntry = (RadarEntry) c3245j.mo3948g((int) c3213d.m15423a());
                if (m15716a(radarEntry, c3245j)) {
                    C3283i.m15940a(centerOffsets, ((radarEntry.b() - this.f14142a.getYChartMin()) * factor) * this.g.m15292a(), ((c3213d.m15423a() * sliceAngle) * this.g.m15293b()) + this.f14142a.getRotationAngle(), a);
                    c3213d.m15424a(a.f14200a, a.f14201b);
                    m15734a(canvas, a.f14200a, a.f14201b, c3245j);
                    if (!(!c3245j.m15698a() || Float.isNaN(a.f14200a) || Float.isNaN(a.f14201b))) {
                        int a2;
                        int c = c3245j.m15700c();
                        if (c == 1122867) {
                            c = c3245j.mo3917b(0);
                        }
                        if (c3245j.m15701d() < 255) {
                            a2 = C3272a.m15841a(c, c3245j.m15701d());
                        } else {
                            a2 = c;
                        }
                        m15798a(canvas, a, c3245j.m15702e(), c3245j.m15703f(), c3245j.m15699b(), a2, c3245j.m15704g());
                    }
                }
            }
        }
        C3279e.m15900b(centerOffsets);
        C3279e.m15900b(a);
    }

    /* renamed from: a */
    public void m15798a(Canvas canvas, C3279e c3279e, float f, float f2, int i, int i2, float f3) {
        canvas.save();
        float a = C3283i.m15928a(f2);
        float a2 = C3283i.m15928a(f);
        if (i != 1122867) {
            Path path = this.f14146e;
            path.reset();
            path.addCircle(c3279e.f14200a, c3279e.f14201b, a, Direction.CW);
            if (a2 > 0.0f) {
                path.addCircle(c3279e.f14200a, c3279e.f14201b, a2, Direction.CCW);
            }
            this.f14144c.setColor(i);
            this.f14144c.setStyle(Style.FILL);
            canvas.drawPath(path, this.f14144c);
        }
        if (i2 != 1122867) {
            this.f14144c.setColor(i2);
            this.f14144c.setStyle(Style.STROKE);
            this.f14144c.setStrokeWidth(C3283i.m15928a(f3));
            canvas.drawCircle(c3279e.f14200a, c3279e.f14201b, a, this.f14144c);
        }
        canvas.restore();
    }
}
