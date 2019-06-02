package org.xclcharts.renderer.plot;

import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import org.xclcharts.renderer.XEnum.Direction;

public class PlotArea {
    private boolean mApplayGradient = false;
    private boolean mBackgroundColorVisible = false;
    private Paint mBackgroundPaint = null;
    private int mBeginColor = -1;
    protected float mBottom = 0.0f;
    private Direction mDirection = Direction.VERTICAL;
    private int mEndColor = -1;
    private float mExtWidth = 0.0f;
    private float mHeight = 0.0f;
    protected float mLeft = 0.0f;
    protected float mRight = 0.0f;
    private TileMode mTileMode = TileMode.MIRROR;
    protected float mTop = 0.0f;
    private float mWidth = 0.0f;

    private void initBackgroundPaint() {
        if (this.mBackgroundPaint == null) {
            this.mBackgroundPaint = new Paint();
            this.mBackgroundPaint.setStyle(Style.FILL);
            this.mBackgroundPaint.setColor(-1);
        }
    }

    public Paint getBackgroundPaint() {
        initBackgroundPaint();
        return this.mBackgroundPaint;
    }

    public void setBackgroundColorVisible(boolean z) {
        this.mBackgroundColorVisible = z;
    }

    public boolean getBackgroundColorVisible() {
        return this.mBackgroundColorVisible;
    }

    public void setBackgroundColor(boolean z, int i) {
        this.mBackgroundColorVisible = z;
        getBackgroundPaint().setColor(i);
        setBeginColor(i);
        setEndColor(i);
    }

    public float getLeft() {
        return this.mLeft;
    }

    public float getPlotLeft() {
        return this.mLeft;
    }

    public float getTop() {
        return this.mTop;
    }

    public float getPlotTop() {
        return this.mTop;
    }

    public float getBottom() {
        return this.mBottom;
    }

    public float getPlotBottom() {
        return this.mBottom;
    }

    public float getRight() {
        return this.mRight;
    }

    public float getPlotRight() {
        return this.mRight + this.mExtWidth;
    }

    public float getWidth() {
        this.mWidth = Math.abs(this.mRight - this.mLeft);
        return this.mWidth;
    }

    public float getPlotWidth() {
        return Math.abs((this.mRight + this.mExtWidth) - this.mLeft);
    }

    public float getHeight() {
        this.mHeight = Math.abs(getBottom() - getTop());
        return this.mHeight;
    }

    public float getPlotHeight() {
        this.mHeight = Math.abs(getPlotBottom() - getPlotTop());
        return this.mHeight;
    }

    public void extWidth(float f) {
        this.mExtWidth = f;
    }

    public float getExtWidth() {
        return this.mExtWidth;
    }

    public void setApplayGradient(boolean z) {
        this.mApplayGradient = z;
    }

    public boolean getApplayGradient() {
        return this.mApplayGradient;
    }

    public void setGradientDirection(Direction direction) {
        this.mDirection = direction;
    }

    public Direction getGradientDirection() {
        return this.mDirection;
    }

    public void setGradientMode(TileMode tileMode) {
        this.mTileMode = tileMode;
    }

    public TileMode getGradientMode() {
        return this.mTileMode;
    }

    public void setBeginColor(int i) {
        this.mBeginColor = i;
    }

    public void setEndColor(int i) {
        this.mEndColor = i;
    }

    public int getBeginColor() {
        return this.mBeginColor;
    }

    public int getEndColor() {
        return this.mEndColor;
    }
}
