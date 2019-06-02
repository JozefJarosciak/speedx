package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.g */
final class C1154g implements Creator<RouteNode> {
    C1154g() {
    }

    /* renamed from: a */
    public RouteNode m4385a(Parcel parcel) {
        return new RouteNode(parcel);
    }

    /* renamed from: a */
    public RouteNode[] m4386a(int i) {
        return new RouteNode[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4385a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4386a(i);
    }
}
