package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.mapapi.search.route.m */
final class C1180m implements Creator<PlanNode> {
    C1180m() {
    }

    /* renamed from: a */
    public PlanNode m4440a(Parcel parcel) {
        return new PlanNode(parcel);
    }

    /* renamed from: a */
    public PlanNode[] m4441a(int i) {
        return new PlanNode[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4440a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4441a(i);
    }
}
