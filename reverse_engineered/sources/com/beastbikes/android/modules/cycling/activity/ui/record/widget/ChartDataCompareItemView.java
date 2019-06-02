package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.widget.NumberTextView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.C3224j;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.p183g.C3272a;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;

public class ChartDataCompareItemView extends RelativeLayout {
    /* renamed from: a */
    private NumberTextView f9003a;
    /* renamed from: b */
    private TextView f9004b;
    /* renamed from: c */
    private LineChart f9005c;
    /* renamed from: d */
    private int f9006d;

    public ChartDataCompareItemView(Context context) {
        super(context);
        m10324a(context, null);
    }

    public ChartDataCompareItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10324a(context, attributeSet);
    }

    public ChartDataCompareItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10324a(context, attributeSet);
    }

    /* renamed from: a */
    private void m10324a(Context context, AttributeSet attributeSet) {
        inflate(context, C1373R.layout.layout_chart_data_compare_item, this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ChartDataCompareItemView);
        this.f9006d = obtainStyledAttributes.getColor(1, -1);
        Drawable drawable = obtainStyledAttributes.getDrawable(0);
        this.f9003a = (NumberTextView) findViewById(C1373R.id.tv_chart_data_compare_value);
        this.f9003a.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        this.f9004b = (TextView) findViewById(C1373R.id.tv_chart_data_compare_unit);
        this.f9005c = (LineChart) findViewById(C1373R.id.linear_chart_data_compare);
        obtainStyledAttributes.recycle();
        m10323a();
    }

    public void setValue(CharSequence charSequence) {
        this.f9003a.setText(charSequence);
    }

    public void setUnit(CharSequence charSequence) {
        this.f9004b.setText(charSequence);
    }

    /* renamed from: a */
    private void m10323a() {
        this.f9005c.setTouchEnabled(false);
        this.f9005c.setDragEnabled(false);
        this.f9005c.setScaleEnabled(false);
        this.f9005c.setPinchZoom(false);
        this.f9005c.setBackgroundColor(Color.parseColor("#00111111"));
        this.f9005c.setUnbindEnabled(true);
        this.f9005c.setDescription(null);
        this.f9005c.setMarkerView(new C2032g(getContext(), C1373R.layout.layout_marker));
        this.f9005c.m10361b(C3283i.m15928a(4.0f), 0.0f, C3283i.m15928a(4.0f), C3283i.m15928a(1.0f));
        XAxis xAxis = this.f9005c.getXAxis();
        xAxis.a(10.0f, 10.0f, 0.0f);
        xAxis.a(false);
        xAxis.c(false);
        xAxis.b(-14145496);
        YAxis axisLeft = this.f9005c.getAxisLeft();
        axisLeft.j(0.0f);
        axisLeft.a(1.0f, 10.0f, 0.0f);
        axisLeft.b(-14145496);
        axisLeft.m15401f(false);
        axisLeft.b(true);
        axisLeft.a(false);
        axisLeft.d(false);
        axisLeft = this.f9005c.getAxisRight();
        axisLeft.b(-14145496);
        axisLeft.c(false);
        axisLeft.b(true);
        axisLeft.a(false);
        this.f9005c.getLegend().m15338e(false);
    }

    public void setHighLightValue(float f) {
        this.f9005c.a(f, 0, false);
    }

    public void setYMaxValue(float f) {
        this.f9005c.getAxisLeft().g(f);
    }

    public void setYMinValue(float f) {
        this.f9005c.getAxisLeft().e(f);
    }

    public void setData(ArrayList<Entry> arrayList) {
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        if (this.f9005c.getData() == null || ((C3238l) this.f9005c.getData()).m15590d() <= 0) {
            LineDataSet lineDataSet = new LineDataSet(arrayList, "");
            lineDataSet.a(AxisDependency.LEFT);
            lineDataSet.c(false);
            lineDataSet.b(false);
            lineDataSet.c(this.f9006d);
            lineDataSet.h(-1);
            lineDataSet.e(1.0f);
            lineDataSet.b(3.0f);
            lineDataSet.k(65);
            lineDataSet.j(C3272a.m15840a());
            lineDataSet.a(ViewCompat.MEASURED_SIZE_MASK);
            lineDataSet.d(false);
            lineDataSet.f(false);
            C3224j c3238l = new C3238l(lineDataSet);
            c3238l.m15585b(-1);
            c3238l.m15584b(9.0f);
            this.f9005c.setData(c3238l);
            this.f9005c.invalidate();
        } else {
            ((LineDataSet) ((C3238l) this.f9005c.getData()).mo3993a(0)).b(arrayList);
            ((C3238l) this.f9005c.getData()).mo3985b();
            this.f9005c.m10365h();
        }
        this.f9005c.a(0.0f, 0, false);
    }
}
