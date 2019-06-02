package com.baidu.mapapi.search.geocode;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult.AddressComponent;

/* renamed from: com.baidu.mapapi.search.geocode.c */
final class C1164c implements Creator<AddressComponent> {
    C1164c() {
    }

    /* renamed from: a */
    public AddressComponent m4405a(Parcel parcel) {
        return new AddressComponent(parcel);
    }

    /* renamed from: a */
    public AddressComponent[] m4406a(int i) {
        return new AddressComponent[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4405a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4406a(i);
    }
}
