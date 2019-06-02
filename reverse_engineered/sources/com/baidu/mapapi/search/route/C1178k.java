package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.route.MassTransitRouteLine.TransitStep.TrafficCondition;

/* renamed from: com.baidu.mapapi.search.route.k */
final class C1178k implements Creator<TrafficCondition> {
    C1178k() {
    }

    /* renamed from: a */
    public TrafficCondition m4436a(Parcel parcel) {
        return new TrafficCondition(parcel);
    }

    /* renamed from: a */
    public TrafficCondition[] m4437a(int i) {
        return new TrafficCondition[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4436a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4437a(i);
    }
}
