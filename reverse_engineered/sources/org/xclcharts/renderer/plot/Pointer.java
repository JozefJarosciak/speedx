package org.xclcharts.renderer.plot;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import org.xclcharts.renderer.XEnum.PointerStyle;

public class Pointer {
    protected float mBaseRadius = 20.0f;
    protected float mCenterX = 0.0f;
    protected float mCenterY = 0.0f;
    private Paint mPaintBaseCircle = null;
    private Paint mPaintPoint = null;
    protected float mPercentage = 0.0f;
    private PointerStyle mPointStyle = PointerStyle.LINE;
    protected float mPointerRadiusPercentage = 0.9f;
    protected float mPointerTailRadiusPercentage = 0.0f;
    private boolean mShowBaseCircle = true;

    public void setPointerStyle(PointerStyle pointerStyle) {
        this.mPointStyle = pointerStyle;
    }

    public PointerStyle getPointerStyle() {
        return this.mPointStyle;
    }

    public void setLength(float f) {
        setLength(f, 0.0f);
    }

    public void setLength(float f, float f2) {
        this.mPointerRadiusPercentage = f;
        this.mPointerTailRadiusPercentage = f2;
    }

    public Paint getPointerPaint() {
        if (this.mPaintPoint == null) {
            this.mPaintPoint = new Paint();
            this.mPaintPoint.setColor(Color.rgb(235, 138, 61));
            this.mPaintPoint.setStrokeWidth(3.0f);
            this.mPaintPoint.setStyle(Style.FILL);
            this.mPaintPoint.setAntiAlias(true);
        }
        return this.mPaintPoint;
    }

    public Paint getBaseCirclePaint() {
        if (this.mPaintBaseCircle == null) {
            this.mPaintBaseCircle = new Paint();
            this.mPaintBaseCircle.setStyle(Style.FILL);
            this.mPaintBaseCircle.setAntiAlias(true);
            this.mPaintBaseCircle.setColor(Color.rgb(235, 138, 61));
            this.mPaintBaseCircle.setStrokeWidth(8.0f);
        }
        return this.mPaintBaseCircle;
    }

    public void setBaseRadius(float f) {
        this.mBaseRadius = f;
    }

    public float getBaseRadius() {
        return this.mBaseRadius;
    }

    public void hideBaseCircle() {
        this.mShowBaseCircle = false;
    }

    public void showBaseCircle() {
        this.mShowBaseCircle = true;
    }

    public boolean isShowBaseCircle() {
        return this.mShowBaseCircle;
    }

    public void setPercentage(float f) {
        this.mPercentage = f;
    }

    public float getPercentage() {
        return this.mPercentage;
    }
}
