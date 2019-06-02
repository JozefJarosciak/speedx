package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class LocationMessage$1 implements Creator<LocationMessage> {
    LocationMessage$1() {
    }

    public LocationMessage createFromParcel(Parcel parcel) {
        return new LocationMessage(parcel);
    }

    public LocationMessage[] newArray(int i) {
        return new LocationMessage[i];
    }
}
