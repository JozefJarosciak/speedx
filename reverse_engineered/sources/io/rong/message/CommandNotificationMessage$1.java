package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CommandNotificationMessage$1 implements Creator<CommandNotificationMessage> {
    CommandNotificationMessage$1() {
    }

    public CommandNotificationMessage createFromParcel(Parcel parcel) {
        return new CommandNotificationMessage(parcel);
    }

    public CommandNotificationMessage[] newArray(int i) {
        return new CommandNotificationMessage[i];
    }
}
