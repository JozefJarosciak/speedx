package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSTerminateMessage$1 implements Creator<CSTerminateMessage> {
    CSTerminateMessage$1() {
    }

    public CSTerminateMessage createFromParcel(Parcel parcel) {
        return new CSTerminateMessage(parcel);
    }

    public CSTerminateMessage[] newArray(int i) {
        return new CSTerminateMessage[i];
    }
}
