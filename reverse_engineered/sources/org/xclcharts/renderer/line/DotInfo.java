package org.xclcharts.renderer.line;

public class DotInfo {
    public Double mValue = Double.valueOf(0.0d);
    public float mX = 0.0f;
    public Double mXValue = Double.valueOf(0.0d);
    public float mY = 0.0f;
    public Double mYValue = Double.valueOf(0.0d);

    public DotInfo(Double d, float f, float f2) {
        this.mValue = d;
        this.mX = f;
        this.mY = f2;
    }

    public DotInfo(Double d, Double d2, float f, float f2) {
        this.mXValue = d;
        this.mYValue = d2;
        this.mX = f;
        this.mY = f2;
    }

    public String getLabel() {
        return new StringBuilder(String.valueOf(Double.toString(this.mXValue.doubleValue()))).append(",").append(Double.toString(this.mYValue.doubleValue())).toString();
    }
}
