package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.LegendType;
import org.xclcharts.renderer.XEnum.PanMode;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.plot.PlotAttrInfo;
import org.xclcharts.renderer.plot.PlotAttrInfoRender;

public class ArcLineChart extends XChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode = null;
    private static final int OFFSET_ANGLE = 270;
    private static final String TAG = "ArcLineChart";
    private float mBarInnerMargin = 0.5f;
    private List<ArcLineData> mDataset;
    private float mInnerRaius = 0.6f;
    private float mLabelOffsetX = 0.0f;
    private Paint mPaintFill = null;
    private Paint mPaintLabel = null;
    private Paint mPaintLine = null;
    private float mRadius = 0.0f;
    private PlotAttrInfoRender plotAttrInfoRender = null;

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

    public ArcLineChart() {
        int i = ViewCompat.MEASURED_STATE_MASK;
        if (this.plotArea != null) {
            i = this.plotArea.getBackgroundPaint().getColor();
        }
        if (this.mPaintFill == null) {
            this.mPaintFill = new Paint();
            this.mPaintFill.setColor(i);
            this.mPaintFill.setAntiAlias(true);
        }
        if (this.plotAttrInfoRender == null) {
            this.plotAttrInfoRender = new PlotAttrInfoRender();
        }
        if (this.plotLegend != null) {
            this.plotLegend.show();
            this.plotLegend.setType(LegendType.ROW);
            this.plotLegend.setHorizontalAlign(HorizontalAlign.CENTER);
            this.plotLegend.setVerticalAlign(VerticalAlign.BOTTOM);
            this.plotLegend.showBox();
            this.plotLegend.hideBackground();
        }
    }

    public ChartType getType() {
        return ChartType.ARCLINE;
    }

    protected void calcPlotRange() {
        super.calcPlotRange();
        this.mRadius = Math.min(div(this.plotArea.getWidth(), 2.0f), div(this.plotArea.getHeight(), 2.0f));
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setLabelOffsetX(float f) {
        this.mLabelOffsetX = f;
    }

    public Paint getLabelPaint() {
        if (this.mPaintLabel == null) {
            this.mPaintLabel = new Paint();
            this.mPaintLabel.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintLabel.setTextSize(18.0f);
            this.mPaintLabel.setAntiAlias(true);
            this.mPaintLabel.setTextAlign(Align.RIGHT);
        }
        return this.mPaintLabel;
    }

    public Paint getLinePaint() {
        if (this.mPaintLine == null) {
            this.mPaintLine = new Paint();
            this.mPaintLine.setColor(Color.rgb(Opcodes.GETFIELD, 205, 230));
            this.mPaintLine.setAntiAlias(true);
            this.mPaintLine.setStrokeWidth(3.0f);
            this.mPaintLine.setStyle(Style.FILL);
        }
        return this.mPaintLine;
    }

    public Paint getInnerPaint() {
        return this.mPaintFill;
    }

    public void setDataSource(List<ArcLineData> list) {
        this.mDataset = list;
    }

    public List<ArcLineData> getDataSource() {
        return this.mDataset;
    }

    public PlotAttrInfo getPlotAttrInfo() {
        return this.plotAttrInfoRender;
    }

    public boolean setBarInnerMargin(float f) {
        if (Double.compare((double) f, 0.0d) == -1) {
            Log.e(TAG, "此比例不能为负数噢!");
            return false;
        } else if (Double.compare((double) f, 0.9d) == 1 || Double.compare((double) f, 0.9d) == 0) {
            Log.e(TAG, "此比例不能大于等于0.9,要给柱形留下点显示空间!");
            return false;
        } else {
            this.mBarInnerMargin = f;
            return true;
        }
    }

    public float getInnerMargin() {
        return this.mBarInnerMargin;
    }

    public void setInnerRaius(float f) {
        this.mInnerRaius = f;
    }

    protected boolean validateAngle(float f) {
        if (Float.compare(f, 0.0f) != 0 && Float.compare(f, 0.0f) != -1) {
            return true;
        }
        Log.e(TAG, "扇区圆心角小于等于0度. 当前圆心角为:" + Float.toString(f));
        return false;
    }

    private boolean renderCap(Canvas canvas, float f, PointF[] pointFArr, int[] iArr) {
        getLinePaint().setColor(SupportMenu.CATEGORY_MASK);
        for (int i = 0; i < pointFArr.length; i++) {
            getLinePaint().setColor(iArr[i]);
            canvas.drawCircle(pointFArr[i].x, pointFArr[i].y, f, getLinePaint());
        }
        return true;
    }

    private boolean renderLabels(Canvas canvas, float f, PointF[] pointFArr) {
        float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getLabelPaint()) / 3.0f;
        int i = 0;
        for (ArcLineData arcLineData : this.mDataset) {
            if (validateAngle(arcLineData.getSliceAngle())) {
                getLabelPaint().setColor(arcLineData.getBarColor());
                canvas.drawText(arcLineData.getLabel(), pointFArr[i].x - this.mLabelOffsetX, pointFArr[i].y + paintFontHeight, getLabelPaint());
                i++;
            }
        }
        return true;
    }

    private boolean renderPlot(Canvas canvas) {
        if (this.mDataset == null) {
            Log.e(TAG, "数据源为空.");
            return false;
        }
        float centerX = this.plotArea.getCenterX();
        float centerY = this.plotArea.getCenterY();
        float radius = getRadius();
        try {
            int size = this.mDataset.size();
            float div = div(sub(radius, mul(radius, this.mInnerRaius)), (float) size);
            float sub = sub(div, mul(div, this.mBarInnerMargin));
            PointF[] pointFArr = new PointF[size];
            PointF[] pointFArr2 = new PointF[size];
            int[] iArr = new int[size];
            canvas.drawCircle(centerX, centerY, radius, this.mPaintFill);
            int i = 0;
            for (ArcLineData arcLineData : this.mDataset) {
                float sliceAngle = arcLineData.getSliceAngle();
                if (validateAngle(sliceAngle)) {
                    getLinePaint().setColor(arcLineData.getBarColor());
                    DrawHelper.getInstance().drawPercent(canvas, getLinePaint(), centerX, centerY, radius, 270.0f, sliceAngle, true);
                    PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, radius - (sub / 2.0f), add(270.0f, sliceAngle));
                    pointFArr2[i] = new PointF(calcArcEndPointXY.x, calcArcEndPointXY.y);
                    iArr[i] = arcLineData.getBarColor();
                    calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, radius - (sub / 2.0f), add(270.0f, 0.0f));
                    pointFArr[i] = new PointF(calcArcEndPointXY.x, calcArcEndPointXY.y);
                    canvas.drawCircle(centerX, centerY, radius - sub, this.mPaintFill);
                    radius = sub(radius, div);
                    i++;
                }
            }
            renderCap(canvas, 0.8f * sub, pointFArr2, iArr);
            renderLabels(canvas, radius, pointFArr);
            this.plotLegend.renderRoundBarKey(canvas, this.mDataset);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "error:" + e.toString());
            return false;
        }
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            calcPlotRange();
            if (!renderPlot(canvas)) {
                return false;
            }
            this.plotAttrInfoRender.renderAttrInfo(canvas, this.plotArea.getCenterX(), this.plotArea.getCenterY(), getRadius());
            renderTitle(canvas);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean render(Canvas canvas) throws Exception {
        if (canvas == null) {
            return false;
        }
        try {
            if (getPanModeStatus()) {
                canvas.save();
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode()[getPlotPanMode().ordinal()]) {
                    case 1:
                        canvas.translate(this.mTranslateXY[0], 0.0f);
                        break;
                    case 2:
                        canvas.translate(0.0f, this.mTranslateXY[1]);
                        break;
                    default:
                        canvas.translate(this.mTranslateXY[0], this.mTranslateXY[1]);
                        break;
                }
                super.render(canvas);
                canvas.restore();
            } else {
                super.render(canvas);
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
