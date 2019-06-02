package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CommandMessage$1 implements Creator<CommandMessage> {
    CommandMessage$1() {
    }

    public CommandMessage createFromParcel(Parcel parcel) {
        return new CommandMessage(parcel);
    }

    public CommandMessage[] newArray(int i) {
        return new CommandMessage[i];
    }
}
