package org.xclcharts.chart;

import android.graphics.Paint;
import java.util.List;

public class BubbleData {
    private int mBorderColor = -1;
    private List<Double> mBubble;
    private int mColor = 0;
    private float mItemLabelRotateAngle = 0.0f;
    private String mLabel = "";
    private Paint mLabelPaint = null;
    private boolean mLabelVisible = false;
    private List<PointD> mPointMap;

    public BubbleData(String str, List<PointD> list, List<Double> list2, int i) {
        setKey(str);
        setDataSet(list);
        setBubble(list2);
        setColor(i);
    }

    public void setDataSet(List<PointD> list) {
        this.mPointMap = list;
    }

    public List<PointD> getDataSet() {
        return this.mPointMap;
    }

    public void setBubble(List<Double> list) {
        this.mBubble = list;
    }

    public List<Double> getBubble() {
        return this.mBubble;
    }

    public void setLabelVisible(boolean z) {
        this.mLabelVisible = z;
    }

    public boolean getLabelVisible() {
        return this.mLabelVisible;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setKey(String str) {
        this.mLabel = str;
    }

    public String getKey() {
        return this.mLabel;
    }

    public Paint getDotLabelPaint() {
        if (this.mLabelPaint == null) {
            this.mLabelPaint = new Paint(1);
        }
        return this.mLabelPaint;
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setBorderColor(int i) {
        this.mBorderColor = i;
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public float getItemLabelRotateAngle() {
        return this.mItemLabelRotateAngle;
    }

    public void setItemLabelRotateAngle(float f) {
        this.mItemLabelRotateAngle = f;
    }
}
