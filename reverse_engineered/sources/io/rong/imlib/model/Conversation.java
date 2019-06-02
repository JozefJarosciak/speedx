package io.rong.imlib.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import io.rong.common.ParcelUtils;

public class Conversation implements Parcelable {
    public static final Creator<Conversation> CREATOR = new C53741();
    private String conversationTitle;
    private ConversationType conversationType;
    private String draft;
    private boolean isTop;
    private MessageContent latestMessage;
    private int latestMessageId;
    private int mentionedCount;
    private ConversationNotificationStatus notificationStatus;
    private String objectName;
    private String portraitUrl;
    private Message$ReceivedStatus receivedStatus;
    private long receivedTime;
    private String senderUserId;
    private String senderUserName;
    private Message$SentStatus sentStatus;
    private long sentTime;
    private String targetId;
    private int unreadMessageCount;

    /* renamed from: io.rong.imlib.model.Conversation$1 */
    static class C53741 implements Creator<Conversation> {
        C53741() {
        }

        public Conversation createFromParcel(Parcel parcel) {
            return new Conversation(parcel);
        }

        public Conversation[] newArray(int i) {
            return new Conversation[i];
        }
    }

    public enum ConversationNotificationStatus {
        DO_NOT_DISTURB(0),
        NOTIFY(1);
        
        private int value;

        private ConversationNotificationStatus(int i) {
            this.value = 1;
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static ConversationNotificationStatus setValue(int i) {
            for (ConversationNotificationStatus conversationNotificationStatus : values()) {
                if (i == conversationNotificationStatus.getValue()) {
                    return conversationNotificationStatus;
                }
            }
            return NOTIFY;
        }
    }

    public enum ConversationType {
        NONE(0, "none"),
        PRIVATE(1, AVStatus.INBOX_PRIVATE),
        DISCUSSION(2, "discussion"),
        GROUP(3, "group"),
        CHATROOM(4, "chatroom"),
        CUSTOMER_SERVICE(5, "customer_service"),
        SYSTEM(6, "system"),
        APP_PUBLIC_SERVICE(7, "app_public_service"),
        PUBLIC_SERVICE(8, "public_service"),
        PUSH_SERVICE(9, "push_service");
        
        private String name;
        private int value;

        private ConversationType(int i, String str) {
            this.value = 1;
            this.name = "";
            this.value = i;
            this.name = str;
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static ConversationType setValue(int i) {
            for (ConversationType conversationType : values()) {
                if (i == conversationType.getValue()) {
                    return conversationType;
                }
            }
            return PRIVATE;
        }
    }

    public enum PublicServiceType {
        APP_PUBLIC_SERVICE(7, "app_public_service"),
        PUBLIC_SERVICE(8, "public_service");
        
        private String name;
        private int value;

        private PublicServiceType(int i, String str) {
            this.value = 1;
            this.name = "";
            this.value = i;
            this.name = str;
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static PublicServiceType setValue(int i) {
            for (PublicServiceType publicServiceType : values()) {
                if (i == publicServiceType.getValue()) {
                    return publicServiceType;
                }
            }
            return null;
        }
    }

    public static Conversation obtain(ConversationType conversationType, String str, String str2) {
        Conversation conversation = new Conversation();
        conversation.setConversationType(conversationType);
        conversation.setTargetId(str);
        conversation.setConversationTitle(str2);
        return conversation;
    }

    public String getPortraitUrl() {
        return this.portraitUrl;
    }

