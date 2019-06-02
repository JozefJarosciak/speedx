package org.xclcharts.renderer.axis;

public class DataAxis extends XYAxis {
    private boolean mAxisStdStatus = false;
    private double mDataAxisMax = 0.0d;
    private double mDataAxisMin = 0.0d;
    private float mDataAxisStd = 0.0f;
    private double mDataAxisSteps = 0.0d;
    private double mDetailModeSteps = 0.0d;
    protected boolean mShowFirstTick = true;

    public void enabledAxisStd() {
        this.mAxisStdStatus = true;
    }

    public void disableddAxisStd() {
        this.mAxisStdStatus = false;
    }

    public void setAxisStd(float f) {
        this.mDataAxisStd = f;
    }

    public boolean getAxisStdStatus() {
        return this.mAxisStdStatus;
    }

    public float getAxisStd() {
        if (this.mAxisStdStatus) {
            return this.mDataAxisStd;
        }
        return (float) this.mDataAxisMin;
    }

    public void setAxisMin(double d) {
        this.mDataAxisMin = d;
    }

    public void setAxisMax(double d) {
        this.mDataAxisMax = d;
    }

    public void setAxisSteps(double d) {
        this.mDataAxisSteps = d;
    }

    public void setDetailModeSteps(double d) {
        this.mDetailModeSteps = d;
    }

    public float getAxisMin() {
        return (float) this.mDataAxisMin;
    }

    public float getAxisMax() {
        return (float) this.mDataAxisMax;
    }

    public double getAxisSteps() {
        return this.mDataAxisSteps;
    }

    public double getDetailModeSteps() {
        return this.mDetailModeSteps;
    }

    public boolean isDetailMode() {
        return Double.compare(this.mDetailModeSteps, 0.0d) != 0;
    }

    public void showFirstTick() {
        this.mShowFirstTick = true;
    }

    public void hideFirstTick() {
        this.mShowFirstTick = false;
    }
}
