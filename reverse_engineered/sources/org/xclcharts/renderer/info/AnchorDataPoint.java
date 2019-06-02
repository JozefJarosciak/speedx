package org.xclcharts.renderer.info;

import android.support.v4.view.ViewCompat;
import org.xclcharts.renderer.XEnum.AnchorStyle;
import org.xclcharts.renderer.XEnum.DataAreaStyle;
import org.xclcharts.renderer.XEnum.LineStyle;

public class AnchorDataPoint {
    private int mAlpha = 100;
    private String mAnchor = "";
    private AnchorStyle mAnchorStyle = AnchorStyle.RECT;
    private int mAnchorTextColor = -16776961;
    private int mAnchorTextSize = 22;
    private int mBgColor = ViewCompat.MEASURED_STATE_MASK;
    private float mCapRectH = 10.0f;
    private float mCapRectHeight = 30.0f;
    private float mCapRectW = 20.0f;
    private DataAreaStyle mDataAreaStyle = DataAreaStyle.STROKE;
    private int mDataChildID = -1;
    private int mDataSeriesID = -1;
    protected LineStyle mLineStyle = LineStyle.SOLID;
    private int mLineWidth = -1;
    private float mRadius = 30.0f;
    private float mRoundRaidus = 15.0f;

    public AnchorDataPoint(int i, int i2, AnchorStyle anchorStyle) {
        this.mDataSeriesID = i;
        this.mDataChildID = i2;
        this.mAnchorStyle = anchorStyle;
    }

    public AnchorDataPoint(int i, AnchorStyle anchorStyle) {
        this.mDataSeriesID = i;
        this.mAnchorStyle = anchorStyle;
    }

    public AnchorStyle getAnchorStyle() {
        return this.mAnchorStyle;
    }

    public void setAnchorStyle(AnchorStyle anchorStyle) {
        this.mAnchorStyle = anchorStyle;
    }

    public int getDataSeriesID() {
        return this.mDataSeriesID;
    }

    public void setDataSeriesID(int i) {
        this.mDataSeriesID = i;
    }

    public int getDataChildID() {
        return this.mDataChildID;
    }

    public void setDataChildID(int i) {
        this.mDataChildID = i;
    }

    public void setAnchor(String str) {
        this.mAnchor = str;
    }

    public String getAnchor() {
        return this.mAnchor;
    }

    public void setRadius(float f) {
        this.mRadius = f;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setTextSize(int i) {
        this.mAnchorTextSize = i;
    }

    public float getTextSize() {
        return (float) this.mAnchorTextSize;
    }

    public void setTextColor(int i) {
        this.mAnchorTextColor = i;
    }

    public int getTextColor() {
        return this.mAnchorTextColor;
    }

    public void setLineWidth(int i) {
        this.mLineWidth = i;
    }

    public int getLineWidth() {
        return this.mLineWidth;
    }

    public void setAlpha(int i) {
        this.mAlpha = i;
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public void setBgColor(int i) {
        this.mBgColor = i;
    }

    public int getBgColor() {
        return this.mBgColor;
    }

    public void setAreaStyle(DataAreaStyle dataAreaStyle) {
        this.mDataAreaStyle = dataAreaStyle;
    }

    public DataAreaStyle getAreaStyle() {
        return this.mDataAreaStyle;
    }

    public void setCapRectAngleWH(float f, float f2) {
        this.mCapRectW = f;
        this.mCapRectH = f2;
    }

    public void setCapRectHeight(float f) {
        this.mCapRectHeight = f;
    }

    public float getCapRectW() {
        return this.mCapRectW;
    }

    public float getCapRectH() {
        return this.mCapRectH;
    }

    public float getCapRectHeight() {
        return this.mCapRectHeight;
    }

    public void setRoundRadius(int i) {
        this.mRoundRaidus = (float) i;
    }

    public float getRoundRadius() {
        return this.mRoundRaidus;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.mLineStyle = lineStyle;
    }

    public LineStyle getLineStyle() {
        return this.mLineStyle;
    }
}
