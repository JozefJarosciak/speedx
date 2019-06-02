package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.g */
final class C1174g implements Creator<IndoorRouteLine> {
    C1174g() {
    }

    /* renamed from: a */
    public IndoorRouteLine m4428a(Parcel parcel) {
        return new IndoorRouteLine(parcel);
    }

    /* renamed from: a */
    public IndoorRouteLine[] m4429a(int i) {
        return new IndoorRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4428a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4429a(i);
    }
}
