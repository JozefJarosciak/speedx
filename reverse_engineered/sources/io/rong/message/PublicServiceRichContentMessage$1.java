package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class PublicServiceRichContentMessage$1 implements Creator<PublicServiceRichContentMessage> {
    PublicServiceRichContentMessage$1() {
    }

    public PublicServiceRichContentMessage createFromParcel(Parcel parcel) {
        return new PublicServiceRichContentMessage(parcel);
    }

    public PublicServiceRichContentMessage[] newArray(int i) {
        return new PublicServiceRichContentMessage[i];
    }
}
