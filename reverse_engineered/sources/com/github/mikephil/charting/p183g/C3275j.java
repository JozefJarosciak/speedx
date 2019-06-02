package com.github.mikephil.charting.p183g;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.View;

/* compiled from: ViewPortHandler */
/* renamed from: com.github.mikephil.charting.g.j */
public class C3275j {
    /* renamed from: a */
    protected final Matrix f14178a = new Matrix();
    /* renamed from: b */
    protected RectF f14179b = new RectF();
    /* renamed from: c */
    protected float f14180c = 0.0f;
    /* renamed from: d */
    protected float f14181d = 0.0f;
    /* renamed from: e */
    protected float[] f14182e = new float[9];
    /* renamed from: f */
    protected Matrix f14183f = new Matrix();
    /* renamed from: g */
    protected final float[] f14184g = new float[9];
    /* renamed from: h */
    private float f14185h = 1.0f;
    /* renamed from: i */
    private float f14186i = Float.MAX_VALUE;
    /* renamed from: j */
    private float f14187j = 1.0f;
    /* renamed from: k */
    private float f14188k = Float.MAX_VALUE;
    /* renamed from: l */
    private float f14189l = 1.0f;
    /* renamed from: m */
    private float f14190m = 1.0f;
    /* renamed from: n */
    private float f14191n = 0.0f;
    /* renamed from: o */
    private float f14192o = 0.0f;
    /* renamed from: p */
    private float f14193p = 0.0f;
    /* renamed from: q */
    private float f14194q = 0.0f;

    /* renamed from: a */
    public void m15850a(float f, float f2) {
        float a = m15847a();
        float c = m15858c();
        float b = m15855b();
        float d = m15860d();
        this.f14181d = f2;
        this.f14180c = f;
        m15851a(a, c, b, d);
    }

    /* renamed from: a */
    public void m15851a(float f, float f2, float f3, float f4) {
        this.f14179b.set(f, f2, this.f14180c - f3, this.f14181d - f4);
    }

    /* renamed from: a */
    public float m15847a() {
        return this.f14179b.left;
    }

    /* renamed from: b */
    public float m15855b() {
        return this.f14180c - this.f14179b.right;
    }

    /* renamed from: c */
    public float m15858c() {
        return this.f14179b.top;
    }

    /* renamed from: d */
    public float m15860d() {
        return this.f14181d - this.f14179b.bottom;
    }

    /* renamed from: e */
    public float m15862e() {
        return this.f14179b.top;
    }

    /* renamed from: f */
    public float m15864f() {
        return this.f14179b.left;
    }

    /* renamed from: g */
    public float m15866g() {
        return this.f14179b.right;
    }

    /* renamed from: h */
    public float m15868h() {
        return this.f14179b.bottom;
    }

    /* renamed from: i */
    public float m15870i() {
        return this.f14179b.width();
    }

    /* renamed from: j */
    public float m15872j() {
        return this.f14179b.height();
    }

    /* renamed from: k */
    public RectF m15874k() {
        return this.f14179b;
    }

    /* renamed from: l */
    public C3279e m15876l() {
        return C3279e.m15897a(this.f14179b.centerX(), this.f14179b.centerY());
    }

    /* renamed from: m */
    public float m15878m() {
        return this.f14181d;
    }

    /* renamed from: n */
    public float m15879n() {
        return this.f14180c;
    }

    /* renamed from: o */
    public float m15880o() {
        return Math.min(this.f14179b.width(), this.f14179b.height());
    }

    /* renamed from: a */
    public void m15852a(float f, float f2, float f3, float f4, Matrix matrix) {
        matrix.reset();
        matrix.set(this.f14178a);
        matrix.postScale(f, f2, f3, f4);
    }

    /* renamed from: a */
    public void m15854a(float[] fArr, View view) {
        Matrix matrix = this.f14183f;
        matrix.reset();
        matrix.set(this.f14178a);
        matrix.postTranslate(-(fArr[0] - m15847a()), -(fArr[1] - m15858c()));
        m15848a(matrix, view, true);
    }

    /* renamed from: a */
    public Matrix m15848a(Matrix matrix, View view, boolean z) {
        this.f14178a.set(matrix);
        m15853a(this.f14178a, this.f14179b);
        if (z) {
            view.invalidate();
        }
        matrix.set(this.f14178a);
        return matrix;
    }

