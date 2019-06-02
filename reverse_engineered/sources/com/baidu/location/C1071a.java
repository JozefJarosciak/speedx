package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.location.a */
final class C1071a implements Creator<BDLocation> {
    C1071a() {
    }

    public BDLocation createFromParcel(Parcel parcel) {
        return new BDLocation(parcel);
    }

    public BDLocation[] newArray(int i) {
        return new BDLocation[i];
    }
}
