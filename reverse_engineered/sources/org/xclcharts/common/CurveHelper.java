package org.xclcharts.common;

import android.graphics.PointF;

public class CurveHelper {
    public static void curve3(PointF pointF, PointF pointF2, PointF pointF3, PointF pointF4, PointF[] pointFArr) {
        PointF center = PointHelper.center(pointF3, pointF);
        PointF center2 = PointHelper.center(pointF, pointF2);
        PointF center3 = PointHelper.center(pointF2, pointF4);
        float distance = PointHelper.distance(pointF3, pointF);
        float distance2 = PointHelper.distance(pointF, pointF2);
        float distance3 = PointHelper.distance(pointF2, pointF4);
        center = PointHelper.percent(center, center2, distance / (distance + distance2));
        center3 = PointHelper.percent(center2, center3, distance2 / (distance2 + distance3));
        pointFArr[0] = PointHelper.translate(center2, pointF.x - center.x, pointF.y - center.y);
        pointFArr[1] = PointHelper.translate(center2, pointF2.x - center3.x, pointF2.y - center3.y);
    }
}
