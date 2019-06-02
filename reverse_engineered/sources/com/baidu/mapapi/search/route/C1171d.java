package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.d */
final class C1171d implements Creator<DrivingRouteLine> {
    C1171d() {
    }

    /* renamed from: a */
    public DrivingRouteLine m4422a(Parcel parcel) {
        return new DrivingRouteLine(parcel);
    }

    /* renamed from: a */
    public DrivingRouteLine[] m4423a(int i) {
        return new DrivingRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4422a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4423a(i);
    }
}
