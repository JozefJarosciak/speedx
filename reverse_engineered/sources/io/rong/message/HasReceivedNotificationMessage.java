package io.rong.message;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import io.rong.imlib.MessageTag;

@MessageTag("RC:RecNtf")
public class HasReceivedNotificationMessage extends NotificationMessage {
    public static final Creator<HasReceivedNotificationMessage> CREATOR = new HasReceivedNotificationMessage$1();
    private boolean hasReceived;

    public HasReceivedNotificationMessage(Parcel parcel) {
    }

    public boolean isHasReceived() {
        return this.hasReceived;
    }

    public void setHasReceived(boolean z) {
        this.hasReceived = z;
    }

    public byte[] encode() {
        return new byte[0];
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }
}
