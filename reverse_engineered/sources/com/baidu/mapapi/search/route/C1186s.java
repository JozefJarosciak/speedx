package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.route.WalkingRouteLine.WalkingStep;

/* renamed from: com.baidu.mapapi.search.route.s */
final class C1186s implements Creator<WalkingStep> {
    C1186s() {
    }

    /* renamed from: a */
    public WalkingStep m4452a(Parcel parcel) {
        return new WalkingStep(parcel);
    }

    /* renamed from: a */
    public WalkingStep[] m4453a(int i) {
        return new WalkingStep[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return m4452a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return m4453a(i);
    }
}
