package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.k */
final class C1157k implements Creator<TrainInfo> {
    C1157k() {
    }

    /* renamed from: a */
    public TrainInfo m4391a(Parcel parcel) {
        return new TrainInfo(parcel);
    }

    /* renamed from: a */
    public TrainInfo[] m4392a(int i) {
        return new TrainInfo[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4391a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4392a(i);
    }
}
