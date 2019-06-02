package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.location.CoordinateType;

public class CoordinateConverter {
    /* renamed from: a */
    private LatLng f3510a;
    /* renamed from: b */
    private CoordType f3511b;

    public enum CoordType {
        GPS,
        COMMON
    }

    /* renamed from: a */
    private static LatLng m4463a(LatLng latLng) {
        return m4464a(latLng, CoordinateType.WGS84);
    }

    /* renamed from: a */
    private static LatLng m4464a(LatLng latLng, String str) {
        return latLng == null ? null : CoordUtil.Coordinate_encryptEx((float) latLng.longitude, (float) latLng.latitude, str);
    }

    /* renamed from: b */
    private static LatLng m4465b(LatLng latLng) {
        return m4464a(latLng, CoordinateType.GCJ02);
    }

    public LatLng convert() {
        if (this.f3510a == null) {
            return null;
        }
        if (this.f3511b == null) {
            this.f3511b = CoordType.GPS;
        }
        switch (this.f3511b) {
            case COMMON:
                return m4465b(this.f3510a);
            case GPS:
                return m4463a(this.f3510a);
            default:
                return null;
        }
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.f3510a = latLng;
        return this;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.f3511b = coordType;
        return this;
    }
}
