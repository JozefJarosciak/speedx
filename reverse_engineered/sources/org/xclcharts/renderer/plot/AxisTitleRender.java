package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.IRender;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum.AxisTitleStyle;

public class AxisTitleRender extends AxisTitle implements IRender {
    private XChart mChart = null;

    public void setRange(XChart xChart) {
        this.mChart = xChart;
    }

    public boolean render(Canvas canvas) {
        if (this.mChart == null) {
            return false;
        }
        float bottom;
        float f;
        float f2;
        float f3;
        float left;
        float top;
        float right;
        if (this.mAxisTitleStyle == AxisTitleStyle.ENDPOINT) {
            left = this.mChart.getLeft();
            top = this.mChart.getPlotArea().getTop();
            right = this.mChart.getPlotArea().getRight();
            bottom = this.mChart.getPlotArea().getBottom();
            f = right;
            f2 = top;
            f3 = left;
        } else {
            left = this.mChart.getLeft();
            top = this.mChart.getTop();
            right = this.mChart.getRight();
            bottom = this.mChart.getBottom();
            f = right;
            f2 = top;
            f3 = left;
        }
        if (getLeftTitle().length() > 0) {
            drawLeftAxisTitle(canvas, getLeftTitle(), (double) f3, (double) f2, (double) f, (double) bottom);
        }
        if (getLowerTitle().length() > 0) {
            drawLowerAxisTitle(canvas, getLowerTitle(), (double) f3, (double) f2, (double) f, (double) bottom);
        }
        if (getRightTitle().length() > 0) {
            drawRightAxisTitle(canvas, getRightTitle(), (double) f3, (double) f2, (double) f, (double) bottom);
        }
        return true;
    }

    public void drawLeftAxisTitle(Canvas canvas, String str, double d, double d2, double d3, double d4) {
        if (canvas != null && str.length() != 0 && "" != str) {
            float round;
            double textWidth = (double) DrawHelper.getInstance().getTextWidth(getLeftTitlePaint(), str);
            float round2 = (float) Math.round((((double) this.mLeftAxisTitleOffsetX) + d) + ((double) getLeftTitlePaint().getTextSize()));
            if (this.mAxisTitleStyle == AxisTitleStyle.ENDPOINT) {
                round = (float) Math.round(textWidth + d2);
            } else {
                round = (float) Math.round((textWidth / 2.0d) + (((d4 - d2) / 2.0d) + d2));
            }
            float f = round;
            for (int i = 0; i < str.length(); i++) {
                double textWidth2 = (double) DrawHelper.getInstance().getTextWidth(getLeftTitlePaint(), str.substring(i, i + 1));
                DrawHelper.getInstance().drawRotateText(str.substring(i, i + 1), round2, f, -90.0f, canvas, getLeftTitlePaint());
                f = (float) (((double) f) - textWidth2);
            }
        }
    }

    public void drawLowerAxisTitle(Canvas canvas, String str, double d, double d2, double d3, double d4) {
        if (canvas != null && "" != str && str.length() != 0) {
            float f;
            float sub = (float) MathHelper.getInstance().sub((double) this.mChart.getBottom(), ((double) DrawHelper.getInstance().getPaintFontHeight(getLowerTitlePaint())) / 2.0d);
            if (this.mAxisTitleStyle == AxisTitleStyle.ENDPOINT) {
                float f2 = (float) d3;
                if (this.mCrossPointTitle.length() > 0) {
                    getLowerTitlePaint().setTextAlign(Align.LEFT);
                    DrawHelper.getInstance().drawRotateText(this.mCrossPointTitle, (float) d, sub, 0.0f, canvas, getLowerTitlePaint());
                }
                getLowerTitlePaint().setTextAlign(Align.RIGHT);
                f = f2;
            } else {
                f = (float) Math.round(((d3 - d) / 2.0d) + d);
            }
            DrawHelper.getInstance().drawRotateText(str, f - this.mLowerAxisTitleOffsetY, sub, 0.0f, canvas, getLowerTitlePaint());
        }
    }

    public void drawRightAxisTitle(Canvas canvas, String str, double d, double d2, double d3, double d4) {
        if (canvas != null && str.length() != 0 && "" != str) {
            float round = (float) Math.round((d3 - ((double) this.mRightAxisTitleOffsetX)) - ((double) getRightTitlePaint().getTextSize()));
            float round2 = (float) Math.round((((d4 - d2) - ((double) DrawHelper.getInstance().getTextWidth(getRightTitlePaint(), str))) / 2.0d) + d2);
            for (int i = 0; i < str.length(); i++) {
                float textWidth = DrawHelper.getInstance().getTextWidth(getRightTitlePaint(), str.substring(i, i + 1));
                DrawHelper.getInstance().drawRotateText(str.substring(i, i + 1), round, round2, 90.0f, canvas, getRightTitlePaint());
                round2 += textWidth;
            }
        }
    }
}
