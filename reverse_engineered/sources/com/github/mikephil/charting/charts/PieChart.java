package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.C3241o;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.p089e.p090b.C3223i;
import com.github.mikephil.charting.p127f.C3264m;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p181d.C3216g;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

public class PieChart extends C3203e<C3241o> {
    /* renamed from: a */
    protected float f6921a;
    /* renamed from: b */
    protected float f6922b;
    /* renamed from: e */
    private RectF f6923e;
    /* renamed from: f */
    private boolean f6924f;
    /* renamed from: g */
    private float[] f6925g;
    /* renamed from: h */
    private float[] f6926h;
    /* renamed from: i */
    private boolean f6927i;
    /* renamed from: j */
    private boolean f6928j;
    /* renamed from: k */
    private boolean f6929k;
    /* renamed from: l */
    private boolean f6930l;
    /* renamed from: m */
    private CharSequence f6931m;
    /* renamed from: n */
    private C3279e f6932n;
    /* renamed from: o */
    private float f6933o;
    /* renamed from: p */
    private boolean f6934p;
    /* renamed from: q */
    private float f6935q;

    public PieChart(Context context) {
        super(context);
        this.f6923e = new RectF();
        this.f6924f = true;
        this.f6925g = new float[1];
        this.f6926h = new float[1];
        this.f6927i = true;
        this.f6928j = false;
        this.f6929k = false;
        this.f6930l = false;
        this.f6931m = "";
        this.f6932n = C3279e.a(0.0f, 0.0f);
        this.f6933o = 50.0f;
        this.f6921a = 55.0f;
        this.f6934p = true;
        this.f6935q = 100.0f;
        this.f6922b = 360.0f;
    }

