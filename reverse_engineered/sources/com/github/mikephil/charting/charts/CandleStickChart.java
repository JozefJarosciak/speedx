package com.github.mikephil.charting.charts;

import android.content.Context;
import android.util.AttributeSet;
import com.github.mikephil.charting.data.C3232h;
import com.github.mikephil.charting.p089e.p128a.C3194d;
import com.github.mikephil.charting.p127f.C3254e;

public class CandleStickChart extends C2009b<C3232h> implements C3194d {
    public CandleStickChart(Context context) {
        super(context);
    }

    public CandleStickChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CandleStickChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3350a() {
        super.mo3350a();
        this.O = new C3254e(this, this.R, this.Q);
        getXAxis().h(0.5f);
        getXAxis().i(0.5f);
    }

    public C3232h getCandleData() {
        return (C3232h) this.C;
    }
}
