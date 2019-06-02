package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

@MessageTag(flag = 0, value = "RC:RLJoin")
public class RealTimeLocationJoinMessage extends MessageContent {
    public static final Creator<RealTimeLocationJoinMessage> CREATOR = new RealTimeLocationJoinMessage$1();
    private String content = "";
    private String extra = "";

    public RealTimeLocationJoinMessage(String str) {
        this.content = str;
    }

    public RealTimeLocationJoinMessage(byte[] bArr) {
    }

    public static RealTimeLocationJoinMessage obtain(String str) {
        return new RealTimeLocationJoinMessage(str);
    }

    public RealTimeLocationJoinMessage(Parcel parcel) {
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
