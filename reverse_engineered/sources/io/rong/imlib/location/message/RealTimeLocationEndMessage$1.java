package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class RealTimeLocationEndMessage$1 implements Creator<RealTimeLocationEndMessage> {
    RealTimeLocationEndMessage$1() {
    }

    public RealTimeLocationEndMessage createFromParcel(Parcel parcel) {
        return new RealTimeLocationEndMessage(parcel);
    }

    public RealTimeLocationEndMessage[] newArray(int i) {
        return new RealTimeLocationEndMessage[i];
    }
}
