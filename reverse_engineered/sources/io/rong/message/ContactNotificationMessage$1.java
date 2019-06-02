package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ContactNotificationMessage$1 implements Creator<ContactNotificationMessage> {
    ContactNotificationMessage$1() {
    }

    public ContactNotificationMessage createFromParcel(Parcel parcel) {
        return new ContactNotificationMessage(parcel);
    }

    public ContactNotificationMessage[] newArray(int i) {
        return new ContactNotificationMessage[i];
    }
}
