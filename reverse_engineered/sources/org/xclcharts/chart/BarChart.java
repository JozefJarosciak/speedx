package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.Log;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.event.click.BarPosition;
import org.xclcharts.renderer.AxesChart;
import org.xclcharts.renderer.XEnum.AxisLocation;
import org.xclcharts.renderer.XEnum.BarCenterStyle;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.Direction;
import org.xclcharts.renderer.XEnum.ItemLabelStyle;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.bar.Bar;
import org.xclcharts.renderer.bar.FlatBar;
import org.xclcharts.renderer.info.AnchorDataPoint;
import org.xclcharts.renderer.info.PlotAxisTick;
import org.xclcharts.renderer.line.PlotCustomLine;

public class BarChart extends AxesChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction = null;
    private static final String TAG = "BarChart";
    private List<AnchorDataPoint> mAnchorSet;
    private BarCenterStyle mBarCenterStyle = BarCenterStyle.SPACE;
    private PlotCustomLine mCustomLine = null;
    private List<BarData> mDataSet;
    protected boolean mEqualAxisMin = false;
    private FlatBar mFlatBar = new FlatBar();

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

    public BarChart() {
        defaultAxisSetting();
    }

    public ChartType getType() {
        return ChartType.BAR;
    }

    public void setBarCenterStyle(BarCenterStyle barCenterStyle) {
        this.mBarCenterStyle = barCenterStyle;
    }

    public BarCenterStyle getBarCenterStyle() {
        return this.mBarCenterStyle;
    }

    public Bar getBar() {
        return this.mFlatBar;
    }

    public void hideBarEqualAxisMin() {
        this.mEqualAxisMin = false;
    }

    public void showBarEqualAxisMin() {
        this.mEqualAxisMin = true;
    }

    public void setCustomLines(List<CustomLineData> list) {
        if (this.mCustomLine == null) {
            this.mCustomLine = new PlotCustomLine();
        }
        this.mCustomLine.setCustomLines(list);
    }

    public void setCategories(List<String> list) {
        if (this.categoryAxis != null) {
            this.categoryAxis.setDataBuilding(list);
        }
    }

    public void setAnchorDataPoint(List<AnchorDataPoint> list) {
        this.mAnchorSet = list;
    }

    public List<AnchorDataPoint> getAnchorDataPoint() {
        return this.mAnchorSet;
    }

    public void setDataSource(List<BarData> list) {
        this.mDataSet = list;
    }

    public List<BarData> getDataSource() {
        return this.mDataSet;
    }

    public void setChartDirection(Direction direction) {
        this.mDirection = direction;
        defaultAxisSetting();
    }

    public Direction getChartDirection() {
        return this.mDirection;
    }

    protected void defaultAxisSetting() {
        if (this.mDirection != null) {
            categoryAxisDefaultSetting();
            dataAxisDefaultSetting();
            if (getBar() != null) {
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mDirection.ordinal()]) {
                    case 1:
                        getBar().getItemLabelPaint().setTextAlign(Align.LEFT);
                        getBar().setBarDirection(Direction.HORIZONTAL);
                        return;
                    case 2:
                        getBar().setBarDirection(Direction.VERTICAL);
                        return;
                    default:
                        return;
                }
            }
        }
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

    protected int getDataAxisDetailSetMaxSize() {
        int i = 0;
        if (this.mDataSet != null) {
            int size = this.mDataSet.size();
            int i2 = 0;
            while (i2 < size) {
                int size2;
                if (i < ((BarData) this.mDataSet.get(i2)).getDataSet().size()) {
                    size2 = ((BarData) this.mDataSet.get(i2)).getDataSet().size();
                } else {
                    size2 = i;
                }
                i2++;
                i = size2;
            }
        }
        return i;
    }

    protected int getDataTickCount() {
        return this.dataAxis.getAixTickCount() + 1;
    }

    protected int getCateTickCount() {
        int size = this.categoryAxis.getDataSet().size();
        if (BarCenterStyle.SPACE != this.mBarCenterStyle) {
            return size + 1;
        }
        return size;
    }

    protected void drawClipDataAxisGridlines(Canvas canvas) {
        float f = 0.0f;
        float f2 = 0.0f;
        int aixTickCount = this.dataAxis.getAixTickCount();
        int i = aixTickCount + 1;
        if (aixTickCount == 0) {
            Log.w(TAG, "数据轴数据源为0!");
            return;
        }
        float axisYPos;
        float left;
        AxisLocation dataAxisLocation = getDataAxisLocation();
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[dataAxisLocation.ordinal()]) {
            case 1:
            case 2:
            case 5:
                f = getVerticalXSteps(aixTickCount);
                axisYPos = getAxisYPos(dataAxisLocation);
                left = this.plotArea.getLeft();
                break;
            case 3:
            case 4:
            case 6:
                f2 = getVerticalYSteps(aixTickCount);
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
        for (int i2 = 0; i2 < i; i2++) {
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
        int size = dataSet.size();
        int cateTickCount = getCateTickCount();
        if (size == 0) {
            Log.w(TAG, "分类轴数据源为0!");
            return;
        }
        float bottom;
        float f2;
        float f3;
        AxisLocation categoryAxisLocation = getCategoryAxisLocation();
        if (AxisLocation.LEFT == categoryAxisLocation || AxisLocation.RIGHT == categoryAxisLocation || AxisLocation.VERTICAL_CENTER == categoryAxisLocation) {
            f = getVerticalYSteps(cateTickCount);
            float axisXPos = getAxisXPos(categoryAxisLocation);
            bottom = this.plotArea.getBottom();
            f2 = axisXPos;
            f3 = 0.0f;
        } else {
            float verticalXSteps = getVerticalXSteps(cateTickCount);
            bottom = getAxisYPos(categoryAxisLocation);
            f2 = this.plotArea.getLeft();
            f3 = verticalXSteps;
        }
        this.mLstCateTick.clear();
        boolean z = true;
        for (int i = 0; i < size; i++) {
            float add;
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[categoryAxisLocation.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    float add2 = add(this.plotArea.getLeft(), mul((float) (i + 1), f3));
                    drawVerticalGridLines(canvas, this.plotArea.getTop(), this.plotArea.getBottom(), i, size, f3, add2);
                    if (!this.categoryAxis.isShowAxisLabels()) {
                        break;
                    }
                    float sub;
                    boolean z2;
                    add = add(bottom, get3DBaseOffsetY());
                    float sub2 = sub(add2, get3DBaseOffsetX());
                    if (BarCenterStyle.SPACE == this.mBarCenterStyle) {
                        if (i == size - 1) {
                            z = false;
                        }
                        sub = sub(sub2, div(f3, 2.0f));
                        z2 = z;
                    } else {
                        z2 = z;
                        sub = sub2;
                    }
                    this.mLstCateTick.add(new PlotAxisTick(sub2, add, (String) dataSet.get(i), sub, add, z2));
                    z = z2;
                    break;
                case 3:
                case 4:
                case 6:
                    float sub3 = sub(bottom, mul((float) (i + 1), f));
                    drawHorizontalGridLines(canvas, this.plotArea.getLeft(), this.plotArea.getRight(), i, size, f, sub3);
                    if (!this.categoryAxis.isShowAxisLabels()) {
                        break;
                    }
                    float add3;
                    boolean z3;
                    add = sub(f2, get3DOffsetX());
                    if (BarCenterStyle.SPACE == this.mBarCenterStyle) {
                        boolean z4;
                        if (i == size - 1) {
                            z4 = false;
                        } else {
                            z4 = z;
                        }
                        add3 = add(sub3, div(f, 2.0f));
                        z3 = z4;
                    } else {
                        z3 = z;
                        add3 = sub3;
                    }
                    this.mLstCateTick.add(new PlotAxisTick(add, sub3, (String) this.categoryAxis.getDataSet().get(i), add, add3, z3));
                    z = z3;
                    break;
                default:
                    break;
            }
        }
    }

    protected float get3DOffsetX() {
        return 0.0f;
    }

    protected float get3DBaseOffsetX() {
        return 0.0f;
    }

    protected float get3DBaseOffsetY() {
        return 0.0f;
    }

    protected boolean renderHorizontalBar(Canvas canvas) {
        if (this.mDataSet == null || this.mDataSet.size() == 0) {
            return false;
        }
        float verticalYSteps = getVerticalYSteps(getCateTickCount());
        float left = this.plotArea.getLeft();
        float bottom = this.plotArea.getBottom();
        int datasetSize = getDatasetSize(this.mDataSet);
        if (datasetSize <= 0) {
            return false;
        }
        int i = 0;
        float[] barHeightAndMargin = this.mFlatBar.getBarHeightAndMargin(verticalYSteps, datasetSize);
        if (barHeightAndMargin == null || barHeightAndMargin.length != 2) {
            Log.e(TAG, "分隔间距计算失败.");
            return false;
        }
        float f = barHeightAndMargin[0];
        float f2 = barHeightAndMargin[1];
        float add = add(mul((float) datasetSize, f), mul(sub((float) datasetSize, 1.0f), f2));
        Double.valueOf(0.0d);
        float hPDataAxisStdX = getHPDataAxisStdX();
        float f3 = 0.0f;
        int i2 = 0;
        while (i2 < datasetSize) {
            int i3;
            BarData barData = (BarData) this.mDataSet.get(i2);
            List dataSet = barData.getDataSet();
            if (dataSet == null) {
                i3 = i;
            } else {
                List dataColor = barData.getDataColor();
                this.mFlatBar.getBarPaint().setColor(barData.getColor().intValue());
                int size = dataSet.size();
                int i4 = 0;
                while (i4 < size) {
                    float textWidth;
                    float sub;
                    float f4;
                    Double d = (Double) dataSet.get(i4);
                    setBarDataColor(this.mFlatBar.getBarPaint(), dataColor, i4, barData.getColor().intValue());
                    float sub2 = sub(bottom, mul((float) (i4 + 1), verticalYSteps));
                    if (BarCenterStyle.SPACE == this.mBarCenterStyle) {
                        sub2 = add(add(sub2, div(verticalYSteps, 2.0f)), add / 2.0f);
                    } else {
                        sub2 = add(sub2, add / 2.0f);
                    }
                    float sub3 = sub(sub2, add(f, f2) * ((float) i));
                    float hPValPosition = getHPValPosition(d.doubleValue());
                    String formatterItemLabel = getFormatterItemLabel(d.doubleValue());
                    if (this.mFlatBar.getItemLabelsVisible()) {
                        textWidth = DrawHelper.getInstance().getTextWidth(this.mFlatBar.getItemLabelPaint(), formatterItemLabel);
                    } else {
                        textWidth = f3;
                    }
                    float f5;
                    if (!this.dataAxis.getAxisStdStatus()) {
                        sub = sub(sub3, f);
                        f4 = hPValPosition;
                        f3 = left;
                        f5 = sub3;
                        sub3 = hPValPosition;
                        hPValPosition = f5;
                    } else if (d.doubleValue() < ((double) this.dataAxis.getAxisStd())) {
                        sub = sub(sub3, f);
                        f4 = hPValPosition - textWidth;
                        f3 = hPValPosition;
                        hPValPosition = sub3;
                        sub3 = hPDataAxisStdX;
                    } else {
                        sub = sub(sub3, f);
                        f4 = hPValPosition;
                        f3 = hPDataAxisStdX;
                        f5 = sub3;
                        sub3 = hPValPosition;
                        hPValPosition = f5;
                    }
                    this.mFlatBar.renderBar(f3, hPValPosition, sub3, sub, canvas);
                    saveBarRectFRecord(i2, i4, f3 + this.mMoveX, sub + this.mMoveY, sub3 + this.mMoveX, hPValPosition + this.mMoveY);
                    float sub4 = sub(hPValPosition, f / 2.0f);
                    drawAnchor(this.mAnchorSet, i2, i4, canvas, f4, sub4, 0.0f);
                    if (this.mEqualAxisMin || Double.compare((double) this.dataAxis.getAxisMin(), d.doubleValue()) != 0) {
                        if (this.mFlatBar.getItemLabelStyle() == ItemLabelStyle.BOTTOM) {
                            this.mFlatBar.renderBarItemLabel(formatterItemLabel, f3, sub4, canvas);
                        } else {
                            this.mFlatBar.renderBarItemLabel(formatterItemLabel, f4, sub4, canvas);
                        }
                    }
                    drawFocusRect(canvas, i2, i4, f3, sub, sub3, hPValPosition);
                    i4++;
                    f3 = textWidth;
                }
                i3 = i + 1;
            }
            i2++;
            i = i3;
        }
        if (this.mCustomLine != null) {
            this.mCustomLine.setHorizontalPlot(this.dataAxis, this.plotArea, getAxisScreenWidth());
            this.mCustomLine.renderHorizontalCustomlinesDataAxis(canvas);
        }
        return true;
    }

    public float getHPValPosition(double d) {
        return add(this.plotArea.getLeft(), mul(getPlotScreenWidth(), div((float) MathHelper.getInstance().sub(d, (double) this.dataAxis.getAxisMin()), this.dataAxis.getAxisRange())));
    }

    public float getHPDataAxisStdX() {
        if (this.dataAxis.getAxisStdStatus()) {
            return getHPValPosition((double) this.dataAxis.getAxisStd());
        }
        return this.plotArea.getLeft();
    }

    public float getVPDataAxisStdY() {
        if (this.dataAxis.getAxisStdStatus()) {
            return getVPValPosition((double) this.dataAxis.getAxisStd());
        }
        return this.plotArea.getBottom();
    }

    public float getVPValPosition(double d) {
        return sub(this.plotArea.getBottom(), mul(getPlotScreenHeight(), div((float) MathHelper.getInstance().sub(d, (double) this.dataAxis.getAxisMin()), this.dataAxis.getAxisRange())));
    }

    protected float getAxisXPos(AxisLocation axisLocation) {
        if (Direction.HORIZONTAL == this.mDirection && this.dataAxis.getAxisStdStatus() && this.categoryAxis.getAxisBuildStdStatus()) {
            return getHPDataAxisStdX();
        }
        return super.getAxisXPos(axisLocation);
    }

    protected float getAxisYPos(AxisLocation axisLocation) {
        if (Direction.VERTICAL == this.mDirection && this.dataAxis.getAxisStdStatus() && this.categoryAxis.getAxisBuildStdStatus()) {
            return getVPDataAxisStdY();
        }
        return super.getAxisYPos(axisLocation);
    }

    protected void drawClipCategoryAxisLine(Canvas canvas) {
        if (Direction.VERTICAL == this.mDirection && this.dataAxis.getAxisStdStatus() && this.categoryAxis.getAxisBuildStdStatus()) {
            float vPDataAxisStdY = getVPDataAxisStdY();
            this.categoryAxis.renderAxis(canvas, this.plotArea.getLeft(), vPDataAxisStdY, this.plotArea.getRight(), vPDataAxisStdY);
        } else if (Direction.HORIZONTAL == this.mDirection && this.dataAxis.getAxisStdStatus() && this.categoryAxis.getAxisBuildStdStatus()) {
            float hPDataAxisStdX = getHPDataAxisStdX();
            this.categoryAxis.renderAxis(canvas, hPDataAxisStdX, this.plotArea.getTop(), hPDataAxisStdX, this.plotArea.getBottom());
        } else {
            super.drawClipCategoryAxisLine(canvas);
        }
    }

    protected boolean renderVerticalBar(Canvas canvas) {
        if (this.mDataSet == null || this.mDataSet.size() == 0) {
            return false;
        }
        if (this.categoryAxis.getDataSet() == null) {
            return false;
        }
        float paintFontHeight;
        float verticalXSteps = getVerticalXSteps(getCateTickCount());
        float vPDataAxisStdY = getVPDataAxisStdY();
        if (this.mFlatBar.getItemLabelsVisible()) {
            paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(this.mFlatBar.getItemLabelPaint());
        } else {
            paintFontHeight = 0.0f;
        }
        int datasetSize = getDatasetSize(this.mDataSet);
        if (datasetSize <= 0) {
            return false;
        }
        int i = 0;
        float[] barWidthAndMargin = this.mFlatBar.getBarWidthAndMargin(verticalXSteps, datasetSize);
        if (barWidthAndMargin == null || barWidthAndMargin.length != 2) {
            Log.w(TAG, "分隔间距计算失败.");
            return false;
        }
        float f = barWidthAndMargin[0];
        float f2 = barWidthAndMargin[1];
        float add = add(mul((float) datasetSize, f), mul(sub((float) datasetSize, 1.0f), f2));
        int size = this.mDataSet.size();
        int i2 = 0;
        while (i2 < size) {
            BarData barData = (BarData) this.mDataSet.get(i2);
            List dataSet = barData.getDataSet();
            if (dataSet == null) {
                datasetSize = i;
            } else {
                List dataColor = barData.getDataColor();
                this.mFlatBar.getBarPaint().setColor(barData.getColor().intValue());
                int size2 = dataSet.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    float add2;
                    float bottom;
                    float f3;
                    float f4;
                    Double d = (Double) dataSet.get(i3);
                    setBarDataColor(this.mFlatBar.getBarPaint(), dataColor, i3, barData.getColor().intValue());
                    float add3 = add(this.plotArea.getLeft(), mul((float) (i3 + 1), verticalXSteps));
                    if (BarCenterStyle.SPACE == this.mBarCenterStyle) {
                        add3 = sub(sub(add3, div(verticalXSteps, 2.0f)), add / 2.0f);
                    } else {
                        add3 = sub(add3, add / 2.0f);
                    }
                    float add4 = add(add3, add(f, f2) * ((float) i));
                    float vPValPosition = getVPValPosition(d.doubleValue());
                    if (!this.dataAxis.getAxisStdStatus()) {
                        add2 = add(add4, f);
                        bottom = this.plotArea.getBottom();
                        f3 = vPValPosition;
                        f4 = vPValPosition;
                        vPValPosition = add4;
                    } else if (d.doubleValue() < ((double) this.dataAxis.getAxisStd())) {
                        add2 = add(add4, f);
                        f3 = vPValPosition + paintFontHeight;
                        f4 = vPDataAxisStdY;
                        bottom = vPValPosition;
                        vPValPosition = add4;
                    } else {
                        add2 = add(add4, f);
                        f3 = vPValPosition;
                        f4 = vPValPosition;
                        bottom = vPDataAxisStdY;
                        vPValPosition = add4;
                    }
                    this.mFlatBar.renderBar(vPValPosition, bottom, add2, f4, canvas);
                    saveBarRectFRecord(i2, i3, vPValPosition + this.mMoveX, f4 + this.mMoveY, add2 + this.mMoveX, bottom + this.mMoveY);
                    drawFocusRect(canvas, i2, i3, vPValPosition, f4, add2, bottom);
                    float add5 = add(add4, f / 2.0f);
                    drawAnchor(this.mAnchorSet, i2, i3, canvas, add5, f3, 0.0f);
                    if (this.mEqualAxisMin || Double.compare((double) this.dataAxis.getAxisMin(), d.doubleValue()) != 0) {
                        if (this.mFlatBar.getItemLabelStyle() == ItemLabelStyle.BOTTOM) {
                            this.mFlatBar.renderBarItemLabel(getFormatterItemLabel(d.doubleValue()), add5, bottom, canvas);
                        } else {
                            this.mFlatBar.renderBarItemLabel(getFormatterItemLabel(d.doubleValue()), add5, f3, canvas);
                        }
                    }
                }
                datasetSize = i + 1;
            }
            i2++;
            i = datasetSize;
        }
        if (this.mCustomLine != null) {
            this.mCustomLine.setVerticalPlot(this.dataAxis, this.plotArea, getAxisScreenHeight());
            this.mCustomLine.renderVerticalCustomlinesDataAxis(canvas);
        }
        return true;
    }

    protected void drawClipPlot(Canvas canvas) {
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mDirection.ordinal()]) {
            case 1:
                renderHorizontalBar(canvas);
                return;
            case 2:
                renderVerticalBar(canvas);
                return;
            default:
                return;
        }
    }

    protected void drawClipLegend(Canvas canvas) {
        this.plotLegend.renderBarKey(canvas, this.mDataSet);
    }

    protected int getDatasetSize(List<BarData> list) {
        if (list == null) {
            return 0;
        }
        int size = list.size();
        int i = 0;
        int i2 = size;
        while (i < size) {
            int i3;
            List dataSet = ((BarData) list.get(i)).getDataSet();
            if (dataSet.size() == 1 && Double.compare(((Double) dataSet.get(0)).doubleValue(), (double) this.dataAxis.getAxisMin()) == 0) {
                i3 = i2 - 1;
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return i2;
    }

    protected void setBarDataColor(Paint paint, List<Integer> list, int i, int i2) {
        if (list == null) {
            return;
        }
        if (list.size() > i) {
            paint.setColor(((Integer) list.get(i)).intValue());
        } else {
            paint.setColor(i2);
        }
    }

    public BarPosition getPositionRecord(float f, float f2) {
        return getBarRecord(f, f2);
    }
}