    public void setPortraitUrl(String str) {
        this.portraitUrl = str;
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

    public String getConversationTitle() {
        return this.conversationTitle;
    }

    public void setConversationTitle(String str) {
        this.conversationTitle = str;
    }

    public int getUnreadMessageCount() {
        return this.unreadMessageCount;
    }

    public void setUnreadMessageCount(int i) {
        this.unreadMessageCount = i;
    }

    public boolean isTop() {
        return this.isTop;
    }

    public void setTop(boolean z) {
        this.isTop = z;
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

    public String getDraft() {
        return this.draft;
    }

    public void setDraft(String str) {
        this.draft = str;
    }

    public String getObjectName() {
        return this.objectName;
    }

    public void setObjectName(String str) {
        this.objectName = str;
    }

    public int getLatestMessageId() {
        return this.latestMessageId;
    }

    public void setLatestMessageId(int i) {
        this.latestMessageId = i;
    }

    public MessageContent getLatestMessage() {
        return this.latestMessage;
    }

    public void setLatestMessage(MessageContent messageContent) {
        this.latestMessage = messageContent;
    }

    public String getSenderUserId() {
        return this.senderUserId;
    }

    public void setSenderUserId(String str) {
        this.senderUserId = str;
    }

    public String getSenderUserName() {
        return this.senderUserName;
    }

    public void setSenderUserName(String str) {
        this.senderUserName = str;
    }

    public ConversationNotificationStatus getNotificationStatus() {
        return this.notificationStatus;
    }

    public void setNotificationStatus(ConversationNotificationStatus conversationNotificationStatus) {
        this.notificationStatus = conversationNotificationStatus;
    }

    public void setMentionedCount(int i) {
        this.mentionedCount = i;
    }

    public int getMentionedCount() {
        return this.mentionedCount;
    }

    public int describeContents() {
        return 0;
    }

    public Conversation(Parcel parcel) {
        boolean z = true;
        Object readFromParcel = ParcelUtils.readFromParcel(parcel);
        setConversationType(ConversationType.setValue(ParcelUtils.readIntFromParcel(parcel).intValue()));
        setTargetId(ParcelUtils.readFromParcel(parcel));
        setConversationTitle(ParcelUtils.readFromParcel(parcel));
        setUnreadMessageCount(ParcelUtils.readIntFromParcel(parcel).intValue());
        if (ParcelUtils.readIntFromParcel(parcel).intValue() != 1) {
            z = false;
        }
        setTop(z);
        setLatestMessageId(ParcelUtils.readIntFromParcel(parcel).intValue());
        setReceivedStatus(new Message$ReceivedStatus(ParcelUtils.readIntFromParcel(parcel).intValue()));
        setSentStatus(Message$SentStatus.setValue(ParcelUtils.readIntFromParcel(parcel).intValue()));
        setReceivedTime(ParcelUtils.readLongFromParcel(parcel).longValue());
        setSentTime(ParcelUtils.readLongFromParcel(parcel).longValue());
        setObjectName(ParcelUtils.readFromParcel(parcel));
        setSenderUserId(ParcelUtils.readFromParcel(parcel));
        setSenderUserName(ParcelUtils.readFromParcel(parcel));
        if (TextUtils.isEmpty(readFromParcel)) {
            setLatestMessage((MessageContent) ParcelUtils.readFromParcel(parcel, MessageContent.class));
        } else {
            try {
                setLatestMessage((MessageContent) ParcelUtils.readFromParcel(parcel, Class.forName(readFromParcel)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        setDraft(ParcelUtils.readFromParcel(parcel));
        setPortraitUrl(ParcelUtils.readFromParcel(parcel));
        int intValue = ParcelUtils.readIntFromParcel(parcel).intValue();
        if (intValue != -1) {
            setNotificationStatus(ConversationNotificationStatus.setValue(intValue));
        }
        intValue = ParcelUtils.readIntFromParcel(parcel).intValue();
        if (intValue > 0) {
            setMentionedCount(intValue);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 0;
        ParcelUtils.writeToParcel(parcel, getLatestMessage() == null ? null : getLatestMessage().getClass().getName());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getConversationType().getValue()));
        ParcelUtils.writeToParcel(parcel, getTargetId());
        ParcelUtils.writeToParcel(parcel, getConversationTitle());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getUnreadMessageCount()));
        if (isTop()) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(i2));
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getLatestMessageId()));
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getReceivedStatus() == null ? 0 : getReceivedStatus().getFlag()));
        if (getSentStatus() != null) {
            i3 = getSentStatus().getValue();
        }
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(i3));
        ParcelUtils.writeToParcel(parcel, Long.valueOf(getReceivedTime()));
        ParcelUtils.writeToParcel(parcel, Long.valueOf(getSentTime()));
        ParcelUtils.writeToParcel(parcel, getObjectName());
        ParcelUtils.writeToParcel(parcel, getSenderUserId());
        ParcelUtils.writeToParcel(parcel, getSenderUserName());
        ParcelUtils.writeToParcel(parcel, getLatestMessage());
        ParcelUtils.writeToParcel(parcel, getDraft());
        ParcelUtils.writeToParcel(parcel, getPortraitUrl());
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getNotificationStatus() == null ? -1 : getNotificationStatus().getValue()));
        ParcelUtils.writeToParcel(parcel, Integer.valueOf(getMentionedCount()));
    }
}
