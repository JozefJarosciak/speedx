package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class RealTimeLocationJoinMessage$1 implements Creator<RealTimeLocationJoinMessage> {
    RealTimeLocationJoinMessage$1() {
    }

    public RealTimeLocationJoinMessage createFromParcel(Parcel parcel) {
        return new RealTimeLocationJoinMessage(parcel);
    }

    public RealTimeLocationJoinMessage[] newArray(int i) {
        return new RealTimeLocationJoinMessage[i];
    }
}
