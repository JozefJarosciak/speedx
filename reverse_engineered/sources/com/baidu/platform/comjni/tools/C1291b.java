package com.baidu.platform.comjni.tools;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.platform.comjni.tools.b */
final class C1291b implements Creator<ParcelItem> {
    C1291b() {
    }

    /* renamed from: a */
    public ParcelItem m4968a(Parcel parcel) {
        ParcelItem parcelItem = new ParcelItem();
        parcelItem.setBundle(parcel.readBundle());
        return parcelItem;
    }

    /* renamed from: a */
    public ParcelItem[] m4969a(int i) {
        return new ParcelItem[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4968a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4969a(i);
    }
}
