package com.baidu.mapapi.search.core;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.core.m */
final class C1159m implements Creator<TransitResultNode> {
    C1159m() {
    }

    /* renamed from: a */
    public TransitResultNode m4395a(Parcel parcel) {
        return new TransitResultNode(parcel);
    }

    /* renamed from: a */
    public TransitResultNode[] m4396a(int i) {
        return new TransitResultNode[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4395a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4396a(i);
    }
}
