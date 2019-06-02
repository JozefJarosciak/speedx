package org.xclcharts.renderer.plot;

import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.LegendType;
import org.xclcharts.renderer.XEnum.VerticalAlign;

public class PlotLegend {
    protected BorderRender mBorder = new BorderRender();
    protected float mColSpan = 10.0f;
    private HorizontalAlign mHorizontalAlign = HorizontalAlign.LEFT;
    private Paint mKeyPaint = null;
    private LegendType mLegendType = LegendType.ROW;
    protected float mMargin = 10.0f;
    protected float mOffsetX = 0.0f;
    protected float mOffsetY = 0.0f;
    protected float mRowSpan = 10.0f;
    protected boolean mShowBackground = true;
    protected boolean mShowBox = true;
    protected boolean mShowBoxBorder = true;
    private VerticalAlign mVerticalAlign = VerticalAlign.TOP;
    private boolean mVisible = false;

    public void show() {
        this.mVisible = true;
    }

    public void hide() {
        this.mVisible = false;
        if (this.mKeyPaint != null) {
            this.mKeyPaint = null;
        }
    }

    public boolean isShow() {
        return this.mVisible;
    }

    public void hideBox() {
        this.mShowBox = false;
    }

    public void hideBorder() {
        this.mShowBoxBorder = false;
    }

    public void hideBackground() {
        this.mShowBackground = false;
    }

    public void showBox() {
        this.mShowBox = true;
        showBorder();
        showBackground();
    }

    public void showBorder() {
        this.mShowBoxBorder = true;
    }

    public void showBackground() {
        this.mShowBackground = true;
    }

    public Paint getPaint() {
        if (this.mKeyPaint == null) {
            this.mKeyPaint = new Paint();
            this.mKeyPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mKeyPaint.setAntiAlias(true);
            this.mKeyPaint.setTextSize(15.0f);
        }
        return this.mKeyPaint;
    }

    public void setLabelMargin(float f) {
        this.mMargin = f;
    }

    public void setRowSpan(float f) {
        this.mRowSpan = f;
    }

    public void setColSpan(float f) {
        this.mColSpan = f;
    }

    public float getLabelMargin() {
        return this.mMargin;
    }

    public void setOffsetX(float f) {
        this.mOffsetX = f;
    }

    public void setOffsetY(float f) {
        this.mOffsetY = f;
    }

    public void setType(LegendType legendType) {
        this.mLegendType = legendType;
    }

    public LegendType getType() {
        return this.mLegendType;
    }

    public void setHorizontalAlign(HorizontalAlign horizontalAlign) {
        this.mHorizontalAlign = horizontalAlign;
    }

    public HorizontalAlign getHorizontalAlign() {
        return this.mHorizontalAlign;
    }

    public void setVerticalAlign(VerticalAlign verticalAlign) {
        this.mVerticalAlign = verticalAlign;
    }

    public VerticalAlign getVerticalAlign() {
        return this.mVerticalAlign;
    }

    public Border getBox() {
        return this.mBorder;
    }
}
