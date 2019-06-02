package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.poi.b */
final class C1166b implements Creator<PoiIndoorResult> {
    C1166b() {
    }

    /* renamed from: a */
    public PoiIndoorResult m4409a(Parcel parcel) {
        return new PoiIndoorResult(parcel);
    }

    /* renamed from: a */
    public PoiIndoorResult[] m4410a(int i) {
        return new PoiIndoorResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4409a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4410a(i);
    }
}
