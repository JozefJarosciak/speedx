package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class RealTimeLocationStartMessage$1 implements Creator<RealTimeLocationStartMessage> {
    RealTimeLocationStartMessage$1() {
    }

    public RealTimeLocationStartMessage createFromParcel(Parcel parcel) {
        return new RealTimeLocationStartMessage(parcel);
    }

    public RealTimeLocationStartMessage[] newArray(int i) {
        return new RealTimeLocationStartMessage[i];
    }
}
