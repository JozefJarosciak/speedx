package com.baidu.mapapi.map;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.map.e */
final class C1120e implements Creator<BaiduMapOptions> {
    C1120e() {
    }

    /* renamed from: a */
    public BaiduMapOptions m4281a(Parcel parcel) {
        return new BaiduMapOptions(parcel);
    }

    /* renamed from: a */
    public BaiduMapOptions[] m4282a(int i) {
        return new BaiduMapOptions[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4281a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4282a(i);
    }
}
