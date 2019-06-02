package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.b */
final class C1149b implements Creator<CityInfo> {
    C1149b() {
    }

    /* renamed from: a */
    public CityInfo m4375a(Parcel parcel) {
        return new CityInfo(parcel);
    }

    /* renamed from: a */
    public CityInfo[] m4376a(int i) {
        return new CityInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4375a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4376a(i);
    }
}
