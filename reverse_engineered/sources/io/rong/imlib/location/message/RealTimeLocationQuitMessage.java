package io.rong.imlib.location.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

@MessageTag(flag = 0, value = "RC:RLQuit")
public class RealTimeLocationQuitMessage extends MessageContent {
    public static final Creator<RealTimeLocationQuitMessage> CREATOR = new RealTimeLocationQuitMessage$1();
    private String content = "";
    private String extra = "";

    public RealTimeLocationQuitMessage(String str) {
        this.content = str;
    }

    public RealTimeLocationQuitMessage(byte[] bArr) {
    }

    public static RealTimeLocationQuitMessage obtain(String str) {
        return new RealTimeLocationQuitMessage(str);
    }

    public RealTimeLocationQuitMessage(Parcel parcel) {
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
