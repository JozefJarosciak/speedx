package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.e */
final class C1152e implements Creator<PoiInfo> {
    C1152e() {
    }

    /* renamed from: a */
    public PoiInfo m4381a(Parcel parcel) {
        return new PoiInfo(parcel);
    }

    /* renamed from: a */
    public PoiInfo[] m4382a(int i) {
        return new PoiInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4381a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4382a(i);
    }
}
