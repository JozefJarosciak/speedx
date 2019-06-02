package org.xclcharts.renderer;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import java.util.ArrayList;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.renderer.XEnum.AxisLocation;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.Direction;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.LegendType;
import org.xclcharts.renderer.XEnum.ODD_EVEN;
import org.xclcharts.renderer.XEnum.PanMode;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.axis.CategoryAxis;
import org.xclcharts.renderer.axis.CategoryAxisRender;
import org.xclcharts.renderer.axis.DataAxis;
import org.xclcharts.renderer.axis.DataAxisRender;
import org.xclcharts.renderer.info.PlotAxisTick;
import org.xclcharts.renderer.plot.AxisTitle;
import org.xclcharts.renderer.plot.AxisTitleRender;

public class AxesChart extends EventChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode;
    private AxisTitleRender axisTitle = null;
    protected CategoryAxisRender categoryAxis = null;
    protected DataAxisRender dataAxis = null;
    private boolean mAxesClosed = false;
    private AxisLocation mCategoryAxisLocation = AxisLocation.BOTTOM;
    private ClipExt mClipExt = null;
    private AxisLocation mDataAxisLocation = AxisLocation.LEFT;
    protected Direction mDirection = Direction.VERTICAL;
    private IFormatterDoubleCallBack mItemLabelFormatter;
    protected ArrayList<PlotAxisTick> mLstCateTick = null;
    protected ArrayList<PlotAxisTick> mLstDataTick = null;
    protected float mMoveX = 0.0f;
    protected float mMoveY = 0.0f;
    private float mxMargin = -25.0f;
    private float myMargin = -10.0f;

    public class ClipExt {
        private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$ChartType;
        private float clipExtBottom = 0.5f;
        private float clipExtLeft = 0.5f;
        private float clipExtRight = 0.5f;
        private float clipExtTop = 0.5f;
        private float mClipExtBottom = -1.0f;
        private float mClipExtLeft = -1.0f;
        private float mClipExtRight = -1.0f;
        private float mClipExtTop = -1.0f;

        static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$ChartType() {
            int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$ChartType;
            if (iArr == null) {
                iArr = new int[ChartType.values().length];
                try {
                    iArr[ChartType.ARCLINE.ordinal()] = 15;
                } catch (NoSuchFieldError e) {
                }
                try {
                    iArr[ChartType.AREA.ordinal()] = 10;
                } catch (NoSuchFieldError e2) {
                }
                try {
                    iArr[ChartType.BAR.ordinal()] = 2;
                } catch (NoSuchFieldError e3) {
                }
                try {
                    iArr[ChartType.BAR3D.ordinal()] = 3;
                } catch (NoSuchFieldError e4) {
                }
                try {
                    iArr[ChartType.BUBBLE.ordinal()] = 18;
                } catch (NoSuchFieldError e5) {
                }
                try {
                    iArr[ChartType.CIRCLE.ordinal()] = 16;
                } catch (NoSuchFieldError e6) {
                }
                try {
                    iArr[ChartType.DIAL.ordinal()] = 13;
                } catch (NoSuchFieldError e7) {
                }
                try {
                    iArr[ChartType.DOUNT.ordinal()] = 7;
                } catch (NoSuchFieldError e8) {
                }
                try {
                    iArr[ChartType.FUNNEL.ordinal()] = 20;
                } catch (NoSuchFieldError e9) {
                }
                try {
                    iArr[ChartType.GAUGE.ordinal()] = 19;
                } catch (NoSuchFieldError e10) {
                }
                try {
                    iArr[ChartType.LINE.ordinal()] = 8;
                } catch (NoSuchFieldError e11) {
                }
                try {
                    iArr[ChartType.NONE.ordinal()] = 1;
                } catch (NoSuchFieldError e12) {
                }
                try {
                    iArr[ChartType.PIE.ordinal()] = 5;
                } catch (NoSuchFieldError e13) {
                }
                try {
                    iArr[ChartType.PIE3D.ordinal()] = 6;
                } catch (NoSuchFieldError e14) {
                }
                try {
                    iArr[ChartType.RADAR.ordinal()] = 12;
                } catch (NoSuchFieldError e15) {
                }
                try {
                    iArr[ChartType.RANGEBAR.ordinal()] = 14;
                } catch (NoSuchFieldError e16) {
                }
                try {
                    iArr[ChartType.ROSE.ordinal()] = 11;
                } catch (NoSuchFieldError e17) {
                }
                try {
                    iArr[ChartType.SCATTER.ordinal()] = 17;
                } catch (NoSuchFieldError e18) {
                }
                try {
                    iArr[ChartType.SPLINE.ordinal()] = 9;
                } catch (NoSuchFieldError e19) {
                }
                try {
                    iArr[ChartType.STACKBAR.ordinal()] = 4;
                } catch (NoSuchFieldError e20) {
                }
                $SWITCH_TABLE$org$xclcharts$renderer$XEnum$ChartType = iArr;
            }
            return iArr;
        }

        public void setExtLeft(float f) {
            this.mClipExtLeft = f;
        }

        public void setExtTop(float f) {
            this.mClipExtTop = f;
        }

        public void setExtRight(float f) {
            this.mClipExtRight = f;
        }

        public void setExtBottom(float f) {
            this.mClipExtBottom = f;
        }

        public void calc(ChartType chartType) {
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$ChartType()[chartType.ordinal()]) {
                case 8:
                case 9:
                case 10:
                    if (Float.compare(this.mClipExtLeft, -1.0f) == 0) {
                        this.clipExtLeft = 10.0f;
                    } else {
                        this.clipExtLeft = this.mClipExtLeft;
                    }
                    if (Float.compare(this.mClipExtTop, -1.0f) == 0) {
                        this.clipExtTop = 0.5f;
                    } else {
                        this.clipExtTop = this.mClipExtTop;
                    }
                    if (Float.compare(this.mClipExtRight, -1.0f) == 0) {
                        this.clipExtRight = 10.0f;
                    } else {
                        this.clipExtRight = this.mClipExtRight;
                    }
                    if (Float.compare(this.mClipExtBottom, -1.0f) == 0) {
                        this.clipExtBottom = 10.0f;
                        return;
                    } else {
                        this.clipExtBottom = this.mClipExtBottom;
                        return;
                    }
                default:
                    return;
            }
        }

        public float getExtLeft() {
            return this.clipExtLeft;
        }

        public float getExtTop() {
            return this.clipExtTop;
        }

        public float getExtRight() {
            return this.clipExtRight;
        }

        public float getExtBottom() {
            return this.clipExtBottom;
        }
    }

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

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode;
        if (iArr == null) {
            iArr = new int[PanMode.values().length];
            try {
                iArr[PanMode.FREE.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PanMode.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PanMode.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode = iArr;
        }
        return iArr;
    }

    public AxesChart() {
        if (this.mLstDataTick == null) {
            this.mLstDataTick = new ArrayList();
        }
        if (this.mLstCateTick == null) {
            this.mLstCateTick = new ArrayList();
        }
        initChart();
    }

    private void initChart() {
        if (this.dataAxis == null) {
            initDataAxis();
        }
        if (this.categoryAxis == null) {
            initCategoryAxis();
        }
        if (this.plotLegend != null) {
            this.plotLegend.show();
            this.plotLegend.setType(LegendType.ROW);
            this.plotLegend.setHorizontalAlign(HorizontalAlign.LEFT);
            this.plotLegend.setVerticalAlign(VerticalAlign.TOP);
            this.plotLegend.hideBox();
        }
    }

    public DataAxis getDataAxis() {
        initDataAxis();
        return this.dataAxis;
    }

    public CategoryAxis getCategoryAxis() {
        initCategoryAxis();
        return this.categoryAxis;
    }

    private void initCategoryAxis() {
        if (this.categoryAxis == null) {
            this.categoryAxis = new CategoryAxisRender();
        }
    }

    public void initDataAxis() {
        if (this.dataAxis == null) {
            this.dataAxis = new DataAxisRender();
        }
    }

    protected void drawCategoryAxisLabels(Canvas canvas, ArrayList<PlotAxisTick> arrayList) {
        if (arrayList != null) {
            boolean z = true;
            for (int i = 0; i < arrayList.size(); i++) {
                PlotAxisTick plotAxisTick = (PlotAxisTick) arrayList.get(i);
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[this.mCategoryAxisLocation.ordinal()]) {
                    case 1:
                    case 2:
                    case 5:
                        boolean z2;
                        ODD_EVEN odd_even = i % 2 != 0 ? ODD_EVEN.ODD : ODD_EVEN.EVEN;
                        if (plotAxisTick.isShowTickMarks() && isDrawXAxisTickMarks(plotAxisTick.X, this.mMoveX)) {
                            z2 = z;
                        } else {
                            z2 = false;
                        }
                        this.categoryAxis.renderAxisVerticalTick(canvas, plotAxisTick.X, plotAxisTick.Y, plotAxisTick.Label, plotAxisTick.labelX, plotAxisTick.labelY, z2, odd_even);
                        break;
                    case 3:
                    case 4:
                    case 6:
                        boolean z3;
                        if (plotAxisTick.isShowTickMarks() && isDrawYAxisTickMarks(plotAxisTick.Y, this.mMoveY)) {
                            z3 = z;
                        } else {
                            z3 = false;
                        }
                        this.categoryAxis.renderAxisHorizontalTick(getLeft(), getPlotArea().getLeft(), canvas, plotAxisTick.X, plotAxisTick.Y, plotAxisTick.Label, plotAxisTick.labelX, plotAxisTick.labelY, z3);
                        break;
                    default:
                        break;
                }
                z = true;
            }
        }
    }

    protected void drawDataAxisLabels(Canvas canvas, ArrayList<PlotAxisTick> arrayList) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                PlotAxisTick plotAxisTick = (PlotAxisTick) arrayList.get(i);
                ODD_EVEN odd_even = i % 2 != 0 ? ODD_EVEN.ODD : ODD_EVEN.EVEN;
                this.dataAxis.setAxisTickCurrentID(plotAxisTick.ID);
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[this.mDataAxisLocation.ordinal()]) {
                    case 1:
                    case 2:
                    case 5:
                        this.dataAxis.renderAxisVerticalTick(canvas, plotAxisTick.X, plotAxisTick.Y, plotAxisTick.Label, isDrawXAxisTickMarks(plotAxisTick.X, this.mMoveX), odd_even);
                        break;
                    case 3:
                    case 4:
                    case 6:
                        this.dataAxis.renderAxisHorizontalTick(getLeft(), getPlotArea().getLeft(), canvas, plotAxisTick.X, plotAxisTick.Y, plotAxisTick.Label, isDrawYAxisTickMarks(plotAxisTick.Y, this.mMoveY));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public AxisTitle getAxisTitle() {
        if (this.axisTitle == null) {
            this.axisTitle = new AxisTitleRender();
        }
        return this.axisTitle;
    }

    protected float getAxisScreenWidth() {
        if (this.plotArea == null) {
            return 0.0f;
        }
        return Math.abs(this.plotArea.getRight() - this.plotArea.getLeft());
    }

    protected float getPlotScreenWidth() {
        if (this.plotArea == null) {
            return 0.0f;
        }
        return Math.abs(this.plotArea.getPlotRight() - this.plotArea.getPlotLeft());
    }

    protected float getPlotScreenHeight() {
        if (this.plotArea == null) {
            return 0.0f;
        }
        return Math.abs(this.plotArea.getPlotBottom() - this.plotArea.getPlotTop());
    }

    protected float getAxisScreenHeight() {
        if (this.plotArea == null) {
            return 0.0f;
        }
        return Math.abs(this.plotArea.getBottom() - this.plotArea.getTop());
    }

    protected float getVerticalYSteps(int i) {
        return div(getPlotScreenHeight(), (float) i);
    }

    public float getVerticalXSteps(int i) {
        return div(getPlotScreenWidth(), (float) i);
    }

    public void setItemLabelFormatter(IFormatterDoubleCallBack iFormatterDoubleCallBack) {
        this.mItemLabelFormatter = iFormatterDoubleCallBack;
    }

    protected String getFormatterItemLabel(double d) {
        String str = "";
        try {
            return this.mItemLabelFormatter.doubleFormatter(Double.valueOf(d));
        } catch (Exception e) {
            return Double.toString(d);
        }
    }

    protected boolean isDrawYAxisTickMarks(float f, float f2) {
        if (Float.compare(f, this.plotArea.getTop() - f2) == -1 || Float.compare(f, this.plotArea.getBottom() - f2) == 1) {
            return false;
        }
        return true;
    }

    protected boolean isDrawXAxisTickMarks(float f, float f2) {
        if (Float.compare(f + f2, this.plotArea.getLeft()) == -1 || Float.compare(f + f2, this.plotArea.getRight()) == 1) {
            return false;
        }
        return true;
    }

    protected void drawHorizontalGridLines(Canvas canvas, float f, float f2, int i, int i2, float f3, float f4) {
        if (i >= 0) {
            if (i > 0) {
                if (i % 2 != 0) {
                    this.plotGrid.renderOddRowsFill(canvas, f, add(f4, f3), f2, f4);
                } else {
                    this.plotGrid.renderEvenRowsFill(canvas, f, add(f4, f3), f2, f4);
                }
            }
            if (i >= 0 && i < i2) {
                this.plotGrid.setPrimaryTickLine(this.dataAxis.isPrimaryTick(i));
                this.plotGrid.renderGridLinesHorizontal(canvas, f, f4, f2, f4);
            }
        }
    }

    protected void drawVerticalGridLines(Canvas canvas, float f, float f2, int i, int i2, float f3, float f4) {
        if (this.plotGrid.isShowVerticalLines()) {
            this.plotGrid.renderGridLinesVertical(canvas, f4, f2, f4, f);
        }
    }

    public void setDataAxisLocation(AxisLocation axisLocation) {
        this.mDataAxisLocation = axisLocation;
    }

    public AxisLocation getDataAxisLocation() {
        return this.mDataAxisLocation;
    }

    public void setCategoryAxisLocation(AxisLocation axisLocation) {
        this.mCategoryAxisLocation = axisLocation;
    }

    public AxisLocation getCategoryAxisLocation() {
        return this.mCategoryAxisLocation;
    }

    protected float getAxisXPos(AxisLocation axisLocation) {
        if (AxisLocation.RIGHT == axisLocation) {
            return this.plotArea.getRight();
        }
        if (AxisLocation.LEFT == axisLocation) {
            return this.plotArea.getLeft();
        }
        if (AxisLocation.VERTICAL_CENTER == axisLocation) {
            return this.plotArea.getCenterX();
        }
        return 0.0f;
    }

    protected float getAxisYPos(AxisLocation axisLocation) {
        if (AxisLocation.TOP == axisLocation) {
            return this.plotArea.getTop();
        }
        if (AxisLocation.BOTTOM == axisLocation) {
            return this.plotArea.getBottom();
        }
        if (AxisLocation.HORIZONTAL_CENTER == axisLocation) {
            return this.plotArea.getCenterY();
        }
        return 0.0f;
    }

    protected void categoryAxisDefaultSetting() {
        if (this.mDataAxisLocation != null && this.categoryAxis != null && this.categoryAxis.isShow()) {
            if (this.mDirection != null) {
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mDirection.ordinal()]) {
                    case 1:
                        setCategoryAxisLocation(AxisLocation.LEFT);
                        break;
                    case 2:
                        setCategoryAxisLocation(AxisLocation.BOTTOM);
                        break;
                }
            }
            if (AxisLocation.LEFT == this.mDataAxisLocation) {
                this.categoryAxis.setHorizontalTickAlign(Align.CENTER);
            }
            this.categoryAxis.getAxisPaint().setStrokeWidth(2.0f);
            this.categoryAxis.getTickMarksPaint().setStrokeWidth(2.0f);
        }
    }

    protected void dataAxisDefaultSetting() {
        if (this.mDataAxisLocation != null && this.dataAxis != null && this.dataAxis.isShow()) {
            if (this.mDirection != null) {
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mDirection.ordinal()]) {
                    case 1:
                        setDataAxisLocation(AxisLocation.BOTTOM);
                        break;
                    case 2:
                        setDataAxisLocation(AxisLocation.LEFT);
                        break;
                }
            }
            if (AxisLocation.LEFT == this.mDataAxisLocation) {
                this.dataAxis.setHorizontalTickAlign(Align.LEFT);
            } else {
                this.dataAxis.setHorizontalTickAlign(Align.RIGHT);
                if (this.dataAxis.isShowAxisLabels()) {
                    this.dataAxis.getTickLabelPaint().setTextAlign(Align.LEFT);
                }
            }
            if (this.dataAxis.isShowAxisLine()) {
                this.dataAxis.getAxisPaint().setStrokeWidth(2.0f);
            }
            if (this.dataAxis.isShowTickMarks()) {
                this.dataAxis.getTickMarksPaint().setStrokeWidth(2.0f);
            }
        }
    }

    public void setAxesClosed(boolean z) {
        this.mAxesClosed = z;
    }

    public boolean getAxesClosedStatus() {
        return this.mAxesClosed;
    }

    protected void initMoveXY() {
        this.mMoveY = 0.0f;
        this.mMoveX = 0.0f;
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode()[getPlotPanMode().ordinal()]) {
            case 1:
                this.mMoveX = this.mTranslateXY[0];
                return;
            case 2:
                this.mMoveY = this.mTranslateXY[1];
                return;
            default:
                this.mMoveX = this.mTranslateXY[0];
                this.mMoveY = this.mTranslateXY[1];
                return;
        }
    }

    protected void drawClipCategoryAxisGridlines(Canvas canvas) {
    }

    protected void drawClipDataAxisGridlines(Canvas canvas) {
    }

    protected void drawClipPlot(Canvas canvas) {
    }

    protected void drawClipAxisClosed(Canvas canvas) {
        if (getAxesClosedStatus()) {
            float left = this.plotArea.getLeft();
            float top = this.plotArea.getTop();
            float right = this.plotArea.getRight();
            float bottom = this.plotArea.getBottom();
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[this.mDataAxisLocation.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    this.dataAxis.renderAxisLine(canvas, left, top, right, top);
                    this.dataAxis.renderAxisLine(canvas, left, bottom, right, bottom);
                    break;
                case 3:
                case 4:
                case 6:
                    this.dataAxis.renderAxisLine(canvas, left, top, left, bottom);
                    this.dataAxis.renderAxisLine(canvas, right, top, right, bottom);
                    break;
            }
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[this.mCategoryAxisLocation.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    this.categoryAxis.renderAxisLine(canvas, left, top, right, top);
                    this.categoryAxis.renderAxisLine(canvas, left, bottom, right, bottom);
                    return;
                case 3:
                case 4:
                case 6:
                    this.categoryAxis.renderAxisLine(canvas, left, bottom, left, top);
                    this.categoryAxis.renderAxisLine(canvas, right, bottom, right, top);
                    return;
                default:
                    return;
            }
        }
    }

    protected void drawClipDataAxisLine(Canvas canvas) {
        float left = this.plotArea.getLeft();
        float top = this.plotArea.getTop();
        float right = this.plotArea.getRight();
        float bottom = this.plotArea.getBottom();
        float f = ((right - left) / 2.0f) + left;
        float f2 = top + ((bottom - top) / 2.0f);
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[this.mDataAxisLocation.ordinal()]) {
            case 1:
                this.dataAxis.renderAxis(canvas, left, top, right, top);
                return;
            case 2:
                this.dataAxis.renderAxis(canvas, left, bottom, right, bottom);
                return;
            case 3:
                this.dataAxis.renderAxis(canvas, left, bottom, left, top);
                return;
            case 4:
                this.dataAxis.renderAxis(canvas, right, top, right, bottom);
                return;
            case 5:
                this.dataAxis.renderAxis(canvas, left, f2, right, f2);
                return;
            case 6:
                this.dataAxis.renderAxis(canvas, f, top, f, bottom);
                return;
            default:
                return;
        }
    }

    protected void drawClipCategoryAxisLine(Canvas canvas) {
        float left = this.plotArea.getLeft();
        float top = this.plotArea.getTop();
        float right = this.plotArea.getRight();
        float bottom = this.plotArea.getBottom();
        float f = ((right - left) / 2.0f) + left;
        float f2 = top + ((bottom - top) / 2.0f);
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[this.mCategoryAxisLocation.ordinal()]) {
            case 1:
                this.categoryAxis.renderAxis(canvas, left, top, right, top);
                return;
            case 2:
                this.categoryAxis.renderAxis(canvas, left, bottom, right, bottom);
                return;
            case 3:
                this.categoryAxis.renderAxis(canvas, left, bottom, left, top);
                return;
            case 4:
                this.categoryAxis.renderAxis(canvas, right, top, right, bottom);
                return;
            case 5:
                this.categoryAxis.renderAxis(canvas, left, f2, right, f2);
                return;
            case 6:
                this.categoryAxis.renderAxis(canvas, f, top, f, bottom);
                return;
            default:
                return;
        }
    }

    protected void drawClipAxisLine(Canvas canvas) {
        drawClipDataAxisLine(canvas);
        drawClipCategoryAxisLine(canvas);
    }

    protected void drawClipDataAxisTickMarks(Canvas canvas) {
        drawDataAxisLabels(canvas, this.mLstDataTick);
        this.mLstDataTick.clear();
    }

    protected void drawClipCategoryAxisTickMarks(Canvas canvas) {
        drawCategoryAxisLabels(canvas, this.mLstCateTick);
        this.mLstCateTick.clear();
    }

    protected void drawClipLegend(Canvas canvas) {
    }

    protected boolean drawFixedPlot(Canvas canvas) {
        this.mMoveY = 0.0f;
        this.mMoveX = 0.0f;
        drawClipDataAxisGridlines(canvas);
        drawClipCategoryAxisGridlines(canvas);
        drawClipPlot(canvas);
        drawClipAxisClosed(canvas);
        drawClipAxisLine(canvas);
        drawClipDataAxisTickMarks(canvas);
        drawClipCategoryAxisTickMarks(canvas);
        drawClipLegend(canvas);
        return true;
    }

    public void setXTickMarksOffsetMargin(float f) {
        this.mxMargin = f;
    }

    public void setYTickMarksOffsetMargin(float f) {
        this.myMargin = f;
    }

    protected float getClipYMargin() {
        return this.myMargin + ((float) getBorderWidth());
    }

    protected float getClipXMargin() {
        return this.mxMargin + ((float) getBorderWidth());
    }

    public ClipExt getClipExt() {
        if (this.mClipExt == null) {
            this.mClipExt = new ClipExt();
        }
        return this.mClipExt;
    }

    protected boolean drawClipVerticalPlot(Canvas canvas) {
        float strokeWidth;
        float f = this.mTranslateXY[0];
        float f2 = this.mTranslateXY[1];
        initMoveXY();
        float clipYMargin = getClipYMargin();
        float clipXMargin = getClipXMargin();
        drawClipAxisClosed(canvas);
        canvas.save();
        canvas.clipRect(getLeft(), getTop(), getRight(), getBottom());
        if (PanMode.VERTICAL == getPlotPanMode() || PanMode.FREE == getPlotPanMode()) {
            if (getPlotGrid().isShowVerticalLines()) {
                strokeWidth = getPlotGrid().getVerticalLinePaint().getStrokeWidth();
            } else {
                strokeWidth = 0.0f;
            }
            canvas.save();
            canvas.clipRect(this.plotArea.getLeft() - strokeWidth, this.plotArea.getTop() - strokeWidth, this.plotArea.getRight() + strokeWidth, this.plotArea.getBottom() + strokeWidth);
            canvas.translate(0.0f, f2);
            drawClipDataAxisGridlines(canvas);
            canvas.restore();
        } else {
            drawClipDataAxisGridlines(canvas);
            strokeWidth = 0.0f;
        }
        if (PanMode.HORIZONTAL == getPlotPanMode() || PanMode.FREE == getPlotPanMode()) {
            if (getPlotGrid().isShowHorizontalLines()) {
                strokeWidth = getPlotGrid().getHorizontalLinePaint().getStrokeWidth();
            }
            canvas.save();
            canvas.clipRect(this.plotArea.getLeft() - strokeWidth, this.plotArea.getTop() - strokeWidth, this.plotArea.getRight() + strokeWidth, strokeWidth + this.plotArea.getBottom());
            canvas.translate(f, 0.0f);
            drawClipCategoryAxisGridlines(canvas);
            canvas.restore();
        } else {
            drawClipCategoryAxisGridlines(canvas);
        }
        canvas.save();
        getClipExt().calc(getType());
        canvas.clipRect(this.plotArea.getLeft() - getClipExt().getExtLeft(), this.plotArea.getTop() - getClipExt().getExtTop(), this.plotArea.getRight() + getClipExt().getExtRight(), this.plotArea.getBottom() + getClipExt().getExtBottom());
        canvas.save();
        canvas.translate(this.mMoveX, this.mMoveY);
        drawClipPlot(canvas);
        canvas.restore();
        canvas.restore();
        canvas.restore();
        drawClipAxisLine(canvas);
        if (PanMode.VERTICAL == getPlotPanMode() || PanMode.FREE == getPlotPanMode()) {
            canvas.save();
            canvas.clipRect(getLeft(), getTop() + clipYMargin, getRight(), getBottom() - clipYMargin);
            canvas.translate(0.0f, f2);
            drawClipDataAxisTickMarks(canvas);
            canvas.restore();
        } else {
            drawClipDataAxisTickMarks(canvas);
        }
        if (PanMode.HORIZONTAL == getPlotPanMode() || PanMode.FREE == getPlotPanMode()) {
            canvas.save();
            canvas.clipRect(getLeft() + clipXMargin, getTop(), getRight() - clipXMargin, getBottom());
            canvas.translate(f, 0.0f);
            drawClipCategoryAxisTickMarks(canvas);
            canvas.restore();
        } else {
            drawClipCategoryAxisTickMarks(canvas);
        }
        drawClipLegend(canvas);
        return true;
    }

    protected boolean drawClipHorizontalPlot(Canvas canvas) {
        float strokeWidth;
        float f = this.mTranslateXY[0];
        float f2 = this.mTranslateXY[1];
        initMoveXY();
        float clipYMargin = getClipYMargin();
        float clipXMargin = getClipXMargin();
        drawClipAxisClosed(canvas);
        canvas.save();
        canvas.clipRect(getLeft(), getTop(), getRight(), getBottom());
        if (PanMode.VERTICAL == getPlotPanMode() || PanMode.FREE == getPlotPanMode()) {
            if (getPlotGrid().isShowVerticalLines()) {
                strokeWidth = getPlotGrid().getVerticalLinePaint().getStrokeWidth();
            } else {
                strokeWidth = 0.0f;
            }
            canvas.save();
            canvas.clipRect(this.plotArea.getLeft() - strokeWidth, this.plotArea.getTop() - strokeWidth, this.plotArea.getRight() + strokeWidth, this.plotArea.getBottom() + strokeWidth);
            canvas.translate(0.0f, f2);
            drawClipCategoryAxisGridlines(canvas);
            canvas.restore();
        } else {
            drawClipCategoryAxisGridlines(canvas);
            strokeWidth = 0.0f;
        }
        if (PanMode.HORIZONTAL == getPlotPanMode() || PanMode.FREE == getPlotPanMode()) {
            if (getPlotGrid().isShowHorizontalLines()) {
                strokeWidth = getPlotGrid().getHorizontalLinePaint().getStrokeWidth();
            }
            canvas.save();
            canvas.clipRect(this.plotArea.getLeft() - strokeWidth, this.plotArea.getTop() - strokeWidth, this.plotArea.getRight() + strokeWidth, strokeWidth + this.plotArea.getBottom());
            canvas.translate(f, 0.0f);
            drawClipDataAxisGridlines(canvas);
            canvas.restore();
        } else {
            drawClipDataAxisGridlines(canvas);
        }
        canvas.save();
        getClipExt().calc(getType());
        canvas.clipRect(this.plotArea.getLeft() - getClipExt().getExtLeft(), this.plotArea.getTop() - getClipExt().getExtTop(), this.plotArea.getRight() + getClipExt().getExtRight(), this.plotArea.getBottom() + getClipExt().getExtBottom());
        canvas.save();
        canvas.translate(this.mMoveX, this.mMoveY);
        drawClipPlot(canvas);
        canvas.restore();
        canvas.restore();
        canvas.restore();
        drawClipAxisLine(canvas);
        if (PanMode.HORIZONTAL == getPlotPanMode() || PanMode.FREE == getPlotPanMode()) {
            canvas.save();
            canvas.clipRect(getLeft() + clipXMargin, getTop(), getRight() - clipXMargin, getBottom());
            canvas.translate(f, 0.0f);
            drawClipDataAxisTickMarks(canvas);
            canvas.restore();
        } else {
            drawClipDataAxisTickMarks(canvas);
        }
        if (PanMode.VERTICAL == getPlotPanMode() || PanMode.FREE == getPlotPanMode()) {
            canvas.save();
            canvas.clipRect(getLeft(), getTop() + clipYMargin, getRight(), getBottom() - clipYMargin);
            canvas.translate(0.0f, f2);
            drawClipCategoryAxisTickMarks(canvas);
            canvas.restore();
        } else {
            drawClipCategoryAxisTickMarks(canvas);
        }
        drawClipLegend(canvas);
        return true;
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            boolean z = true;
            calcPlotRange();
            this.plotArea.render(canvas);
            if (getPanModeStatus()) {
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mDirection.ordinal()]) {
                    case 1:
                        z = drawClipHorizontalPlot(canvas);
                        break;
                    case 2:
                        z = drawClipVerticalPlot(canvas);
                        break;
                }
            }
            z = drawFixedPlot(canvas);
            if (z) {
                renderTitle(canvas);
                if (this.axisTitle != null) {
                    this.axisTitle.setRange(this);
                    this.axisTitle.render(canvas);
                }
                renderFocusShape(canvas);
                renderToolTip(canvas);
            }
            return z;
        } catch (Exception e) {
            throw e;
        }
    }
}
