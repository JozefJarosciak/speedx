package org.xclcharts.chart;

import android.util.Log;
import org.xclcharts.common.MathHelper;

public class ArcLineData {
    private final String TAG = "RoundBarData";
    private int mColor = 0;
    private String mKey = "";
    private String mLabel = "";
    private double mValue = 0.0d;

    public ArcLineData(String str, double d, int i) {
        setLabel(str);
        setPercentage(d);
        setBarColor(i);
    }

    public ArcLineData(String str, String str2, double d, int i) {
        setLabel(str2);
        setPercentage(d);
        setBarColor(i);
        setKey(str);
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public void setPercentage(double d) {
        this.mValue = d;
    }

    public void setBarColor(int i) {
        this.mColor = i;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public double getPercentage() {
        return this.mValue;
    }

    public int getBarColor() {
        return this.mColor;
    }

    public float getSliceAngle() {
        try {
            float percentage = (float) getPercentage();
            if (percentage < 101.0f && percentage >= 0.0f) {
                return MathHelper.getInstance().round((percentage / 100.0f) * 360.0f, 2);
            }
            Log.e("RoundBarData", "输入的百分比不合规范.须在0~100之间.");
            return 0.0f;
        } catch (Exception e) {
            return -1.0f;
        }
    }
}
