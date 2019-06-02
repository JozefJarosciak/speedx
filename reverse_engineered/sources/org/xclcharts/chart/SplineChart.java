package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.LnChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.CrurveLineStyle;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.line.DotInfo;
import org.xclcharts.renderer.line.PlotCustomLine;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotDotRender;
import org.xclcharts.renderer.line.PlotLine;

public class SplineChart extends LnChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle;
    private static String TAG = "SplineChart";
    private Path mBezierPath = new Path();
    private CrurveLineStyle mCrurveLineStyle = CrurveLineStyle.BEZIERCURVE;
    private List<SplineData> mDataSet;
    private IFormatterTextCallBack mLabelFormatter;
    private List<DotInfo> mLstDotInfo = new ArrayList();
    private List<LnData> mLstKey = new ArrayList();
    private List<PointF> mLstPoints = new ArrayList();
    private double mMaxValue = 0.0d;
    private double mMinValue = 0.0d;
    private PlotCustomLine mXAxisCustomLine = null;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle;
        if (iArr == null) {
            iArr = new int[CrurveLineStyle.values().length];
            try {
                iArr[CrurveLineStyle.BEELINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CrurveLineStyle.BEZIERCURVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle = iArr;
        }
        return iArr;
    }

    public SplineChart() {
        categoryAxisDefaultSetting();
        dataAxisDefaultSetting();
    }

    public ChartType getType() {
        return ChartType.SPLINE;
    }

    protected void categoryAxisDefaultSetting() {
        if (this.categoryAxis != null) {
            this.categoryAxis.setHorizontalTickAlign(Align.CENTER);
        }
    }

    protected void dataAxisDefaultSetting() {
        if (this.dataAxis != null) {
            this.dataAxis.setHorizontalTickAlign(Align.LEFT);
        }
    }

    public void setCategories(List<String> list) {
        if (this.categoryAxis != null) {
            this.categoryAxis.setDataBuilding(list);
        }
    }

    public void setDataSource(List<SplineData> list) {
        this.mDataSet = list;
    }

    public List<SplineData> getDataSource() {
        return this.mDataSet;
    }

    public void setCategoryAxisMax(double d) {
        this.mMaxValue = d;
    }

    public void setCategoryAxisMin(double d) {
        this.mMinValue = d;
    }

    public void setCategoryAxisCustomLines(List<CustomLineData> list) {
        if (this.mXAxisCustomLine == null) {
            this.mXAxisCustomLine = new PlotCustomLine();
        }
        this.mXAxisCustomLine.setCustomLines(list);
    }

    public void setDotLabelFormatter(IFormatterTextCallBack iFormatterTextCallBack) {
        this.mLabelFormatter = iFormatterTextCallBack;
    }

    protected String getFormatterDotLabel(String str) {
        String str2 = "";
        try {
            str = this.mLabelFormatter.textFormatter(str);
        } catch (Exception e) {
        }
        return str;
    }

    public void setCrurveLineStyle(CrurveLineStyle crurveLineStyle) {
        this.mCrurveLineStyle = crurveLineStyle;
    }

    public CrurveLineStyle getCrurveLineStyle() {
        return this.mCrurveLineStyle;
    }

    private void calcAllPoints(SplineData splineData, List<PointF> list, List<DotInfo> list2) {
        if (splineData == null) {
            Log.w(TAG, "传入的数据序列参数为空.");
        } else if (Double.compare(this.mMaxValue, this.mMinValue) == -1) {
            Log.w(TAG, "轴最大值小于最小值.");
        } else if (Double.compare(this.mMaxValue, this.mMinValue) == 0) {
            Log.w(TAG, "轴最大值与最小值相等.");
        } else {
            this.plotArea.getLeft();
            this.plotArea.getBottom();
            List lineDataSet = splineData.getLineDataSet();
            if (lineDataSet != null) {
                int size = lineDataSet.size();
                for (int i = 0; i < size; i++) {
                    PointD pointD = (PointD) lineDataSet.get(i);
                    float lnXValPosition = getLnXValPosition(pointD.f18260x, this.mMaxValue, this.mMinValue);
                    float vPValPosition = getVPValPosition(pointD.f18261y);
                    if (i == 0) {
                        list.add(new PointF(lnXValPosition, vPValPosition));
                        list.add(new PointF(lnXValPosition, vPValPosition));
                    } else {
                        list.add(new PointF(lnXValPosition, vPValPosition));
                    }
                    list2.add(new DotInfo(Double.valueOf(pointD.f18260x), Double.valueOf(pointD.f18261y), lnXValPosition, vPValPosition));
                }
            }
        }
    }

    private boolean renderLine(Canvas canvas, SplineData splineData, List<PointF> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                PointF pointF = (PointF) list.get(i - 1);
                PointF pointF2 = (PointF) list.get(i);
                DrawHelper.getInstance().drawLine(splineData.getLineStyle(), pointF.x, pointF.y, pointF2.x, pointF2.y, canvas, splineData.getLinePaint());
            }
        }
        return true;
    }

    private boolean renderBezierCurveLine(Canvas canvas, Path path, SplineData splineData, List<PointF> list) {
        renderBezierCurveLine(canvas, splineData.getLinePaint(), path, list);
        return true;
    }

    private boolean renderDotAndLabel(Canvas canvas, SplineData splineData, int i, List<PointF> list) {
        PlotLine plotLine = splineData.getPlotLine();
        if (plotLine.getDotStyle().equals(DotStyle.HIDE) && !splineData.getLabelVisible()) {
            return true;
        }
        float itemLabelRotateAngle = splineData.getItemLabelRotateAngle();
        PlotDot plotDot = plotLine.getPlotDot();
        float dotRadius = plotDot.getDotRadius();
        int size = this.mLstDotInfo.size();
        for (int i2 = 0; i2 < size; i2++) {
            DotInfo dotInfo = (DotInfo) this.mLstDotInfo.get(i2);
            if (!plotLine.getDotStyle().equals(DotStyle.HIDE)) {
                PlotDotRender.getInstance().renderDot(canvas, plotDot, dotInfo.mX, dotInfo.mY, plotLine.getDotPaint());
                savePointRecord(i, i2, dotInfo.mX + this.mMoveX, dotInfo.mY + this.mMoveY, (dotInfo.mX - dotRadius) + this.mMoveX, (dotInfo.mY - dotRadius) + this.mMoveY, (dotInfo.mX + dotRadius) + this.mMoveX, (dotInfo.mY + dotRadius) + this.mMoveY);
            }
            drawAnchor(getAnchorDataPoint(), i, i2, canvas, dotInfo.mX, dotInfo.mY, dotRadius);
            if (splineData.getLabelVisible()) {
                splineData.getLabelOptions().drawLabel(canvas, plotLine.getDotLabelPaint(), getFormatterDotLabel(dotInfo.getLabel()), dotInfo.mX, dotInfo.mY, itemLabelRotateAngle, splineData.getLineColor());
            }
        }
        return true;
    }

    private boolean renderPlot(Canvas canvas) {
        if (Double.compare(this.mMaxValue, this.mMinValue) == 0 && Double.compare(0.0d, this.mMaxValue) == 0) {
            Log.e(TAG, "请检查是否有设置分类轴的最大最小值。");
            return false;
        } else if (this.mDataSet == null) {
            Log.e(TAG, "数据源为空.");
            return false;
        } else {
            int size = this.mDataSet.size();
            for (int i = 0; i < size; i++) {
                SplineData splineData = (SplineData) this.mDataSet.get(i);
                calcAllPoints(splineData, this.mLstPoints, this.mLstDotInfo);
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle()[getCrurveLineStyle().ordinal()]) {
                    case 1:
                        renderLine(canvas, splineData, this.mLstPoints);
                        break;
                    case 2:
                        renderBezierCurveLine(canvas, this.mBezierPath, splineData, this.mLstPoints);
                        break;
                    default:
                        Log.e(TAG, "未知的枚举类型.");
                        continue;
                }
                renderDotAndLabel(canvas, splineData, i, this.mLstPoints);
                this.mLstKey.add((LnData) this.mDataSet.get(i));
                this.mLstDotInfo.clear();
                this.mLstPoints.clear();
                this.mBezierPath.reset();
            }
            return true;
        }
    }

    protected void drawClipPlot(Canvas canvas) {
        if (renderPlot(canvas)) {
            if (this.mCustomLine != null) {
                this.mCustomLine.setVerticalPlot(this.dataAxis, this.plotArea, getPlotScreenHeight());
                this.mCustomLine.renderVerticalCustomlinesDataAxis(canvas);
            }
            if (this.mXAxisCustomLine != null) {
                this.mXAxisCustomLine.renderCategoryAxisCustomlines(canvas, getPlotScreenWidth(), this.plotArea, this.mMaxValue, this.mMinValue);
            }
        }
    }

    protected void drawClipLegend(Canvas canvas) {
        this.plotLegend.renderLineKey(canvas, this.mLstKey);
        this.mLstKey.clear();
    }
}
