package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.p127f.C3257h;
import com.github.mikephil.charting.p127f.C3267r;
import com.github.mikephil.charting.p127f.C3270u;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p181d.C3214e;
import com.github.mikephil.charting.p183g.C3276c;
import com.github.mikephil.charting.p183g.C3282h;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: HorizontalBarChart */
/* renamed from: com.github.mikephil.charting.charts.d */
public class C3201d extends C2011a {
    protected float[] aa;
    private RectF ab;

    public C3201d(Context context) {
        super(context);
        this.ab = new RectF();
        this.aa = new float[2];
    }

    public C3201d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ab = new RectF();
        this.aa = new float[2];
    }

    public C3201d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ab = new RectF();
        this.aa = new float[2];
    }

    /* renamed from: a */
    protected void mo3350a() {
        this.Q = new C3276c();
        super.mo3350a();
        this.s = new C3282h(this.Q);
        this.t = new C3282h(this.Q);
        this.O = new C3257h(this, this.R, this.Q);
        setHighlighter(new C3214e(this));
        this.q = new C3270u(this.Q, this.o, this.s);
        this.r = new C3270u(this.Q, this.p, this.t);
        this.u = new C3267r(this.Q, this.H, this.s, this);
    }

    /* renamed from: j */
    public void mo3894j() {
        m10357a(this.ab);
        float f = 0.0f + this.ab.left;
        float f2 = this.ab.top + 0.0f;
        float f3 = 0.0f + this.ab.right;
        float f4 = this.ab.bottom + 0.0f;
        if (this.o.m15397N()) {
            f2 += this.o.m15400b(this.q.m10333a());
        }
        if (this.p.m15397N()) {
            f4 += this.p.m15400b(this.r.m10333a());
        }
        float f5 = (float) this.H.f13938D;
        if (this.H.A()) {
            if (this.H.m15381B() == XAxisPosition.BOTTOM) {
                f += f5;
            } else if (this.H.m15381B() == XAxisPosition.TOP) {
                f3 += f5;
            } else if (this.H.m15381B() == XAxisPosition.BOTH_SIDED) {
                f += f5;
                f3 += f5;
            }
        }
        f2 += getExtraTopOffset();
        f3 += getExtraRightOffset();
        f4 += getExtraBottomOffset();
        f += getExtraLeftOffset();
        f5 = C3283i.m15928a(this.l);
        this.Q.m15851a(Math.max(f5, f), Math.max(f5, f2), Math.max(f5, f3), Math.max(f5, f4));
        if (this.B) {
            Log.i("MPAndroidChart", "offsetLeft: " + f + ", offsetTop: " + f2 + ", offsetRight: " + f3 + ", offsetBottom: " + f4);
            Log.i("MPAndroidChart", "Content: " + this.Q.m15874k().toString());
        }
        m10364g();
        mo3893f();
    }

    /* renamed from: f */
    protected void mo3893f() {
        this.t.m15912a(this.p.t, this.p.u, this.H.u, this.H.t);
        this.s.m15912a(this.o.t, this.o.u, this.H.u, this.H.t);
    }

    /* renamed from: b */
    protected float[] m15319b(C3213d c3213d) {
        return new float[]{c3213d.m15435j(), c3213d.m15434i()};
    }

    /* renamed from: a */
    public C3213d mo3892a(float f, float f2) {
        if (this.C != null) {
            return getHighlighter().mo3906a(f2, f);
        }
        if (this.B) {
            Log.e("MPAndroidChart", "Can't select by touch. No data set.");
        }
        return null;
    }

    public float getLowestVisibleX() {
        mo3343a(AxisDependency.LEFT).m15913a(this.Q.m15864f(), this.Q.m15868h(), this.y);
        return (float) Math.max((double) this.H.t, this.y.f14197b);
    }

    public float getHighestVisibleX() {
        mo3343a(AxisDependency.LEFT).m15913a(this.Q.m15864f(), this.Q.m15862e(), this.z);
        return (float) Math.min((double) this.H.s, this.z.f14197b);
    }

    public void setVisibleXRangeMaximum(float f) {
        this.Q.m15859c(this.H.u / f);
    }

    public void setVisibleXRangeMinimum(float f) {
        this.Q.m15861d(this.H.u / f);
    }
}
