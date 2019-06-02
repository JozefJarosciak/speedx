package com.mapzen.p091a.p092a.p187b;

import android.location.Location;
import android.os.Build.VERSION;

/* compiled from: SystemClock */
/* renamed from: com.mapzen.a.a.b.h */
public class C4223h implements C4213a {
    /* renamed from: a */
    public long mo5994a() {
        return System.currentTimeMillis();
    }

    /* renamed from: a */
    public static long m16762a(Location location) {
        if (VERSION.SDK_INT >= 17) {
            return location.getElapsedRealtimeNanos();
        }
        return location.getTime() * 1000000;
    }
}
