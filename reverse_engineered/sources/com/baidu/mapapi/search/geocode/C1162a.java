package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.geocode.a */
final class C1162a implements Creator<GeoCodeResult> {
    C1162a() {
    }

    /* renamed from: a */
    public GeoCodeResult m4401a(Parcel parcel) {
        return new GeoCodeResult(parcel);
    }

    /* renamed from: a */
    public GeoCodeResult[] m4402a(int i) {
        return new GeoCodeResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4401a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4402a(i);
    }
}
