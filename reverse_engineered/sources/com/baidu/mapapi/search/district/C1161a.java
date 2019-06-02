package com.baidu.mapapi.search.district;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.district.a */
final class C1161a implements Creator<DistrictResult> {
    C1161a() {
    }

    /* renamed from: a */
    public DistrictResult m4399a(Parcel parcel) {
        return new DistrictResult(parcel);
    }

    /* renamed from: a */
    public DistrictResult[] m4400a(int i) {
        return new DistrictResult[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4399a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4400a(i);
    }
}
