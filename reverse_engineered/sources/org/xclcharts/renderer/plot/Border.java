package org.xclcharts.renderer.plot;

import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import org.xclcharts.renderer.XEnum.LineStyle;
import org.xclcharts.renderer.XEnum.RectType;

public class Border {
    protected static final int mBorderSpadding = 5;
    private LineStyle mLineStyle = LineStyle.SOLID;
    protected Paint mPaintBackground = null;
    private Paint mPaintBorderLine = null;
    private int mRaidus = 15;
    private RectType mRectType = RectType.ROUNDRECT;

    public Paint getLinePaint() {
        if (this.mPaintBorderLine == null) {
            this.mPaintBorderLine = new Paint();
            this.mPaintBorderLine.setAntiAlias(true);
            this.mPaintBorderLine.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintBorderLine.setStyle(Style.STROKE);
            this.mPaintBorderLine.setStrokeWidth(2.0f);
        }
        return this.mPaintBorderLine;
    }

    public void setBorderLineColor(int i) {
        getLinePaint().setColor(i);
    }

    public void setBorderLineStyle(LineStyle lineStyle) {
        this.mLineStyle = lineStyle;
    }

    public void setBorderRectType(RectType rectType) {
        this.mRectType = rectType;
    }

    public LineStyle getBorderLineStyle() {
        return this.mLineStyle;
    }

    public RectType getBorderRectType() {
        return this.mRectType;
    }

    public void setRoundRadius(int i) {
        this.mRaidus = i;
    }

    public int getRoundRadius() {
        return this.mRaidus;
    }

    public int getBorderWidth() {
        if (getBorderRectType() == RectType.ROUNDRECT) {
            return 5 + getRoundRadius();
        }
        return 5;
    }

    public Paint getBackgroundPaint() {
        if (this.mPaintBackground == null) {
            this.mPaintBackground = new Paint();
            this.mPaintBackground.setAntiAlias(true);
            this.mPaintBackground.setStyle(Style.FILL);
            this.mPaintBackground.setColor(-1);
            this.mPaintBackground.setAlpha(220);
        }
        return this.mPaintBackground;
    }
}
