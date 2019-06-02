package org.xclcharts.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import org.xclcharts.chart.PieData;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.LabelBoxStyle;
import org.xclcharts.renderer.XEnum.LegendType;
import org.xclcharts.renderer.XEnum.PanMode;
import org.xclcharts.renderer.XEnum.SliceLabelStyle;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.info.PlotArcLabelInfo;
import org.xclcharts.renderer.plot.LabelBrokenLine;
import org.xclcharts.renderer.plot.LabelBrokenLineRender;
import org.xclcharts.renderer.plot.PlotLabel;
import org.xclcharts.renderer.plot.PlotLabelRender;

public class CirChart extends EventChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$SliceLabelStyle = null;
    private static final String TAG = "CirChart";
    protected float mInitOffsetAngle = 0.0f;
    private boolean mIsLabelLineSyncColor = false;
    private boolean mIsLabelPointSyncColor = false;
    private boolean mIsLabelSyncColor = false;
    private LabelBrokenLineRender mLabelLine = null;
    private SliceLabelStyle mLabelStyle = SliceLabelStyle.INSIDE;
    protected float mOffsetAngle = 0.0f;
    private Paint mPaintLabel = null;
    private PlotLabelRender mPlotLabel = null;
    private float mRadius = 0.0f;

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

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$SliceLabelStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$SliceLabelStyle;
        if (iArr == null) {
            iArr = new int[SliceLabelStyle.values().length];
            try {
                iArr[SliceLabelStyle.BROKENLINE.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SliceLabelStyle.HIDE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SliceLabelStyle.INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[SliceLabelStyle.OUTSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$SliceLabelStyle = iArr;
        }
        return iArr;
    }

    public CirChart() {
        if (this.plotLegend != null) {
            this.plotLegend.show();
            this.plotLegend.setType(LegendType.ROW);
            this.plotLegend.setHorizontalAlign(HorizontalAlign.CENTER);
            this.plotLegend.setVerticalAlign(VerticalAlign.BOTTOM);
            this.plotLegend.showBox();
            this.plotLegend.hideBackground();
        }
    }

    protected void calcPlotRange() {
        super.calcPlotRange();
        this.mRadius = Math.min(div(this.plotArea.getWidth(), 2.0f), div(this.plotArea.getHeight(), 2.0f));
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setInitialAngle(float f) {
        this.mOffsetAngle = f;
        this.mInitOffsetAngle = f;
    }

    public float getInitialAngle() {
        return this.mInitOffsetAngle;
    }

    public float getOffsetAngle() {
        return this.mOffsetAngle;
    }

    public void setLabelStyle(SliceLabelStyle sliceLabelStyle) {
        this.mLabelStyle = sliceLabelStyle;
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$SliceLabelStyle()[sliceLabelStyle.ordinal()]) {
            case 2:
                getLabelPaint().setTextAlign(Align.CENTER);
                return;
            default:
                return;
        }
    }

    public SliceLabelStyle getLabelStyle() {
        return this.mLabelStyle;
    }

    public Paint getLabelPaint() {
        if (this.mPaintLabel == null) {
            this.mPaintLabel = new Paint(1);
            this.mPaintLabel.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintLabel.setAntiAlias(true);
            this.mPaintLabel.setTextAlign(Align.CENTER);
            this.mPaintLabel.setTextSize(18.0f);
        }
        return this.mPaintLabel;
    }

    public LabelBrokenLine getLabelBrokenLine() {
        if (this.mLabelLine == null) {
            this.mLabelLine = new LabelBrokenLineRender();
        }
        return this.mLabelLine;
    }

    protected PointF renderLabelInside(Canvas canvas, String str, float f, float f2, float f3, float f4, float f5, boolean z) {
        PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(f2, f3, MathHelper.getInstance().sub(f4, f4 / 2.0f), f5);
        if (z) {
            getPlotLabel().drawLabel(canvas, getLabelPaint(), str, calcArcEndPointXY.x, calcArcEndPointXY.y, f);
        }
        return new PointF(calcArcEndPointXY.x, calcArcEndPointXY.y);
    }

    protected PointF renderLabelOutside(Canvas canvas, String str, float f, float f2, float f3, float f4, float f5, boolean z) {
        PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(f2, f3, MathHelper.getInstance().add(f4, f4 / 10.0f), f5);
        if (z) {
            getPlotLabel().drawLabel(canvas, getLabelPaint(), str, calcArcEndPointXY.x, calcArcEndPointXY.y, f);
        }
        return new PointF(calcArcEndPointXY.x, calcArcEndPointXY.y);
    }

    protected PointF renderLabelLine(Canvas canvas, PieData pieData, float f, float f2, float f3, float f4, boolean z) {
        if (this.mLabelLine == null) {
            this.mLabelLine = new LabelBrokenLineRender();
        }
        if (this.mIsLabelLineSyncColor) {
            this.mLabelLine.getLabelLinePaint().setColor(pieData.getSliceColor());
        }
        if (this.mIsLabelPointSyncColor) {
            this.mLabelLine.getPointPaint().setColor(pieData.getSliceColor());
        }
        return this.mLabelLine.renderLabelLine(pieData.getLabel(), pieData.getItemLabelRotateAngle(), f, f2, f3, f4, canvas, getLabelPaint(), z, this.mPlotLabel);
    }

    public void syncLabelLineColor() {
        this.mIsLabelLineSyncColor = true;
    }

    public void syncLabelPointColor() {
        this.mIsLabelPointSyncColor = true;
    }

    public void syncLabelColor() {
        this.mIsLabelSyncColor = true;
    }

    public PlotLabel getPlotLabel() {
        if (this.mPlotLabel == null) {
            this.mPlotLabel = new PlotLabelRender();
            this.mPlotLabel.setLabelBoxStyle(LabelBoxStyle.TEXT);
        }
        return this.mPlotLabel;
    }

    protected boolean renderLabel(Canvas canvas, PieData pieData, PlotArcLabelInfo plotArcLabelInfo, boolean z, boolean z2) {
        if (SliceLabelStyle.HIDE == this.mLabelStyle) {
            return true;
        }
        if (pieData == null) {
            return false;
        }
        String label = pieData.getLabel();
        if ("" == label || label.length() == 0) {
            return true;
        }
        float x = plotArcLabelInfo.getX();
        float y = plotArcLabelInfo.getY();
        float radius = plotArcLabelInfo.getRadius();
        float add = (float) MathHelper.getInstance().add((double) plotArcLabelInfo.getOffsetAngle(), (double) (plotArcLabelInfo.getCurrentAngle() / 2.0f));
        if (Float.compare(add, 0.0f) == 0 || Float.compare(add, 0.0f) == -1) {
            Log.e(TAG, "计算出来的圆心角等于0.");
            return false;
        }
        PointF renderLabelInside;
        if (this.mIsLabelSyncColor) {
            getLabelPaint().setColor(pieData.getSliceColor());
        }
        int color = getLabelPaint().getColor();
        SliceLabelStyle sliceLabelStyle = this.mLabelStyle;
        if (pieData.getCustLabelStyleStatus()) {
            sliceLabelStyle = pieData.getLabelStyle();
            if (SliceLabelStyle.INSIDE == sliceLabelStyle) {
                getLabelPaint().setTextAlign(Align.CENTER);
            }
            getLabelPaint().setColor(pieData.getCustLabelColor());
        }
        if (SliceLabelStyle.INSIDE == sliceLabelStyle) {
            renderLabelInside = renderLabelInside(canvas, label, pieData.getItemLabelRotateAngle(), x, y, radius, add, z2);
        } else if (SliceLabelStyle.OUTSIDE == sliceLabelStyle) {
            renderLabelInside = renderLabelOutside(canvas, label, pieData.getItemLabelRotateAngle(), x, y, radius, add, z2);
        } else if (SliceLabelStyle.BROKENLINE == sliceLabelStyle) {
            renderLabelInside = renderLabelLine(canvas, pieData, x, y, radius, add, z2);
        } else {
            Log.e(TAG, "未知的标签处理类型.");
            return false;
        }
        getLabelPaint().setColor(color);
        if (z) {
            plotArcLabelInfo.setLabelPointF(renderLabelInside);
        }
        return true;
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            calcPlotRange();
            this.plotArea.render(canvas);
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
