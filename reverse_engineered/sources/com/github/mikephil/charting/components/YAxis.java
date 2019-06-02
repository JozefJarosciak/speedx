package com.github.mikephil.charting.components;

import android.graphics.Paint;
import com.github.mikephil.charting.p183g.C3283i;

public class YAxis extends C1476a {
    /* renamed from: B */
    protected boolean f13943B;
    /* renamed from: C */
    protected boolean f13944C;
    /* renamed from: D */
    protected int f13945D;
    /* renamed from: E */
    protected float f13946E;
    /* renamed from: F */
    protected float f13947F;
    /* renamed from: G */
    protected float f13948G;
    /* renamed from: H */
    protected float f13949H;
    /* renamed from: I */
    protected float f13950I;
    /* renamed from: J */
    private boolean f13951J;
    /* renamed from: K */
    private boolean f13952K;
    /* renamed from: L */
    private YAxisLabelPosition f13953L;
    /* renamed from: M */
    private AxisDependency f13954M;

    public enum AxisDependency {
        LEFT,
        RIGHT
    }

    public enum YAxisLabelPosition {
        OUTSIDE_CHART,
        INSIDE_CHART
    }

    public YAxis() {
        this.f13951J = true;
        this.f13952K = true;
        this.f13943B = false;
        this.f13944C = false;
        this.f13945D = -7829368;
        this.f13946E = 1.0f;
        this.f13947F = 10.0f;
        this.f13948G = 10.0f;
        this.f13953L = YAxisLabelPosition.OUTSIDE_CHART;
        this.f13949H = 0.0f;
        this.f13950I = Float.POSITIVE_INFINITY;
        this.f13954M = AxisDependency.LEFT;
        this.x = 0.0f;
    }

    public YAxis(AxisDependency axisDependency) {
        this.f13951J = true;
        this.f13952K = true;
        this.f13943B = false;
        this.f13944C = false;
        this.f13945D = -7829368;
        this.f13946E = 1.0f;
        this.f13947F = 10.0f;
        this.f13948G = 10.0f;
        this.f13953L = YAxisLabelPosition.OUTSIDE_CHART;
        this.f13949H = 0.0f;
        this.f13950I = Float.POSITIVE_INFINITY;
        this.f13954M = axisDependency;
        this.x = 0.0f;
    }

    /* renamed from: B */
    public AxisDependency m15385B() {
        return this.f13954M;
    }

    /* renamed from: C */
    public float m15386C() {
        return this.f13949H;
    }

    /* renamed from: D */
    public float m15387D() {
        return this.f13950I;
    }

    /* renamed from: E */
    public YAxisLabelPosition m15388E() {
        return this.f13953L;
    }

    /* renamed from: F */
    public boolean m15389F() {
        return this.f13952K;
    }

    /* renamed from: G */
    public boolean m15390G() {
        return this.f13951J;
    }

    /* renamed from: H */
    public boolean m15391H() {
        return this.f13943B;
    }

    /* renamed from: I */
    public float m15392I() {
        return this.f13947F;
    }

    /* renamed from: l */
    public void m15402l(float f) {
        this.f13948G = f;
    }

    /* renamed from: J */
    public float m15393J() {
        return this.f13948G;
    }

    /* renamed from: K */
    public boolean m15394K() {
        return this.f13944C;
    }

    /* renamed from: f */
    public void m15401f(boolean z) {
        this.f13944C = z;
    }

    /* renamed from: L */
    public int m15395L() {
        return this.f13945D;
    }

    /* renamed from: M */
    public float m15396M() {
        return this.f13946E;
    }

    /* renamed from: a */
    public float m15398a(Paint paint) {
        paint.setTextSize(this.z);
        float v = (v() * 2.0f) + ((float) C3283i.m15931a(paint, p()));
        float C = m15386C();
        float D = m15387D();
        if (C > 0.0f) {
            C = C3283i.m15928a(C);
        }
        if (D > 0.0f && D != Float.POSITIVE_INFINITY) {
            D = C3283i.m15928a(D);
        }
        if (((double) D) <= 0.0d) {
            D = v;
        }
        return Math.max(C, Math.min(v, D));
    }

    /* renamed from: b */
    public float m15400b(Paint paint) {
        paint.setTextSize(this.z);
        return ((float) C3283i.m15946b(paint, p())) + (w() * 2.0f);
    }

    /* renamed from: N */
    public boolean m15397N() {
        if (A() && h() && m15388E() == YAxisLabelPosition.OUTSIDE_CHART) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void m15399a(float f, float f2) {
        if (this.q) {
            f = this.t;
        }
        if (this.r) {
            f2 = this.s;
        }
        float abs = Math.abs(f2 - f);
        if (abs == 0.0f) {
            f2 += 1.0f;
            f -= 1.0f;
        }
        if (!this.q) {
            this.t = f - ((abs / 100.0f) * m15393J());
        }
        if (!this.r) {
            this.s = ((abs / 100.0f) * m15392I()) + f2;
        }
        this.u = Math.abs(this.s - this.t);
    }
}
