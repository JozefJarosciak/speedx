package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1972d;
import com.beastbikes.android.modules.cycling.activity.ui.record.p124e.C1985b;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Program;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Stage;
import com.beastbikes.android.utils.C2555d;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.C3232h;
import com.github.mikephil.charting.data.C3236i;
import com.github.mikephil.charting.data.C3237k;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.LineDataSet$Mode;
import com.github.mikephil.charting.listener.C2012c;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p121c.C1968d;
import com.github.mikephil.charting.p181d.C3213d;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrainStatisticsChartView extends LinearLayout implements C2012c {
    /* renamed from: a */
    private static final Logger f9072a = LoggerFactory.getLogger("TrainStatisticsChartView");
    /* renamed from: b */
    private TextView f9073b;
    /* renamed from: c */
    private TextView f9074c;
    /* renamed from: d */
    private TextView f9075d;
    /* renamed from: e */
    private TextView f9076e;
    /* renamed from: f */
    private CombinedChart f9077f;
    /* renamed from: g */
    private int f9078g;
    /* renamed from: h */
    private ArrayList<CandleEntry> f9079h;
    /* renamed from: i */
    private ArrayList<Entry> f9080i;
    /* renamed from: j */
    private int f9081j;
    /* renamed from: k */
    private long f9082k;
    /* renamed from: l */
    private DecimalFormat f9083l;

    public TrainStatisticsChartView(Context context) {
        super(context);
        m10395a(context, null, 0);
    }

    public TrainStatisticsChartView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m10395a(context, attributeSet, 0);
    }

    public TrainStatisticsChartView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10395a(context, attributeSet, i);
    }

    /* renamed from: a */
    public void mo3357a(Entry entry, C3213d c3213d) {
        setSelectedContent((int) entry.m15450i());
    }

    /* renamed from: a */
    public void mo3356a() {
        setSelectedContent(-1);
    }

    /* renamed from: a */
    private void m10395a(Context context, @Nullable AttributeSet attributeSet, int i) {
        inflate(context, C1373R.layout.layout_train_statistics_chart, this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TrainStatisticsChartView, i, 0);
        this.f9078g = obtainStyledAttributes.getColor(0, -1);
        CharSequence string = obtainStyledAttributes.getString(1);
        CharSequence string2 = obtainStyledAttributes.getString(2);
        CharSequence string3 = obtainStyledAttributes.getString(3);
        CharSequence string4 = obtainStyledAttributes.getString(4);
        findViewById(C1373R.id.view_train_statistics_name_left).setBackgroundColor(this.f9078g);
        TextView textView = (TextView) findViewById(C1373R.id.tv_train_statistics_chart_title);
        textView.setTextColor(this.f9078g);
        textView.setText(string);
        ((TextView) findViewById(C1373R.id.tv_train_statistics_chart_unit)).setText(string2);
        findViewById(C1373R.id.view_train_statistics_mine).setBackgroundColor(this.f9078g);
        this.f9073b = (TextView) findViewById(C1373R.id.tv_train_statistics_time);
        ((TextView) findViewById(C1373R.id.tv_train_statistics_mine)).setText(string3);
        ((TextView) findViewById(C1373R.id.tv_train_statistics_target)).setText(string4);
        this.f9074c = (TextView) findViewById(C1373R.id.tv_train_statistics_my_value);
        this.f9075d = (TextView) findViewById(C1373R.id.tv_train_statistics_state);
        this.f9075d.setTextColor(this.f9078g);
        this.f9076e = (TextView) findViewById(C1373R.id.tv_train_statistics_target_value);
        this.f9077f = (CombinedChart) findViewById(C1373R.id.combinedChart_train_statistics);
        obtainStyledAttributes.recycle();
        this.f9083l = new DecimalFormat("#");
        m10399b();
    }

    /* renamed from: b */
    public void m10399b() {
        this.f9077f.getDescription().m15338e(false);
        this.f9077f.setBackgroundColor(-15658735);
        this.f9077f.setDrawGridBackground(false);
        this.f9077f.setDrawBarShadow(false);
        this.f9077f.setHighlightFullBarEnabled(false);
        this.f9077f.setTouchEnabled(true);
        this.f9077f.setDragEnabled(true);
        this.f9077f.setScaleEnabled(false);
        this.f9077f.setNoDataText("");
        this.f9077f.setDrawOrder(new DrawOrder[]{DrawOrder.CANDLE, DrawOrder.LINE});
        this.f9077f.getLegend().m15338e(false);
        YAxis axisLeft = this.f9077f.getAxisLeft();
        axisLeft.s();
        axisLeft.a(5, true);
        axisLeft.b(0.5f);
        axisLeft.m15401f(false);
        axisLeft.b(false);
        axisLeft.a(false);
        axisLeft.d(0.0f);
        axisLeft.a(new C1972d());
        axisLeft.e(-1);
        axisLeft.k(11.0f);
        axisLeft.m15402l(0.0f);
        this.f9077f.getAxisRight().e(false);
        XAxis xAxis = this.f9077f.getXAxis();
        xAxis.c(1.0f);
        xAxis.m15384a(XAxisPosition.BOTTOM);
        xAxis.a(false);
        xAxis.a(5, true);
        xAxis.c(true);
        xAxis.s();
        xAxis.k(11.0f);
        xAxis.e(-1);
        xAxis.a(new C1972d());
        this.f9077f.setMarker(new C1985b(getContext(), C1373R.drawable.bg_chart_marker_ring_image));
        this.f9077f.setOnChartValueSelectedListener(this);
    }

    public void setXValueFormatter(C1968d c1968d) {
        this.f9077f.getXAxis().a(c1968d);
    }

    /* renamed from: a */
    public void m10397a(TrainCourseDTO trainCourseDTO, ArrayList<Entry> arrayList, boolean z) {
        if (trainCourseDTO == null || arrayList == null) {
            f9072a.error("dto is null or entries is null");
            return;
        }
        this.f9082k = trainCourseDTO.getCourseTime();
        C3237k c3237k = new C3237k();
        this.f9080i = arrayList;
        this.f9081j = this.f9080i.size();
        this.f9077f.getXAxis().f((float) (this.f9081j - 1));
        if (this.f9081j < 5) {
            this.f9077f.getXAxis().a(this.f9081j, true);
            this.f9077f.getAxisLeft().a(this.f9081j, true);
        }
        f9072a.info("totalTime: " + this.f9082k + " sampleSize: " + this.f9081j);
        if (this.f9081j > 0) {
            c3237k.m15664a(m10394a(this.f9080i));
            c3237k.m15663a(m10393a(trainCourseDTO, this.f9081j, z));
            this.f9077f.setData(c3237k);
            this.f9077f.invalidate();
        }
    }

    /* renamed from: a */
    private C3232h m10393a(TrainCourseDTO trainCourseDTO, int i, boolean z) {
        int i2;
        int powerHigh;
        long courseTime = trainCourseDTO.getCourseTime();
        C3232h c3232h = new C3232h();
        c3232h.m15586b(false);
        this.f9079h = new ArrayList();
        Iterator it = trainCourseDTO.getPrograms().iterator();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (it.hasNext()) {
            int i6;
            Program program = (Program) it.next();
            int recycleCount = program.getRecycleCount();
            ArrayList stages = program.getStages();
            if (!(stages == null || stages.isEmpty())) {
                i2 = 0;
                int i7 = i4;
                int i8 = i5;
                i5 = i3;
                while (i2 < recycleCount) {
                    Iterator it2 = stages.iterator();
                    int i9 = i8;
                    i8 = i5;
                    i5 = i9;
                    while (it2.hasNext()) {
                        Stage stage = (Stage) it2.next();
                        long stageTime = (long) stage.getStageTime();
                        long j = (long) (((((double) stageTime) * 1.0d) / ((double) courseTime)) * ((double) i));
                        f9072a.info("stageTime: " + stageTime + " courseTotalTime: " + courseTime + " sampleSize: " + i + " temp: " + j);
                        if (z) {
                            powerHigh = stage.getPowerHigh();
                            i4 = stage.getPowerLow();
                        } else {
                            powerHigh = stage.getCadenceHigh();
                            i4 = stage.getCadenceLow();
                        }
                        i3 = i5;
                        for (i6 = 0; ((long) i6) < j; i6++) {
                            this.f9079h.add(new CandleEntry((float) i3, (float) powerHigh, (float) i4, (float) powerHigh, (float) i4));
                            i3++;
                        }
                        i5 = i3;
                        i8 = i4;
                        i7 = powerHigh;
                    }
                    i2++;
                    i9 = i5;
                    i5 = i8;
                    i8 = i9;
                }
                i3 = i5;
                i4 = i7;
                i5 = i8;
            }
        }
        i2 = i - this.f9079h.size();
        powerHigh = 0;
        while (powerHigh < i2) {
            i6 = i5 + 1;
            this.f9079h.add(new CandleEntry((float) i5, (float) i4, (float) i3, (float) i4, (float) i3));
            powerHigh++;
            i5 = i6;
        }
        f9072a.info("size: " + this.f9079h.size());
        C3220e c3236i = new C3236i(this.f9079h, "");
        c3236i.m15659f(-14145496);
        c3236i.m15654c(true);
        c3236i.m15650b(0.0f);
        c3236i.mo3918b(false);
        c3236i.m15653c(0.0f);
        c3236i.mo3916a(false);
        c3232h.m15579a(c3236i);
        return c3232h;
    }

    /* renamed from: a */
    private C3238l m10394a(ArrayList<Entry> arrayList) {
        C3238l c3238l = new C3238l();
        C3220e lineDataSet = new LineDataSet(arrayList, "");
        lineDataSet.c(this.f9078g);
        lineDataSet.j(0);
        lineDataSet.e(1.0f);
        lineDataSet.d(false);
        lineDataSet.c(false);
        lineDataSet.b(false);
        lineDataSet.a(LineDataSet$Mode.LINEAR);
        lineDataSet.a(AxisDependency.LEFT);
        lineDataSet.a(-1);
        lineDataSet.f(1.0f);
        lineDataSet.a(-1);
        lineDataSet.g(true);
        lineDataSet.f(false);
        c3238l.m15579a(lineDataSet);
        return c3238l;
    }

    private void setSelectedContent(int i) {
        if (i == -1) {
            this.f9073b.setText("--:--:--");
            this.f9074c.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            this.f9075d.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            this.f9076e.setText(HelpFormatter.DEFAULT_LONG_OPT_PREFIX);
            return;
        }
        this.f9073b.setText(C2555d.m12802b((long) ((((float) i) / this.f9077f.getXAxis().u()) * ((float) this.f9082k))));
        float b = ((Entry) this.f9080i.get(i)).mo3912b();
        this.f9074c.setText(this.f9083l.format((double) b));
        CandleEntry candleEntry = (CandleEntry) this.f9079h.get(i);
        if (b < candleEntry.m15460c()) {
            this.f9075d.setText(C1373R.string.str_training_analysis_lower);
        } else if (b > candleEntry.m15458a()) {
            this.f9075d.setText(C1373R.string.str_training_analysis_higher);
        } else {
            this.f9075d.setText(C1373R.string.str_training_analysis_normal);
        }
        this.f9076e.setText(this.f9083l.format((double) candleEntry.m15460c()) + HelpFormatter.DEFAULT_OPT_PREFIX + this.f9083l.format((double) candleEntry.m15458a()));
    }
}
