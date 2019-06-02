package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.c */
final class C1170c implements Creator<BikingRouteLine> {
    C1170c() {
    }

    /* renamed from: a */
    public BikingRouteLine m4420a(Parcel parcel) {
        return new BikingRouteLine(parcel);
    }

    /* renamed from: a */
    public BikingRouteLine[] m4421a(int i) {
        return new BikingRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4420a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4421a(i);
    }
}
