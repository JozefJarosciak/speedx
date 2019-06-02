package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import java.util.List;

public class SpatialRelationUtil {
    /* renamed from: a */
    private static LatLng m4466a(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
        GeoPoint ll2mc2 = CoordUtil.ll2mc(latLng2);
        GeoPoint ll2mc3 = CoordUtil.ll2mc(latLng3);
        double sqrt = Math.sqrt(((ll2mc.getLongitudeE6() - ll2mc.getLongitudeE6()) * (ll2mc2.getLongitudeE6() - ll2mc.getLongitudeE6())) + ((ll2mc2.getLatitudeE6() - ll2mc.getLatitudeE6()) * (ll2mc2.getLatitudeE6() - ll2mc.getLatitudeE6())));
        double latitudeE6 = (((ll2mc3.getLatitudeE6() - ll2mc.getLatitudeE6()) * (ll2mc2.getLatitudeE6() - ll2mc.getLatitudeE6())) + ((ll2mc2.getLongitudeE6() - ll2mc.getLongitudeE6()) * (ll2mc3.getLongitudeE6() - ll2mc.getLongitudeE6()))) / (sqrt * sqrt);
        return CoordUtil.mc2ll(new GeoPoint(((ll2mc2.getLatitudeE6() - ll2mc.getLatitudeE6()) * latitudeE6) + ll2mc.getLatitudeE6(), ll2mc.getLongitudeE6() + ((ll2mc2.getLongitudeE6() - ll2mc.getLongitudeE6()) * latitudeE6)));
    }

    public static LatLng getNearestPointFromLine(List<LatLng> list, LatLng latLng) {
        if (list == null || list.size() == 0 || latLng == null) {
            return null;
        }
        int i = 0;
        LatLng latLng2 = null;
        while (i < list.size() - 1) {
            LatLng a = m4466a((LatLng) list.get(i), (LatLng) list.get(i + 1), latLng);
            LatLng latLng3 = ((a.latitude - ((LatLng) list.get(i)).latitude) * (a.latitude - ((LatLng) list.get(i + 1)).latitude) > 0.0d || (a.longitude - ((LatLng) list.get(i)).longitude) * (a.longitude - ((LatLng) list.get(i + 1)).longitude) > 0.0d) ? DistanceUtil.getDistance(latLng, (LatLng) list.get(i)) < DistanceUtil.getDistance(latLng, (LatLng) list.get(i + 1)) ? (LatLng) list.get(i) : (LatLng) list.get(i + 1) : a;
            if (latLng2 != null && DistanceUtil.getDistance(latLng, r0) >= DistanceUtil.getDistance(latLng, latLng2)) {
                latLng3 = latLng2;
            }
            i++;
            latLng2 = latLng3;
        }
        return latLng2;
    }

    public static boolean isCircleContainsPoint(LatLng latLng, int i, LatLng latLng2) {
        if (latLng == null || i == 0 || latLng2 == null) {
            return false;
        }
        double distance = DistanceUtil.getDistance(latLng, latLng2);
        return distance <= ((double) i) ? distance == ((double) i) ? true : true : false;
    }

    public static boolean isPolygonContainsPoint(List<LatLng> list, LatLng latLng) {
        boolean z = false;
        if (list == null || list.size() == 0 || latLng == null) {
            return false;
        }
        int i = 0;
        while (i < list.size()) {
            if (latLng.longitude == ((LatLng) list.get(i)).longitude && latLng.latitude == ((LatLng) list.get(i)).latitude) {
                return true;
            }
            i++;
        }
        int size = list.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            int i4;
            LatLng latLng2 = (LatLng) list.get(i2);
            LatLng latLng3 = (LatLng) list.get((i2 + 1) % size);
            if (latLng2.latitude == latLng3.latitude) {
                i4 = i3;
            } else if (latLng.latitude < Math.min(latLng2.latitude, latLng3.latitude)) {
                i4 = i3;
            } else if (latLng.latitude > Math.max(latLng2.latitude, latLng3.latitude)) {
                i4 = i3;
            } else {
                double d = latLng2.longitude + (((latLng.latitude - latLng2.latitude) * (latLng3.longitude - latLng2.longitude)) / (latLng3.latitude - latLng2.latitude));
                if (d == latLng.longitude) {
                    return true;
                }
                i4 = d < latLng.longitude ? i3 + 1 : i3;
            }
            i2++;
            i3 = i4;
        }
        if (i3 % 2 == 1) {
            z = true;
        }
        return z;
    }
}
