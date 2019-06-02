package org.xclcharts.renderer.plot;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.alibaba.fastjson.asm.Opcodes;
import org.xclcharts.renderer.XEnum.LineStyle;

public class PlotGrid {
    private boolean mEvenRowBgColorVisible = false;
    private boolean mGridLinesHorizontalVisible = false;
    private boolean mGridLinesVerticalVisible = false;
    private LineStyle mHorizontalLineStyle = LineStyle.SOLID;
    private boolean mOddRowBgColorVisible = false;
    private Paint mPaintEvenBgColor = null;
    private Paint mPaintGridLineHorizontal = null;
    private Paint mPaintGridLineVertical = null;
    private Paint mPaintOddBgColor = null;
    private LineStyle mVerticalLineStyle = LineStyle.SOLID;

    private void initEvenBgColorPaint() {
        if (this.mPaintEvenBgColor == null) {
            this.mPaintEvenBgColor = new Paint();
            this.mPaintEvenBgColor.setStyle(Style.FILL);
            this.mPaintEvenBgColor.setColor(Color.rgb(239, 239, 239));
            this.mPaintEvenBgColor.setAntiAlias(true);
        }
    }

    private void initOddBgColorPaint() {
        if (this.mPaintOddBgColor == null) {
            this.mPaintOddBgColor = new Paint();
            this.mPaintOddBgColor.setStyle(Style.FILL);
            this.mPaintOddBgColor.setColor(-1);
            this.mPaintOddBgColor.setAntiAlias(true);
        }
    }

    private void initHorizontalLinePaint() {
        if (this.mPaintGridLineHorizontal == null) {
            this.mPaintGridLineHorizontal = new Paint();
            this.mPaintGridLineHorizontal.setAntiAlias(true);
            this.mPaintGridLineHorizontal.setStrokeWidth(1.0f);
            this.mPaintGridLineHorizontal.setColor(Color.rgb(Opcodes.GETFIELD, 205, 230));
        }
    }

    private void initVerticalLinePaint() {
        if (this.mPaintGridLineVertical == null) {
            this.mPaintGridLineVertical = new Paint();
            this.mPaintGridLineVertical.setColor(Color.rgb(Opcodes.GETFIELD, 205, 230));
            this.mPaintGridLineVertical.setStrokeWidth(1.0f);
            this.mPaintGridLineVertical.setAntiAlias(true);
        }
    }

    public void setOddRowBackgroundColor(int i) {
        this.mPaintOddBgColor.setColor(i);
    }

    public void setEvenRowBackgroundColor(int i) {
        this.mPaintOddBgColor.setColor(i);
    }

    public void showHorizontalLines() {
        this.mGridLinesHorizontalVisible = true;
    }

    public void hideHorizontalLines() {
        this.mGridLinesHorizontalVisible = false;
        if (this.mPaintGridLineHorizontal != null) {
            this.mPaintGridLineHorizontal = null;
        }
    }

    public boolean isShowHorizontalLines() {
        return this.mGridLinesHorizontalVisible;
    }

    public void showVerticalLines() {
        this.mGridLinesVerticalVisible = true;
    }

    public void hideVerticalLines() {
        this.mGridLinesVerticalVisible = false;
        if (this.mPaintGridLineVertical != null) {
            this.mPaintGridLineVertical = null;
        }
    }

    public boolean isShowVerticalLines() {
        return this.mGridLinesVerticalVisible;
    }

    public void showOddRowBgColor() {
        this.mOddRowBgColorVisible = true;
    }

    public void hideOddRowBgColor() {
        this.mOddRowBgColorVisible = false;
        if (this.mPaintOddBgColor != null) {
            this.mPaintOddBgColor = null;
        }
    }

    public boolean isShowOddRowBgColor() {
        return this.mOddRowBgColorVisible;
    }

    public void showEvenRowBgColor() {
        this.mEvenRowBgColorVisible = true;
    }

    public void hideEvenRowBgColor() {
        this.mEvenRowBgColorVisible = false;
        if (this.mPaintEvenBgColor != null) {
            this.mPaintEvenBgColor = null;
        }
    }

    public boolean isShowEvenRowBgColor() {
        return this.mEvenRowBgColorVisible;
    }

    public Paint getHorizontalLinePaint() {
        initHorizontalLinePaint();
        return this.mPaintGridLineHorizontal;
    }

    public Paint getVerticalLinePaint() {
        initVerticalLinePaint();
        return this.mPaintGridLineVertical;
    }

    public Paint getOddRowsBgColorPaint() {
        initOddBgColorPaint();
        return this.mPaintOddBgColor;
    }

    public Paint getEvenRowsBgColorPaint() {
        initEvenBgColorPaint();
        return this.mPaintEvenBgColor;
    }

    public LineStyle getVerticalLineStyle() {
        return this.mVerticalLineStyle;
    }

    public void setVerticalLineStyle(LineStyle lineStyle) {
        this.mVerticalLineStyle = lineStyle;
    }

    public LineStyle getHorizontalLineStyle() {
        return this.mHorizontalLineStyle;
    }

    public void setHorizontalLineStyle(LineStyle lineStyle) {
        this.mHorizontalLineStyle = lineStyle;
    }
}
