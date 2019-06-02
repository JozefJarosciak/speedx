package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1971c;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1975g;
import com.beastbikes.android.modules.cycling.activity.ui.record.p124e.C1983a;
import com.github.mikephil.charting.charts.C2011a;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.C3224j;
import com.github.mikephil.charting.data.C3226a;
import com.github.mikephil.charting.data.C3230b;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.C2012c;
import com.github.mikephil.charting.p121c.C1968d;
import com.github.mikephil.charting.p181d.C3213d;
import java.util.ArrayList;
import java.util.List;

/* compiled from: BaseBarChartView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.a */
public class C2025a extends C2024b implements C2012c {
    /* renamed from: d */
    private C2011a f9164d;
    /* renamed from: e */
    private C1968d f9165e;
    /* renamed from: f */
    private C1968d f9166f;
    /* renamed from: g */
    private RelativeLayout f9167g;

    public C2025a(Context context) {
        super(context);
    }

    public C2025a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2025a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3358a(Context context, AttributeSet attributeSet) {
        super.mo3358a(context, attributeSet);
        ((ViewStub) findViewById(C1373R.id.viewStub_bar_chart)).inflate();
        this.f9164d = (C2011a) findViewById(C1373R.id.bar_chart);
        this.f9164d.setNoDataText("");
        ((ViewStub) findViewById(C1373R.id.viewStub_chart_no_data)).inflate();
    }

    /* renamed from: a */
    public void mo3357a(Entry entry, C3213d c3213d) {
        m10456a(entry);
    }

    /* renamed from: a */
    public void mo3356a() {
        m10463d();
    }

    /* renamed from: b */
    public void mo3359b() {
        this.f9164d.getDescription().m15338e(false);
        this.f9164d.setPinchZoom(false);
        this.f9164d.setScaleEnabled(false);
        this.f9164d.setDragEnabled(false);
        this.f9164d.setDrawGridBackground(false);
        this.f9164d.setDrawBarShadow(false);
        this.f9164d.setDrawValueAboveBar(false);
        this.f9164d.setHighlightFullBarEnabled(false);
        this.f9164d.setExtraBottomOffset(10.0f);
        YAxis axisLeft = this.f9164d.getAxisLeft();
        if (this.f9166f == null) {
            this.f9166f = new C1971c();
        }
        axisLeft.e(true);
        axisLeft.a(this.f9166f);
        axisLeft.d(0.0f);
        axisLeft.e(-1);
        axisLeft.k(11.0f);
        axisLeft.b(false);
        axisLeft.a(true);
        axisLeft.a(1.0f, 10.0f, 0.0f);
        axisLeft.a(6, true);
        this.f9164d.getAxisRight().e(false);
        XAxis xAxis = this.f9164d.getXAxis();
        xAxis.e(-1);
        xAxis.k(12.0f);
        xAxis.c(1.0f);
        xAxis.c(6);
        xAxis.a(false);
        xAxis.j(8.0f);
        if (this.f9165e == null) {
            this.f9165e = new C1975g();
        }
        xAxis.a(this.f9165e);
        xAxis.m15384a(XAxisPosition.BOTTOM);
        this.f9164d.getLegend().m15338e(false);
    }

    protected void setMark(C1983a c1983a) {
        this.f9164d.setMarkerView(c1983a);
    }

    /* renamed from: c */
    public void m10462c() {
        this.f9164d.getAxisLeft().e(false);
    }

    public void setXLabelFormatter(C1968d c1968d) {
        this.f9165e = c1968d;
    }

    public void setYlabelFormatter(C1968d c1968d) {
        this.f9166f = c1968d;
    }

    public void setMaxVisibleValueCount(int i) {
        this.f9164d.setMaxVisibleValueCount(i);
    }

    public void setXAxisLabelTextSize(float f) {
        this.f9164d.getXAxis().k(f);
    }

    public void setYAxisLabelTextSize(float f) {
        this.f9164d.getAxisLeft().k(f);
    }

    public void setAxisLabelTextSize(float f) {
        this.f9164d.getXAxis().k(f);
        this.f9164d.getAxisLeft().k(f);
    }

