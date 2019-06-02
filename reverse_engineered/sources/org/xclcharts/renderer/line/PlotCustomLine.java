package org.xclcharts.renderer.line;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.util.Log;
import java.util.List;
import org.xclcharts.chart.CustomLineData;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.axis.DataAxisRender;
import org.xclcharts.renderer.plot.PlotAreaRender;

public class PlotCustomLine {
    private static /* synthetic */ int[] $SWITCH_TABLE$android$graphics$Paint$Align = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign = null;
    private static final int CAPSIZE = 10;
    private static final String TAG = "PlotCustomLine";
    private float mAxisScreenHeight = 0.0f;
    private float mAxisScreenWidth = 0.0f;
    private List<CustomLineData> mCustomLineDataset;
    private DataAxisRender mDataAxis = null;
    private PlotDot mDot = null;
    private PlotAreaRender mPlotArea = null;

    static /* synthetic */ int[] $SWITCH_TABLE$android$graphics$Paint$Align() {
        int[] iArr = $SWITCH_TABLE$android$graphics$Paint$Align;
        if (iArr == null) {
            iArr = new int[Align.values().length];
            try {
                iArr[Align.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Align.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Align.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$android$graphics$Paint$Align = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign;
        if (iArr == null) {
            iArr = new int[VerticalAlign.values().length];
            try {
                iArr[VerticalAlign.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VerticalAlign.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VerticalAlign.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign = iArr;
        }
        return iArr;
    }

    public void setVerticalPlot(DataAxisRender dataAxisRender, PlotAreaRender plotAreaRender, float f) {
        setDataAxis(dataAxisRender);
        setPlotArea(plotAreaRender);
        setAxisScreenHeight(f);
    }

    public void setHorizontalPlot(DataAxisRender dataAxisRender, PlotAreaRender plotAreaRender, float f) {
        setDataAxis(dataAxisRender);
        setPlotArea(plotAreaRender);
        setAxisScreenWidth(f);
    }

    private boolean validateParams() {
        if (this.mDataAxis == null) {
            Log.e(TAG, "数据轴基类没有传过来。");
            return false;
        } else if (this.mPlotArea == null) {
            Log.e(TAG, "绘图区基类没有传过来。");
            return false;
        } else if (this.mCustomLineDataset != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean renderVerticalCustomlinesDataAxis(Canvas canvas) {
        if (!validateParams()) {
            return false;
        }
        if (0.0f == this.mAxisScreenHeight) {
            Log.e(TAG, "轴的屏幕高度值没有传过来。");
            return false;
        }
        double sub = (double) MathHelper.getInstance().sub(this.mDataAxis.getAxisMax(), this.mDataAxis.getAxisMin());
        for (CustomLineData customLineData : this.mCustomLineDataset) {
            customLineData.getCustomLinePaint().setColor(customLineData.getColor());
            customLineData.getCustomLinePaint().setStrokeWidth((float) customLineData.getLineStroke());
            float mul = MathHelper.getInstance().mul(this.mAxisScreenHeight, (float) MathHelper.getInstance().div(MathHelper.getInstance().sub(customLineData.getValue().doubleValue(), (double) this.mDataAxis.getAxisMin()), sub));
            float sub2 = MathHelper.getInstance().sub(this.mPlotArea.getBottom(), mul);
            if (customLineData.isShowLine()) {
                DrawHelper.getInstance().drawLine(customLineData.getLineStyle(), this.mPlotArea.getLeft(), sub2, this.mPlotArea.getRight(), sub2, canvas, customLineData.getCustomLinePaint());
            }
            renderCapLabelVerticalPlot(canvas, customLineData, mul);
        }
        return true;
    }

    private void renderCapLabelVerticalPlot(Canvas canvas, CustomLineData customLineData, float f) {
        float f2 = 0.0f;
        if (customLineData.getLabel().length() > 0) {
            float sub;
            float sub2 = MathHelper.getInstance().sub(this.mPlotArea.getBottom(), f);
            switch ($SWITCH_TABLE$android$graphics$Paint$Align()[customLineData.getLabelHorizontalPostion().ordinal()]) {
                case 1:
                    sub = MathHelper.getInstance().sub(MathHelper.getInstance().add(this.mPlotArea.getLeft(), MathHelper.getInstance().div(MathHelper.getInstance().sub(this.mPlotArea.getRight(), this.mPlotArea.getLeft()), 2.0f)), (float) customLineData.getLabelOffset());
                    customLineData.getLineLabelPaint().setTextAlign(Align.CENTER);
                    f2 = MathHelper.getInstance().add(this.mPlotArea.getLeft(), MathHelper.getInstance().div(MathHelper.getInstance().sub(this.mPlotArea.getRight(), this.mPlotArea.getLeft()), 2.0f));
                    break;
                case 2:
                    sub = MathHelper.getInstance().sub(this.mPlotArea.getLeft(), (float) customLineData.getLabelOffset());
                    customLineData.getLineLabelPaint().setTextAlign(Align.RIGHT);
                    f2 = this.mPlotArea.getRight();
                    break;
                case 3:
                    sub = MathHelper.getInstance().add(this.mPlotArea.getRight(), (float) customLineData.getLabelOffset());
                    customLineData.getLineLabelPaint().setTextAlign(Align.LEFT);
                    f2 = this.mPlotArea.getLeft();
                    break;
                default:
                    sub = 0.0f;
                    break;
            }
            renderLineCapVerticalPlot(canvas, customLineData, f2, sub2);
            renderLabel(canvas, customLineData, sub, sub2);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void renderLabel(android.graphics.Canvas r8, org.xclcharts.chart.CustomLineData r9, float r10, float r11) {
        /*
        r7 = this;
        r3 = 1077936128; // 0x40400000 float:3.0 double:5.325712093E-315;
        r0 = org.xclcharts.common.DrawHelper.getInstance();
        r1 = r9.getLineLabelPaint();
        r0 = r0.getPaintFontHeight(r1);
        r1 = $SWITCH_TABLE$android$graphics$Paint$Align();
        r2 = r9.getLabelHorizontalPostion();
        r2 = r2.ordinal();
        r1 = r1[r2];
        switch(r1) {
            case 1: goto L_0x003a;
            case 2: goto L_0x0036;
            case 3: goto L_0x004f;
            default: goto L_0x001f;
        };
    L_0x001f:
        r3 = r11;
    L_0x0020:
        r0 = org.xclcharts.common.DrawHelper.getInstance();
        r1 = r9.getLabel();
        r4 = r9.getLabelRotateAngle();
        r6 = r9.getLineLabelPaint();
        r2 = r10;
        r5 = r8;
        r0.drawRotateText(r1, r2, r3, r4, r5, r6);
        return;
    L_0x0036:
        r0 = r0 / r3;
        r11 = r11 + r0;
        r3 = r11;
        goto L_0x0020;
    L_0x003a:
        r0 = r9.isShowLine();
        if (r0 == 0) goto L_0x001f;
    L_0x0040:
        r0 = org.xclcharts.common.DrawHelper.getInstance();
        r1 = r9.getCustomLinePaint();
        r0 = r0.getPaintFontHeight(r1);
        r11 = r11 - r0;
        r3 = r11;
        goto L_0x0020;
    L_0x004f:
        r0 = r0 / r3;
        r11 = r11 + r0;
        r3 = r11;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xclcharts.renderer.line.PlotCustomLine.renderLabel(android.graphics.Canvas, org.xclcharts.chart.CustomLineData, float, float):void");
    }

    public boolean renderHorizontalCustomlinesDataAxis(Canvas canvas) {
        if (!validateParams()) {
            return false;
        }
        if (0.0f == this.mAxisScreenWidth) {
            Log.e(TAG, "轴的屏幕宽度值没有传过来。");
            return false;
        }
        double axisMax = (double) (this.mDataAxis.getAxisMax() - this.mDataAxis.getAxisMin());
        for (CustomLineData customLineData : this.mCustomLineDataset) {
            customLineData.getCustomLinePaint().setColor(customLineData.getColor());
            customLineData.getCustomLinePaint().setStrokeWidth((float) customLineData.getLineStroke());
            double doubleValue = ((double) this.mAxisScreenWidth) * ((customLineData.getValue().doubleValue() - ((double) this.mDataAxis.getAxisMin())) / axisMax);
            float left = (float) (((double) this.mPlotArea.getLeft()) + doubleValue);
            if (customLineData.isShowLine()) {
                DrawHelper.getInstance().drawLine(customLineData.getLineStyle(), left, this.mPlotArea.getBottom(), left, this.mPlotArea.getTop(), canvas, customLineData.getCustomLinePaint());
            }
            renderCapLabelHorizontalPlot(canvas, customLineData, doubleValue);
        }
        return true;
    }

    public boolean renderCategoryAxisCustomlines(Canvas canvas, float f, PlotAreaRender plotAreaRender, double d, double d2) {
        setPlotArea(plotAreaRender);
        for (CustomLineData customLineData : this.mCustomLineDataset) {
            customLineData.getCustomLinePaint().setColor(customLineData.getColor());
            customLineData.getCustomLinePaint().setStrokeWidth((float) customLineData.getLineStroke());
            float lnPlotXValPosition = MathHelper.getInstance().getLnPlotXValPosition(f, plotAreaRender.getLeft(), customLineData.getValue().doubleValue(), d, d2);
            float add = MathHelper.getInstance().add(plotAreaRender.getLeft(), lnPlotXValPosition);
            if (customLineData.isShowLine()) {
                DrawHelper.getInstance().drawLine(customLineData.getLineStyle(), add, plotAreaRender.getBottom(), add, plotAreaRender.getTop(), canvas, customLineData.getCustomLinePaint());
            }
            renderCapLabelHorizontalPlot(canvas, customLineData, (double) lnPlotXValPosition);
        }
        return true;
    }

    private void renderCapLabelHorizontalPlot(Canvas canvas, CustomLineData customLineData, double d) {
        float f = 0.0f;
        if (customLineData.getLabel().length() > 0) {
            float top;
            float left = (float) (((double) this.mPlotArea.getLeft()) + d);
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign()[customLineData.getLabelVerticalAlign().ordinal()]) {
                case 1:
                    top = this.mPlotArea.getTop() - ((float) customLineData.getLabelOffset());
                    f = this.mPlotArea.getBottom();
                    break;
                case 2:
                    top = (this.mPlotArea.getBottom() - ((this.mPlotArea.getBottom() - this.mPlotArea.getTop()) / 2.0f)) - ((float) customLineData.getLabelOffset());
                    f = this.mPlotArea.getBottom() - ((this.mPlotArea.getBottom() - this.mPlotArea.getTop()) / 2.0f);
                    break;
                case 3:
                    top = this.mPlotArea.getBottom() + ((float) customLineData.getLabelOffset());
                    f = this.mPlotArea.getTop();
                    break;
                default:
                    top = 0.0f;
                    break;
            }
            customLineData.getLineLabelPaint().setTextAlign(Align.CENTER);
            renderLineCapHorizontalPlot(canvas, customLineData, left, f);
            DrawHelper.getInstance().drawRotateText(customLineData.getLabel(), left, top, customLineData.getLabelRotateAngle(), canvas, customLineData.getLineLabelPaint());
        }
    }

    private void renderLineCapHorizontalPlot(Canvas canvas, CustomLineData customLineData, float f, float f2) {
        renderLineCap(canvas, customLineData, f, f2, f, f2);
    }

    private void renderLineCapVerticalPlot(Canvas canvas, CustomLineData customLineData, float f, float f2) {
        renderLineCap(canvas, customLineData, f - 20.0f, f2 - 20.0f, f, f2);
    }

    private void renderLineCap(Canvas canvas, CustomLineData customLineData, float f, float f2, float f3, float f4) {
        initPlotDot();
        this.mDot.setDotStyle(customLineData.getCustomeLineCap());
        PlotDotRender.getInstance().renderDot(canvas, this.mDot, f + ((f3 - f) / 2.0f), f2 + ((f4 - f2) / 2.0f), customLineData.getCustomLinePaint());
    }

    private void initPlotDot() {
        if (this.mDot == null) {
            this.mDot = new PlotDot();
        }
    }

    public void setCustomLines(List<CustomLineData> list) {
        this.mCustomLineDataset = list;
    }

    public void setDataAxis(DataAxisRender dataAxisRender) {
        this.mDataAxis = dataAxisRender;
    }

    public void setPlotArea(PlotAreaRender plotAreaRender) {
        this.mPlotArea = plotAreaRender;
    }

    public void setAxisScreenHeight(float f) {
        this.mAxisScreenHeight = f;
    }

    public void setAxisScreenWidth(float f) {
        this.mAxisScreenWidth = f;
    }
}
