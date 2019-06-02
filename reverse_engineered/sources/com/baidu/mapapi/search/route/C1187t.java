package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.t */
final class C1187t implements Creator<WalkingRouteResult> {
    C1187t() {
    }

    /* renamed from: a */
    public WalkingRouteResult m4454a(Parcel parcel) {
        return new WalkingRouteResult(parcel);
    }

    /* renamed from: a */
    public WalkingRouteResult[] m4455a(int i) {
        return new WalkingRouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4454a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4455a(i);
    }
}
