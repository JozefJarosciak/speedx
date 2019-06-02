package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.l */
final class C1179l implements Creator<MassTransitRouteResult> {
    C1179l() {
    }

    /* renamed from: a */
    public MassTransitRouteResult m4438a(Parcel parcel) {
        return new MassTransitRouteResult(parcel);
    }

    /* renamed from: a */
    public MassTransitRouteResult[] m4439a(int i) {
        return new MassTransitRouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4438a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4439a(i);
    }
}
