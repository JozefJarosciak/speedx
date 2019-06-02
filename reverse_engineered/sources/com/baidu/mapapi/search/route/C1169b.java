package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.route.BikingRouteLine.BikingStep;

/* renamed from: com.baidu.mapapi.search.route.b */
final class C1169b implements Creator<BikingStep> {
    C1169b() {
    }

    /* renamed from: a */
    public BikingStep m4418a(Parcel parcel) {
        return new BikingStep(parcel);
    }

    /* renamed from: a */
    public BikingStep[] m4419a(int i) {
        return new BikingStep[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4418a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4419a(i);
    }
}
