package org.xclcharts.renderer.plot;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import org.xclcharts.renderer.XEnum.AxisTitleStyle;

public class AxisTitle {
    protected AxisTitleStyle mAxisTitleStyle = AxisTitleStyle.NORMAL;
    protected String mCrossPointTitle = "";
    private String mLeftAxisTitle = "";
    protected float mLeftAxisTitleOffsetX = 0.0f;
    private String mLowerAxisTitle = "";
    protected float mLowerAxisTitleOffsetY = 0.0f;
    private Paint mPaintLeftAxisTitle = null;
    private Paint mPaintLowerAxisTitle = null;
    private Paint mPaintRightAxisTitle = null;
    private String mRightAxisTitle = "";
    protected float mRightAxisTitleOffsetX = 0.0f;

    private void initLeftAxisTitlePaint() {
        if (this.mPaintLeftAxisTitle == null) {
            this.mPaintLeftAxisTitle = new Paint();
            this.mPaintLeftAxisTitle.setTextAlign(Align.CENTER);
            this.mPaintLeftAxisTitle.setAntiAlias(true);
            this.mPaintLeftAxisTitle.setTextSize(26.0f);
            this.mPaintLeftAxisTitle.setColor(Color.rgb(255, 153, 204));
        }
    }

    private void initLowerAxisTitlePaint() {
        if (this.mPaintLowerAxisTitle == null) {
            this.mPaintLowerAxisTitle = new Paint();
            this.mPaintLowerAxisTitle.setTextAlign(Align.CENTER);
            this.mPaintLowerAxisTitle.setAntiAlias(true);
            this.mPaintLowerAxisTitle.setTextSize(26.0f);
            this.mPaintLowerAxisTitle.setColor(Color.rgb(58, 65, 83));
        }
    }

    private void initRightAxisTitlePaint() {
        if (this.mPaintRightAxisTitle == null) {
            this.mPaintRightAxisTitle = new Paint();
            this.mPaintRightAxisTitle.setTextAlign(Align.CENTER);
            this.mPaintRightAxisTitle.setAntiAlias(true);
            this.mPaintRightAxisTitle.setTextSize(26.0f);
            this.mPaintRightAxisTitle.setColor(Color.rgb(51, 204, 204));
        }
    }

    public Paint getLeftTitlePaint() {
        initLeftAxisTitlePaint();
        return this.mPaintLeftAxisTitle;
    }

    public Paint getLowerTitlePaint() {
        initLowerAxisTitlePaint();
        return this.mPaintLowerAxisTitle;
    }

    public Paint getRightTitlePaint() {
        initRightAxisTitlePaint();
        return this.mPaintRightAxisTitle;
    }

    public void setLeftTitle(String str) {
        this.mLeftAxisTitle = str;
    }

    public void setLowerTitle(String str) {
        this.mLowerAxisTitle = str;
    }

    public void setRightTitle(String str) {
        this.mRightAxisTitle = str;
    }

    public String getLeftTitle() {
        return this.mLeftAxisTitle;
    }

    public String getLowerTitle() {
        return this.mLowerAxisTitle;
    }

    public String getRightTitle() {
        return this.mRightAxisTitle;
    }

    public void setTitleStyle(AxisTitleStyle axisTitleStyle) {
        this.mAxisTitleStyle = axisTitleStyle;
    }

    public void setCrossPointTitle(String str) {
        this.mCrossPointTitle = str;
    }

    public void setLeftAxisTitleOffsetX(float f) {
        this.mLeftAxisTitleOffsetX = f;
    }

    public void setRightAxisTitleOffsetX(float f) {
        this.mRightAxisTitleOffsetX = f;
    }

    public void setLowerAxisTitleOffsetY(float f) {
        this.mLowerAxisTitleOffsetY = f;
    }
}
