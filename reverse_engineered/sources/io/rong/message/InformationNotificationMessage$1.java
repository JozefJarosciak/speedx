package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class InformationNotificationMessage$1 implements Creator<InformationNotificationMessage> {
    InformationNotificationMessage$1() {
    }

    public InformationNotificationMessage createFromParcel(Parcel parcel) {
        return new InformationNotificationMessage(parcel);
    }

    public InformationNotificationMessage[] newArray(int i) {
        return new InformationNotificationMessage[i];
    }
}
