package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ProfileNotificationMessage$1 implements Creator<ProfileNotificationMessage> {
    ProfileNotificationMessage$1() {
    }

    public ProfileNotificationMessage createFromParcel(Parcel parcel) {
        return new ProfileNotificationMessage(parcel);
    }

    public ProfileNotificationMessage[] newArray(int i) {
        return new ProfileNotificationMessage[i];
    }
}
