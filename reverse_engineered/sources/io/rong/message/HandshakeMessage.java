package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.alipay.sdk.util.C0880h;
import io.rong.imlib.MessageTag;

@MessageTag(flag = 0, value = "RC:HsMsg")
public class HandshakeMessage extends TextMessage {
    public static final Creator<HandshakeMessage> CREATOR = new HandshakeMessage$1();
    private int type;

    public HandshakeMessage(byte[] bArr) {
    }

    public static HandshakeMessage obtain(String str) {
        HandshakeMessage handshakeMessage = new HandshakeMessage();
        handshakeMessage.setContent(str);
        return handshakeMessage;
    }

    public HandshakeMessage(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    public byte[] encode() {
        return ("{\"type\":" + this.type + C0880h.f2222d).getBytes();
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
