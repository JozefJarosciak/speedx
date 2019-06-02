package com.github.mikephil.charting.p183g;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.RectF;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p089e.p090b.C1477f;
import com.github.mikephil.charting.p089e.p090b.C3235d;
import com.github.mikephil.charting.p089e.p090b.C3244c;
import com.github.mikephil.charting.p089e.p090b.C3246k;

/* compiled from: Transformer */
/* renamed from: com.github.mikephil.charting.g.g */
public class C3281g {
    /* renamed from: a */
    protected Matrix f14209a = new Matrix();
    /* renamed from: b */
    protected Matrix f14210b = new Matrix();
    /* renamed from: c */
    protected C3275j f14211c;
    /* renamed from: d */
    protected float[] f14212d = new float[1];
    /* renamed from: e */
    protected float[] f14213e = new float[1];
    /* renamed from: f */
    protected float[] f14214f = new float[1];
    /* renamed from: g */
    protected float[] f14215g = new float[1];
    /* renamed from: h */
    protected Matrix f14216h = new Matrix();
    /* renamed from: i */
    float[] f14217i = new float[2];
    /* renamed from: j */
    private Matrix f14218j = new Matrix();
    /* renamed from: k */
    private Matrix f14219k = new Matrix();

    public C3281g(C3275j c3275j) {
        this.f14211c = c3275j;
    }

    /* renamed from: a */
    public void m15912a(float f, float f2, float f3, float f4) {
        float f5 = 0.0f;
        float i = this.f14211c.m15870i() / f2;
        float j = this.f14211c.m15872j() / f3;
        if (Float.isInfinite(i)) {
            i = 0.0f;
        }
        if (!Float.isInfinite(j)) {
            f5 = j;
        }
        this.f14209a.reset();
        this.f14209a.postTranslate(-f, -f4);
        this.f14209a.postScale(i, -f5);
    }

    /* renamed from: a */
    public void mo4019a(boolean z) {
        this.f14210b.reset();
        if (z) {
            this.f14210b.setTranslate(this.f14211c.m15847a(), -this.f14211c.m15858c());
            this.f14210b.postScale(1.0f, -1.0f);
            return;
        }
        this.f14210b.postTranslate(this.f14211c.m15847a(), this.f14211c.m15878m() - this.f14211c.m15860d());
    }

    /* renamed from: a */
    public float[] m15922a(C3246k c3246k, float f, float f2, int i, int i2) {
        int i3 = ((int) ((((float) (i2 - i)) * f) + 1.0f)) * 2;
        if (this.f14212d.length != i3) {
            this.f14212d = new float[i3];
        }
        float[] fArr = this.f14212d;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            Entry g = c3246k.mo3948g((i4 / 2) + i);
            if (g != null) {
                fArr[i4] = g.m15450i();
                fArr[i4 + 1] = g.mo3912b() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        m15910a().mapPoints(fArr);
        return fArr;
    }

    /* renamed from: a */
    public float[] m15919a(C3244c c3244c, float f, int i, int i2) {
        int i3 = ((i2 - i) + 1) * 2;
        if (this.f14213e.length != i3) {
            this.f14213e = new float[i3];
        }
        float[] fArr = this.f14213e;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            Entry g = c3244c.mo3948g((i4 / 2) + i);
            if (g != null) {
                fArr[i4] = g.m15450i();
                fArr[i4 + 1] = g.mo3912b() * f;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        m15910a().mapPoints(fArr);
        return fArr;
    }

    /* renamed from: a */
    public float[] m15921a(C1477f c1477f, float f, float f2, int i, int i2) {
        int i3 = (((int) (((float) (i2 - i)) * f)) + 1) * 2;
        if (this.f14214f.length != i3) {
            this.f14214f = new float[i3];
        }
        float[] fArr = this.f14214f;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            Entry g = c1477f.g((i4 / 2) + i);
            if (g != null) {
                fArr[i4] = g.m15450i();
                fArr[i4 + 1] = g.mo3912b() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        m15910a().mapPoints(fArr);
        return fArr;
    }

    /* renamed from: a */
    public float[] m15920a(C3235d c3235d, float f, float f2, int i, int i2) {
        int i3 = ((int) ((((float) (i2 - i)) * f) + 1.0f)) * 2;
        if (this.f14215g.length != i3) {
            this.f14215g = new float[i3];
        }
        float[] fArr = this.f14215g;
        for (int i4 = 0; i4 < i3; i4 += 2) {
            CandleEntry candleEntry = (CandleEntry) c3235d.mo3948g((i4 / 2) + i);
            if (candleEntry != null) {
                fArr[i4] = candleEntry.m15450i();
                fArr[i4 + 1] = candleEntry.m15458a() * f2;
            } else {
                fArr[i4] = 0.0f;
                fArr[i4 + 1] = 0.0f;
            }
        }
        m15910a().mapPoints(fArr);
        return fArr;
    }

    /* renamed from: a */
    public void m15914a(Path path) {
        path.transform(this.f14209a);
        path.transform(this.f14211c.m15881p());
        path.transform(this.f14210b);
    }

    /* renamed from: a */
    public void m15918a(float[] fArr) {
        this.f14209a.mapPoints(fArr);
        this.f14211c.m15881p().mapPoints(fArr);
        this.f14210b.mapPoints(fArr);
    }

    /* renamed from: a */
    public void m15915a(RectF rectF) {
        this.f14209a.mapRect(rectF);
        this.f14211c.m15881p().mapRect(rectF);
        this.f14210b.mapRect(rectF);
    }

    /* renamed from: a */
    public void m15916a(RectF rectF, float f) {
        rectF.top *= f;
        rectF.bottom *= f;
        this.f14209a.mapRect(rectF);
        this.f14211c.m15881p().mapRect(rectF);
        this.f14210b.mapRect(rectF);
    }

    /* renamed from: b */
    public void m15924b(RectF rectF, float f) {
        rectF.left *= f;
        rectF.right *= f;
        this.f14209a.mapRect(rectF);
        this.f14211c.m15881p().mapRect(rectF);
        this.f14210b.mapRect(rectF);
    }

    /* renamed from: b */
    public void m15925b(float[] fArr) {
        Matrix matrix = this.f14216h;
        matrix.reset();
        this.f14210b.invert(matrix);
        matrix.mapPoints(fArr);
        this.f14211c.m15881p().invert(matrix);
        matrix.mapPoints(fArr);
        this.f14209a.invert(matrix);
        matrix.mapPoints(fArr);
    }

    /* renamed from: a */
    public C3277d m15911a(float f, float f2) {
        C3277d a = C3277d.m15892a(0.0d, 0.0d);
        m15913a(f, f2, a);
        return a;
    }

    /* renamed from: a */
    public void m15913a(float f, float f2, C3277d c3277d) {
        this.f14217i[0] = f;
        this.f14217i[1] = f2;
        m15925b(this.f14217i);
        c3277d.f14196a = (double) this.f14217i[0];
        c3277d.f14197b = (double) this.f14217i[1];
    }

    /* renamed from: b */
    public C3277d m15923b(float f, float f2) {
        this.f14217i[0] = f;
        this.f14217i[1] = f2;
        m15918a(this.f14217i);
        return C3277d.m15892a((double) this.f14217i[0], (double) this.f14217i[1]);
    }

    /* renamed from: a */
    public Matrix m15910a() {
        this.f14218j.set(this.f14209a);
        this.f14218j.postConcat(this.f14211c.f14178a);
        this.f14218j.postConcat(this.f14210b);
        return this.f14218j;
    }
}
