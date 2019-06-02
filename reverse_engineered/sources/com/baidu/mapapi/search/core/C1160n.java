package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.n */
final class C1160n implements Creator<VehicleInfo> {
    C1160n() {
    }

    /* renamed from: a */
    public VehicleInfo m4397a(Parcel parcel) {
        return new VehicleInfo(parcel);
    }

    /* renamed from: a */
    public VehicleInfo[] m4398a(int i) {
        return new VehicleInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4397a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4398a(i);
    }
}
