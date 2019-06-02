package com.beastbikes.android.modules.cycling.p111a;

import ch.qos.logback.core.CoreConstants;
import com.baidu.mapapi.model.LatLng;

/* compiled from: PointDTO */
/* renamed from: com.beastbikes.android.modules.cycling.a.c */
public class C1887c implements C1886b {
    /* renamed from: a */
    private double f8449a;
    /* renamed from: b */
    private double f8450b;

    public C1887c(LatLng latLng) {
        this.f8449a = latLng.latitude;
        this.f8450b = latLng.longitude;
    }

    /* renamed from: a */
    public double mo3263a() {
        return this.f8450b;
    }

    /* renamed from: b */
    public double mo3264b() {
        return this.f8449a;
    }

    public String toString() {
        return "{x=" + this.f8449a + ", y=" + this.f8450b + CoreConstants.CURLY_RIGHT;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C1887c c1887c = (C1887c) obj;
        if (Double.compare(c1887c.f8449a, this.f8449a) != 0) {
            return false;
        }
        if (Double.compare(c1887c.f8450b, this.f8450b) != 0) {
            return false;
        }
        return true;
    }
}
