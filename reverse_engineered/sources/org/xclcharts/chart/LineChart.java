package org.xclcharts.chart;

import android.graphics.Canvas;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.LnChart;
import org.xclcharts.renderer.XEnum.BarCenterStyle;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotDotRender;
import org.xclcharts.renderer.line.PlotLine;

public class LineChart extends LnChart {
    private static final String TAG = "LineChart";
    protected List<LineData> mDataSet;
    private boolean mLineAxisIntersectVisible = true;
    private List<LnData> mLstKey = new ArrayList();

    public ChartType getType() {
        return ChartType.LINE;
    }

    public void setCategories(List<String> list) {
        if (this.categoryAxis != null) {
            this.categoryAxis.setDataBuilding(list);
        }
    }

    public void setDataSource(List<LineData> list) {
        this.mDataSet = list;
    }

    public List<LineData> getDataSource() {
        return this.mDataSet;
    }

    public void setLineAxisIntersectVisible(boolean z) {
        this.mLineAxisIntersectVisible = z;
    }

    public boolean getLineAxisIntersectVisible() {
        return this.mLineAxisIntersectVisible;
    }

    public void setBarCenterStyle(BarCenterStyle barCenterStyle) {
        this.mBarCenterStyle = barCenterStyle;
    }

    public BarCenterStyle getBarCenterStyle() {
        return this.mBarCenterStyle;
    }

    public void setXCoordFirstTickmarksBegin(boolean z) {
        this.mXCoordFirstTickmarksBegin = z;
    }

    private boolean renderLine(Canvas canvas, LineData lineData, String str, int i) {
        if (this.categoryAxis == null || this.dataAxis == null) {
            return false;
        }
        if (lineData == null) {
            Log.i(TAG, "传入的线的数据序列参数为空.");
            return false;
        }
        float left = this.plotArea.getLeft();
        float bottom = this.plotArea.getBottom();
        List dataSet = this.categoryAxis.getDataSet();
        if (dataSet == null || dataSet.size() == 0) {
            Log.w(TAG, "分类轴数据为空.");
            return false;
        }
        List linePoint = lineData.getLinePoint();
        if (linePoint == null || linePoint.size() == 0) {
            Log.w(TAG, "当前分类的线数据序列值为空.");
            return false;
        }
        int i2 = 0;
        if (1 == dataSet.size()) {
            i2 = 1;
        }
        float verticalXSteps = getVerticalXSteps(getCategoryAxisCount());
        float itemLabelRotateAngle = lineData.getItemLabelRotateAngle();
        PlotLine plotLine = lineData.getPlotLine();
        PlotDot plotDot = plotLine.getPlotDot();
        float dotRadius = plotDot.getDotRadius();
        int size = linePoint.size();
        int i3 = 0;
        int i4 = i2;
        float f = bottom;
        float f2 = left;
        while (i3 < size) {
            float add;
            float sub;
            double doubleValue = ((Double) linePoint.get(i3)).doubleValue();
            float vPValPosition = getVPValPosition(doubleValue);
            if (this.mXCoordFirstTickmarksBegin) {
                add = add(left, mul((float) (i4 + 1), verticalXSteps));
            } else {
                add = add(left, mul((float) i4, verticalXSteps));
            }
            if (this.mXCoordFirstTickmarksBegin && BarCenterStyle.SPACE == this.mBarCenterStyle) {
                sub = sub(add, div(verticalXSteps, 2.0f));
            } else {
                sub = add;
            }
            if (i4 == 0) {
                f = vPValPosition;
                f2 = sub;
            }
            if (!getLineAxisIntersectVisible()) {
                if (Double.compare(doubleValue, (double) this.dataAxis.getAxisMin()) == 0) {
                    i2 = i4 + 1;
                    i3++;
                    i4 = i2;
                    f = vPValPosition;
                    f2 = sub;
                }
            }
            if (!str.equalsIgnoreCase("LINE")) {
                if (str.equalsIgnoreCase("DOT2LABEL")) {
                    float f3;
                    if (plotLine.getDotStyle().equals(DotStyle.HIDE)) {
                        f3 = sub;
                    } else {
                        PlotDotRender.getInstance().renderDot(canvas, plotDot, sub, vPValPosition, plotLine.getDotPaint());
                        savePointRecord(i, i3, sub + this.mMoveX, vPValPosition + this.mMoveY, (sub - dotRadius) + this.mMoveX, (vPValPosition - dotRadius) + this.mMoveY, (sub + dotRadius) + this.mMoveX, (vPValPosition + dotRadius) + this.mMoveY);
                        f3 = add(sub, dotRadius);
                    }
                    drawAnchor(getAnchorDataPoint(), i, i3, canvas, f3 - dotRadius, vPValPosition, dotRadius);
                    if (lineData.getLabelVisible()) {
                        lineData.getLabelOptions().drawLabel(canvas, plotLine.getDotLabelPaint(), getFormatterItemLabel(doubleValue), f3 - dotRadius, vPValPosition, itemLabelRotateAngle, lineData.getLineColor());
                        sub = f3;
                    } else {
                        sub = f3;
                    }
                } else {
                    Log.w(TAG, "未知的参数标识。");
                    return false;
                }
            } else if (getLineAxisIntersectVisible() || Float.compare(f, bottom) != 0) {
                DrawHelper.getInstance().drawLine(lineData.getLineStyle(), f2, f, sub, vPValPosition, canvas, plotLine.getLinePaint());
            }
            i2 = i4 + 1;
            i3++;
            i4 = i2;
            f = vPValPosition;
            f2 = sub;
        }
        return true;
    }

    private boolean renderVerticalPlot(Canvas canvas) {
        if (this.mDataSet == null) {
            Log.w(TAG, "数据轴数据为空.");
            return false;
        }
        this.mLstKey.clear();
        String str = "";
        int size = this.mDataSet.size();
        for (int i = 0; i < size; i++) {
            if (!renderLine(canvas, (LineData) this.mDataSet.get(i), "LINE", i)) {
                return false;
            }
            if (!renderLine(canvas, (LineData) this.mDataSet.get(i), "DOT2LABEL", i)) {
                return false;
            }
            str = ((LineData) this.mDataSet.get(i)).getLineKey();
            if ("" != str && str.length() > 0) {
                this.mLstKey.add((LnData) this.mDataSet.get(i));
            }
        }
        return true;
    }

    protected void drawClipPlot(Canvas canvas) {
        if (renderVerticalPlot(canvas) && this.mCustomLine != null) {
            this.mCustomLine.setVerticalPlot(this.dataAxis, this.plotArea, getAxisScreenHeight());
            this.mCustomLine.renderVerticalCustomlinesDataAxis(canvas);
        }
    }

    protected void drawClipLegend(Canvas canvas) {
        this.plotLegend.renderLineKey(canvas, this.mLstKey);
        this.mLstKey.clear();
    }
}
