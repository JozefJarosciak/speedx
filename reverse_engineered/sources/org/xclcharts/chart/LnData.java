package org.xclcharts.chart;

import android.graphics.Paint;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.XEnum.LineStyle;
import org.xclcharts.renderer.line.PlotLine;
import org.xclcharts.renderer.plot.PlotLabel;
import org.xclcharts.renderer.plot.PlotLabelRender;

public class LnData {
    private String mLabel;
    private boolean mLabelVisible;
    private LineStyle mLineStyle;
    private PlotLabelRender mPlotLabel;
    private PlotLine mPlotLine;

    public LnData() {
        this.mLabelVisible = false;
        this.mPlotLine = null;
        this.mLineStyle = LineStyle.SOLID;
        this.mPlotLabel = null;
        this.mPlotLine = new PlotLine();
    }

    public void setLabelVisible(boolean z) {
        this.mLabelVisible = z;
        getLabelOptions().setOffsetY(15.0f);
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

    public PlotLine getPlotLine() {
        return this.mPlotLine;
    }

    public void setLineColor(int i) {
        this.mPlotLine.getLinePaint().setColor(i);
        this.mPlotLine.getDotLabelPaint().setColor(i);
        this.mPlotLine.getDotPaint().setColor(i);
    }

    public int getLineColor() {
        return this.mPlotLine.getLinePaint().getColor();
    }

    public void setDotStyle(DotStyle dotStyle) {
        this.mPlotLine.setDotStyle(dotStyle);
    }

    public DotStyle getDotStyle() {
        return this.mPlotLine.getDotStyle();
    }

    public void setLineKey(String str) {
        this.mLabel = str;
    }

    public String getLineKey() {
        return this.mLabel;
    }

    public Paint getDotLabelPaint() {
        return this.mPlotLine.getDotLabelPaint();
    }

    public Paint getLinePaint() {
        return this.mPlotLine.getLinePaint();
    }

    public Paint getDotPaint() {
        return this.mPlotLine.getDotPaint();
    }

    public void setDotRadius(int i) {
        this.mPlotLine.getPlotDot().setDotRadius((float) i);
    }

    public LineStyle getLineStyle() {
        return this.mLineStyle;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.mLineStyle = lineStyle;
    }

    public PlotLabel getLabelOptions() {
        if (this.mPlotLabel == null) {
            this.mPlotLabel = new PlotLabelRender();
        }
        return this.mPlotLabel;
    }
}
