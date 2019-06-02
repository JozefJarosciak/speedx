package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSSuspendMessage$1 implements Creator<CSSuspendMessage> {
    CSSuspendMessage$1() {
    }

    public CSSuspendMessage createFromParcel(Parcel parcel) {
        return new CSSuspendMessage(parcel);
    }

    public CSSuspendMessage[] newArray(int i) {
        return new CSSuspendMessage[i];
    }
}
