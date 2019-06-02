package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.n */
final class C1181n implements Creator<SuggestAddrInfo> {
    C1181n() {
    }

    /* renamed from: a */
    public SuggestAddrInfo m4442a(Parcel parcel) {
        return new SuggestAddrInfo(parcel);
    }

    /* renamed from: a */
    public SuggestAddrInfo[] m4443a(int i) {
        return new SuggestAddrInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4442a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4443a(i);
    }
}
