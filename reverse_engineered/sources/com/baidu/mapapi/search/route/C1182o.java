package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.o */
final class C1182o implements Creator<TransitRouteLine> {
    C1182o() {
    }

    /* renamed from: a */
    public TransitRouteLine m4444a(Parcel parcel) {
        return new TransitRouteLine(parcel);
    }

    /* renamed from: a */
    public TransitRouteLine[] m4445a(int i) {
        return new TransitRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4444a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4445a(i);
    }
}
