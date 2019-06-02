package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
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

public class BubbleChart extends LnChart {
    private static String TAG = "BubbleChart";
    private float mBubbleMaxSize = 0.0f;
    private float mBubbleMinSize = 0.0f;
    private float mBubbleScaleMax = 0.0f;
    private float mBubbleScaleMin = 0.0f;
    private List<BubbleData> mDataset;
    private IFormatterTextCallBack mLabelFormatter;
    private double mMaxValue = 0.0d;
    private double mMinValue = 0.0d;
    private Paint mPaintBorderPoint = null;
    private Paint mPaintPoint = null;
    private PlotDot mPlotDot = new PlotDot();
    private PlotQuadrantRender mPlotQuadrant = null;

    public BubbleChart() {
        initChart();
    }

    public ChartType getType() {
        return ChartType.BUBBLE;
    }

    private void initChart() {
        if (this.mPlotDot != null) {
            this.mPlotDot.setDotStyle(DotStyle.DOT);
        }
        categoryAxisDefaultSetting();
        dataAxisDefaultSetting();
        setAxesClosed(true);
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

    public void setBubbleMaxSize(float f) {
        this.mBubbleMaxSize = f;
    }

    public void setBubbleMinSize(float f) {
        this.mBubbleMinSize = f;
    }

    public void setBubbleScaleMax(float f) {
        this.mBubbleScaleMax = f;
    }

    public void setBubbleScaleMin(float f) {
        this.mBubbleScaleMin = f;
    }

    public void setCategories(List<String> list) {
        if (this.categoryAxis != null) {
            this.categoryAxis.setDataBuilding(list);
        }
    }

    public void setDataSource(List<BubbleData> list) {
        this.mDataset = list;
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

    private float calcRaidus(float f, float f2, float f3) {
        return mul(f3, div(f, f2));
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

    public Paint getPointBorderPaint() {
        if (this.mPaintBorderPoint == null) {
            this.mPaintBorderPoint = new Paint(1);
            this.mPaintBorderPoint.setStyle(Style.STROKE);
            this.mPaintBorderPoint.setStrokeWidth(2.0f);
        }
        return this.mPaintBorderPoint;
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

    private void renderPoints(Canvas canvas, BubbleData bubbleData, int i) {
        List dataSet = bubbleData.getDataSet();
        if (dataSet != null) {
            if (Float.compare(this.mBubbleScaleMax, this.mBubbleScaleMin) == 0) {
                Log.e(TAG, "没有指定用于决定气泡大小的最大最小实际数据值。");
            } else if (Float.compare(this.mBubbleMaxSize, this.mBubbleMinSize) == 0) {
                Log.e(TAG, "没有指定气泡本身，最大最小半径。");
            } else if (Double.compare(this.mMaxValue, this.mMinValue) == -1) {
                Log.e(TAG, "轴最大值小于最小值.");
            } else if (Double.compare(this.mMaxValue, this.mMinValue) == 0) {
                Log.e(TAG, "轴最大值与最小值相等.");
            } else {
                float f = this.mBubbleScaleMax - this.mBubbleScaleMin;
                float f2 = this.mBubbleMaxSize - this.mBubbleMinSize;
                List bubble = bubbleData.getBubble();
                int size = bubble.size();
                getPointPaint().setColor(bubbleData.getColor());
                if (bubbleData.getBorderColor() != -1) {
                    getPointBorderPaint().setColor(bubbleData.getBorderColor());
                }
                float itemLabelRotateAngle = bubbleData.getItemLabelRotateAngle();
                int size2 = dataSet.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    PointD pointD = (PointD) dataSet.get(i2);
                    float lnXValPosition = getLnXValPosition(pointD.f18260x, this.mMaxValue, this.mMinValue);
                    float vPValPosition = getVPValPosition(pointD.f18261y);
                    if (i2 < size) {
                        double doubleValue = ((Double) bubble.get(i2)).doubleValue();
                        float calcRaidus = calcRaidus(f, f2, (float) doubleValue);
                        if (!(Float.compare(calcRaidus, 0.0f) == 0 || Float.compare(calcRaidus, 0.0f) == -1)) {
                            this.mPlotDot.setDotRadius(calcRaidus);
                            PlotDotRender.getInstance().renderDot(canvas, this.mPlotDot, lnXValPosition, vPValPosition, getPointPaint());
                            savePointRecord(i, i2, lnXValPosition + this.mMoveX, vPValPosition + this.mMoveY, (lnXValPosition - calcRaidus) + this.mMoveX, (vPValPosition - calcRaidus) + this.mMoveY, (lnXValPosition + calcRaidus) + this.mMoveX, (vPValPosition + calcRaidus) + this.mMoveY);
                            if (bubbleData.getBorderColor() != -1) {
                                canvas.drawCircle(lnXValPosition, vPValPosition, calcRaidus, getPointBorderPaint());
                            }
                            drawAnchor(getAnchorDataPoint(), i, i2, canvas, lnXValPosition, vPValPosition, calcRaidus);
                            if (bubbleData.getLabelVisible()) {
                                DrawHelper.getInstance().drawRotateText(getFormatterDotLabel(new StringBuilder(String.valueOf(Double.toString(pointD.f18260x))).append(",").append(Double.toString(pointD.f18261y)).append(" : ").append(Double.toString(doubleValue)).toString()), lnXValPosition, vPValPosition, itemLabelRotateAngle, canvas, bubbleData.getDotLabelPaint());
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean renderPlot(Canvas canvas) {
        if (this.mMaxValue == this.mMinValue && 0.0d == this.mMaxValue) {
            Log.e(TAG, "请检查是否有设置分类轴的最大最小值。");
            return false;
        } else if (this.mDataset == null) {
            Log.e(TAG, "数据源为空.");
            return false;
        } else {
            drawQuadrant(canvas);
            int size = this.mDataset.size();
            for (int i = 0; i < size; i++) {
                renderPoints(canvas, (BubbleData) this.mDataset.get(i), i);
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
        this.plotLegend.renderBubbleKey(canvas, this.mDataset);
    }
}