    public PieChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6923e = new RectF();
        this.f6924f = true;
        this.f6925g = new float[1];
        this.f6926h = new float[1];
        this.f6927i = true;
        this.f6928j = false;
        this.f6929k = false;
        this.f6930l = false;
        this.f6931m = "";
        this.f6932n = C3279e.a(0.0f, 0.0f);
        this.f6933o = 50.0f;
        this.f6921a = 55.0f;
        this.f6934p = true;
        this.f6935q = 100.0f;
        this.f6922b = 360.0f;
    }

    public PieChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6923e = new RectF();
        this.f6924f = true;
        this.f6925g = new float[1];
        this.f6926h = new float[1];
        this.f6927i = true;
        this.f6928j = false;
        this.f6929k = false;
        this.f6930l = false;
        this.f6931m = "";
        this.f6932n = C3279e.a(0.0f, 0.0f);
        this.f6933o = 50.0f;
        this.f6921a = 55.0f;
        this.f6934p = true;
        this.f6935q = 100.0f;
        this.f6922b = 360.0f;
    }

    /* renamed from: a */
    protected void m8142a() {
        super.a();
        this.O = new C3264m(this, this.R, this.Q);
        this.H = null;
        this.P = new C3216g(this);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.C != null) {
            this.O.a(canvas);
            if (w()) {
                this.O.a(canvas, this.S);
            }
            this.O.c(canvas);
            this.O.b(canvas);
            this.N.a(canvas);
            b(canvas);
            c(canvas);
        }
    }

    /* renamed from: j */
    public void m8151j() {
        super.j();
        if (this.C != null) {
            float diameter = getDiameter() / 2.0f;
            C3279e centerOffsets = getCenterOffsets();
            float c = ((C3241o) this.C).a().c();
            this.f6923e.set((centerOffsets.f14200a - diameter) + c, (centerOffsets.f14201b - diameter) + c, (centerOffsets.f14200a + diameter) - c, (diameter + centerOffsets.f14201b) - c);
            C3279e.b(centerOffsets);
        }
    }

    /* renamed from: b */
    protected void m8144b() {
        m8140k();
    }

    /* renamed from: b */
    protected float[] m8145b(C3213d c3213d) {
        C3279e centerCircleBox = getCenterCircleBox();
        float radius = getRadius();
        float f = (radius / 10.0f) * 3.6f;
        if (m8147d()) {
            f = (radius - ((radius / 100.0f) * getHoleRadius())) / 2.0f;
        }
        f = radius - f;
        radius = getRotationAngle();
        int a = (int) c3213d.a();
        float f2 = this.f6925g[a] / 2.0f;
        float cos = (float) ((((double) f) * Math.cos(Math.toRadians((double) (((this.f6926h[a] + radius) - f2) * this.R.a())))) + ((double) centerCircleBox.f14200a));
        f = (float) ((Math.sin(Math.toRadians((double) (((this.f6926h[a] + radius) - f2) * this.R.a()))) * ((double) f)) + ((double) centerCircleBox.f14201b));
        C3279e.b(centerCircleBox);
        return new float[]{cos, f};
    }

    /* renamed from: k */
    private void m8140k() {
        int i;
        int k = ((C3241o) this.C).k();
        if (this.f6925g.length != k) {
            this.f6925g = new float[k];
        } else {
            for (i = 0; i < k; i++) {
                this.f6925g[i] = 0.0f;
            }
        }
        if (this.f6926h.length != k) {
            this.f6926h = new float[k];
        } else {
            for (i = 0; i < k; i++) {
                this.f6926h[i] = 0.0f;
            }
        }
        float m = ((C3241o) this.C).m();
        List i2 = ((C3241o) this.C).i();
        int i3 = 0;
        k = 0;
        while (i3 < ((C3241o) this.C).d()) {
            C3223i c3223i = (C3223i) i2.get(i3);
            int i4 = k;
            for (int i5 = 0; i5 < c3223i.F(); i5++) {
                this.f6925g[i4] = m8139e(Math.abs(((PieEntry) c3223i.g(i5)).b()), m);
                if (i4 == 0) {
                    this.f6926h[i4] = this.f6925g[i4];
                } else {
                    this.f6926h[i4] = this.f6926h[i4 - 1] + this.f6925g[i4];
                }
                i4++;
            }
            i3++;
            k = i4;
        }
    }

    /* renamed from: a */
    public boolean m8143a(int i) {
        if (!w()) {
            return false;
        }
        for (C3213d a : this.S) {
            if (((int) a.a()) == i) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: e */
    private float m8139e(float f, float f2) {
        return (f / f2) * this.f6922b;
    }

    @Deprecated
    public XAxis getXAxis() {
        throw new RuntimeException("PieChart has no XAxis");
    }

    /* renamed from: a */
    public int m8141a(float f) {
        float c = C3283i.c(f - getRotationAngle());
        for (int i = 0; i < this.f6926h.length; i++) {
            if (this.f6926h[i] > c) {
                return i;
            }
        }
        return -1;
    }

    public float[] getDrawAngles() {
        return this.f6925g;
    }

    public float[] getAbsoluteAngles() {
        return this.f6926h;
    }

    public void setHoleColor(int i) {
        ((C3264m) this.O).b().setColor(i);
    }

    public void setDrawSlicesUnderHole(boolean z) {
        this.f6928j = z;
    }

    /* renamed from: c */
    public boolean m8146c() {
        return this.f6928j;
    }

    public void setDrawHoleEnabled(boolean z) {
        this.f6927i = z;
    }

    /* renamed from: d */
    public boolean m8147d() {
        return this.f6927i;
    }

    public void setCenterText(CharSequence charSequence) {
        if (charSequence == null) {
            this.f6931m = "";
        } else {
            this.f6931m = charSequence;
        }
    }

    public CharSequence getCenterText() {
        return this.f6931m;
    }

    public void setDrawCenterText(boolean z) {
        this.f6934p = z;
    }

    /* renamed from: e */
    public boolean m8148e() {
        return this.f6934p;
    }

    protected float getRequiredLegendOffset() {
        return this.N.a().getTextSize() * 2.0f;
    }

    protected float getRequiredBaseOffset() {
        return 0.0f;
    }

    public float getRadius() {
        if (this.f6923e == null) {
            return 0.0f;
        }
        return Math.min(this.f6923e.width() / 2.0f, this.f6923e.height() / 2.0f);
    }

    public RectF getCircleBox() {
        return this.f6923e;
    }

    public C3279e getCenterCircleBox() {
        return C3279e.a(this.f6923e.centerX(), this.f6923e.centerY());
    }

    public void setCenterTextTypeface(Typeface typeface) {
        ((C3264m) this.O).d().setTypeface(typeface);
    }

    public void setCenterTextSize(float f) {
        ((C3264m) this.O).d().setTextSize(C3283i.a(f));
    }

    public void setCenterTextSizePixels(float f) {
        ((C3264m) this.O).d().setTextSize(f);
    }

    public C3279e getCenterTextOffset() {
        return C3279e.a(this.f6932n.f14200a, this.f6932n.f14201b);
    }

    public void setCenterTextColor(int i) {
        ((C3264m) this.O).d().setColor(i);
    }

    public void setHoleRadius(float f) {
        this.f6933o = f;
    }

    public float getHoleRadius() {
        return this.f6933o;
    }

    public void setTransparentCircleColor(int i) {
        Paint c = ((C3264m) this.O).c();
        int alpha = c.getAlpha();
        c.setColor(i);
        c.setAlpha(alpha);
    }

    public void setTransparentCircleRadius(float f) {
        this.f6921a = f;
    }

    public float getTransparentCircleRadius() {
        return this.f6921a;
    }

    public void setTransparentCircleAlpha(int i) {
        ((C3264m) this.O).c().setAlpha(i);
    }

    @Deprecated
    public void setDrawSliceText(boolean z) {
        this.f6924f = z;
    }

    public void setDrawEntryLabels(boolean z) {
        this.f6924f = z;
    }

    /* renamed from: f */
    public boolean m8149f() {
        return this.f6924f;
    }

    public void setEntryLabelColor(int i) {
        ((C3264m) this.O).e().setColor(i);
    }

    public void setEntryLabelTypeface(Typeface typeface) {
        ((C3264m) this.O).e().setTypeface(typeface);
    }

    public void setEntryLabelTextSize(float f) {
        ((C3264m) this.O).e().setTextSize(C3283i.a(f));
    }

    public void setUsePercentValues(boolean z) {
        this.f6929k = z;
    }

    /* renamed from: g */
    public boolean m8150g() {
        return this.f6929k;
    }

    public void setCenterTextRadiusPercent(float f) {
        this.f6935q = f;
    }

    public float getCenterTextRadiusPercent() {
        return this.f6935q;
    }

    public float getMaxAngle() {
        return this.f6922b;
    }

    public void setMaxAngle(float f) {
        float f2 = 360.0f;
        float f3 = 90.0f;
        if (f <= 360.0f) {
            f2 = f;
        }
        if (f2 >= 90.0f) {
            f3 = f2;
        }
        this.f6922b = f3;
    }

    protected void onDetachedFromWindow() {
        if (this.O != null && (this.O instanceof C3264m)) {
            ((C3264m) this.O).f();
        }
        super.onDetachedFromWindow();
    }
}
