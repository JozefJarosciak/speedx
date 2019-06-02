package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class ImageMessage$1 implements Creator<ImageMessage> {
    ImageMessage$1() {
    }

    public ImageMessage createFromParcel(Parcel parcel) {
        return new ImageMessage(parcel);
    }

    public ImageMessage[] newArray(int i) {
        return new ImageMessage[i];
    }
}
