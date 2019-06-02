package com.mapbox.mapboxsdk.geometry;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class LatLng$1 implements Creator<LatLng> {
    LatLng$1() {
    }

    public LatLng createFromParcel(Parcel parcel) {
        return new LatLng(parcel);
    }

    public LatLng[] newArray(int i) {
        return new LatLng[i];
    }
}
