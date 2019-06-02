package cn.jpush.android.p002a;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/* renamed from: cn.jpush.android.a.h */
final class C0399h implements LocationListener {
    /* renamed from: a */
    final /* synthetic */ C0398g f474a;

    C0399h(C0398g c0398g) {
        this.f474a = c0398g;
    }

    public final void onLocationChanged(Location location) {
        this.f474a.m1018a(location);
    }

    public final void onProviderDisabled(String str) {
        this.f474a.m1018a(null);
        this.f474a.m1022c();
    }

    public final void onProviderEnabled(String str) {
        this.f474a.m1021b();
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
