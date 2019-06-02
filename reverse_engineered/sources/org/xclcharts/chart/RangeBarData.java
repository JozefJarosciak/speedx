package org.xclcharts.chart;

public class RangeBarData {
    private double mMax = 0.0d;
    private double mMin = 0.0d;
    private double mX = 0.0d;

    public RangeBarData(double d, double d2, double d3) {
        setX(d);
        setMax(d3);
        setMin(d2);
    }

    public void setMax(double d) {
        this.mMax = d;
    }

    public void setMin(double d) {
        this.mMin = d;
    }

    public double getMax() {
        return this.mMax;
    }

    public double getMin() {
        return this.mMin;
    }

    public void setX(double d) {
        this.mX = d;
    }

    public double getX() {
        return this.mX;
    }
}
