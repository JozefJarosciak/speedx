package org.xclcharts.renderer.plot;

import android.graphics.Canvas;

public class PlotQuadrantRender extends PlotQuadrant {
    public void drawQuadrant(Canvas canvas, float f, float f2, float f3, float f4, float f5, float f6) {
        if (this.mShowBgColor) {
            getBgColorPaint().setColor(this.mFirstColor);
            canvas.drawRect(f, f4, f5, f2, getBgColorPaint());
            getBgColorPaint().setColor(this.mSecondColor);
            canvas.drawRect(f, f2, f5, f6, getBgColorPaint());
            getBgColorPaint().setColor(this.mThirdColor);
            canvas.drawRect(f3, f2, f, f6, getBgColorPaint());
            getBgColorPaint().setColor(this.mFourthColor);
            canvas.drawRect(f3, f4, f, f2, getBgColorPaint());
        }
        if (this.mShowVerticalLine) {
            canvas.drawLine(f, f4, f, f6, getVerticalLinePaint());
        }
        if (this.mShowHorizontalLine) {
            canvas.drawLine(f3, f2, f5, f2, getVerticalLinePaint());
        }
    }
}
