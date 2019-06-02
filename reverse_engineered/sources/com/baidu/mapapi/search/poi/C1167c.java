package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.poi.c */
final class C1167c implements Creator<PoiResult> {
    C1167c() {
    }

    /* renamed from: a */
    public PoiResult m4411a(Parcel parcel) {
        return new PoiResult(parcel);
    }

    /* renamed from: a */
    public PoiResult[] m4412a(int i) {
        return new PoiResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4411a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4412a(i);
    }
}
