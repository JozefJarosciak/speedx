package com.beastbikes.android.utils.polyline;

import com.alibaba.fastjson.asm.Opcodes;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class Point implements Serializable {
    private static final long serialVersionUID = 1;
    private final double lat;
    private final double lng;

    public Point(double d, double d2) {
        this.lat = d;
        this.lng = d2;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLng() {
        return this.lng;
    }

    public String toString() {
        return "(" + this.lat + ", " + this.lng + ")";
    }

    public static String toGeoJSON(List<Point> list) {
        StringBuilder stringBuilder = new StringBuilder("[");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            stringBuilder.append(toGeoJSON((Point) it.next()));
            if (it.hasNext()) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String toGeoJSON(Point point) {
        return "[" + point.getLng() + "," + point.getLat() + "]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point point = (Point) obj;
        if (Math.abs(point.getLat() - this.lat) > 0.001d || Math.abs(point.getLng() - this.lng) > 0.001d) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((int) (Double.doubleToLongBits(this.lat) ^ (Double.doubleToLongBits(this.lat) >>> 32))) + Opcodes.INVOKEINTERFACE) * 37) + ((int) (Double.doubleToLongBits(this.lng) ^ (Double.doubleToLongBits(this.lng) >>> 32)));
    }
}
