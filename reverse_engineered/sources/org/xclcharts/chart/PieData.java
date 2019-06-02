package org.xclcharts.chart;

import org.xclcharts.renderer.XEnum.SliceLabelStyle;

public class PieData {
    private int mCustLabelColor = 0;
    private boolean mCustLabelStyle = false;
    private float mItemLabelRotateAngle = 0.0f;
    private SliceLabelStyle mLabelStyle = SliceLabelStyle.INSIDE;
    private int mPieColor = 0;
    private String mPieKey = "";
    private String mPieLabel = "";
    private double mPieValue = 0.0d;
    private boolean mSelected = false;

    public PieData(String str, double d, int i) {
        setLabel(str);
        setPercentage(d);
        setSliceColor(i);
    }

    public PieData(String str, double d, int i, boolean z) {
        setLabel(str);
        setPercentage(d);
        setSliceColor(i);
        setSelected(z);
    }

    public PieData(String str, String str2, double d, int i) {
        setLabel(str2);
        setPercentage(d);
        setSliceColor(i);
        setKey(str);
    }

    public PieData(String str, String str2, double d, int i, boolean z) {
        setLabel(str2);
        setPercentage(d);
        setSliceColor(i);
        setKey(str);
        setSelected(z);
    }

    public void setKey(String str) {
        this.mPieKey = str;
    }

    public String getKey() {
        return this.mPieKey;
    }

    public void setLabel(String str) {
        this.mPieLabel = str;
    }

    public void setPercentage(double d) {
        this.mPieValue = d;
    }

    public void setSliceColor(int i) {
        this.mPieColor = i;
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
    }

    public String getLabel() {
        return this.mPieLabel;
    }

    public double getPercentage() {
        return this.mPieValue;
    }

    public boolean getSelected() {
        return this.mSelected;
    }

    public int getSliceColor() {
        return this.mPieColor;
    }

    public float getItemLabelRotateAngle() {
        return this.mItemLabelRotateAngle;
    }

    public void setItemLabelRotateAngle(float f) {
        this.mItemLabelRotateAngle = f;
    }

    public void setCustLabelStyle(SliceLabelStyle sliceLabelStyle, int i) {
        this.mLabelStyle = sliceLabelStyle;
        this.mCustLabelStyle = true;
        this.mCustLabelColor = i;
    }

    public SliceLabelStyle getLabelStyle() {
        return this.mLabelStyle;
    }

    public boolean getCustLabelStyleStatus() {
        return this.mCustLabelStyle;
    }

    public int getCustLabelColor() {
        return this.mCustLabelColor;
    }
}
