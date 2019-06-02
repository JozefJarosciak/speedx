package org.xclcharts.chart;

import android.graphics.Canvas;
import android.util.Log;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.BarCenterStyle;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.Direction;
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.renderer.bar.Bar3D;

public class BarChart3D extends BarChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction = null;
    private static final String TAG = "BarChart3D";
    private Bar3D mBar3D = new Bar3D();

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction;
        if (iArr == null) {
            iArr = new int[Direction.values().length];
            try {
                iArr[Direction.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Direction.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction = iArr;
        }
        return iArr;
    }

    public BarChart3D() {
        if (this.categoryAxis != null) {
            this.categoryAxis.hideTickMarks();
        }
        setBarCenterStyle(BarCenterStyle.TICKMARKS);
    }

    public ChartType getType() {
        return ChartType.BAR3D;
    }

    public void setAxis3DBaseThickness(int i) {
        this.mBar3D.setAxis3DBaseThickness(i);
    }

    public double getAxis3DBaseThickness() {
        return (double) this.mBar3D.getAxis3DBaseThickness();
    }

    public void setBarThickness(int i) {
        this.mBar3D.setThickness(i);
    }

    public double getBarThickness() {
        return (double) this.mBar3D.getThickness();
    }

    public void setBarAngle(int i) {
        this.mBar3D.setAngle(i);
    }

    public int getBarAngle() {
        return this.mBar3D.getAngle();
    }

    public void setBarAlpha(int i) {
        this.mBar3D.setAlpha(i);
    }

    public void setAxis3DBaseColor(int i) {
        this.mBar3D.setAxis3DBaseColor(i);
    }

    public Bar getBar() {
        return this.mBar3D;
    }

    protected boolean renderHorizontalBar(Canvas canvas) {
        List dataSource = getDataSource();
        if (dataSource == null || dataSource.size() == 0) {
            return false;
        }
        float verticalYSteps = getVerticalYSteps(getCateTickCount());
        float left = this.mMoveX + this.plotArea.getLeft();
        float bottom = this.plotArea.getBottom();
        int datasetSize = getDatasetSize(dataSource);
        if (datasetSize <= 0) {
            return false;
        }
        int i = 0;
        float[] barHeightAndMargin = this.mBar3D.getBarHeightAndMargin(verticalYSteps, datasetSize);
        if (barHeightAndMargin == null || barHeightAndMargin.length != 2) {
            Log.e(TAG, "分隔间距计算失败.");
            return false;
        }
        float f = barHeightAndMargin[0];
        float f2 = barHeightAndMargin[1];
        float add = add(mul((float) datasetSize, f), mul((float) (datasetSize - 1), f2));
        float plotWidth = this.plotArea.getPlotWidth();
        float axisRange = this.dataAxis.getAxisRange();
        int i2 = 0;
        while (i2 < datasetSize) {
            int i3;
            BarData barData = (BarData) dataSource.get(i2);
            List dataSet = barData.getDataSet();
            if (dataSet == null) {
                i3 = i;
            } else if (dataSet.size() == 0) {
                i3 = i;
            } else {
                List dataColor = barData.getDataColor();
                int intValue = barData.getColor().intValue();
                this.mBar3D.getBarPaint().setColor(intValue);
                for (int i4 = 0; i4 < dataSet.size(); i4++) {
                    Double d = (Double) dataSet.get(i4);
                    setBarDataColor(this.mBar3D.getBarPaint(), dataColor, i4, intValue);
                    float sub = sub(add(sub(bottom, mul((float) (i4 + 1), verticalYSteps)), add / 2.0f), (f + f2) * ((float) i));
                    float mul = mul(plotWidth, div((float) MathHelper.getInstance().sub(d.doubleValue(), (double) this.dataAxis.getAxisMin()), axisRange));
                    float sub2 = sub(sub, f);
                    float add2 = add(left, mul);
                    this.mBar3D.renderHorizontal3DBar(left, sub2, add2, sub, this.mBar3D.getBarPaint().getColor(), canvas);
                    saveBarRectFRecord(i2, i4, left + this.mMoveX, sub2 + this.mMoveY, add2 + this.mMoveX, sub + this.mMoveY);
                    float sub3 = sub(sub, f / 2.0f);
                    drawAnchor(getAnchorDataPoint(), i2, i4, canvas, add2, sub3, 0.0f);
                    if (this.mEqualAxisMin || Double.compare((double) this.dataAxis.getAxisMin(), d.doubleValue()) != 0) {
                        this.mBar3D.renderBarItemLabel(getFormatterItemLabel(d.doubleValue()), add2, sub3, canvas);
                    }
                }
                i3 = i + 1;
            }
            i2++;
            i = i3;
        }
        return true;
    }

    protected float get3DOffsetX() {
        return (float) (this.mBar3D.getOffsetX() * 2.0d);
    }

    protected float get3DBaseOffsetX() {
        return (float) this.mBar3D.getOffsetX((double) this.mBar3D.getAxis3DBaseThickness(), (double) this.mBar3D.getAngle());
    }

    protected float get3DBaseOffsetY() {
        double axis3DBaseThickness = (double) this.mBar3D.getAxis3DBaseThickness();
        return (float) MathHelper.getInstance().add(MathHelper.getInstance().add(this.mBar3D.getOffsetY(axis3DBaseThickness, (double) this.mBar3D.getAngle()), axis3DBaseThickness), (double) DrawHelper.getInstance().getPaintFontHeight(this.categoryAxis.getTickLabelPaint()));
    }

    protected boolean renderVerticalBar(Canvas canvas) {
        float left = this.plotArea.getLeft();
        float bottom = this.plotArea.getBottom();
        List dataSet = this.categoryAxis.getDataSet();
        if (dataSet == null || dataSet.size() == 0) {
            return false;
        }
        float div = div(this.plotArea.getPlotWidth(), (float) (dataSet.size() + 1));
        List dataSource = getDataSource();
        if (dataSource == null || dataSource.size() == 0) {
            return false;
        }
        int datasetSize = getDatasetSize(dataSource);
        if (datasetSize <= 0) {
            return false;
        }
        int i = 0;
        float[] barWidthAndMargin = this.mBar3D.getBarWidthAndMargin(div, datasetSize);
        if (barWidthAndMargin == null || barWidthAndMargin.length != 2) {
            Log.e(TAG, "分隔间距计算失败.");
            return false;
        }
        float f = barWidthAndMargin[0];
        float f2 = barWidthAndMargin[1];
        float add = add(mul((float) datasetSize, f), mul((float) (datasetSize - 1), f2));
        double axisRange = (double) this.dataAxis.getAxisRange();
        double axisMin = (double) this.dataAxis.getAxisMin();
        int i2 = 0;
        while (i2 < datasetSize) {
            int i3;
            BarData barData = (BarData) dataSource.get(i2);
            List dataSet2 = barData.getDataSet();
            if (dataSet2 == null) {
                i3 = i;
            } else {
                List dataColor = barData.getDataColor();
                int intValue = barData.getColor().intValue();
                this.mBar3D.getBarPaint().setColor(intValue);
                for (int i4 = 0; i4 < dataSet2.size(); i4++) {
                    Double d = (Double) dataSet2.get(i4);
                    setBarDataColor(this.mBar3D.getBarPaint(), dataColor, i4, intValue);
                    float mul = mul(this.plotArea.getPlotHeight(), (float) MathHelper.getInstance().div(MathHelper.getInstance().sub(d.doubleValue(), axisMin), axisRange));
                    float add2 = add(sub(add(left, mul((float) (i4 + 1), div)), add / 2.0f), (f + f2) * ((float) i));
                    float add3 = add(add2, f);
                    float sub = sub(bottom, mul);
                    this.mBar3D.renderVertical3DBar(add2, sub, add3, bottom, this.mBar3D.getBarPaint().getColor(), canvas);
                    saveBarRectFRecord(i2, i4, add2 + this.mMoveX, sub + this.mMoveY, add3 + this.mMoveX, this.plotArea.getBottom() + this.mMoveY);
                    float add4 = add(add2, f / 2.0f);
                    drawAnchor(getAnchorDataPoint(), i2, i4, canvas, add4, sub, 0.0f);
                    this.mBar3D.renderBarItemLabel(getFormatterItemLabel(d.doubleValue()), add4, sub, canvas);
                    drawFocusRect(canvas, i2, i4, add2, sub, add3, bottom);
                }
                i3 = i + 1;
            }
            i2++;
            i = i3;
        }
        return true;
    }

    protected void drawClipAxisLine(Canvas canvas) {
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[getChartDirection().ordinal()]) {
            case 1:
                this.dataAxis.renderAxis(canvas, this.plotArea.getLeft(), this.plotArea.getBottom(), this.plotArea.getPlotRight(), this.plotArea.getBottom());
                this.mBar3D.render3DYAxis(this.plotArea.getLeft(), this.plotArea.getTop(), this.plotArea.getPlotRight(), this.plotArea.getBottom(), canvas);
                return;
            case 2:
                this.dataAxis.renderAxis(canvas, this.plotArea.getLeft(), this.plotArea.getTop(), this.plotArea.getLeft(), this.plotArea.getBottom());
                this.mBar3D.render3DXAxis(this.plotArea.getLeft(), this.plotArea.getBottom(), this.plotArea.getPlotRight(), this.plotArea.getBottom(), canvas);
                return;
            default:
                return;
        }
    }
}
