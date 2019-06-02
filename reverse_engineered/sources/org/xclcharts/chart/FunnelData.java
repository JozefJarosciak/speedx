package org.xclcharts.chart;

public class FunnelData implements Comparable<FunnelData> {
    private int mAlpha = -1;
    private int mColor;
    private float mData;
    private String mLabel;

    public FunnelData(String str, float f, int i) {
        setLabel(str);
        setData(f);
        setColor(i);
    }

    public void setAlpha(int i) {
        this.mAlpha = i;
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setData(float f) {
        this.mData = f;
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

    public float getData() {
        return this.mData;
    }

    public int compareTo(FunnelData funnelData) {
        return Float.compare(Float.valueOf(getData()).floatValue(), Float.valueOf(funnelData.getData()).floatValue());
    }
}
