package org.xclcharts.event.click;

import android.graphics.PointF;

public class PointPosition extends RectPosition {
    protected PointF mPoint = null;

    public PointF getPosition() {
        return this.mPoint;
    }

    public String getPointInfo() {
        if (this.mPoint == null) {
            return "";
        }
        return "x:" + Float.toString(this.mPoint.x) + " y:" + Float.toString(this.mPoint.y);
    }
}
