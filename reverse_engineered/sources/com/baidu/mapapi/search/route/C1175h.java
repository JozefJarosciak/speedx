package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.h */
final class C1175h implements Creator<IndoorRouteResult> {
    C1175h() {
    }

    /* renamed from: a */
    public IndoorRouteResult m4430a(Parcel parcel) {
        return new IndoorRouteResult(parcel);
    }

    /* renamed from: a */
    public IndoorRouteResult[] m4431a(int i) {
        return new IndoorRouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4430a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4431a(i);
    }
}
