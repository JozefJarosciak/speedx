package com.beastbikes.android.utils;

import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.baidu.mapapi.utils.CoordinateConverter.CoordType;

/* compiled from: Gps2GoogleUtil */
/* renamed from: com.beastbikes.android.utils.g */
public class C2558g {
    /* renamed from: a */
    public static LatLng m12840a(LatLng latLng) {
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        coordinateConverter.from(CoordType.GPS);
        coordinateConverter.coord(latLng);
        LatLng convert = coordinateConverter.convert();
        return new LatLng((latLng.latitude * 2.0d) - convert.latitude, (latLng.longitude * 2.0d) - convert.longitude);
    }

    /* renamed from: b */
    public static com.mapbox.mapboxsdk.geometry.LatLng m12845b(LatLng latLng) {
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        coordinateConverter.from(CoordType.GPS);
        coordinateConverter.coord(latLng);
        LatLng convert = coordinateConverter.convert();
        return new com.mapbox.mapboxsdk.geometry.LatLng((latLng.latitude * 2.0d) - convert.latitude, (latLng.longitude * 2.0d) - convert.longitude);
    }

    /* renamed from: a */
    public static com.google.android.gms.maps.model.LatLng m12841a(double d, double d2) {
        if (C2558g.m12847c(d, d2)) {
            return new com.google.android.gms.maps.model.LatLng(d, d2);
        }
        double d3 = C2558g.m12848d(d2 - 105.0d, d - 35.0d);
        double e = C2558g.m12849e(d2 - 105.0d, d - 35.0d);
        double d4 = (d / 180.0d) * 3.141592653589793d;
        double sin = Math.sin(d4);
        sin = 1.0d - (sin * (0.006693421622965943d * sin));
        double sqrt = Math.sqrt(sin);
        return new com.google.android.gms.maps.model.LatLng(d + ((d3 * 180.0d) / ((6335552.717000426d / (sin * sqrt)) * 3.141592653589793d)), ((e * 180.0d) / ((Math.cos(d4) * (6378245.0d / sqrt)) * 3.141592653589793d)) + d2);
    }

    /* renamed from: b */
    public static LatLng m12844b(double d, double d2) {
        if (C2558g.m12847c(d, d2)) {
            return new LatLng(d, d2);
        }
        double d3 = C2558g.m12848d(d2 - 105.0d, d - 35.0d);
        double e = C2558g.m12849e(d2 - 105.0d, d - 35.0d);
        double d4 = (d / 180.0d) * 3.141592653589793d;
        double sin = Math.sin(d4);
        sin = 1.0d - (sin * (0.006693421622965943d * sin));
        double sqrt = Math.sqrt(sin);
        return new LatLng(d + ((d3 * 180.0d) / ((6335552.717000426d / (sin * sqrt)) * 3.141592653589793d)), ((e * 180.0d) / ((Math.cos(d4) * (6378245.0d / sqrt)) * 3.141592653589793d)) + d2);
    }

    /* renamed from: c */
    static boolean m12847c(double d, double d2) {
        if (d2 < 72.004d || d2 > 137.8347d || d < 0.8293d || d > 55.8271d) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    static double m12848d(double d, double d2) {
        return (((((((-100.0d + (2.0d * d)) + (3.0d * d2)) + ((0.2d * d2) * d2)) + ((0.1d * d) * d2)) + (0.2d * Math.sqrt(Math.abs(d)))) + ((((20.0d * Math.sin((6.0d * d) * 3.141592653589793d)) + (20.0d * Math.sin((2.0d * d) * 3.141592653589793d))) * 2.0d) / 3.0d)) + ((((20.0d * Math.sin(3.141592653589793d * d2)) + (40.0d * Math.sin((d2 / 3.0d) * 3.141592653589793d))) * 2.0d) / 3.0d)) + ((((160.0d * Math.sin((d2 / 12.0d) * 3.141592653589793d)) + (320.0d * Math.sin((3.141592653589793d * d2) / 30.0d))) * 2.0d) / 3.0d);
    }

    /* renamed from: e */
    static double m12849e(double d, double d2) {
        return (((((((300.0d + d) + (2.0d * d2)) + ((0.1d * d) * d)) + ((0.1d * d) * d2)) + (0.1d * Math.sqrt(Math.abs(d)))) + ((((20.0d * Math.sin((6.0d * d) * 3.141592653589793d)) + (20.0d * Math.sin((2.0d * d) * 3.141592653589793d))) * 2.0d) / 3.0d)) + ((((20.0d * Math.sin(3.141592653589793d * d)) + (40.0d * Math.sin((d / 3.0d) * 3.141592653589793d))) * 2.0d) / 3.0d)) + ((((150.0d * Math.sin((d / 12.0d) * 3.141592653589793d)) + (300.0d * Math.sin((d / 30.0d) * 3.141592653589793d))) * 2.0d) / 3.0d);
    }

    /* renamed from: f */
    public static com.mapbox.mapboxsdk.geometry.LatLng m12850f(double d, double d2) {
        return C2558g.m12846b(new com.google.android.gms.maps.model.LatLng(d, d2));
    }

    /* renamed from: a */
    public static com.mapbox.mapboxsdk.geometry.LatLng m12842a(com.google.android.gms.maps.model.LatLng latLng) {
        return C2558g.m12846b(latLng);
    }

    /* renamed from: b */
    public static com.mapbox.mapboxsdk.geometry.LatLng m12846b(com.google.android.gms.maps.model.LatLng latLng) {
        if (C2558g.m12847c(latLng.longitude, latLng.latitude)) {
            return new com.mapbox.mapboxsdk.geometry.LatLng(latLng.latitude, latLng.longitude);
        }
        double d = latLng.longitude;
        double d2 = latLng.latitude;
        double d3 = C2558g.m12848d(d - 105.0d, d2 - 35.0d);
        double e = C2558g.m12849e(d - 105.0d, d2 - 35.0d);
        double d4 = (d2 / 180.0d) * 3.141592653589793d;
        double sin = Math.sin(d4);
        sin = 1.0d - (sin * (0.006693421622965943d * sin));
        double sqrt = Math.sqrt(sin);
        return new com.mapbox.mapboxsdk.geometry.LatLng(d2 + ((d3 * 180.0d) / ((6335552.717000426d / (sin * sqrt)) * 3.141592653589793d)), d + ((e * 180.0d) / ((Math.cos(d4) * (6378245.0d / sqrt)) * 3.141592653589793d)));
    }

    /* renamed from: g */
    public static LatLng m12851g(double d, double d2) {
        LatLng latLng = new LatLng(d, d2);
        CoordinateConverter coordinateConverter = new CoordinateConverter();
        coordinateConverter.from(CoordType.GPS);
        coordinateConverter.coord(latLng);
        return coordinateConverter.convert();
    }

    /* renamed from: a */
    public static String m12843a(String str, String str2, String str3) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!(TextUtils.isEmpty(str) || str.equals("null") || str.equals(EnvironmentCompat.MEDIA_UNKNOWN))) {
            stringBuilder.append(str);
        }
        if (!(TextUtils.isEmpty(str2) || str2.equals("null") || str.equals(str2) || str2.equals(EnvironmentCompat.MEDIA_UNKNOWN))) {
            stringBuilder.append(",").append(str2);
        }
        if (!(TextUtils.isEmpty(str3) || str3.equals("null") || str3.equals(EnvironmentCompat.MEDIA_UNKNOWN))) {
            stringBuilder.append(",").append(str3);
        }
        return stringBuilder.toString();
    }
}
