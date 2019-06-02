package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.LnChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotDotRender;
import org.xclcharts.renderer.plot.PlotQuadrant;
import org.xclcharts.renderer.plot.PlotQuadrantRender;

public class ScatterChart extends LnChart {
    private static String TAG = "ScatterChart";
    private List<ScatterData> mDataset;
    private IFormatterTextCallBack mLabelFormatter;
    private double mMaxValue = 0.0d;
    private double mMinValue = 0.0d;
    private Paint mPaintPoint = null;
    private PlotQuadrantRender mPlotQuadrant = null;

    public ScatterChart() {
        categoryAxisDefaultSetting();
        dataAxisDefaultSetting();
        setAxesClosed(true);
    }

    public ChartType getType() {
        return ChartType.SCATTER;
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

    public void setDataSource(List<ScatterData> list) {
        this.mDataset = list;
    }

    public List<ScatterData> getDataSource() {
        return this.mDataset;
    }

    public void setCategoryAxisMax(double d) {
        this.mMaxValue = d;
    }

    public void setCategoryAxisMin(double d) {
        this.mMinValue = d;
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

    public PlotQuadrant getPlotQuadrant() {
        if (this.mPlotQuadrant == null) {
            this.mPlotQuadrant = new PlotQuadrantRender();
        }
        return this.mPlotQuadrant;
    }

    public Paint getPointPaint() {
        if (this.mPaintPoint == null) {
            this.mPaintPoint = new Paint(1);
        }
        return this.mPaintPoint;
    }

    private void drawQuadrant(Canvas canvas) {
        if (getPlotQuadrant().isShow()) {
            Double valueOf = Double.valueOf(getPlotQuadrant().getQuadrantXValue());
            Double valueOf2 = Double.valueOf(getPlotQuadrant().getQuadrantYValue());
            float lnXValPosition = getLnXValPosition(valueOf.doubleValue(), this.mMaxValue, this.mMinValue);
            float vPValPosition = getVPValPosition(valueOf2.doubleValue());
            this.mPlotQuadrant.drawQuadrant(canvas, lnXValPosition, vPValPosition, this.plotArea.getLeft(), this.plotArea.getPlotTop(), this.plotArea.getPlotRight(), this.plotArea.getBottom());
        }
    }

    private void renderPoints(Canvas canvas, ScatterData scatterData, int i) {
        if (scatterData == null) {
            Log.w(TAG, "传入的数据序列参数为空.");
        } else if (Double.compare(this.mMaxValue, this.mMinValue) == -1) {
            Log.w(TAG, "轴最大值小于最小值.");
        } else if (Double.compare(this.mMaxValue, this.mMinValue) == 0) {
            Log.w(TAG, "轴最大值与最小值相等.");
        } else {
            float axisRange = this.dataAxis.getAxisRange();
            if (Float.compare(axisRange, 0.0f) == 0 || Float.compare(axisRange, 0.0f) == -1) {
                Log.w(TAG, "数据轴高度小于或等于0.");
                return;
            }
            List dataSet = scatterData.getDataSet();
            if (dataSet != null) {
                float itemLabelRotateAngle = scatterData.getItemLabelRotateAngle();
                PlotDot plotDot = scatterData.getPlotDot();
                float dotRadius = plotDot.getDotRadius();
                int size = dataSet.size();
                for (int i2 = 0; i2 < size; i2++) {
                    PointD pointD = (PointD) dataSet.get(i2);
                    float lnXValPosition = getLnXValPosition(pointD.f18260x, this.mMaxValue, this.mMinValue);
                    float vPValPosition = getVPValPosition(pointD.f18261y);
                    if (!plotDot.getDotStyle().equals(DotStyle.HIDE)) {
                        getPointPaint().setColor(plotDot.getColor());
                        getPointPaint().setAlpha(plotDot.getAlpha());
                        PlotDotRender.getInstance().renderDot(canvas, plotDot, lnXValPosition, vPValPosition, getPointPaint());
                        savePointRecord(i, i2, lnXValPosition + this.mMoveX, vPValPosition + this.mMoveY, (lnXValPosition - dotRadius) + this.mMoveX, (vPValPosition - dotRadius) + this.mMoveY, (lnXValPosition + dotRadius) + this.mMoveX, (vPValPosition + dotRadius) + this.mMoveY);
                    }
                    drawAnchor(getAnchorDataPoint(), i, i2, canvas, lnXValPosition, vPValPosition, dotRadius);
                    if (scatterData.getLabelVisible()) {
                        DrawHelper.getInstance().drawRotateText(getFormatterDotLabel(new StringBuilder(String.valueOf(Double.toString(pointD.f18260x))).append(",").append(Double.toString(pointD.f18261y)).toString()), lnXValPosition, vPValPosition, itemLabelRotateAngle, canvas, scatterData.getDotLabelPaint());
                    }
                }
            }
        }
    }

    private boolean renderPlot(Canvas canvas) {
        if (this.mMaxValue == this.mMinValue && 0.0d == this.mMaxValue) {
            Log.w(TAG, "请检查是否有设置分类轴的最大最小值。");
            return false;
        } else if (this.mDataset == null) {
            Log.w(TAG, "数据源为空.");
            return false;
        } else {
            drawQuadrant(canvas);
            int size = this.mDataset.size();
            for (int i = 0; i < size; i++) {
                ScatterData scatterData = (ScatterData) this.mDataset.get(i);
                if (!scatterData.getPlotDot().getDotStyle().equals(DotStyle.HIDE) || scatterData.getLabelVisible()) {
                    renderPoints(canvas, scatterData, i);
                }
            }
            return true;
        }
    }

    protected void drawClipPlot(Canvas canvas) {
        if (renderPlot(canvas) && this.mCustomLine != null) {
            this.mCustomLine.setVerticalPlot(this.dataAxis, this.plotArea, getAxisScreenHeight());
            this.mCustomLine.renderVerticalCustomlinesDataAxis(canvas);
        }
    }

    protected void drawClipLegend(Canvas canvas) {
        this.plotLegend.renderPointKey(canvas, this.mDataset);
    }
}
