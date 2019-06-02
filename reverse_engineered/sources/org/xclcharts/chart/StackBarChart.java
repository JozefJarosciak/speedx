package org.xclcharts.chart;

import android.graphics.Canvas;
import java.util.List;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.BarCenterStyle;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.renderer.bar.FlatBar;

public class StackBarChart extends BarChart {
    private FlatBar flatBar = null;
    private boolean mTotalLabelVisible = true;

    public StackBarChart() {
        if (this.flatBar == null) {
            this.flatBar = new FlatBar();
        }
    }

    public ChartType getType() {
        return ChartType.STACKBAR;
    }

    public void setTotalLabelVisible(boolean z) {
        this.mTotalLabelVisible = z;
    }

    public Bar getBar() {
        return this.flatBar;
    }

    private float getHBarHeight(float f) {
        float round = MathHelper.getInstance().round(mul(f, 0.5f), 2);
        float barMaxPxHeight = this.flatBar.getBarMaxPxHeight();
        return (Float.compare(barMaxPxHeight, 0.0f) == 1 && Float.compare(round, barMaxPxHeight) == 1) ? barMaxPxHeight : round;
    }

    protected boolean renderHorizontalBar(Canvas canvas) {
        if (this.categoryAxis.getDataSet() == null) {
            return false;
        }
        float plotScreenWidth = getPlotScreenWidth();
        float axisRange = this.dataAxis.getAxisRange();
        float verticalYSteps = getVerticalYSteps(getCateTickCount());
        float hBarHeight = getHBarHeight(verticalYSteps);
        int size = this.categoryAxis.getDataSet().size();
        for (int i = 0; i < size; i++) {
            float f;
            float f2;
            float left = this.plotArea.getLeft();
            float sub = sub(this.plotArea.getBottom(), mul((float) (i + 1), verticalYSteps));
            double d = 0.0d;
            if (BarCenterStyle.SPACE == getBarCenterStyle()) {
                sub = add(sub, div(verticalYSteps, 2.0f));
                f = sub;
                f2 = sub;
            } else {
                f = sub;
                f2 = sub;
            }
            List dataSource = getDataSource();
            if (!(dataSource == null || dataSource.size() == 0)) {
                int size2 = dataSource.size();
                int i2 = 0;
                while (i2 < size2) {
                    float f3;
                    double d2;
                    BarData barData = (BarData) dataSource.get(i2);
                    double d3;
                    if (barData.getDataSet() == null) {
                        d3 = d;
                        f3 = left;
                        d2 = d3;
                    } else {
                        this.flatBar.getBarPaint().setColor(barData.getColor().intValue());
                        if (barData.getDataSet().size() < i + 1) {
                            d3 = d;
                            f3 = left;
                            d2 = d3;
                        } else {
                            float mul;
                            double doubleValue = ((Double) barData.getDataSet().get(i)).doubleValue();
                            double add = MathHelper.getInstance().add(d, doubleValue);
                            if (i2 == 0) {
                                mul = mul(plotScreenWidth, div((float) MathHelper.getInstance().sub(doubleValue, (double) this.dataAxis.getAxisMin()), axisRange));
                            } else {
                                mul = mul(plotScreenWidth, div((float) doubleValue, axisRange));
                            }
                            f3 = sub(f, hBarHeight / 2.0f);
                            float add2 = add(left, mul);
                            float add3 = add(f, hBarHeight / 2.0f);
                            this.flatBar.renderBar(left, f3, add2, add3, canvas);
                            saveBarRectFRecord(i2, i, left + this.mMoveX, f3 + this.mMoveY, add2 + this.mMoveX, add3 + this.mMoveY);
                            drawFocusRect(canvas, i2, i, left, f3, add2, add3);
                            float add4 = add(left, mul / 2.0f);
                            drawAnchor(getAnchorDataPoint(), i2, i, canvas, add4, f2, 0.0f);
                            this.flatBar.renderBarItemLabel(getFormatterItemLabel(doubleValue), add4, f2, canvas);
                            f3 = add(left, mul);
                            d2 = add;
                        }
                    }
                    i2++;
                    left = f3;
                    d = d2;
                }
                if (this.mTotalLabelVisible) {
                    this.flatBar.renderBarItemLabel(getFormatterItemLabel(d), add(this.plotArea.getLeft(), mul(div(plotScreenWidth, axisRange), (float) MathHelper.getInstance().sub(d, (double) this.dataAxis.getAxisMin()))), f, canvas);
                }
            }
        }
        return true;
    }

