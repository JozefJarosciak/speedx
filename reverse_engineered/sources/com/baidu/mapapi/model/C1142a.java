package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.model.a */
final class C1142a implements Creator<LatLng> {
    C1142a() {
    }

    /* renamed from: a */
    public LatLng m4359a(Parcel parcel) {
        return new LatLng(parcel);
    }

    /* renamed from: a */
    public LatLng[] m4360a(int i) {
        return new LatLng[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4359a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4360a(i);
    }
}
