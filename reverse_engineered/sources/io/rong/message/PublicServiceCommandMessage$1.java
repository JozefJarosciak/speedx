package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class PublicServiceCommandMessage$1 implements Creator<PublicServiceCommandMessage> {
    PublicServiceCommandMessage$1() {
    }

    public PublicServiceCommandMessage createFromParcel(Parcel parcel) {
        return new PublicServiceCommandMessage(parcel);
    }

    public PublicServiceCommandMessage[] newArray(int i) {
        return new PublicServiceCommandMessage[i];
    }
}
