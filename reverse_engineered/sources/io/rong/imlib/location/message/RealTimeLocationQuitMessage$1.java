package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class RealTimeLocationQuitMessage$1 implements Creator<RealTimeLocationQuitMessage> {
    RealTimeLocationQuitMessage$1() {
    }

    public RealTimeLocationQuitMessage createFromParcel(Parcel parcel) {
        return new RealTimeLocationQuitMessage(parcel);
    }

    public RealTimeLocationQuitMessage[] newArray(int i) {
        return new RealTimeLocationQuitMessage[i];
    }
}
