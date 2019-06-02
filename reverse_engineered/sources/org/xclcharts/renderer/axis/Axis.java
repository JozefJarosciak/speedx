package org.xclcharts.renderer.axis;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.support.v4.view.ViewCompat;

public class Axis {
    private boolean mAxisLabelsVisible = true;
    private boolean mAxisLineVisible = true;
    private boolean mAxisVisible = true;
    private Paint mPaintAxis = null;
    private Paint mPaintTickLabels = null;
    private Paint mPaintTickMarks = null;
    private float mTickLabelRotateAngle = 0.0f;
    private boolean mTickMarksVisible = true;

    private void initAxisPaint() {
        if (this.mPaintAxis == null) {
            this.mPaintAxis = new Paint();
            this.mPaintAxis.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintAxis.setAntiAlias(true);
            this.mPaintAxis.setStrokeWidth(5.0f);
        }
    }

    private void initTickMarksPaint() {
        if (this.mPaintTickMarks == null) {
            this.mPaintTickMarks = new Paint();
            this.mPaintTickMarks.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintTickMarks.setStrokeWidth(3.0f);
            this.mPaintTickMarks.setAntiAlias(true);
        }
    }

    private void initTickLabelPaint() {
        if (this.mPaintTickLabels == null) {
            this.mPaintTickLabels = new Paint();
            this.mPaintTickLabels.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintTickLabels.setTextAlign(Align.RIGHT);
            this.mPaintTickLabels.setTextSize(18.0f);
            this.mPaintTickLabels.setAntiAlias(true);
        }
    }

    public void show() {
        this.mAxisVisible = true;
    }

    public void hide() {
        this.mAxisVisible = false;
    }

    public boolean isShow() {
        return this.mAxisVisible;
    }

    public void showAxisLine() {
        this.mAxisLineVisible = true;
    }

    public void hideAxisLine() {
        this.mAxisLineVisible = false;
    }

    public boolean isShowAxisLine() {
        return this.mAxisLineVisible;
    }

    public Paint getAxisPaint() {
        initAxisPaint();
        return this.mPaintAxis;
    }

    public Paint getTickMarksPaint() {
        initTickMarksPaint();
        return this.mPaintTickMarks;
    }

    public Paint getTickLabelPaint() {
        initTickLabelPaint();
        return this.mPaintTickLabels;
    }

    public void showTickMarks() {
        this.mTickMarksVisible = true;
    }

    public void hideTickMarks() {
        this.mTickMarksVisible = false;
    }

    public boolean isShowTickMarks() {
        return this.mTickMarksVisible;
    }

    public void showAxisLabels() {
        this.mAxisLabelsVisible = true;
    }

    public void hideAxisLabels() {
        this.mAxisLabelsVisible = false;
    }

    public boolean isShowAxisLabels() {
        return this.mAxisLabelsVisible;
    }

    public float getTickLabelRotateAngle() {
        return this.mTickLabelRotateAngle;
    }

    public void setTickLabelRotateAngle(float f) {
        this.mTickLabelRotateAngle = f;
    }
}
