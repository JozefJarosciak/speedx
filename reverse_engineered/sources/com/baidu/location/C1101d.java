package com.baidu.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;

/* renamed from: com.baidu.location.d */
final class C1101d implements Creator<Poi> {
    C1101d() {
    }

    public Poi createFromParcel(Parcel parcel) {
        return new Poi(parcel.readString(), parcel.readString(), parcel.readDouble());
    }

    public Poi[] newArray(int i) {
        return new Poi[i];
    }
}
