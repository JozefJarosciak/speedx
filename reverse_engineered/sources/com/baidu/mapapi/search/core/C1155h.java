package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.h */
final class C1155h implements Creator<RouteStep> {
    C1155h() {
    }

    /* renamed from: a */
    public RouteStep m4387a(Parcel parcel) {
        return new RouteStep(parcel);
    }

    /* renamed from: a */
    public RouteStep[] m4388a(int i) {
        return new RouteStep[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4387a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4388a(i);
    }
}
