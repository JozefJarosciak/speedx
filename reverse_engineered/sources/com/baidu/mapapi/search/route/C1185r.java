package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.r */
final class C1185r implements Creator<WalkingRouteLine> {
    C1185r() {
    }

    /* renamed from: a */
    public WalkingRouteLine m4450a(Parcel parcel) {
        return new WalkingRouteLine(parcel);
    }

    /* renamed from: a */
    public WalkingRouteLine[] m4451a(int i) {
        return new WalkingRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4450a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4451a(i);
    }
}
