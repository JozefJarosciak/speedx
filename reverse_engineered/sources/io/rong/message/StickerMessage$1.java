package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class StickerMessage$1 implements Creator<StickerMessage> {
    StickerMessage$1() {
    }

    public StickerMessage createFromParcel(Parcel parcel) {
        return new StickerMessage(parcel);
    }

    public StickerMessage[] newArray(int i) {
        return new StickerMessage[i];
    }
}
