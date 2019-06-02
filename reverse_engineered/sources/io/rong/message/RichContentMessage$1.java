package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class RichContentMessage$1 implements Creator<RichContentMessage> {
    RichContentMessage$1() {
    }

    public RichContentMessage createFromParcel(Parcel parcel) {
        return new RichContentMessage(parcel);
    }

    public RichContentMessage[] newArray(int i) {
        return new RichContentMessage[i];
    }
}