    private float getVBarWidth(float f) {
        float mul = mul(f, 0.5f);
        float barMaxPxWidth = this.flatBar.getBarMaxPxWidth();
        return (Float.compare(barMaxPxWidth, 0.0f) == 1 && Float.compare(mul, barMaxPxWidth) == 1) ? barMaxPxWidth : mul;
    }

    protected boolean renderVerticalBar(Canvas canvas) {
        List dataSet = this.categoryAxis.getDataSet();
        if (dataSet == null) {
            return false;
        }
        List dataSource = getDataSource();
        if (dataSource == null) {
            return false;
        }
        float verticalXSteps = getVerticalXSteps(dataSet.size() + 1);
        float axisScreenHeight = getAxisScreenHeight();
        float axisRange = this.dataAxis.getAxisRange();
        float vBarWidth = getVBarWidth(verticalXSteps);
        int size = dataSet.size();
        for (int i = 0; i < size; i++) {
            float f;
            float f2;
            float add = add(this.plotArea.getLeft(), mul((float) (i + 1), verticalXSteps));
            float bottom = this.plotArea.getBottom();
            Double valueOf = Double.valueOf(0.0d);
            if (BarCenterStyle.SPACE == getBarCenterStyle()) {
                add = sub(add, div(verticalXSteps, 2.0f));
                f = add;
                f2 = add;
            } else {
                f = add;
                f2 = add;
            }
            int size2 = dataSource.size();
            int i2 = 0;
            while (i2 < size2) {
                Double d;
                BarData barData = (BarData) dataSource.get(i2);
                if (barData.getDataSet() == null) {
                    d = valueOf;
                } else {
                    this.flatBar.getBarPaint().setColor(barData.getColor().intValue());
                    if (barData.getDataSet().size() < i + 1) {
                        d = valueOf;
                    } else {
                        float mul;
                        Double d2 = (Double) barData.getDataSet().get(i);
                        Double valueOf2 = Double.valueOf(MathHelper.getInstance().add(valueOf.doubleValue(), d2.doubleValue()));
                        if (i2 == 0) {
                            mul = mul(axisScreenHeight, div((float) MathHelper.getInstance().sub(d2.doubleValue(), (double) this.dataAxis.getAxisMin()), axisRange));
                        } else {
                            mul = mul(axisScreenHeight, (float) MathHelper.getInstance().div(d2.doubleValue(), (double) axisRange));
                        }
                        float sub = sub(f2, vBarWidth / 2.0f);
                        float sub2 = sub(bottom, mul);
                        float add2 = add(f2, vBarWidth / 2.0f);
                        this.flatBar.renderBar(sub, sub2, add2, bottom, canvas);
                        saveBarRectFRecord(i2, i, sub + this.mMoveX, sub2 + this.mMoveY, add2 + this.mMoveX, bottom + this.mMoveY);
                        drawFocusRect(canvas, i2, i, sub, sub2, add2, bottom);
                        float sub3 = sub(bottom, mul / 2.0f);
                        drawAnchor(getAnchorDataPoint(), i2, i, canvas, f, sub3, 0.0f);
                        this.flatBar.renderBarItemLabel(getFormatterItemLabel(d2.doubleValue()), f, sub3, canvas);
                        bottom = sub(bottom, mul);
                        d = valueOf2;
                    }
                }
                i2++;
                valueOf = d;
            }
            if (this.mTotalLabelVisible) {
                add = MathHelper.getInstance().mul(div(axisScreenHeight, axisRange), (float) MathHelper.getInstance().sub(valueOf.doubleValue(), (double) this.dataAxis.getAxisMin()));
                this.flatBar.renderBarItemLabel(getFormatterItemLabel(valueOf.doubleValue()), f2, sub(this.plotArea.getBottom(), add), canvas);
            }
        }
        return true;
    }
}
