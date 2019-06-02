package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.j */
final class C1156j implements Creator<TaxiInfo> {
    C1156j() {
    }

    /* renamed from: a */
    public TaxiInfo m4389a(Parcel parcel) {
        return new TaxiInfo(parcel);
    }

    /* renamed from: a */
    public TaxiInfo[] m4390a(int i) {
        return new TaxiInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4389a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4390a(i);
    }
}
