package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;

public class UnknownMessage extends MessageContent {
    public static final Creator<UnknownMessage> CREATOR = new C53841();

    /* renamed from: io.rong.imlib.model.UnknownMessage$1 */
    static class C53841 implements Creator<UnknownMessage> {
        C53841() {
        }

        public UnknownMessage createFromParcel(Parcel parcel) {
            return new UnknownMessage(parcel);
        }

        public UnknownMessage[] newArray(int i) {
            return new UnknownMessage[i];
        }
    }

    public UnknownMessage(byte[] bArr) {
    }

    public byte[] encode() {
        return new byte[0];
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(0));
    }

    public UnknownMessage(Parcel parcel) {
        ParcelUtils.readIntFromParcel(parcel);
    }
}
