package org.xclcharts.renderer.plot;

import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import org.xclcharts.renderer.XEnum.LabelLinePoint;

public class LabelBrokenLine {
    private static final String TAG = "LabelBrokenLine";
    protected float mBrokenStartPoint = 3.0f;
    protected boolean mIsBZLine = false;
    private float mLabelBrokenLineLength = 30.0f;
    private LabelLinePoint mLinePoint = LabelLinePoint.ALL;
    private Paint mPaintLabelLine = null;
    private Paint mPaintPoint = null;
    private float mRadius = 5.0f;

    public void isBZLine() {
        this.mIsBZLine = true;
    }

    public void isBeeLine() {
        this.mIsBZLine = false;
    }

    public void setLinePointStyle(LabelLinePoint labelLinePoint) {
        this.mLinePoint = labelLinePoint;
    }

    public LabelLinePoint getLinePointStyle() {
        return this.mLinePoint;
    }

    public void setRadius(float f) {
        this.mRadius = f;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setBrokenLine(float f) {
        this.mLabelBrokenLineLength = f;
    }

    public float getBrokenLine() {
        return this.mLabelBrokenLineLength;
    }

    public Paint getLabelLinePaint() {
        if (this.mPaintLabelLine == null) {
            this.mPaintLabelLine = new Paint(1);
            this.mPaintLabelLine.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintLabelLine.setStrokeWidth(2.0f);
        }
        return this.mPaintLabelLine;
    }

    public Paint getPointPaint() {
        if (this.mPaintPoint == null) {
            this.mPaintPoint = new Paint(1);
        }
        return this.mPaintPoint;
    }

    public void setBrokenStartPoint(float f) {
        if (Float.compare(f, 1.0f) == -1 || Float.compare(f, 10.0f) == 1) {
            Log.e(TAG, "值必须在1到10范围内.");
        } else {
            this.mBrokenStartPoint = f;
        }
    }
}
