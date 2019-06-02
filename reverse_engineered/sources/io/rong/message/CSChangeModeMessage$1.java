package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSChangeModeMessage$1 implements Creator<CSChangeModeMessage> {
    CSChangeModeMessage$1() {
    }

    public CSChangeModeMessage createFromParcel(Parcel parcel) {
        return new CSChangeModeMessage(parcel);
    }

    public CSChangeModeMessage[] newArray(int i) {
        return new CSChangeModeMessage[i];
    }
}
