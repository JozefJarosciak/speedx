package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.i */
final class C1176i implements Creator<MassTransitRouteLine> {
    C1176i() {
    }

    /* renamed from: a */
    public MassTransitRouteLine m4432a(Parcel parcel) {
        return new MassTransitRouteLine(parcel);
    }

    /* renamed from: a */
    public MassTransitRouteLine[] m4433a(int i) {
        return new MassTransitRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4432a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4433a(i);
    }
}
