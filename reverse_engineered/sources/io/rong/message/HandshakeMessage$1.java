package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class HandshakeMessage$1 implements Creator<HandshakeMessage> {
    HandshakeMessage$1() {
    }

    public HandshakeMessage createFromParcel(Parcel parcel) {
        return new HandshakeMessage(parcel);
    }

    public HandshakeMessage[] newArray(int i) {
        return new HandshakeMessage[i];
    }
}
