package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class VoiceMessage$1 implements Creator<VoiceMessage> {
    VoiceMessage$1() {
    }

    public VoiceMessage createFromParcel(Parcel parcel) {
        return new VoiceMessage(parcel);
    }

    public VoiceMessage[] newArray(int i) {
        return new VoiceMessage[i];
    }
}
