package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.q */
final class C1184q implements Creator<TransitRouteResult> {
    C1184q() {
    }

    /* renamed from: a */
    public TransitRouteResult m4448a(Parcel parcel) {
        return new TransitRouteResult(parcel);
    }

    /* renamed from: a */
    public TransitRouteResult[] m4449a(int i) {
        return new TransitRouteResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4448a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4449a(i);
    }
}
