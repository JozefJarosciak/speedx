package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class Message$1 implements Creator<Message> {
    Message$1() {
    }

    public Message createFromParcel(Parcel parcel) {
        return new Message(parcel);
    }

    public Message[] newArray(int i) {
        return new Message[i];
    }
}
