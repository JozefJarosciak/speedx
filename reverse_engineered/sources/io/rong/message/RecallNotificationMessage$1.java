package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class RecallNotificationMessage$1 implements Creator<RecallNotificationMessage> {
    RecallNotificationMessage$1() {
    }

    public RecallNotificationMessage createFromParcel(Parcel parcel) {
        return new RecallNotificationMessage(parcel);
    }

    public RecallNotificationMessage[] newArray(int i) {
        return new RecallNotificationMessage[i];
    }
}
