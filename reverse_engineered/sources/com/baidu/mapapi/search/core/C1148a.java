package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.a */
final class C1148a implements Creator<BusInfo> {
    C1148a() {
    }

    /* renamed from: a */
    public BusInfo m4373a(Parcel parcel) {
        return new BusInfo(parcel);
    }

    /* renamed from: a */
    public BusInfo[] m4374a(int i) {
        return new BusInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4373a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4374a(i);
    }
}
