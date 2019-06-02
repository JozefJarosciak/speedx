package org.xclcharts.chart;

public class Funnel2Data implements Comparable<Funnel2Data> {
    private int mAlpha = -1;
    private float mBaseData;
    private int mColor;
    private String mLabel;
    private float mPercentData;

    public Funnel2Data(String str, float f, float f2, int i) {
        setLabel(str);
        setBaseData(f);
        setPercentData(f2);
        setColor(i);
    }

    public void setAlpha(int i) {
        this.mAlpha = i;
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setBaseData(float f) {
        this.mBaseData = f;
    }

    public void setPercentData(float f) {
        this.mPercentData = f;
    }

    public float getBaseData() {
        return this.mBaseData;
    }

    public float getPercentData() {
        return this.mPercentData;
    }

    public int compareTo(Funnel2Data funnel2Data) {
        return Float.compare(Float.valueOf(getBaseData()).floatValue(), Float.valueOf(funnel2Data.getBaseData()).floatValue());
    }
}
