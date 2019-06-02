package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSUpdateMessage$1 implements Creator<CSUpdateMessage> {
    CSUpdateMessage$1() {
    }

    public CSUpdateMessage createFromParcel(Parcel parcel) {
        return new CSUpdateMessage(parcel);
    }

    public CSUpdateMessage[] newArray(int i) {
        return new CSUpdateMessage[i];
    }
}
