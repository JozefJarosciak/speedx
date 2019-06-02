package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import org.xclcharts.renderer.XEnum.LabelBoxStyle;
import org.xclcharts.renderer.XEnum.RectType;

public class PlotLabel {
    protected final int DEF_BOX_BG_ALPHA = 100;
    protected BorderRender mBorder = null;
    protected LabelBoxStyle mLabelBoxStyle = LabelBoxStyle.CAPRECT;
    protected float mMargin = 5.0f;
    protected float mOffsetX = 0.0f;
    protected float mOffsetY = 0.0f;
    protected float mRadius = 0.0f;
    protected float mScale = 0.2f;
    protected boolean mShowBackground = true;
    protected boolean mShowBoxBorder = true;

    public Border getBox() {
        initBox();
        return this.mBorder;
    }

    protected void initBox() {
        if (this.mBorder == null) {
            this.mBorder = new BorderRender();
            this.mBorder.setBorderRectType(RectType.RECT);
            this.mBorder.getBackgroundPaint().setColor(Color.rgb(240, 255, 112));
            this.mBorder.getBackgroundPaint().setAlpha(100);
        }
    }

    public void hideBorder() {
        this.mShowBoxBorder = false;
    }

    public void hideBackground() {
        this.mShowBackground = false;
    }

    public void showBorder() {
        this.mShowBoxBorder = true;
    }

    public void showBackground() {
        this.mShowBackground = true;
    }

    public void setCapBoxAngleHeight(float f) {
        this.mScale = f;
    }

    public void setCircleBoxRadius(float f) {
        this.mRadius = f;
    }

    public void setLabelBoxStyle(LabelBoxStyle labelBoxStyle) {
        this.mLabelBoxStyle = labelBoxStyle;
        if (LabelBoxStyle.TEXT == this.mLabelBoxStyle) {
            hideBorder();
            hideBackground();
        } else if (LabelBoxStyle.CIRCLE == this.mLabelBoxStyle) {
            hideBorder();
            showBackground();
        } else {
            showBorder();
            showBackground();
        }
    }

    public void setOffsetX(float f) {
        this.mOffsetX = f;
    }

    public void setOffsetY(float f) {
        this.mOffsetY = f;
    }

    public void setMargin(float f) {
        this.mMargin = f;
    }

    public float getMargin() {
        return this.mMargin;
    }

    public boolean drawLabel(Canvas canvas, Paint paint, String str, float f, float f2, float f3) {
        return true;
    }

    public boolean drawLabel(Canvas canvas, Paint paint, String str, float f, float f2, float f3, int i) {
        return true;
    }
}
