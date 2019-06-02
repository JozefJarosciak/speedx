package org.xclcharts.renderer.line;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import org.xclcharts.renderer.XEnum.DotStyle;

public class PlotLine {
    private Paint mPaintDot = null;
    private Paint mPaintLabel = null;
    private Paint mPaintLine = null;
    private PlotDot mPlotDot = null;

    public PlotLine() {
        if (this.mPlotDot == null) {
            this.mPlotDot = new PlotDot();
        }
    }

    private void initLinePaint() {
        if (this.mPaintLine == null) {
            this.mPaintLine = new Paint();
            this.mPaintLine.setColor(-16776961);
            this.mPaintLine.setAntiAlias(true);
            this.mPaintLine.setStrokeWidth(5.0f);
        }
    }

    private void initLabelPaint() {
        if (this.mPaintLabel == null) {
            this.mPaintLabel = new Paint();
            this.mPaintLabel.setColor(-16776961);
            this.mPaintLabel.setTextSize(18.0f);
            this.mPaintLabel.setTextAlign(Align.CENTER);
            this.mPaintLabel.setAntiAlias(true);
        }
    }

    public Paint getLinePaint() {
        initLinePaint();
        return this.mPaintLine;
    }

    public Paint getDotLabelPaint() {
        initLabelPaint();
        return this.mPaintLabel;
    }

    public Paint getDotPaint() {
        if (this.mPaintDot == null) {
            this.mPaintDot = new Paint();
            this.mPaintDot.setColor(-16776961);
            this.mPaintDot.setAntiAlias(true);
            this.mPaintDot.setStrokeWidth(5.0f);
        }
        return this.mPaintDot;
    }

    public PlotDot getPlotDot() {
        return this.mPlotDot;
    }

    public void setDotStyle(DotStyle dotStyle) {
        this.mPlotDot.setDotStyle(dotStyle);
    }

    public DotStyle getDotStyle() {
        return this.mPlotDot.getDotStyle();
    }
}
