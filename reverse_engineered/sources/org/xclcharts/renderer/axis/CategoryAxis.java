package org.xclcharts.renderer.axis;

public class CategoryAxis extends XYAxis {
    private boolean mAxisBindStd = false;
    private double mAxisSteps = 0.0d;

    public void setAxisSteps(double d) {
        this.mAxisSteps = d;
    }

    public double getAxisSteps() {
        return this.mAxisSteps;
    }

    public void setAxisBuildStd(boolean z) {
        this.mAxisBindStd = z;
    }

    public boolean getAxisBuildStdStatus() {
        return this.mAxisBindStd;
    }
}
