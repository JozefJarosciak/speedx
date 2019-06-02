package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.util.Log;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.event.click.BarPosition;
import org.xclcharts.renderer.AxesChart;
import org.xclcharts.renderer.XEnum.AxisLocation;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.Direction;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.renderer.bar.FlatBar;
import org.xclcharts.renderer.info.PlotAxisTick;

public class RangeBarChart extends AxesChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction = null;
    private static final String TAG = "RangeBarChart";
    private float mBarWidth = 50.0f;
    private List<RangeBarData> mDataSet;
    private FlatBar mFlatBar = new FlatBar();
    private String mKey = "";
    private boolean mLabelVisible = true;
    private double mMaxValue = 0.0d;
    private double mMinValue = 0.0d;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation;
        if (iArr == null) {
            iArr = new int[AxisLocation.values().length];
            try {
                iArr[AxisLocation.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AxisLocation.HORIZONTAL_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AxisLocation.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AxisLocation.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AxisLocation.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AxisLocation.VERTICAL_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation = iArr;
        }
        return iArr;
    }

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

    public RangeBarChart() {
        categoryAxisDefaultSetting();
        dataAxisDefaultSetting();
    }

    public ChartType getType() {
        return ChartType.RANGEBAR;
    }

    public Bar getBar() {
        return this.mFlatBar;
    }

    public void setCategories(List<String> list) {
        if (this.categoryAxis != null) {
            this.categoryAxis.setDataBuilding(list);
        }
    }

    public void setDataSource(List<RangeBarData> list) {
        this.mDataSet = list;
    }

    public List<RangeBarData> getDataSource() {
        return this.mDataSet;
    }

    public void setBarWidth(float f) {
        this.mBarWidth = f;
    }

    public float getBarWidth() {
        return this.mBarWidth;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setLabelVisible(boolean z) {
        this.mLabelVisible = z;
    }

    public boolean getLabelVisible() {
        return this.mLabelVisible;
    }

    protected void categoryAxisDefaultSetting() {
        if (this.categoryAxis != null) {
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mDirection.ordinal()]) {
                case 1:
                    this.categoryAxis.setHorizontalTickAlign(Align.LEFT);
                    this.categoryAxis.getTickLabelPaint().setTextAlign(Align.RIGHT);
                    this.categoryAxis.setVerticalTickPosition(VerticalAlign.MIDDLE);
                    setCategoryAxisLocation(AxisLocation.LEFT);
                    return;
                case 2:
                    this.categoryAxis.setHorizontalTickAlign(Align.CENTER);
                    this.categoryAxis.getTickLabelPaint().setTextAlign(Align.CENTER);
                    this.categoryAxis.setVerticalTickPosition(VerticalAlign.BOTTOM);
                    setCategoryAxisLocation(AxisLocation.BOTTOM);
                    return;
                default:
                    return;
            }
        }
    }

    protected void dataAxisDefaultSetting() {
        if (this.dataAxis != null) {
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mDirection.ordinal()]) {
                case 1:
                    this.dataAxis.setHorizontalTickAlign(Align.CENTER);
                    this.dataAxis.getTickLabelPaint().setTextAlign(Align.CENTER);
                    this.dataAxis.setVerticalTickPosition(VerticalAlign.BOTTOM);
                    setDataAxisLocation(AxisLocation.BOTTOM);
                    return;
                case 2:
                    this.dataAxis.setHorizontalTickAlign(Align.LEFT);
                    this.dataAxis.getTickLabelPaint().setTextAlign(Align.RIGHT);
                    this.dataAxis.setVerticalTickPosition(VerticalAlign.MIDDLE);
                    setDataAxisLocation(AxisLocation.LEFT);
                    return;
                default:
                    return;
            }
        }
    }

    private float[] cataPosition(double d, double d2) {
        float[] fArr = new float[2];
        float axisRange = this.dataAxis.getAxisRange();
        float axisScreenHeight = getAxisScreenHeight();
        fArr[0] = mul(axisScreenHeight, div((float) MathHelper.getInstance().sub(d, (double) this.dataAxis.getAxisMin()), axisRange));
        fArr[1] = mul(axisScreenHeight, div((float) MathHelper.getInstance().sub(d2, (double) this.dataAxis.getAxisMin()), axisRange));
        return fArr;
    }

    public void setCategoryAxisMax(double d) {
        this.mMaxValue = d;
    }

    public void setCategoryAxisMin(double d) {
        this.mMinValue = d;
    }

    protected boolean renderVerticalBar(Canvas canvas) {
        if (this.mMaxValue == this.mMinValue && 0.0d == this.mMaxValue) {
            Log.e(TAG, "请检查是否有设置分类轴的最大最小值。");
            return false;
        } else if (this.mDataSet == null) {
            Log.e(TAG, "数据轴数据源为空");
            return false;
        } else if (this.categoryAxis.getDataSet() == null) {
            Log.e(TAG, "分类轴数据集为空.");
            return false;
        } else if (this.mDataSet == null) {
            return false;
        } else {
            if (this.categoryAxis.getDataSet() == null) {
                return false;
            }
            float f = this.mBarWidth / 2.0f;
            float plotScreenWidth = getPlotScreenWidth();
            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(this.mFlatBar.getItemLabelPaint());
            int size = this.mDataSet.size();
            for (int i = 0; i < size; i++) {
                RangeBarData rangeBarData = (RangeBarData) this.mDataSet.get(i);
                float add = add(this.plotArea.getLeft(), (float) (((double) plotScreenWidth) * ((rangeBarData.getX() - this.mMinValue) / (this.mMaxValue - this.mMinValue))));
                float[] cataPosition = cataPosition(rangeBarData.getMin(), rangeBarData.getMax());
                float sub = sub(this.plotArea.getBottom(), cataPosition[0]);
                float sub2 = sub(this.plotArea.getBottom(), cataPosition[1]);
                this.mFlatBar.renderBar(add - f, sub, add + f, sub2, canvas);
                saveBarRectFRecord(i, 0, (add - f) + this.mMoveX, sub + this.mMoveY, (add + f) + this.mMoveX, sub2 + this.mMoveY);
                drawFocusRect(canvas, i, 0, add - f, sub, add + f, sub2);
                if (getLabelVisible()) {
                    this.mFlatBar.renderBarItemLabel(getFormatterItemLabel(rangeBarData.getMax()), add, sub2 - (paintFontHeight / 2.0f), canvas);
                    this.mFlatBar.renderBarItemLabel(getFormatterItemLabel(rangeBarData.getMin()), add, (sub + paintFontHeight) + (paintFontHeight / 2.0f), canvas);
                }
            }
            return true;
        }
    }

    public BarPosition getPositionRecord(float f, float f2) {
        return getBarRecord(f, f2);
    }

    protected void drawClipDataAxisGridlines(Canvas canvas) {
        float f = 0.0f;
        float f2 = 0.0f;
        int aixTickCount = this.dataAxis.getAixTickCount();
        if (aixTickCount == 0) {
            Log.e(TAG, "数据库数据源为0!");
            return;
        }
        int i;
        float axisYPos;
        float left;
        if (1 == aixTickCount) {
            i = aixTickCount - 1;
        } else {
            i = aixTickCount;
        }
        AxisLocation dataAxisLocation = getDataAxisLocation();
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[dataAxisLocation.ordinal()]) {
            case 1:
            case 2:
            case 5:
                f = getVerticalXSteps(i);
                axisYPos = getAxisYPos(dataAxisLocation);
                left = this.plotArea.getLeft();
                break;
            case 3:
            case 4:
            case 6:
                f2 = getVerticalYSteps(i);
                float axisXPos = getAxisXPos(dataAxisLocation);
                axisYPos = this.plotArea.getBottom();
                left = axisXPos;
                break;
            default:
                axisYPos = 0.0f;
                left = 0.0f;
                break;
        }
        this.mLstDataTick.clear();
        for (int i2 = 0; i2 < aixTickCount; i2++) {
            double add;
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[dataAxisLocation.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    float add2 = add(left, mul((float) i2, f));
                    drawVerticalGridLines(canvas, this.plotArea.getTop(), this.plotArea.getBottom(), i2, aixTickCount, f, add2);
                    add = MathHelper.getInstance().add((double) this.dataAxis.getAxisMin(), ((double) i2) * this.dataAxis.getAxisSteps());
                    this.mLstDataTick.add(new PlotAxisTick(i2, add2, axisYPos, Double.toString(add)));
                    break;
                case 3:
                case 4:
                case 6:
                    float sub = sub(this.plotArea.getBottom(), mul((float) i2, f2));
                    drawHorizontalGridLines(canvas, this.plotArea.getLeft(), this.plotArea.getRight(), i2, aixTickCount, f2, sub);
                    add = MathHelper.getInstance().add((double) this.dataAxis.getAxisMin(), ((double) i2) * this.dataAxis.getAxisSteps());
                    this.mLstDataTick.add(new PlotAxisTick(i2, left, sub, Double.toString(add)));
                    break;
                default:
                    break;
            }
        }
    }

    protected void drawClipCategoryAxisGridlines(Canvas canvas) {
        List dataSet = this.categoryAxis.getDataSet();
        float f = 0.0f;
        float f2 = 0.0f;
        int size = dataSet.size();
        int i = size + 1;
        if (size == 0) {
            Log.e(TAG, "分类轴数据源为0!");
            return;
        }
        float bottom;
        float f3;
        AxisLocation categoryAxisLocation = getCategoryAxisLocation();
        if (AxisLocation.LEFT == categoryAxisLocation || AxisLocation.RIGHT == categoryAxisLocation || AxisLocation.VERTICAL_CENTER == categoryAxisLocation) {
            f2 = getVerticalYSteps(i);
            float axisXPos = getAxisXPos(categoryAxisLocation);
            bottom = this.plotArea.getBottom();
            f3 = axisXPos;
        } else {
            f = getVerticalXSteps(i);
            bottom = getAxisYPos(categoryAxisLocation);
            f3 = this.plotArea.getLeft();
        }
        this.mLstCateTick.clear();
        for (int i2 = 0; i2 < size; i2++) {
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[categoryAxisLocation.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    float add = add(this.plotArea.getLeft(), mul((float) (i2 + 1), f));
                    drawVerticalGridLines(canvas, this.plotArea.getTop(), this.plotArea.getBottom(), i2, size, f, add);
                    if (!this.categoryAxis.isShowAxisLabels()) {
                        break;
                    }
                    this.mLstCateTick.add(new PlotAxisTick(add, bottom, (String) dataSet.get(i2)));
                    break;
                case 3:
                case 4:
                case 6:
                    float sub = sub(bottom, mul((float) (i2 + 1), f2));
                    drawHorizontalGridLines(canvas, this.plotArea.getLeft(), this.plotArea.getRight(), i2, size, f2, sub);
                    if (!this.categoryAxis.isShowAxisLabels()) {
                        break;
                    }
                    this.mLstCateTick.add(new PlotAxisTick(f3, sub, (String) this.categoryAxis.getDataSet().get(i2)));
                    break;
                default:
                    break;
            }
        }
    }

    protected void drawClipPlot(Canvas canvas) {
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mDirection.ordinal()]) {
            case 2:
                renderVerticalBar(canvas);
                return;
            default:
                return;
        }
    }

    protected void drawClipLegend(Canvas canvas) {
        this.plotLegend.renderRangeBarKey(canvas, getKey(), this.mFlatBar.getBarPaint().getColor());
    }
}
