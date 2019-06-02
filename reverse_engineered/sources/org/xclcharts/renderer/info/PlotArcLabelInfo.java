package org.xclcharts.renderer.info;

import android.graphics.PointF;

public class PlotArcLabelInfo extends PlotDataInfo {
    public float CurrentAngle = 0.0f;
    public float OffsetAngle = 0.0f;
    public float Radius = 0.0f;
    private PointF mLabelPointF = null;

    public PlotArcLabelInfo(int i, float f, float f2, float f3, float f4, float f5) {
        this.ID = i;
        this.X = f;
        this.Y = f2;
        this.Radius = f3;
        this.OffsetAngle = f4;
        this.CurrentAngle = f5;
    }

    public float getRadius() {
        return this.Radius;
    }

    public void setRadius(float f) {
        this.Radius = f;
    }

    public float getOffsetAngle() {
        return this.OffsetAngle;
    }

    public void setOffsetAngle(float f) {
        this.OffsetAngle = f;
    }

    public float getCurrentAngle() {
        return this.CurrentAngle;
    }

    public void setCurrentAngle(float f) {
        this.CurrentAngle = f;
    }

    public PointF getLabelPointF() {
        return this.mLabelPointF;
    }

    public void setLabelPointF(PointF pointF) {
        this.mLabelPointF = pointF;
    }
}
