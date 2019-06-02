package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class TextMessage$1 implements Creator<TextMessage> {
    TextMessage$1() {
    }

    public TextMessage createFromParcel(Parcel parcel) {
        return new TextMessage(parcel);
    }

    public TextMessage[] newArray(int i) {
        return new TextMessage[i];
    }
}
