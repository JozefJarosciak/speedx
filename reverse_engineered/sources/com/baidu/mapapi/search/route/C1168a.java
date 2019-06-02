package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.a */
final class C1168a implements Creator<BikingRouteLine> {
    C1168a() {
    }

    /* renamed from: a */
    public BikingRouteLine m4416a(Parcel parcel) {
        return new BikingRouteLine(parcel);
    }

    /* renamed from: a */
    public BikingRouteLine[] m4417a(int i) {
        return new BikingRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4416a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4417a(i);
    }
}
