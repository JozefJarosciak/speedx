package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class CSPullEvaluateMessage$1 implements Creator<CSPullEvaluateMessage> {
    CSPullEvaluateMessage$1() {
    }

    public CSPullEvaluateMessage createFromParcel(Parcel parcel) {
        return new CSPullEvaluateMessage(parcel);
    }

    public CSPullEvaluateMessage[] newArray(int i) {
        return new CSPullEvaluateMessage[i];
    }
}
