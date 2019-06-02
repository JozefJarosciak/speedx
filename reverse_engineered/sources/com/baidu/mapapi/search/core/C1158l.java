package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.l */
final class C1158l implements Creator<TransitBaseInfo> {
    C1158l() {
    }

    /* renamed from: a */
    public TransitBaseInfo m4393a(Parcel parcel) {
        return new TransitBaseInfo(parcel);
    }

    /* renamed from: a */
    public TransitBaseInfo[] m4394a(int i) {
        return new TransitBaseInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4393a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4394a(i);
    }
}
