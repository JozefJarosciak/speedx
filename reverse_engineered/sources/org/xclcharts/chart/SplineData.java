package org.xclcharts.chart;

import java.util.List;
import org.xclcharts.renderer.XEnum.DotStyle;

public class SplineData extends LnData {
    private float mItemLabelRotateAngle = 0.0f;
    private List<PointD> mLinePointMap;

    public SplineData(String str, List<PointD> list, int i) {
        setLineKey(str);
        setLineDataSet(list);
        setLineColor(i);
    }

    public SplineData(String str, List<PointD> list, int i, DotStyle dotStyle) {
        setLineKey(str);
        setLineDataSet(list);
        setLineColor(i);
        setDotStyle(dotStyle);
    }

    public void setLineDataSet(List<PointD> list) {
        this.mLinePointMap = list;
    }

    public List<PointD> getLineDataSet() {
        return this.mLinePointMap;
    }

    public float getItemLabelRotateAngle() {
        return this.mItemLabelRotateAngle;
    }

    public void setItemLabelRotateAngle(float f) {
        this.mItemLabelRotateAngle = f;
    }
}
