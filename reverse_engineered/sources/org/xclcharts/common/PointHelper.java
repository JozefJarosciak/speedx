package org.xclcharts.common;

import android.graphics.PointF;

public class PointHelper {
    public static PointF center(PointF pointF, PointF pointF2) {
        return new PointF((pointF.x + pointF2.x) / 2.0f, (pointF.y + pointF2.y) / 2.0f);
    }

    public static float distance(PointF pointF, PointF pointF2) {
        return (float) Math.hypot((double) Math.abs(pointF2.x - pointF.x), (double) Math.abs(pointF2.y - pointF.y));
    }

    public static PointF translate(PointF pointF, float f, float f2) {
        return new PointF(pointF.x + f, pointF.y + f2);
    }

    public static PointF percent(PointF pointF, PointF pointF2, float f) {
        return percent(pointF, f, pointF2, f);
    }

    public static PointF percent(PointF pointF, float f, PointF pointF2, float f2) {
        return new PointF(((pointF2.x - pointF.x) * f) + pointF.x, ((pointF2.y - pointF.y) * f2) + pointF.y);
    }
}
