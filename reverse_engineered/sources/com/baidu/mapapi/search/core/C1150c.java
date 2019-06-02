package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.c */
final class C1150c implements Creator<CoachInfo> {
    C1150c() {
    }

    /* renamed from: a */
    public CoachInfo m4377a(Parcel parcel) {
        return new CoachInfo(parcel);
    }

    /* renamed from: a */
    public CoachInfo[] m4378a(int i) {
        return new CoachInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4377a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4378a(i);
    }
}
