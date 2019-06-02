package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import io.rong.common.ParcelUtils;
import io.rong.imlib.model.Conversation.ConversationType;

public class Message implements Parcelable {
    public static final Creator<Message> CREATOR = new Message$1();
    private String UId;
    private MessageContent content;
    private ConversationType conversationType;
    private String extra;
    private Message$MessageDirection messageDirection;
    private int messageId;
    private String objectName;
    private Message$ReceivedStatus receivedStatus;
    private long receivedTime;
    private String senderUserId;
    private Message$SentStatus sentStatus;
    private long sentTime;
    private String targetId;

    public String getUId() {
        return this.UId;
    }

    public void setUId(String str) {
        this.UId = str;
    }

    public Message(io.rong.imlib.NativeObject.Message message) {
        this.conversationType = ConversationType.setValue(message.getConversationType());
        this.targetId = message.getTargetId();
        this.messageId = message.getMessageId();
        this.messageDirection = !message.getMessageDirection() ? Message$MessageDirection.SEND : Message$MessageDirection.RECEIVE;
        this.senderUserId = message.getSenderUserId();
        this.receivedStatus = new Message$ReceivedStatus(message.getReadStatus());
        this.sentStatus = Message$SentStatus.setValue(message.getSentStatus());
        this.receivedTime = message.getReceivedTime();
        this.sentTime = message.getSentTime();
        this.objectName = message.getObjectName();
        this.UId = message.getUId();
        this.extra = message.getExtra();
    }

    public static Message obtain(String str, ConversationType conversationType, MessageContent messageContent) {
        Message message = new Message();
        message.setTargetId(str);
        message.setConversationType(conversationType);
        message.setContent(messageContent);
        return message;
    }

    public ConversationType getConversationType() {
        return this.conversationType;
    }

    public void setConversationType(ConversationType conversationType) {
        this.conversationType = conversationType;
    }

    public String getTargetId() {
        return this.targetId;
    }

    public void setTargetId(String str) {
        this.targetId = str;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int i) {
        this.messageId = i;
    }

    public Message$MessageDirection getMessageDirection() {
        return this.messageDirection;
    }

    public void setMessageDirection(Message$MessageDirection message$MessageDirection) {
        this.messageDirection = message$MessageDirection;
    }

    public Message$ReceivedStatus getReceivedStatus() {
        return this.receivedStatus;
    }

    public void setReceivedStatus(Message$ReceivedStatus message$ReceivedStatus) {
        this.receivedStatus = message$ReceivedStatus;
    }

    public Message$SentStatus getSentStatus() {
        return this.sentStatus;
    }

    public void setSentStatus(Message$SentStatus message$SentStatus) {
        this.sentStatus = message$SentStatus;
    }

    public long getReceivedTime() {
        return this.receivedTime;
    }

    public void setReceivedTime(long j) {
        this.receivedTime = j;
    }

    public long getSentTime() {
        return this.sentTime;
    }

    public void setSentTime(long j) {
        this.sentTime = j;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public MessageContent getContent() {
        return this.content;
    }

    public void setContent(MessageContent messageContent) {
        this.content = messageContent;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String str) {
        this.senderUserId = str;
    }

    public int describeContents() {
        return 0;
    }

    public Message(Parcel parcel) {
        String readFromParcel = ParcelUtils.readFromParcel(parcel);
        Class cls = null;
        if (readFromParcel != null) {
            try {
                cls = Class.forName(readFromParcel);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        setTargetId(ParcelUtils.readFromParcel(parcel));
        setMessageId(ParcelUtils.readIntFromParcel(parcel).intValue());
        setSenderUserId(ParcelUtils.readFromParcel(parcel));
        setReceivedTime(ParcelUtils.readLongFromParcel(parcel).longValue());
        setSentTime(ParcelUtils.readLongFromParcel(parcel).longValue());
        setObjectName(ParcelUtils.readFromParcel(parcel));
        setContent((MessageContent) ParcelUtils.readFromParcel(parcel, cls));
        setExtra(ParcelUtils.readFromParcel(parcel));
        setUId(ParcelUtils.readFromParcel(parcel));
        setConversationType(ConversationType.setValue(ParcelUtils.readIntFromParcel(parcel).intValue()));
        setMessageDirection(Message$MessageDirection.setValue(ParcelUtils.readIntFromParcel(parcel).intValue()));
        setReceivedStatus(new Message$ReceivedStatus(ParcelUtils.readIntFromParcel(parcel).intValue()));
        setSentStatus(Message$SentStatus.setValue(ParcelUtils.readIntFromParcel(parcel).intValue()));
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        ParcelUtils.writeToParcel(parcel, getContent() != null ? getContent().getClass().getName() : null);
        ParcelUtils.writeToParcel(parcel, getTargetId());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getMessageId()));
        ParcelUtils.writeToParcel(parcel, getSenderUserId());
        ParcelUtils.writeToParcel(parcel, Long.valueOf(getReceivedTime()));
        ParcelUtils.writeToParcel(parcel, Long.valueOf(getSentTime()));
        ParcelUtils.writeToParcel(parcel, getObjectName());
        ParcelUtils.writeToParcel(parcel, getContent());
        ParcelUtils.writeToParcel(parcel, getExtra());
        ParcelUtils.writeToParcel(parcel, getUId());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getConversationType().getValue()));
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getMessageDirection() == null ? 0 : getMessageDirection().getValue()));
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getReceivedStatus() == null ? 0 : getReceivedStatus().getFlag()));
        if (getSentStatus() != null) {
            i2 = getSentStatus().getValue();
        }
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(i2));
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Message)) {
            return super.equals(obj);
        }
        if (this.messageId == ((Message) obj).getMessageId()) {
            return true;
        }
        return false;
    }
}
