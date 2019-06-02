package io.rong.imlib.TypingMessage;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class TypingStatusMessage$1 implements Creator<TypingStatusMessage> {
    TypingStatusMessage$1() {
    }

    public TypingStatusMessage createFromParcel(Parcel parcel) {
        return new TypingStatusMessage(parcel);
    }

    public TypingStatusMessage[] newArray(int i) {
        return new TypingStatusMessage[i];
    }
}
