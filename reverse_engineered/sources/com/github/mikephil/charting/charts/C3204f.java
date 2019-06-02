package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.AttributeSet;
import com.avos.avoscloud.AVException;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.C3242p;
import com.github.mikephil.charting.p089e.p090b.C3245j;
import com.github.mikephil.charting.p127f.C3265n;
import com.github.mikephil.charting.p127f.C3268s;
import com.github.mikephil.charting.p127f.C3271v;
import com.github.mikephil.charting.p181d.C3217i;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: RadarChart */
/* renamed from: com.github.mikephil.charting.charts.f */
public class C3204f extends C3203e<C3242p> {
    /* renamed from: a */
    protected C3271v f13886a;
    /* renamed from: b */
    protected C3268s f13887b;
    /* renamed from: e */
    private float f13888e = 2.5f;
    /* renamed from: f */
    private float f13889f = 1.5f;
    /* renamed from: g */
    private int f13890g = Color.rgb(AVException.INVALID_FILE_NAME, AVException.INVALID_FILE_NAME, AVException.INVALID_FILE_NAME);
    /* renamed from: h */
    private int f13891h = Color.rgb(AVException.INVALID_FILE_NAME, AVException.INVALID_FILE_NAME, AVException.INVALID_FILE_NAME);
    /* renamed from: i */
    private int f13892i = 150;
    /* renamed from: j */
    private boolean f13893j = true;
    /* renamed from: k */
    private int f13894k = 0;
    /* renamed from: l */
    private YAxis f13895l;

    public C3204f(Context context) {
        super(context);
    }

    public C3204f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C3204f(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3898a() {
        super.mo3898a();
        this.f13895l = new YAxis(AxisDependency.LEFT);
        this.f13888e = C3283i.m15928a(1.5f);
        this.f13889f = C3283i.m15928a(0.75f);
        this.O = new C3265n(this, this.R, this.Q);
        this.f13886a = new C3271v(this.Q, this.f13895l, this);
        this.f13887b = new C3268s(this.Q, this.H, this);
        this.P = new C3217i(this);
    }

    /* renamed from: b */
    protected void mo3899b() {
        super.mo3899b();
        this.f13895l.m15399a(((C3242p) this.C).m15574a(AxisDependency.LEFT), ((C3242p) this.C).m15581b(AxisDependency.LEFT));
        this.H.a(0.0f, (float) ((C3245j) ((C3242p) this.C).m15598l()).mo3938F());
    }

    /* renamed from: h */
    public void mo3905h() {
        if (this.C != null) {
            mo3899b();
            this.f13886a.mo3340a(this.f13895l.t, this.f13895l.s, this.f13895l.m15391H());
            this.f13887b.mo3340a(this.H.t, this.H.s, false);
            if (!(this.K == null || this.K.m15352c())) {
                this.N.m15758a(this.C);
            }
            m15331j();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.C != null) {
            if (this.H.A()) {
                this.f13887b.mo3340a(this.H.t, this.H.s, false);
            }
            this.f13887b.mo4004a(canvas);
            if (this.f13893j) {
                this.O.mo3998c(canvas);
            }
            if (this.f13895l.A() && this.f13895l.o()) {
                this.f13886a.mo4017e(canvas);
            }
            this.O.mo3995a(canvas);
            if (w()) {
                this.O.mo3996a(canvas, this.S);
            }
            if (this.f13895l.A() && !this.f13895l.o()) {
                this.f13886a.mo4017e(canvas);
            }
            this.f13886a.mo4011a(canvas);
            this.O.mo3997b(canvas);
            this.N.m15755a(canvas);
            b(canvas);
            c(canvas);
        }
    }

    public float getFactor() {
        RectF k = this.Q.m15874k();
        return Math.min(k.width() / 2.0f, k.height() / 2.0f) / this.f13895l.u;
    }

    public float getSliceAngle() {
        return 360.0f / ((float) ((C3245j) ((C3242p) this.C).m15598l()).mo3938F());
    }

    /* renamed from: a */
    public int mo3897a(float f) {
        float c = C3283i.m15948c(f - getRotationAngle());
        float sliceAngle = getSliceAngle();
        int F = ((C3245j) ((C3242p) this.C).m15598l()).mo3938F();
        for (int i = 0; i < F; i++) {
            if ((((float) (i + 1)) * sliceAngle) - (sliceAngle / 2.0f) > c) {
                return i;
            }
        }
        return 0;
    }

    public YAxis getYAxis() {
        return this.f13895l;
    }

    public void setWebLineWidth(float f) {
        this.f13888e = C3283i.m15928a(f);
    }

    public float getWebLineWidth() {
        return this.f13888e;
    }

    public void setWebLineWidthInner(float f) {
        this.f13889f = C3283i.m15928a(f);
    }

    public float getWebLineWidthInner() {
        return this.f13889f;
    }

    public void setWebAlpha(int i) {
        this.f13892i = i;
    }

    public int getWebAlpha() {
        return this.f13892i;
    }

    public void setWebColor(int i) {
        this.f13890g = i;
    }

    public int getWebColor() {
        return this.f13890g;
    }

    public void setWebColorInner(int i) {
        this.f13891h = i;
    }

    public int getWebColorInner() {
        return this.f13891h;
    }

    public void setDrawWeb(boolean z) {
        this.f13893j = z;
    }

    public void setSkipWebLineCount(int i) {
        this.f13894k = Math.max(0, i);
    }

    public int getSkipWebLineCount() {
        return this.f13894k;
    }

    protected float getRequiredLegendOffset() {
        return this.N.m15754a().getTextSize() * 4.0f;
    }

    protected float getRequiredBaseOffset() {
        if (this.H.A() && this.H.h()) {
            return (float) this.H.f13938D;
        }
        return C3283i.m15928a(10.0f);
    }

    public float getRadius() {
        RectF k = this.Q.m15874k();
        return Math.min(k.width() / 2.0f, k.height() / 2.0f);
    }

    public float getYChartMax() {
        return this.f13895l.s;
    }

    public float getYChartMin() {
        return this.f13895l.t;
    }

    public float getYRange() {
        return this.f13895l.u;
    }
}
