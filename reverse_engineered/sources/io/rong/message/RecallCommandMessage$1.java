package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class RecallCommandMessage$1 implements Creator<RecallCommandMessage> {
    RecallCommandMessage$1() {
    }

    public RecallCommandMessage createFromParcel(Parcel parcel) {
        return new RecallCommandMessage(parcel);
    }

    public RecallCommandMessage[] newArray(int i) {
        return new RecallCommandMessage[i];
    }
}
