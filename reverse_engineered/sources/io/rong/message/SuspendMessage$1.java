package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class SuspendMessage$1 implements Creator<SuspendMessage> {
    SuspendMessage$1() {
    }

    public SuspendMessage createFromParcel(Parcel parcel) {
        return new SuspendMessage(parcel);
    }

    public SuspendMessage[] newArray(int i) {
        return new SuspendMessage[i];
    }
}
