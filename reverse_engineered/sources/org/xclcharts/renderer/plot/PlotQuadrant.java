package org.xclcharts.renderer.plot;

import android.graphics.Paint;

public class PlotQuadrant {
    protected int mFirstColor = -1;
    protected int mFourthColor = -1;
    private Paint mPaintBgColor = null;
    private Paint mPaintHorizontalLine = null;
    private Paint mPaintVerticalLine = null;
    private double mQuadrantXValue = 0.0d;
    private double mQuadrantYValue = 0.0d;
    protected int mSecondColor = -1;
    protected boolean mShow = false;
    protected boolean mShowBgColor = true;
    protected boolean mShowHorizontalLine = true;
    protected boolean mShowVerticalLine = true;
    protected int mThirdColor = -1;

    public void show(double d, double d2) {
        setQuadrantXYValue(d, d2);
        this.mShow = true;
    }

    public void hide() {
        this.mShow = false;
    }

    public boolean isShow() {
        return this.mShow;
    }

    public void showBgColor() {
        this.mShowBgColor = true;
    }

    public void hideBgColor() {
        this.mShowBgColor = false;
    }

    public void showVerticalLine() {
        this.mShowVerticalLine = true;
    }

    public void hideVerticalLine() {
        this.mShowVerticalLine = false;
    }

    public void showHorizontalLine() {
        this.mShowHorizontalLine = true;
    }

    public void hideHorizontalLine() {
        this.mShowHorizontalLine = false;
    }

    public void setBgColor(int i, int i2, int i3, int i4) {
        this.mFirstColor = i;
        this.mSecondColor = i2;
        this.mThirdColor = i3;
        this.mFourthColor = i4;
    }

    public Paint getVerticalLinePaint() {
        if (this.mPaintVerticalLine == null) {
            this.mPaintVerticalLine = new Paint(1);
        }
        return this.mPaintVerticalLine;
    }

    public Paint getHorizontalLinePaint() {
        if (this.mPaintHorizontalLine == null) {
            this.mPaintHorizontalLine = new Paint(1);
        }
        return this.mPaintHorizontalLine;
    }

    public Paint getBgColorPaint() {
        if (this.mPaintBgColor == null) {
            this.mPaintBgColor = new Paint(1);
        }
        return this.mPaintBgColor;
    }

    public void setQuadrantXYValue(double d, double d2) {
        this.mQuadrantXValue = d;
        this.mQuadrantYValue = d2;
    }

    public double getQuadrantXValue() {
        return this.mQuadrantXValue;
    }

    public double getQuadrantYValue() {
        return this.mQuadrantYValue;
    }
}
