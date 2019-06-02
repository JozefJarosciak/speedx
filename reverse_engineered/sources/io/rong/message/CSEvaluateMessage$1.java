package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSEvaluateMessage$1 implements Creator<CSEvaluateMessage> {
    CSEvaluateMessage$1() {
    }

    public CSEvaluateMessage createFromParcel(Parcel parcel) {
        return new CSEvaluateMessage(parcel);
    }

    public CSEvaluateMessage[] newArray(int i) {
        return new CSEvaluateMessage[i];
    }
}
