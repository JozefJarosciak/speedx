package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1970b;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1972d;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.p121c.C1968d;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BaseLineChartView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.c */
public class C2026c extends C2024b {
    /* renamed from: d */
    protected Drawable f9168d;
    /* renamed from: e */
    protected int f9169e;
    /* renamed from: f */
    protected float f9170f;
    /* renamed from: g */
    protected RelativeLayout f9171g;
    /* renamed from: h */
    private float f9172h;
    /* renamed from: i */
    private float f9173i;
    /* renamed from: j */
    private float f9174j;
    /* renamed from: k */
    private float f9175k;
    /* renamed from: l */
    private float f9176l;
    /* renamed from: m */
    private long f9177m;
    /* renamed from: n */
    private LineChart f9178n;
    /* renamed from: o */
    private C1968d f9179o;

    public C2026c(Context context) {
        super(context);
    }

    public C2026c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2026c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3358a(Context context, AttributeSet attributeSet) {
        super.mo3358a(context, attributeSet);
        ((ViewStub) findViewById(C1373R.id.viewStub_line_chart)).inflate();
        this.f9178n = (LineChart) findViewById(C1373R.id.line_chart);
        this.f9178n.setNoDataText("");
    }

    /* renamed from: a */
    public void mo3356a() {
        this.f9178n.setTouchEnabled(false);
        this.f9178n.setDragEnabled(false);
        this.f9178n.setScaleEnabled(false);
        this.f9178n.setPinchZoom(false);
        this.f9178n.setUnbindEnabled(true);
        this.f9178n.getDescription().m15338e(false);
        this.f9178n.setExtraBottomOffset(10.0f);
        XAxis xAxis = this.f9178n.getXAxis();
        xAxis.a(10.0f, 10.0f, 0.0f);
        xAxis.a(false);
        xAxis.a(6, true);
        xAxis.c(true);
        xAxis.b(Color.parseColor("#333333"));
        xAxis.e(-1);
        xAxis.k(10.0f);
        xAxis.m15384a(XAxisPosition.BOTTOM);
        xAxis.j(8.0f);
        if (this.f9174j != 0.0f) {
            xAxis.f(this.f9174j);
        }
        if (this.f9179o == null) {
            xAxis.a(new C1970b(this.b, this.f9176l));
        } else {
            xAxis.a(this.f9179o);
        }
        LimitLine limitLine = new LimitLine(this.f9170f);
        limitLine.m15371a(0.5f);
        limitLine.m15372a(15.0f, 15.0f, 0.0f);
        limitLine.m15374a(LimitLabelPosition.LEFT_TOP);
        limitLine.m15373a(-1);
        YAxis axisLeft = this.f9178n.getAxisLeft();
        axisLeft.m();
        axisLeft.a(limitLine);
        if (this.f9172h != 0.0f) {
            axisLeft.f(this.f9172h);
        }
        axisLeft.d(this.f9173i);
        axisLeft.j(0.0f);
        axisLeft.a(6, true);
        axisLeft.a(1.0f, 10.0f, 0.0f);
        axisLeft.m15401f(false);
        axisLeft.b(true);
        axisLeft.a(0.5f);
        axisLeft.b(-14540254);
        axisLeft.a(new C1972d());
        axisLeft.k(10.0f);
        axisLeft.e(-1);
        axisLeft.d(false);
        this.f9178n.getAxisRight().e(false);
        this.f9178n.getLegend().m15338e(false);
    }

    public void setData(ArrayList<Entry> arrayList) {
        if (arrayList == null) {
            this.f9178n.setData(null);
        } else if (this.f9178n.getData() == null || ((C3238l) this.f9178n.getData()).m15590d() <= 0) {
            LineDataSet lineDataSet = new LineDataSet(arrayList, this.b);
            lineDataSet.c(this.f9169e);
            lineDataSet.h(this.f9169e);
            lineDataSet.e(2.0f);
            lineDataSet.c(false);
            lineDataSet.b(false);
            lineDataSet.e(true);
            if (C3283i.m15951d() >= 18) {
                lineDataSet.a(this.f9168d);
            } else {
                lineDataSet.j(ViewCompat.MEASURED_STATE_MASK);
            }
            List arrayList2 = new ArrayList();
            arrayList2.add(lineDataSet);
            this.f9178n.setData(new C3238l(arrayList2));
            this.f9178n.invalidate();
        } else {
            ((LineDataSet) ((C3238l) this.f9178n.getData()).mo3993a(0)).b(arrayList);
            ((C3238l) this.f9178n.getData()).mo3985b();
            this.f9178n.m10365h();
        }
    }

    /* renamed from: e */
    public boolean mo3360e() {
        if (this.f9171g != null) {
            return this.f9171g.getVisibility() != 0;
        } else {
            return super.mo3360e();
        }
    }

    public void setXValueFormatter(C1968d c1968d) {
        this.f9179o = c1968d;
    }

    public void setFillDrawable(Drawable drawable) {
        this.f9168d = drawable;
    }

    public void setLineColor(int i) {
        this.f9169e = i;
    }

    public void setAverageLineValue(float f) {
        this.f9170f = f;
    }

    public void setYMaxValue(float f) {
        this.f9172h = f;
    }

    public void setYMinValue(float f) {
        this.f9173i = f;
    }

    public void setXMinValue(float f) {
        this.f9175k = f;
    }

    public void setXMaxValue(float f) {
        this.f9174j = f;
    }

    public void setTotalDistance(float f) {
        this.f9176l = f;
    }

    public void setTotalTime(long j) {
        this.f9177m = j;
    }
}