    public void setXAxisLabelTextColor(int i) {
        this.f9164d.getXAxis().e(i);
    }

    public void setYAxisLabelTextColor(int i) {
        this.f9164d.getAxisLeft().e(i);
    }

    public void setAxisLabelTextColor(int i) {
        this.f9164d.getXAxis().e(i);
        this.f9164d.getAxisLeft().e(i);
    }

    public void setXLabelCount(int i) {
        this.f9164d.getXAxis().c(i);
    }

    /* renamed from: a */
    protected void m10458a(ArrayList<BarEntry> arrayList, int i) {
        if (arrayList == null) {
            this.f9164d.setData(null);
            return;
        }
        if (this.f9164d.getData() == null || ((C3226a) this.f9164d.getData()).m15590d() <= 0) {
            C3230b c3230b = new C3230b(arrayList, "");
            c3230b.m15508c(i);
            List arrayList2 = new ArrayList();
            arrayList2.add(c3230b);
            C3224j c3226a = new C3226a(arrayList2);
            c3226a.m15580a(false);
            c3226a.m15585b(-1);
            c3226a.m15600a(0.9f);
            this.f9164d.setData(c3226a);
        } else {
            ((C3230b) ((C3226a) this.f9164d.getData()).mo3993a(0)).m15542b((List) arrayList);
            ((C3226a) this.f9164d.getData()).mo3985b();
            this.f9164d.m10365h();
        }
        this.f9164d.setFitBars(true);
        this.f9164d.invalidate();
    }

    /* renamed from: a */
    protected void m10459a(ArrayList<BarEntry> arrayList, ArrayList<Integer> arrayList2) {
        if (arrayList != null) {
            if (this.f9164d.getData() == null || ((C3226a) this.f9164d.getData()).m15590d() <= 0) {
                C3230b c3230b = new C3230b(arrayList, "");
                c3230b.m15504a((List) arrayList2);
                List arrayList3 = new ArrayList();
                arrayList3.add(c3230b);
                C3224j c3226a = new C3226a(arrayList3);
                c3226a.m15580a(false);
                c3226a.m15585b(-1);
                c3226a.m15600a(0.9f);
                this.f9164d.setData(c3226a);
            } else {
                ((C3230b) ((C3226a) this.f9164d.getData()).mo3993a(0)).m15542b((List) arrayList);
                ((C3226a) this.f9164d.getData()).mo3985b();
                this.f9164d.m10365h();
            }
            this.f9164d.setFitBars(true);
            this.f9164d.invalidate();
        }
    }

    /* renamed from: a */
    protected void m10460a(boolean z) {
        this.f9167g = (RelativeLayout) findViewById(C1373R.id.relative_chart_no_data);
        this.f9167g.setClickable(true);
        this.f9167g.setVisibility(0);
        ImageView imageView = (ImageView) findViewById(C1373R.id.img_chart_no_data_icon);
        TextView textView = (TextView) findViewById(C1373R.id.textView_chart_no_data_function);
        TextView textView2 = (TextView) findViewById(C1373R.id.textView_chart_no_data_function_desc);
        TextView textView3 = (TextView) findViewById(C1373R.id.textView_chart_no_data_bottom_desc);
        if (z) {
            imageView.setImageResource(C1373R.drawable.ic_chart_no_cadence);
            textView.setText(C1373R.string.str_have_not_connect_cadence_device);
            textView3.setText(C1373R.string.str_cadence_only_support_desc);
            textView2.setText(C1373R.string.str_cadence_no_data_desc);
            return;
        }
        imageView.setImageResource(C1373R.drawable.ic_chart_no_power);
        textView.setText(C1373R.string.str_cycling_report_empty_data_title_power);
        textView3.setText(C1373R.string.str_cycling_report_empty_data_footersubtitle_power);
        textView2.setText(C1373R.string.str_cycling_report_empty_data_subtitle_power);
    }

    /* renamed from: a */
    protected void m10456a(Entry entry) {
    }

    /* renamed from: d */
    protected void m10463d() {
    }

    /* renamed from: e */
    public boolean mo3360e() {
        if (this.f9167g != null) {
            return this.f9167g.getVisibility() != 0;
        } else {
            return super.mo3360e();
        }
    }
}
