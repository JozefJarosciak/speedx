package com.beastbikes.android.modules.cycling.route.p133b;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import com.alibaba.fastjson.asm.Opcodes;
import com.baidu.mapapi.UIMsg.m_AppUI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.HttpStatus;
import org.xclcharts.chart.AreaChart;
import org.xclcharts.chart.AreaData;
import org.xclcharts.chart.PointD;
import org.xclcharts.chart.SplineChart;
import org.xclcharts.chart.SplineData;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.PanMode;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.view.ChartView;

/* compiled from: RouteElevationView */
/* renamed from: com.beastbikes.android.modules.cycling.route.b.a */
public class C2186a extends ChartView {
    /* renamed from: a */
    private static final String f10252a = C2186a.class.getName();
    /* renamed from: b */
    private LinkedList<String> f10253b = new LinkedList();
    /* renamed from: c */
    private LinkedList<AreaData> f10254c = new LinkedList();
    /* renamed from: d */
    private AreaChart f10255d = new AreaChart();
    /* renamed from: e */
    private LinkedList<String> f10256e = new LinkedList();
    /* renamed from: f */
    private SplineChart f10257f = new SplineChart();

    public C2186a(Context context) {
        super(context);
    }

    public C2186a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2186a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f10255d.setChartRange((float) i, (float) i2);
        this.f10257f.setChartRange((float) i, (float) i2);
    }

    public void render(Canvas canvas) {
        try {
            this.f10255d.render(canvas);
            this.f10257f.render(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLabelsData(int i) {
        this.f10253b.clear();
        for (int i2 = 0; i2 < i; i2++) {
            this.f10253b.add(String.valueOf(i2));
        }
    }

    public void setData(ArrayList<Double> arrayList) {
        AreaData areaData = new AreaData("", arrayList, -14960504, -1, -1179656);
        areaData.getLinePaint().setStrokeWidth(3.0f);
        areaData.setDotStyle(DotStyle.HIDE);
        areaData.setApplayGradient(true);
        areaData.setDataSet(arrayList);
        this.f10254c.add(areaData);
    }

    /* renamed from: a */
    public void m11209a(double d, double d2, int i) {
        try {
            this.f10255d.setPadding(80.0f, 30.0f, 0.0f, 50.0f);
            int[] iArr = new int[]{10, 20, 40, 50, 100, 200, 300, HttpStatus.SC_BAD_REQUEST, 1000, 1200, m_AppUI.MSG_APP_DATA_OK};
            int a = C2186a.m11207a(iArr, Math.abs((d - d2) / 5.0d));
            int i2 = iArr[a];
            this.f10255d.getDataAxis().setAxisMin(Math.min(0.0d, d2));
            this.f10255d.getDataAxis().setAxisMax((double) (iArr[a] * 5));
            this.f10255d.getDataAxis().setAxisSteps((double) i2);
            this.f10255d.hideBorder();
            this.f10255d.setCategories(this.f10253b);
            this.f10255d.setDataSource(this.f10254c);
            this.f10255d.setPlotPanMode(PanMode.HORIZONTAL);
            this.f10255d.getPlotGrid().showHorizontalLines();
            this.f10255d.getPlotGrid().hideVerticalLines();
            this.f10255d.getPlotGrid().getHorizontalLinePaint().setColor(1434025086);
            this.f10257f.getPlotGrid().getHorizontalLinePaint().setStrokeWidth(0.1f);
            this.f10255d.getDataAxis().hideAxisLine();
            this.f10255d.getDataAxis().hideTickMarks();
            this.f10255d.getDataAxis().getTickLabelPaint().setColor(1434025086);
            this.f10255d.getCategoryAxis().hideAxisLine();
            this.f10255d.getCategoryAxis().hideTickMarks();
            this.f10255d.getCategoryAxis().hideAxisLabels();
            this.f10255d.setAreaAlpha(Opcodes.GETFIELD);
            this.f10255d.getPlotLegend().show();
            this.f10255d.ActiveListenItemClick();
            this.f10255d.extPointClickRange(5);
            this.f10255d.getPlotLegend().setVerticalAlign(VerticalAlign.BOTTOM);
            this.f10255d.getPlotLegend().setHorizontalAlign(HorizontalAlign.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        m11210b(d, d2, i);
    }

    /* renamed from: a */
    private LinkedList<SplineData> m11208a(int i, int i2) {
        LinkedList<SplineData> linkedList = new LinkedList();
        ArrayList arrayList = new ArrayList();
        double d = (double) (i2 / 10);
        this.f10256e.add("0");
        arrayList.add(Double.valueOf(0.0d));
        List arrayList2 = new ArrayList();
        for (int i3 = 1; i3 < 9; i3++) {
            this.f10256e.add(String.valueOf(((double) i3) * d));
            arrayList.add(Double.valueOf((double) i3));
        }
        this.f10256e.add(String.valueOf(i2));
        arrayList.add(Double.valueOf((double) i));
        for (int i4 = 0; i4 < 10; i4++) {
            arrayList2.add(new PointD(Double.valueOf((String) this.f10256e.get(i4)).doubleValue(), ((Double) arrayList.get(i4)).doubleValue()));
        }
        SplineData splineData = new SplineData();
        splineData.setDotStyle(DotStyle.HIDE);
        splineData.setLineDataSet(arrayList2);
        linkedList.add(splineData);
        return linkedList;
    }

    /* renamed from: b */
    public void m11210b(double d, double d2, int i) {
        try {
            this.f10257f.setPadding(80.0f, 30.0f, 0.0f, 50.0f);
            int[] iArr = new int[]{10, 20, 40, 50, 100, 200, 300, HttpStatus.SC_BAD_REQUEST, 1000, 1200, m_AppUI.MSG_APP_DATA_OK};
            int a = C2186a.m11207a(iArr, Math.abs((d - d2) / 5.0d));
            int i2 = iArr[a];
            this.f10257f.getDataAxis().setAxisMin(Math.min(0.0d, d2));
            this.f10257f.getDataAxis().setAxisMax((double) (iArr[a] * 5));
            this.f10257f.getDataAxis().setAxisSteps((double) i2);
            this.f10257f.hideBorder();
            this.f10257f.setCategories(this.f10256e);
            this.f10257f.setDataSource(m11208a(iArr[a] * 5, i));
            this.f10257f.setPlotPanMode(PanMode.HORIZONTAL);
            this.f10257f.getPlotGrid().showVerticalLines();
            this.f10257f.getPlotGrid().getVerticalLinePaint().setColor(1434025086);
            this.f10257f.getPlotGrid().getHorizontalLinePaint().setColor(1434025086);
            this.f10257f.getPlotGrid().getVerticalLinePaint().setStrokeWidth(0.1f);
            this.f10257f.getPlotGrid().hideHorizontalLines();
            this.f10257f.getDataAxis().hideAxisLine();
            this.f10257f.getDataAxis().hideTickMarks();
            this.f10257f.getDataAxis().hideAxisLabels();
            this.f10257f.getCategoryAxis().hideAxisLine();
            this.f10257f.getCategoryAxis().hideTickMarks();
            this.f10257f.getCategoryAxis().getTickLabelPaint().setColor(ViewCompat.MEASURED_SIZE_MASK);
            this.f10257f.getPlotLegend().hide();
            this.f10257f.ActiveListenItemClick();
            this.f10257f.extPointClickRange(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static int m11207a(int[] iArr, double d) {
        int i = 0;
        int length = iArr.length - 1;
        while (i <= length) {
            int i2 = (i + length) / 2;
            if (i2 == i) {
                return i + 1;
            }
            if (i2 == length) {
                return length;
            }
            if (d == ((double) iArr[i2])) {
                return i2;
            }
            if (d < ((double) iArr[i2])) {
                length = i;
            } else if (i2 + 1 == length) {
                return length;
            } else {
                int i3 = length;
                length = i2;
                i2 = i3;
            }
            i = length;
            length = i2;
        }
        return -1;
    }
}
