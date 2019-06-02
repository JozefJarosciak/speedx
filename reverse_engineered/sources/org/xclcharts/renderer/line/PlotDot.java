package org.xclcharts.renderer.line;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import org.xclcharts.renderer.XEnum.DotStyle;

public class PlotDot {
    private int mAlpha = 255;
    private int mColor = ViewCompat.MEASURED_STATE_MASK;
    protected DotStyle mDotStyle = DotStyle.DOT;
    private float mRadius = 10.0f;
    private int mRing2InnerColor = SupportMenu.CATEGORY_MASK;
    private int mRingInnerColor = -1;

    public void setColor(int i) {
        this.mColor = i;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setRingInnerColor(int i) {
        this.mRingInnerColor = i;
    }

    public void setRing2InnerColor(int i) {
        this.mRing2InnerColor = i;
    }

    public int getRingInnerColor() {
        return this.mRingInnerColor;
    }

    public int getRing2InnerColor() {
        return this.mRing2InnerColor;
    }

    public void setDotStyle(DotStyle dotStyle) {
        this.mDotStyle = dotStyle;
    }

    public DotStyle getDotStyle() {
        return this.mDotStyle;
    }

    public void setDotRadius(float f) {
        this.mRadius = f;
    }

    public float getDotRadius() {
        return this.mRadius;
    }

    public void setAlpah(int i) {
        this.mAlpha = i;
    }

    public int getAlpha() {
        return this.mAlpha;
    }
}
