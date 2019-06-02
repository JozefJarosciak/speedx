package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.poi.a */
final class C1165a implements Creator<PoiDetailResult> {
    C1165a() {
    }

    /* renamed from: a */
    public PoiDetailResult m4407a(Parcel parcel) {
        return new PoiDetailResult(parcel);
    }

    /* renamed from: a */
    public PoiDetailResult[] m4408a(int i) {
        return new PoiDetailResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4407a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4408a(i);
    }
}
