package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class RealTimeLocationStatusMessage$1 implements Creator<RealTimeLocationStatusMessage> {
    RealTimeLocationStatusMessage$1() {
    }

    public RealTimeLocationStatusMessage createFromParcel(Parcel parcel) {
        return new RealTimeLocationStatusMessage(parcel);
    }

    public RealTimeLocationStatusMessage[] newArray(int i) {
        return new RealTimeLocationStatusMessage[i];
    }
}
