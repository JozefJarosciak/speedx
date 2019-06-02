package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

@MessageTag(flag = 0, value = "RC:SpMsg")
public class SuspendMessage extends MessageContent {
    public static final Creator<SuspendMessage> CREATOR = new SuspendMessage$1();

    public SuspendMessage(byte[] bArr) {
    }

    public SuspendMessage(Parcel parcel) {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    public byte[] encode() {
        return "{\"type\":1}".getBytes();
    }
}
