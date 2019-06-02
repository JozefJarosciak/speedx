package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ReadReceiptMessage$1 implements Creator<ReadReceiptMessage> {
    ReadReceiptMessage$1() {
    }

    public ReadReceiptMessage createFromParcel(Parcel parcel) {
        return new ReadReceiptMessage(parcel);
    }

    public ReadReceiptMessage[] newArray(int i) {
        return new ReadReceiptMessage[i];
    }
}
