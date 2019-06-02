package io.rong.push.notification;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.push.RongPushClient.ConversationType;
import io.rong.push.common.ParcelUtils;

public class PushNotificationMessage implements Parcelable {
    public static final Creator<PushNotificationMessage> CREATOR = new C54111();
    private ConversationType conversationType;
    private String extra;
    private String isFromPush;
    private String objectName;
    private String pushContent;
    private String pushData;
    private String pushId;
    private String pushTitle;
    private long receivedTime;
    private String senderId;
    private String senderName;
    private Uri senderPortrait;
    private String targetId;
    private String targetUserName;

    /* renamed from: io.rong.push.notification.PushNotificationMessage$1 */
    static class C54111 implements Creator<PushNotificationMessage> {
        C54111() {
        }

        public PushNotificationMessage createFromParcel(Parcel parcel) {
            return new PushNotificationMessage(parcel);
        }

        public PushNotificationMessage[] newArray(int i) {
            return new PushNotificationMessage[i];
        }
    }

    public void setPushId(String str) {
        this.pushId = str;
    }

    public void setConversationType(ConversationType conversationType) {
        this.conversationType = conversationType;
    }

    public void setReceivedTime(long j) {
        this.receivedTime = j;
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public void setSenderId(String str) {
        this.senderId = str;
    }

    public void setSenderName(String str) {
        this.senderName = str;
    }

    public void setSenderPortrait(Uri uri) {
        this.senderPortrait = uri;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public void setTargetUserName(String str) {
        this.targetUserName = str;
    }

    public void setPushTitle(String str) {
        this.pushTitle = str;
    }

    public void setPushContent(String str) {
        this.pushContent = str;
    }

    public void setPushData(String str) {
        this.pushData = str;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public void setPushFlag(String str) {
        this.isFromPush = str;
    }

    public String getPushId() {
        return this.pushId;
    }

    public ConversationType getConversationType() {
        return this.conversationType;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public String getTargetUserName() {
        return this.targetUserName;
    }

    public long getReceivedTime() {
        return this.receivedTime;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public String getSenderName() {
        return this.senderName;
    }

    public Uri getSenderPortrait() {
        return this.senderPortrait;
    }

    public String getPushTitle() {
        return this.pushTitle;
    }

    public String getPushContent() {
        return this.pushContent;
    }

    public String getPushData() {
        return this.pushData;
    }

    public String getExtra() {
        return this.extra;
    }

    public String getPushFlag() {
        return this.isFromPush;
    }

    public void writeToParcel(Parcel parcel, int i) {
        ParcelUtils.writeToParcel(parcel, getPushId());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getConversationType().getValue()));
        ParcelUtils.writeToParcel(parcel, Long.valueOf(getReceivedTime()));
        ParcelUtils.writeToParcel(parcel, getObjectName());
        ParcelUtils.writeToParcel(parcel, getSenderId());
        ParcelUtils.writeToParcel(parcel, getSenderName());
        ParcelUtils.writeToParcel(parcel, getSenderPortrait());
        ParcelUtils.writeToParcel(parcel, getTargetId());
        ParcelUtils.writeToParcel(parcel, getTargetUserName());
        ParcelUtils.writeToParcel(parcel, getPushTitle());
        ParcelUtils.writeToParcel(parcel, getPushContent());
        ParcelUtils.writeToParcel(parcel, getPushData());
        ParcelUtils.writeToParcel(parcel, getExtra());
        ParcelUtils.writeToParcel(parcel, getPushFlag());
    }

    public PushNotificationMessage(Parcel parcel) {
        setPushId(ParcelUtils.readFromParcel(parcel));
        setConversationType(ConversationType.setValue(ParcelUtils.readIntFromParcel(parcel).intValue()));
        setReceivedTime(ParcelUtils.readLongFromParcel(parcel).longValue());
        setObjectName(ParcelUtils.readFromParcel(parcel));
        setSenderId(ParcelUtils.readFromParcel(parcel));
        setSenderName(ParcelUtils.readFromParcel(parcel));
        setSenderPortrait((Uri) ParcelUtils.readFromParcel(parcel, Uri.class));
        setTargetId(ParcelUtils.readFromParcel(parcel));
        setTargetUserName(ParcelUtils.readFromParcel(parcel));
        setPushTitle(ParcelUtils.readFromParcel(parcel));
        setPushContent(ParcelUtils.readFromParcel(parcel));
        setPushData(ParcelUtils.readFromParcel(parcel));
        setExtra(ParcelUtils.readFromParcel(parcel));
        setPushFlag(ParcelUtils.readFromParcel(parcel));
    }

    public int describeContents() {
        return 0;
    }
}
