package com.baidu.mapapi.model;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comapi.util.C1278b;
import com.baidu.platform.comapi.util.CoordTrans;
import com.baidu.platform.comjni.tools.C1290a;
import java.util.List;

public class CoordUtil {
    public static LatLng Coordinate_encryptEx(float f, float f2, String str) {
        return C1278b.m4825a(f, f2, str);
    }

    public static LatLng decodeLocation(String str) {
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(C1278b.m4827a(str)) : C1278b.m4827a(str);
    }

    public static List<LatLng> decodeLocationList(String str) {
        return C1278b.m4832c(str);
    }

    public static List<List<LatLng>> decodeLocationList2D(String str) {
        return C1278b.m4833d(str);
    }

    public static LatLng decodeNodeLocation(String str) {
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(C1278b.m4830b(str)) : C1278b.m4830b(str);
    }

    public static double getDistance(Point point, Point point2) {
        return C1290a.m4963a(point, point2);
    }

    public static int getMCDistanceByOneLatLngAndRadius(LatLng latLng, int i) {
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? C1278b.m4824a(CoordTrans.gcjToBaidu(latLng), i) : C1278b.m4824a(latLng, i);
    }

    public static GeoPoint ll2mc(LatLng latLng) {
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? C1278b.m4828a(CoordTrans.gcjToBaidu(latLng)) : C1278b.m4828a(latLng);
    }

    public static Point ll2point(LatLng latLng) {
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? C1278b.m4831b(CoordTrans.gcjToBaidu(latLng)) : C1278b.m4831b(latLng);
    }

    public static LatLng mc2ll(GeoPoint geoPoint) {
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(C1278b.m4826a(geoPoint)) : C1278b.m4826a(geoPoint);
    }
}
