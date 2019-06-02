package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.model.c */
final class C1144c implements Creator<ParcelItem> {
    C1144c() {
    }

    /* renamed from: a */
    public ParcelItem m4363a(Parcel parcel) {
        ParcelItem parcelItem = new ParcelItem();
        parcelItem.setBundle(parcel.readBundle());
        return parcelItem;
    }

    /* renamed from: a */
    public ParcelItem[] m4364a(int i) {
        return new ParcelItem[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4363a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4364a(i);
    }
}
