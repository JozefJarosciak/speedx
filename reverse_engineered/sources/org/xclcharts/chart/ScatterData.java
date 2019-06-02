package org.xclcharts.chart;

import android.graphics.Paint;
import java.util.List;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.line.PlotDot;

public class ScatterData {
    private float mItemLabelRotateAngle = 0.0f;
    private String mLabel = "";
    private Paint mLabelPaint = null;
    private boolean mLabelVisible = false;
    private PlotDot mPlotDot = null;
    private List<PointD> mPointMap;

    public ScatterData(String str, List<PointD> list, int i, DotStyle dotStyle) {
        setKey(str);
        setDataSet(list);
        getPlotDot().setColor(i);
        getPlotDot().setDotStyle(dotStyle);
    }

    public void setDataSet(List<PointD> list) {
        this.mPointMap = list;
    }

    public List<PointD> getDataSet() {
        return this.mPointMap;
    }

    public void setLabelVisible(boolean z) {
        this.mLabelVisible = z;
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

    public void setKey(String str) {
        this.mLabel = str;
    }

    public String getKey() {
        return this.mLabel;
    }

    public Paint getDotLabelPaint() {
        if (this.mLabelPaint == null) {
            this.mLabelPaint = new Paint(1);
        }
        return this.mLabelPaint;
    }

    public PlotDot getPlotDot() {
        if (this.mPlotDot == null) {
            this.mPlotDot = new PlotDot();
            this.mPlotDot.setDotStyle(DotStyle.DOT);
        }
        return this.mPlotDot;
    }

    public void setDotStyle(DotStyle dotStyle) {
        getPlotDot().setDotStyle(dotStyle);
    }

    public DotStyle getDotStyle() {
        return getPlotDot().getDotStyle();
    }

    public float getItemLabelRotateAngle() {
        return this.mItemLabelRotateAngle;
    }

    public void setItemLabelRotateAngle(float f) {
        this.mItemLabelRotateAngle = f;
    }
}
