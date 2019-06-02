package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class HasReceivedNotificationMessage$1 implements Creator<HasReceivedNotificationMessage> {
    HasReceivedNotificationMessage$1() {
    }

    public HasReceivedNotificationMessage createFromParcel(Parcel parcel) {
        return new HasReceivedNotificationMessage(parcel);
    }

    public HasReceivedNotificationMessage[] newArray(int i) {
        return new HasReceivedNotificationMessage[i];
    }
}
