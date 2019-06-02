package org.xclcharts.renderer.info;

public class PlotDataInfo {
    public int ID = -1;
    public String Label = "";
    /* renamed from: X */
    public float f18263X = 0.0f;
    /* renamed from: Y */
    public float f18264Y = 0.0f;
    public float labelX = 0.0f;
    public float labelY = 0.0f;

    public PlotDataInfo(float f, float f2, String str) {
        this.f18263X = f;
        this.f18264Y = f2;
        this.Label = str;
        this.labelX = f;
        this.labelY = f2;
    }

    public PlotDataInfo(int i, float f, float f2, String str) {
        this.ID = i;
        this.f18263X = f;
        this.f18264Y = f2;
        this.Label = str;
        this.labelX = f;
        this.labelY = f2;
    }

    public PlotDataInfo(int i, float f, float f2, String str, float f3, float f4) {
        this.ID = i;
        this.f18263X = f;
        this.f18264Y = f2;
        this.Label = str;
        this.labelX = f3;
        this.labelY = f4;
    }

    public float getX() {
        return this.f18263X;
    }

    public void setX(float f) {
        this.f18263X = f;
    }

    public float getY() {
        return this.f18264Y;
    }

    public void setY(float f) {
        this.f18264Y = f;
    }

    public String getLabel() {
        return this.Label;
    }

    public void setLabel(String str) {
        this.Label = str;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int i) {
        this.ID = i;
    }
}
