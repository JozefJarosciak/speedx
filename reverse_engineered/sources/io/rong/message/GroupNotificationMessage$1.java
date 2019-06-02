package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class GroupNotificationMessage$1 implements Creator<GroupNotificationMessage> {
    GroupNotificationMessage$1() {
    }

    public GroupNotificationMessage createFromParcel(Parcel parcel) {
        return new GroupNotificationMessage(parcel);
    }

    public GroupNotificationMessage[] newArray(int i) {
        return new GroupNotificationMessage[i];
    }
}
