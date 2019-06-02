package com.beastbikes.android.modules.cycling.p111a;

import ch.qos.logback.core.CoreConstants;
import com.google.android.gms.maps.model.LatLng;

/* compiled from: PointDTOForGoogle */
/* renamed from: com.beastbikes.android.modules.cycling.a.d */
public class C1888d implements C1886b {
    /* renamed from: a */
    private double f8451a;
    /* renamed from: b */
    private double f8452b;

    public C1888d(LatLng latLng) {
        this.f8451a = latLng.latitude;
        this.f8452b = latLng.longitude;
    }

    /* renamed from: a */
    public double mo3263a() {
        return this.f8452b;
    }

    /* renamed from: b */
    public double mo3264b() {
        return this.f8451a;
    }

    public String toString() {
        return "{x=" + this.f8451a + ", y=" + this.f8452b + CoreConstants.CURLY_RIGHT;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C1888d c1888d = (C1888d) obj;
        if (Double.compare(c1888d.f8451a, this.f8451a) != 0) {
            return false;
        }
        if (Double.compare(c1888d.f8452b, this.f8452b) != 0) {
            return false;
        }
        return true;
    }
}
