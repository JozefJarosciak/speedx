package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class DiscussionNotificationMessage$1 implements Creator<DiscussionNotificationMessage> {
    DiscussionNotificationMessage$1() {
    }

    public DiscussionNotificationMessage createFromParcel(Parcel parcel) {
        return new DiscussionNotificationMessage(parcel);
    }

    public DiscussionNotificationMessage[] newArray(int i) {
        return new DiscussionNotificationMessage[i];
    }
}
