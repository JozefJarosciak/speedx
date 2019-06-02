package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.route.MassTransitRouteLine.TransitStep;

/* renamed from: com.baidu.mapapi.search.route.j */
final class C1177j implements Creator<TransitStep> {
    C1177j() {
    }

    /* renamed from: a */
    public TransitStep m4434a(Parcel parcel) {
        return new TransitStep(parcel);
    }

    /* renamed from: a */
    public TransitStep[] m4435a(int i) {
        return new TransitStep[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4434a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4435a(i);
    }
}
