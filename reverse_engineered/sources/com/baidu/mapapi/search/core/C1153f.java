package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.f */
final class C1153f implements Creator<PriceInfo> {
    C1153f() {
    }

    /* renamed from: a */
    public PriceInfo m4383a(Parcel parcel) {
        return new PriceInfo(parcel);
    }

    /* renamed from: a */
    public PriceInfo[] m4384a(int i) {
        return new PriceInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4383a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4384a(i);
    }
}
