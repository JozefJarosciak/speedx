package org.xclcharts.chart;

import java.util.List;
import org.xclcharts.renderer.XEnum.DataAreaStyle;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.XEnum.LineStyle;

public class RadarData extends LineData {
    private DataAreaStyle mAreaStyle = DataAreaStyle.FILL;
    private LineStyle mLineStyle = LineStyle.SOLID;

    public RadarData() {
        getPlotLine().setDotStyle(DotStyle.HIDE);
    }

    public RadarData(String str, List<Double> list, int i, DataAreaStyle dataAreaStyle) {
        setLabel(str);
        setLineColor(i);
        setDataSet(list);
        setAreaStyle(dataAreaStyle);
        getPlotLine().setDotStyle(DotStyle.HIDE);
    }

    public void setAreaStyle(DataAreaStyle dataAreaStyle) {
        this.mAreaStyle = dataAreaStyle;
    }

    public DataAreaStyle getAreaStyle() {
        return this.mAreaStyle;
    }

    public LineStyle getLineStyle() {
        return this.mLineStyle;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.mLineStyle = lineStyle;
    }
}
