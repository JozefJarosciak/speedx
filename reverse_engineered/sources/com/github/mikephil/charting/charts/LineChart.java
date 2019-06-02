package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.p089e.p128a.C3196g;
import com.github.mikephil.charting.p127f.C3263j;

public class LineChart extends C2009b<C3238l> implements C3196g {
    public LineChart(Context context) {
        super(context);
    }

    public LineChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LineChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3350a() {
        super.mo3350a();
        this.O = new C3263j(this, this.R, this.Q);
    }

    public C3238l getLineData() {
        return (C3238l) this.C;
    }

    protected void onDetachedFromWindow() {
        if (this.O != null && (this.O instanceof C3263j)) {
            ((C3263j) this.O).mo4003b();
        }
        super.onDetachedFromWindow();
    }
}
