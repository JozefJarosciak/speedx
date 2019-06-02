package com.baidu.mapapi.search.busline;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.busline.a */
final class C1147a implements Creator<BusLineResult> {
    C1147a() {
    }

    /* renamed from: a */
    public BusLineResult m4367a(Parcel parcel) {
        return new BusLineResult(parcel);
    }

    /* renamed from: a */
    public BusLineResult[] m4368a(int i) {
        return new BusLineResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4367a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4368a(i);
    }
}