    /* renamed from: a */
    public void m15853a(Matrix matrix, RectF rectF) {
        float f = 0.0f;
        matrix.getValues(this.f14184g);
        float f2 = this.f14184g[2];
        float f3 = this.f14184g[0];
        float f4 = this.f14184g[5];
        float f5 = this.f14184g[4];
        this.f14189l = Math.min(Math.max(this.f14187j, f3), this.f14188k);
        this.f14190m = Math.min(Math.max(this.f14185h, f5), this.f14186i);
        if (rectF != null) {
            f3 = rectF.width();
            f = rectF.height();
        } else {
            f3 = 0.0f;
        }
        this.f14191n = Math.min(Math.max(f2, ((-f3) * (this.f14189l - 1.0f)) - this.f14193p), this.f14193p);
        this.f14192o = Math.max(Math.min(f4, (f * (this.f14190m - 1.0f)) + this.f14194q), -this.f14194q);
        this.f14184g[2] = this.f14191n;
        this.f14184g[0] = this.f14189l;
        this.f14184g[5] = this.f14192o;
        this.f14184g[4] = this.f14190m;
        matrix.setValues(this.f14184g);
    }

    /* renamed from: a */
    public void m15849a(float f) {
        if (f < 1.0f) {
            f = 1.0f;
        }
        this.f14187j = f;
        m15853a(this.f14178a, this.f14179b);
    }

    /* renamed from: b */
    public void m15856b(float f) {
        if (f == 0.0f) {
            f = Float.MAX_VALUE;
        }
        this.f14188k = f;
        m15853a(this.f14178a, this.f14179b);
    }

    /* renamed from: c */
    public void m15859c(float f) {
        if (f < 1.0f) {
            f = 1.0f;
        }
        this.f14185h = f;
        m15853a(this.f14178a, this.f14179b);
    }

    /* renamed from: d */
    public void m15861d(float f) {
        if (f == 0.0f) {
            f = Float.MAX_VALUE;
        }
        this.f14186i = f;
        m15853a(this.f14178a, this.f14179b);
    }

    /* renamed from: p */
    public Matrix m15881p() {
        return this.f14178a;
    }

    /* renamed from: e */
    public boolean m15863e(float f) {
        return m15867g(f) && m15869h(f);
    }

    /* renamed from: f */
    public boolean m15865f(float f) {
        return m15871i(f) && m15873j(f);
    }

    /* renamed from: b */
    public boolean m15857b(float f, float f2) {
        return m15863e(f) && m15865f(f2);
    }

    /* renamed from: g */
    public boolean m15867g(float f) {
        return this.f14179b.left <= 1.0f + f;
    }

    /* renamed from: h */
    public boolean m15869h(float f) {
        return this.f14179b.right >= (((float) ((int) (f * 100.0f))) / 100.0f) - 1.0f;
    }

    /* renamed from: i */
    public boolean m15871i(float f) {
        return this.f14179b.top <= f;
    }

    /* renamed from: j */
    public boolean m15873j(float f) {
        return this.f14179b.bottom >= ((float) ((int) (f * 100.0f))) / 100.0f;
    }

    /* renamed from: q */
    public float m15882q() {
        return this.f14189l;
    }

    /* renamed from: r */
    public float m15883r() {
        return this.f14190m;
    }

    /* renamed from: s */
    public boolean m15884s() {
        return m15886u() && m15885t();
    }

    /* renamed from: t */
    public boolean m15885t() {
        return this.f14190m <= this.f14185h && this.f14185h <= 1.0f;
    }

    /* renamed from: u */
    public boolean m15886u() {
        return this.f14189l <= this.f14187j && this.f14187j <= 1.0f;
    }

    /* renamed from: k */
    public void m15875k(float f) {
        this.f14193p = C3283i.m15928a(f);
    }

    /* renamed from: l */
    public void m15877l(float f) {
        this.f14194q = C3283i.m15928a(f);
    }

    /* renamed from: v */
    public boolean m15887v() {
        return this.f14193p <= 0.0f && this.f14194q <= 0.0f;
    }

    /* renamed from: w */
    public boolean m15888w() {
        return this.f14189l > this.f14187j;
    }

    /* renamed from: x */
    public boolean m15889x() {
        return this.f14189l < this.f14188k;
    }

    /* renamed from: y */
    public boolean m15890y() {
        return this.f14190m > this.f14185h;
    }

    /* renamed from: z */
    public boolean m15891z() {
        return this.f14190m < this.f14186i;
    }
}
