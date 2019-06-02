package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import com.github.mikephil.charting.data.C3226a;
import com.github.mikephil.charting.data.C3231g;
import com.github.mikephil.charting.data.C3232h;
import com.github.mikephil.charting.data.C3237k;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.data.C3243q;
import com.github.mikephil.charting.p089e.p128a.C3198f;
import com.github.mikephil.charting.p127f.C3256f;
import com.github.mikephil.charting.p181d.C3212c;
import com.github.mikephil.charting.p181d.C3213d;

public class CombinedChart extends C2009b<C3237k> implements C3198f {
    /* renamed from: a */
    protected boolean f13869a = false;
    protected DrawOrder[] aa;
    private boolean ab = true;
    private boolean ac = false;

    public enum DrawOrder {
        BAR,
        BUBBLE,
        LINE,
        CANDLE,
        SCATTER
    }

    public CombinedChart(Context context) {
        super(context);
    }

    public CombinedChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CombinedChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3350a() {
        super.mo3350a();
        this.aa = new DrawOrder[]{DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.LINE, DrawOrder.CANDLE, DrawOrder.SCATTER};
        setHighlighter(new C3212c(this, this));
        setHighlightFullBarEnabled(true);
        this.O = new C3256f(this, this.R, this.Q);
    }

    public C3237k getCombinedData() {
        return (C3237k) this.C;
    }

    public void setData(C3237k c3237k) {
        super.setData(c3237k);
        setHighlighter(new C3212c(this, this));
        ((C3256f) this.O).m15744b();
        this.O.mo3994a();
    }

    /* renamed from: a */
    public C3213d m15311a(float f, float f2) {
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

    public C3238l getLineData() {
        if (this.C == null) {
            return null;
        }
        return ((C3237k) this.C).m15669m();
    }

    public C3226a getBarData() {
        if (this.C == null) {
            return null;
        }
        return ((C3237k) this.C).m15670n();
    }

    public C3243q getScatterData() {
        if (this.C == null) {
            return null;
        }
        return ((C3237k) this.C).m15671o();
    }

    public C3232h getCandleData() {
        if (this.C == null) {
            return null;
        }
        return ((C3237k) this.C).m15672p();
    }

    public C3231g getBubbleData() {
        if (this.C == null) {
            return null;
        }
        return ((C3237k) this.C).m15662a();
    }

    /* renamed from: d */
    public boolean mo3353d() {
        return this.ac;
    }

    /* renamed from: c */
    public boolean mo3352c() {
        return this.ab;
    }

    public void setDrawValueAboveBar(boolean z) {
        this.ab = z;
    }

    public void setDrawBarShadow(boolean z) {
        this.ac = z;
    }

    public void setHighlightFullBarEnabled(boolean z) {
        this.f13869a = z;
    }

    /* renamed from: e */
    public boolean mo3354e() {
        return this.f13869a;
    }

    public DrawOrder[] getDrawOrder() {
        return this.aa;
    }

    public void setDrawOrder(DrawOrder[] drawOrderArr) {
        if (drawOrderArr != null && drawOrderArr.length > 0) {
            this.aa = drawOrderArr;
        }
    }
}
