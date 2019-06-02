package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class PublicServiceMultiRichContentMessage$1 implements Creator<PublicServiceMultiRichContentMessage> {
    PublicServiceMultiRichContentMessage$1() {
    }

    public PublicServiceMultiRichContentMessage createFromParcel(Parcel parcel) {
        return new PublicServiceMultiRichContentMessage(parcel);
    }

    public PublicServiceMultiRichContentMessage[] newArray(int i) {
        return new PublicServiceMultiRichContentMessage[i];
    }
}
