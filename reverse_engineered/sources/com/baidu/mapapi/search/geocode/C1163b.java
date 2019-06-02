package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.geocode.b */
final class C1163b implements Creator<ReverseGeoCodeResult> {
    C1163b() {
    }

    /* renamed from: a */
    public ReverseGeoCodeResult m4403a(Parcel parcel) {
        return new ReverseGeoCodeResult(parcel);
    }

    /* renamed from: a */
    public ReverseGeoCodeResult[] m4404a(int i) {
        return new ReverseGeoCodeResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4403a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4404a(i);
    }
}
