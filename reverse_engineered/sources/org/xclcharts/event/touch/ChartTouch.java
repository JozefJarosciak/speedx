package org.xclcharts.event.touch;

import android.view.MotionEvent;
import android.view.View;
import org.xclcharts.renderer.XChart;

public class ChartTouch implements IChartTouch {
    private final float FIXED_RANGE = 8.0f;
    private int action = 0;
    private float halfDist = 0.0f;
    private XChart mChart = null;
    private float mPanRatio = 1.0f;
    private View mView = null;
    private float newDist = 0.0f;
    private float newX = 0.0f;
    private float newY = 0.0f;
    private float oldDist = 1.0f;
    private float oldX = 0.0f;
    private float oldY = 0.0f;
    private float scaleRate = 0.0f;

    public ChartTouch(View view, XChart xChart) {
        this.mChart = xChart;
        this.mView = view;
    }

    public ChartTouch(View view, XChart xChart, float f) {
        this.mChart = xChart;
        this.mView = view;
        this.mPanRatio = f;
    }

    public void handleTouch(MotionEvent motionEvent) {
        switch (motionEvent.getPointerCount()) {
            case 1:
                handleTouch_PanMode(motionEvent);
                return;
            case 2:
                handleTouch_Scale(motionEvent);
                return;
            default:
                return;
        }
    }

    public void handleTouch_Scale(MotionEvent motionEvent) {
        if (this.mChart != null && this.mChart.getScaleStatus()) {
            switch (motionEvent.getActionMasked()) {
                case 0:
                    this.scaleRate = 1.0f;
                    return;
                case 1:
                case 6:
                    return;
                case 2:
                    this.newDist = spacing(motionEvent);
                    if (Float.compare(this.newDist, 10.0f) == 1) {
                        this.halfDist = this.newDist / 2.0f;
                        if (Float.compare(this.oldDist, 0.0f) != 0) {
                            this.scaleRate = this.newDist / this.oldDist;
                            this.mChart.setScale(this.scaleRate, this.scaleRate, motionEvent.getX() - this.halfDist, motionEvent.getY() - this.halfDist);
                            if (this.mView != null) {
                                this.mView.invalidate((int) this.mChart.getLeft(), (int) this.mChart.getTop(), (int) this.mChart.getRight(), (int) this.mChart.getBottom());
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                case 5:
                    this.oldDist = spacing(motionEvent);
                    return;
                default:
                    return;
            }
        }
    }

    public void handleTouch_PanMode(MotionEvent motionEvent) {
        this.action = motionEvent.getAction();
        if (this.action == 2) {
            if (this.oldX > 0.0f && this.oldY > 0.0f) {
                this.newX = motionEvent.getX(0);
                this.newY = motionEvent.getY(0);
                if (Float.compare(Math.abs(this.newX - this.oldX), 8.0f) == 1 || Float.compare(Math.abs(this.newY - this.oldY), 8.0f) == 1) {
                    setLocation(this.oldX, this.oldY, this.newX, this.newY);
                    this.oldX = this.newX;
                    this.oldY = this.newY;
                }
            }
        } else if (this.action == 0) {
            this.oldX = motionEvent.getX(0);
            this.oldY = motionEvent.getY(0);
        } else if (this.action == 5) {
        } else {
            if (this.action == 1 || this.action == 6) {
                this.oldX = 0.0f;
                this.oldY = 0.0f;
                if (this.action == 6) {
                    this.oldX = -1.0f;
                    this.oldY = -1.0f;
                }
            }
        }
    }

    public void setPanRatio(float f) {
        this.mPanRatio = f;
    }

    private void setLocation(float f, float f2, float f3, float f4) {
        float f5 = 1.0f;
        if (this.mChart != null && this.mView != null) {
            float[] translateXY = this.mChart.getTranslateXY();
            if (translateXY != null) {
                float f6 = translateXY[0];
                f6 = translateXY[1];
                f6 = (translateXY[0] + f3) - f;
                float f7 = (translateXY[1] + f4) - f2;
                if (this.mChart.getCtlPanRangeStatus()) {
                    float plotWidth;
                    if (Float.compare(this.mPanRatio, 0.0f) > 0) {
                        plotWidth = this.mChart.getPlotArea().getPlotWidth() / this.mPanRatio;
                        f5 = this.mChart.getHeight() / this.mPanRatio;
                    } else {
                        plotWidth = 1.0f;
                    }
                    if (Float.compare(Math.abs(f6), plotWidth) == 1 || Float.compare(Math.abs(f7), r0) == 1) {
                        return;
                    }
                }
                this.mChart.setTranslateXY(f6, f7);
                this.mView.invalidate((int) this.mChart.getLeft(), (int) this.mChart.getTop(), (int) this.mChart.getRight(), (int) this.mChart.getBottom());
            }
        }
    }

    private float spacing(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }
}
