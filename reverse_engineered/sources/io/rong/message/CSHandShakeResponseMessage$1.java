package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSHandShakeResponseMessage$1 implements Creator<CSHandShakeResponseMessage> {
    CSHandShakeResponseMessage$1() {
    }

    public CSHandShakeResponseMessage createFromParcel(Parcel parcel) {
        return new CSHandShakeResponseMessage(parcel);
    }

    public CSHandShakeResponseMessage[] newArray(int i) {
        return new CSHandShakeResponseMessage[i];
    }
}
