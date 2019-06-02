package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.d */
final class C1151d implements Creator<PlaneInfo> {
    C1151d() {
    }

    /* renamed from: a */
    public PlaneInfo m4379a(Parcel parcel) {
        return new PlaneInfo(parcel);
    }

    /* renamed from: a */
    public PlaneInfo[] m4380a(int i) {
        return new PlaneInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4379a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4380a(i);
    }
}
