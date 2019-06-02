package com.mapbox.mapboxsdk.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class MapboxMapOptions$1 implements Creator<MapboxMapOptions> {
    MapboxMapOptions$1() {
    }

    public MapboxMapOptions createFromParcel(Parcel parcel) {
        return new MapboxMapOptions(parcel, null);
    }

    public MapboxMapOptions[] newArray(int i) {
        return new MapboxMapOptions[i];
    }
}
