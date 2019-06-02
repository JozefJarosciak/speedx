package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.model.b */
final class C1143b implements Creator<LatLngBounds> {
    C1143b() {
    }

    /* renamed from: a */
    public LatLngBounds m4361a(Parcel parcel) {
        return new LatLngBounds(parcel);
    }

    /* renamed from: a */
    public LatLngBounds[] m4362a(int i) {
        return new LatLngBounds[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4361a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4362a(i);
    }
}
