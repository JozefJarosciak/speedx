package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSChangeModeResponseMessage$1 implements Creator<CSChangeModeResponseMessage> {
    CSChangeModeResponseMessage$1() {
    }

    public CSChangeModeResponseMessage createFromParcel(Parcel parcel) {
        return new CSChangeModeResponseMessage(parcel);
    }

    public CSChangeModeResponseMessage[] newArray(int i) {
        return new CSChangeModeResponseMessage[i];
    }
}
