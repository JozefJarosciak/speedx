package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

@MessageTag(flag = 1, value = "RC:RLEnd")
public class RealTimeLocationEndMessage extends MessageContent {
    public static final Creator<RealTimeLocationEndMessage> CREATOR = new RealTimeLocationEndMessage$1();
    private String content = "";
    private String extra = "";

    public RealTimeLocationEndMessage(String str) {
        this.content = str;
    }

    public RealTimeLocationEndMessage(byte[] bArr) {
    }

    public static RealTimeLocationEndMessage obtain(String str) {
        return new RealTimeLocationEndMessage(str);
    }

    public RealTimeLocationEndMessage(Parcel parcel) {
        this.content = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.content);
    }

    public byte[] encode() {
        return new byte[0];
    }
}
