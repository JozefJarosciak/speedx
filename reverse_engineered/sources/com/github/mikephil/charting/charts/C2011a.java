package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.C3226a;
import com.github.mikephil.charting.p089e.p128a.C2010a;
import com.github.mikephil.charting.p127f.C3250b;
import com.github.mikephil.charting.p181d.C3211a;
import com.github.mikephil.charting.p181d.C3213d;

/* compiled from: BarChart */
/* renamed from: com.github.mikephil.charting.charts.a */
public class C2011a extends C2009b<C3226a> implements C2010a {
    /* renamed from: a */
    protected boolean f9063a = false;
    private boolean aa = true;
    private boolean ab = false;
    private boolean ac = false;

    public C2011a(Context context) {
        super(context);
    }

    public C2011a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2011a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3350a() {
        super.mo3350a();
        this.O = new C3250b(this, this.R, this.Q);
        setHighlighter(new C3211a(this));
        getXAxis().h(0.5f);
        getXAxis().i(0.5f);
    }

    /* renamed from: b */
    protected void mo3351b() {
        if (this.ac) {
            this.H.a(((C3226a) this.C).m15593g() - (((C3226a) this.C).m15599a() / 2.0f), (((C3226a) this.C).m15599a() / 2.0f) + ((C3226a) this.C).m15594h());
        } else {
            this.H.a(((C3226a) this.C).m15593g(), ((C3226a) this.C).m15594h());
        }
        this.o.m15399a(((C3226a) this.C).m15574a(AxisDependency.LEFT), ((C3226a) this.C).m15581b(AxisDependency.LEFT));
        this.p.m15399a(((C3226a) this.C).m15574a(AxisDependency.RIGHT), ((C3226a) this.C).m15581b(AxisDependency.RIGHT));
    }

    /* renamed from: a */
    public C3213d mo3892a(float f, float f2) {
        if (this.C == null) {
            Log.e("MPAndroidChart", "Can't select by touch. No data set.");
            return null;
        }
        C3213d a = getHighlighter().mo3906a(f, f2);
        if (a == null || !mo3354e()) {
            return a;
        }
        return new C3213d(a.m15423a(), a.m15427b(), a.m15428c(), a.m15429d(), a.m15431f(), -1, a.m15433h());
    }

    public void setDrawValueAboveBar(boolean z) {
        this.aa = z;
    }

    /* renamed from: c */
    public boolean mo3352c() {
        return this.aa;
    }

    public void setDrawBarShadow(boolean z) {
        this.ab = z;
    }

    /* renamed from: d */
    public boolean mo3353d() {
        return this.ab;
    }

    public void setHighlightFullBarEnabled(boolean z) {
        this.f9063a = z;
    }

    /* renamed from: e */
    public boolean mo3354e() {
        return this.f9063a;
    }

    public C3226a getBarData() {
        return (C3226a) this.C;
    }

    public void setFitBars(boolean z) {
        this.ac = z;
    }
}
