package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;

/* renamed from: com.baidu.mapapi.search.route.p */
final class C1183p implements Creator<TransitStep> {
    C1183p() {
    }

    /* renamed from: a */
    public TransitStep m4446a(Parcel parcel) {
        return new TransitStep(parcel);
    }

    /* renamed from: a */
    public TransitStep[] m4447a(int i) {
        return new TransitStep[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4446a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4447a(i);
    }
}
