package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.f */
final class C1173f implements Creator<DrivingRouteResult> {
    C1173f() {
    }

    /* renamed from: a */
    public DrivingRouteResult m4426a(Parcel parcel) {
        return new DrivingRouteResult(parcel);
    }

    /* renamed from: a */
    public DrivingRouteResult[] m4427a(int i) {
        return new DrivingRouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4426a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4427a(i);
    }
}
