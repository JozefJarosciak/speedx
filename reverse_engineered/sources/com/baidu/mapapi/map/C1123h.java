package com.baidu.mapapi.map;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.map.h */
final class C1123h implements Creator<MapStatus> {
    C1123h() {
    }

    /* renamed from: a */
    public MapStatus m4288a(Parcel parcel) {
        return new MapStatus(parcel);
    }

    /* renamed from: a */
    public MapStatus[] m4289a(int i) {
        return new MapStatus[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4288a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4289a(i);
    }
}
