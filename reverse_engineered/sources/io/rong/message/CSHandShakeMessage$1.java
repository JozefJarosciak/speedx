package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSHandShakeMessage$1 implements Creator<CSHandShakeMessage> {
    CSHandShakeMessage$1() {
    }

    public CSHandShakeMessage createFromParcel(Parcel parcel) {
        return new CSHandShakeMessage(parcel);
    }

    public CSHandShakeMessage[] newArray(int i) {
        return new CSHandShakeMessage[i];
    }
}
