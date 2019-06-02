package com.mapbox.mapboxsdk.camera;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.mapbox.mapboxsdk.geometry.LatLng;

class CameraPosition$1 implements Creator<CameraPosition> {
    CameraPosition$1() {
    }

    public CameraPosition createFromParcel(Parcel parcel) {
        double readDouble = parcel.readDouble();
        return new CameraPosition((LatLng) parcel.readParcelable(LatLng.class.getClassLoader()), parcel.readDouble(), parcel.readDouble(), readDouble);
    }

    public CameraPosition[] newArray(int i) {
        return new CameraPosition[i];
    }
}
