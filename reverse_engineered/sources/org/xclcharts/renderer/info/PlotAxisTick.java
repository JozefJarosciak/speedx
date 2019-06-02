package org.xclcharts.renderer.info;

public class PlotAxisTick extends PlotDataInfo {
    private boolean mShowTickMarks = true;

    public PlotAxisTick(float f, float f2, String str) {
        this.X = f;
        this.Y = f2;
        this.Label = str;
        this.labelX = f;
        this.labelY = f2;
    }

    public PlotAxisTick(int i, float f, float f2, String str) {
        this.ID = i;
        this.X = f;
        this.Y = f2;
        this.Label = str;
        this.labelX = f;
        this.labelY = f2;
    }

    public PlotAxisTick(float f, float f2, String str, float f3, float f4) {
        this.X = f;
        this.Y = f2;
        this.Label = str;
        this.labelX = f3;
        this.labelY = f4;
    }

    public PlotAxisTick(float f, float f2, String str, float f3, float f4, boolean z) {
        this.X = f;
        this.Y = f2;
        this.Label = str;
        this.labelX = f3;
        this.labelY = f4;
        this.mShowTickMarks = z;
    }

    public float getLabelX() {
        return this.labelX;
    }

    public void setLabelX(float f) {
        this.labelX = f;
    }

    public float getLabelY() {
        return this.labelY;
    }

    public void setLabelY(float f) {
        this.labelY = f;
    }

    public boolean isShowTickMarks() {
        return this.mShowTickMarks;
    }
}
