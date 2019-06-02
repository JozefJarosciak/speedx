package com.baidu.mapapi.model.inner;

public class GeoPoint {
    /* renamed from: a */
    private double f3291a;
    /* renamed from: b */
    private double f3292b;

    public GeoPoint(double d, double d2) {
        this.f3291a = d;
        this.f3292b = d2;
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        boolean z = this.f3291a == ((GeoPoint) obj).f3291a && this.f3292b == ((GeoPoint) obj).f3292b;
        return z;
    }

    public double getLatitudeE6() {
        return this.f3291a;
    }

    public double getLongitudeE6() {
        return this.f3292b;
    }

    public void setLatitudeE6(double d) {
        this.f3291a = d;
    }

    public void setLongitudeE6(double d) {
        this.f3292b = d;
    }

    public String toString() {
        return "GeoPoint: Latitude: " + this.f3291a + ", Longitude: " + this.f3292b;
    }
}
