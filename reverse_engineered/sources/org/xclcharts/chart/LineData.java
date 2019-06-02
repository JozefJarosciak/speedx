package org.xclcharts.chart;

import java.util.List;
import org.xclcharts.renderer.XEnum.DotStyle;

public class LineData extends LnData {
    private float mItemLabelRotateAngle = 0.0f;
    private List<Double> mLinePoint;

    public LineData(String str, List<Double> list, int i) {
        setLabel(str);
        setLineKey(str);
        setDataSet(list);
        setLineColor(i);
    }

    public LineData(String str, List<Double> list, int i, DotStyle dotStyle) {
        setLabel(str);
        setLineKey(str);
        setLineColor(i);
        setDataSet(list);
        setDotStyle(dotStyle);
    }

    public void setDataSet(List<Double> list) {
        this.mLinePoint = list;
    }

    public List<Double> getLinePoint() {
        return this.mLinePoint;
    }

    public float getItemLabelRotateAngle() {
        return this.mItemLabelRotateAngle;
    }

    public void setItemLabelRotateAngle(float f) {
        this.mItemLabelRotateAngle = f;
    }
}
