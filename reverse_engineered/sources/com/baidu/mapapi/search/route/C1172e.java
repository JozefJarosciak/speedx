package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.route.DrivingRouteLine.DrivingStep;

/* renamed from: com.baidu.mapapi.search.route.e */
final class C1172e implements Creator<DrivingStep> {
    C1172e() {
    }

    /* renamed from: a */
    public DrivingStep m4424a(Parcel parcel) {
        return new DrivingStep(parcel);
    }

    /* renamed from: a */
    public DrivingStep[] m4425a(int i) {
        return new DrivingStep[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4424a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4425a(i);
    }
}
