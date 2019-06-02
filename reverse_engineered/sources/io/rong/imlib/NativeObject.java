package io.rong.imlib;

import org.json.JSONObject;

public class NativeObject {

    public interface ConnectAckCallback {
        void operationComplete(int i, String str);
    }

    public interface PublishAckListener {
        void operationComplete(int i, String str, long j);
    }

    public interface TokenListener {
        void OnError(int i, String str);
    }

    public interface BizAckListener {
        void operationComplete(int i, int i2);
    }

    public interface DiscussionInfoListener {
        void OnError(int i);

        void onReceived(DiscussionInfo discussionInfo);
    }

    public static abstract class ReceiveMessageListener {
        public abstract void onReceived(Message message, int i, boolean z);

        protected Message getNewMessage() {
            return new Message();
        }
    }

    public interface ExceptionListener {
        void onError(int i, String str);
    }

    public interface ChatroomInfoListener {
        void OnError(int i);

        void OnSuccess(int i, UserInfo[] userInfoArr);
    }

    public interface SetBlacklistListener {
        void OnError(int i);

        void OnSuccess(String str);
    }

    public interface PushSettingListener {
        void OnError(int i);

        void OnSuccess(String str, int i);
    }

    public interface HistoryMessageListener {
        void onError(int i);

        void onReceived(Message[] messageArr);
    }

    public interface CreateDiscussionCallback {
        void OnError(int i);

        void OnSuccess(String str);
    }

    public interface AccountInfoListener {
        void OnError(int i);

        void onReceived(AccountInfo[] accountInfoArr);
    }

    public static class AccountInfo {
        private byte[] accountId;
        private byte[] accountName;
        private int accountType;
        private byte[] accountUri;
        private byte[] extra;

        public byte[] getAccountId() {
            return this.accountId;
        }

        public void setAccountId(byte[] bArr) {
            this.accountId = bArr;
        }

        public byte[] getAccountName() {
            return this.accountName;
        }

        public void setAccountName(byte[] bArr) {
            this.accountName = bArr;
        }

        public byte[] getAccountUri() {
            return this.accountUri;
        }

        public void setAccountUri(byte[] bArr) {
            this.accountUri = bArr;
        }

        public byte[] getExtra() {
            return this.extra;
        }

        public void setExtra(byte[] bArr) {
            this.extra = bArr;
        }

        public int getAccountType() {
            return this.accountType;
        }

        public void setAccountType(int i) {
            this.accountType = i;
        }
    }

    public static class Conversation {
        private long ReceivedTime;
        private String UId;
        private boolean blockPush;
        private byte[] content;
        private String conversationTitle;
        private int conversationType;
        private String draft;
        private String extra;
        private boolean isTop;
        private long lastTime;
        private int mentionCount;
        private String messageContent;
        private boolean messageDirection;
        private int messageId;
        private String objectName;
        private String portraitUrl;
        private int readStatus;
        private int receiveStatus;
        private String senderName;
        private String senderUserId;
        private int sentStatus;
        private long sentTime;
        private String targetId;
        private int unreadMessageCount;
        private String userId;
        private String userName;
        private String userPortrait;

        public Conversation(String str) {
        }

        public String getUId() {
            return this.UId;
        }

        public void setUId(String str) {
            this.UId = str;
        }

        public long getSentTime() {
            return this.sentTime;
        }

        public void setSentTime(long j) {
            this.sentTime = j;
        }

        public String getSenderUserId() {
            return this.senderUserId;
        }

        public void setSenderUserId(String str) {
            this.senderUserId = str;
        }

        public boolean isMessageDirection() {
            return this.messageDirection;
        }

        public void setMessageDirection(boolean z) {
            this.messageDirection = z;
        }

        public void setIsTop(boolean z) {
            this.isTop = z;
        }

        public int getConversationType() {
            return this.conversationType;
        }

        public void setConversationType(int i) {
            this.conversationType = i;
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

        public void setConversationTitle(byte[] bArr) {
            this.conversationTitle = new String(bArr);
        }

        public boolean isTop() {
            return this.isTop;
        }

        public void setTop(boolean z) {
            this.isTop = z;
        }

        public String getDraft() {
            return this.draft;
        }

        public void setDraft(String str) {
            this.draft = str;
        }

        public int getUnreadMessageCount() {
            return this.unreadMessageCount;
        }

        public void setUnreadMessageCount(int i) {
            this.unreadMessageCount = i;
        }

        public String getObjectName() {
            return this.objectName;
        }

        public void setObjectName(String str) {
            this.objectName = str;
        }

        public int getMessageId() {
            return this.messageId;
        }

        public void setMessageId(int i) {
            this.messageId = i;
        }

        public int getReceiveStatus() {
            return this.receiveStatus;
        }

        public void setReceiveStatus(int i) {
            this.receiveStatus = i;
        }

        public int getSentStatus() {
            return this.sentStatus;
        }

        public void setSentStatus(int i) {
            this.sentStatus = i;
        }

        public long getReceivedTime() {
            return this.ReceivedTime;
        }

        public void setReceivedTime(long j) {
            this.ReceivedTime = j;
        }

        public String getSenderName() {
            return this.senderName;
        }

        public void setSenderName(String str) {
            this.senderName = str;
        }

        public String getMessageContent() {
            return this.messageContent;
        }

        public void setMessageContent(String str) {
            this.messageContent = str;
        }

        public boolean isBlockPush() {
            return this.blockPush;
        }

        public void setBlockPush(boolean z) {
            this.blockPush = z;
        }

        public long getLastTime() {
            return this.lastTime;
        }

        public void setLastTime(long j) {
            this.lastTime = j;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getUserPortrait() {
            return this.userPortrait;
        }

        public void setUserPortrait(String str) {
            this.userPortrait = str;
        }

        public int getReadStatus() {
            return this.readStatus;
        }

        public void setReadStatus(int i) {
            this.readStatus = i;
        }

        public byte[] getContent() {
            return this.content;
        }

        public void setContent(byte[] bArr) {
            this.content = bArr;
        }

        public String getExtra() {
            return this.extra;
        }

        public void setExtra(String str) {
            this.extra = str;
        }

        public String getPortraitUrl() {
            return this.portraitUrl;
        }

        public void setPortraitUrl(String str) {
            this.portraitUrl = str;
        }

        public int getMentionCount() {
            return this.mentionCount;
        }

        public void setMentionCount(int i) {
            this.mentionCount = i;
        }
    }

    public static class DiscussionInfo {
        private String adminId;
        private String discussionId;
        private String discussionName;
        private int inviteStatus;
        private String userIds;

        public String getDiscussionId() {
            return this.discussionId;
        }

        public void setDiscussionId(String str) {
            this.discussionId = str;
        }

        public String getDiscussionName() {
            return this.discussionName;
        }

        public void setDiscussionName(byte[] bArr) {
            this.discussionName = new String(bArr);
        }

        public String getAdminId() {
            return this.adminId;
        }

        public void setAdminId(String str) {
            this.adminId = str;
        }

        public String getUserIds() {
            return this.userIds;
        }

        public void setUserIds(String str) {
            this.userIds = str;
        }

        public int getInviteStatus() {
            return this.inviteStatus;
        }

        public void setInviteStatus(int i) {
            this.inviteStatus = i;
        }
    }

    public interface GetUserDataListener {
        void OnError(int i);

        void OnSuccess(String str);
    }

    public static class Message {
        private String UId;
        private byte[] content;
        private int conversationType;
        private String extra;
        private boolean messageDirection;
        private int messageId;
        private String objectName;
        private String pushContent;
        private int readStatus;
        private long receivedTime;
        private String senderUserId;
        private int sentStatus;
        private long sentTime;
        private String targetId;

        public Message(JSONObject jSONObject) {
            this.conversationType = jSONObject.optInt("conversation_category");
            this.targetId = jSONObject.optString("target_id");
            this.messageId = jSONObject.optInt("id");
            this.messageDirection = jSONObject.optBoolean("message_direction");
            this.senderUserId = jSONObject.optString("sender_user_id");
            this.readStatus = jSONObject.optInt("read_status");
            this.sentStatus = jSONObject.optInt("send_status");
            this.receivedTime = jSONObject.optLong("receive_time");
            this.sentTime = jSONObject.optLong("send_time");
            this.objectName = jSONObject.optString("object_name");
            this.content = jSONObject.optString("content").getBytes();
            this.extra = jSONObject.optString("extra");
            this.pushContent = jSONObject.optString("push");
        }

        public String getUId() {
            return this.UId;
        }

        public void setUId(String str) {
            this.UId = str;
        }

        public String getPushContent() {
            return this.pushContent;
        }

        public void setPushContent(String str) {
            this.pushContent = str;
        }

        public int getConversationType() {
            return this.conversationType;
        }

        public void setConversationType(int i) {
            this.conversationType = i;
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

        public boolean getMessageDirection() {
            return this.messageDirection;
        }

        public void setMessageDirection(boolean z) {
            this.messageDirection = z;
        }

        public int getReadStatus() {
            return this.readStatus;
        }

        public void setReadStatus(int i) {
            this.readStatus = i;
        }

        public int getSentStatus() {
            return this.sentStatus;
        }

        public void setSentStatus(int i) {
            this.sentStatus = i;
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

        public byte[] getContent() {
            return this.content;
        }

        public void setContent(byte[] bArr) {
            this.content = bArr;
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
    }

    public static class ReceiptInfo {
        private byte[] targetId;
        private long timestamp;

        public byte[] getTargetId() {
            return this.targetId;
        }

        public void setTargetId(byte[] bArr) {
            this.targetId = bArr;
        }

        public long getTimestamp() {
            return this.timestamp;
        }

        public void setTimestamp(long j) {
            this.timestamp = j;
        }
    }

    public static class UserInfo {
        private String accountExtra;
        private int categoryId;
        private long joinTime;
        private String url;
        private String userId;
        private String userName;

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }

        public int getCategoryId() {
            return this.categoryId;
        }

        public void setCategoryId(int i) {
            this.categoryId = i;
        }

        public String getUserName() {
            return this.userName;
        }

        public void setUserName(String str) {
            this.userName = str;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getAccountExtra() {
            return this.accountExtra;
        }

        public void setAccountExtra(String str) {
            this.accountExtra = str;
        }

        public long getJoinTime() {
            return this.joinTime;
        }

        public void setJoinTime(long j) {
            this.joinTime = j;
        }
    }

    protected native void AddPushSetting(String str, int i, PublishAckListener publishAckListener);

    protected native void AddToBlacklist(String str, PublishAckListener publishAckListener);

    protected native boolean ClearConversations(int[] iArr);

    protected native boolean ClearMessages(int i, String str, boolean z);

    protected native boolean ClearUnread(int i, String str);

    protected native void Connect(String str, String str2, int i, ConnectAckCallback connectAckCallback);

    protected native void CreateInviteDiscussion(String str, String[] strArr, CreateDiscussionCallback createDiscussionCallback);

    protected native boolean DeleteMessages(int[] iArr);

    protected native void Disconnect(int i);

    protected native void EnvironmentChangeNotify(int i);

    protected native void GetAuthConfig(TokenListener tokenListener);

    protected native void GetBlacklist(SetBlacklistListener setBlacklistListener);

    protected native void GetBlacklistStatus(String str, BizAckListener bizAckListener);

    protected native void GetBlockPush(String str, int i, BizAckListener bizAckListener);

    protected native int GetCateUnreadCount(int[] iArr);

    protected native Conversation GetConversationEx(String str, int i);

    protected native Conversation[] GetConversationListEx(int[] iArr);

    protected native long GetDeltaTime();

    protected native void GetDiscussionInfo(String str, DiscussionInfoListener discussionInfoListener);

    protected native DiscussionInfo GetDiscussionInfoSync(String str);

    protected native void GetDownloadUrl(int i, String str, TokenListener tokenListener);

    protected native Message[] GetHistoryMessagesEx(String str, int i, String str2, int i2, int i3, boolean z);

    protected native byte[] GetLatestMessagesbyObjectName(String str, int i, String str2, int i2);

    protected native Message[] GetMentionMessages(String str, int i);

    protected native Message GetMessageById(int i);

    protected native Message GetMessageByUId(String str);

    protected native int GetMessageCount(String str, int i);

    protected native long GetSendTimeByMessageId(int i);

    protected native String GetTextMessageDraft(int i, String str);

    protected native int GetTotalUnreadCount();

    protected native int GetUnreadCount(String str, int i);

    protected native void GetUploadToken(int i, TokenListener tokenListener);

    protected native void GetUserData(GetUserDataListener getUserDataListener);

    protected native String GetUserIdByToken(String str);

    protected native UserInfo GetUserInfoExSync(String str, int i);

    protected native void GetVoIPKey(int i, String str, String str2, TokenListener tokenListener);

    protected native int InitClient(String str, String str2, String str3, String str4, String str5);

    protected native void InviteMemberToDiscussion(String str, String[] strArr, PublishAckListener publishAckListener);

    protected native void JoinChatRoom(String str, int i, int i2, boolean z, PublishAckListener publishAckListener);

    protected native void JoinExistingChatroom(String str, int i, int i2, PublishAckListener publishAckListener);

    protected native void JoinGroup(String str, String str2, PublishAckListener publishAckListener);

    protected native AccountInfo[] LoadAccountInfo();

    protected native void LoadHistoryMessage(String str, int i, long j, int i2, HistoryMessageListener historyMessageListener);

    protected native boolean QueryChatroomInfo(String str, int i, int i2, ChatroomInfoListener chatroomInfoListener);

    protected native void QueryPushSetting(PushSettingListener pushSettingListener);

    protected native ReceiptInfo[] QueryReceiptStatus();

    protected native void QuitChatRoom(String str, int i, PublishAckListener publishAckListener);

    protected native void QuitDiscussion(String str, PublishAckListener publishAckListener);

    protected native void QuitGroup(String str, PublishAckListener publishAckListener);

    protected native boolean RecallMessage(String str);

    protected native void RegisterMessageType(String str, int i);

    protected native boolean RemoveConversation(int i, String str);

    protected native void RemoveFromBlacklist(String str, PublishAckListener publishAckListener);

    protected native void RemoveMemberFromDiscussion(String str, String str2, PublishAckListener publishAckListener);

    protected native void RemovePushSetting(PublishAckListener publishAckListener);

    protected native void RenameDiscussion(String str, String str2, PublishAckListener publishAckListener);

    protected native int SaveMessage(String str, int i, String str2, String str3, byte[] bArr, boolean z, int i2);

    protected native void SearchAccount(String str, int i, int i2, AccountInfoListener accountInfoListener);

    protected native void SendMessage(String str, int i, int i2, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i3, PublishAckListener publishAckListener, boolean z);

    protected native void SetBlockPush(String str, int i, boolean z, BizAckListener bizAckListener);

    protected native void SetDeviceInfo(String str, String str2, String str3, String str4, String str5);

    protected native void SetExceptionListener(ExceptionListener exceptionListener);

    protected native void SetInviteStatus(String str, int i, PublishAckListener publishAckListener);

    protected native boolean SetIsTop(int i, String str, boolean z);

    protected native void SetLogStatus(boolean z);

    protected native boolean SetMessageContent(int i, byte[] bArr, String str);

    protected native boolean SetMessageExtra(int i, String str);

    protected native void SetMessageListener(ReceiveMessageListener receiveMessageListener);

    protected native boolean SetReadStatus(int i, int i2);

    protected native boolean SetReceiptStatus(String str, int i, long j, String str2);

    protected native boolean SetSendStatus(int i, int i2);

    protected native boolean SetTextMessageDraft(int i, String str, String str2);

    protected native void SetUserData(String str, PublishAckListener publishAckListener);

    protected native void SubscribeAccount(String str, int i, boolean z, PublishAckListener publishAckListener);

    protected native void SyncGroups(String[] strArr, String[] strArr2, PublishAckListener publishAckListener);

    protected native boolean UpdateConversationInfo(String str, int i, String str2, String str3);

    protected native boolean UpdateMessageReceiptStatus(String str, int i, long j);

    protected native void setJNIEnv(NativeObject nativeObject);

    static {
        System.loadLibrary("RongIMLib");
    }

    NativeObject() {
        setJNIEnv(this);
    }

    protected void ping() {
        EnvironmentChangeNotify(105);
    }

    protected void networkUnavailable() {
        EnvironmentChangeNotify(101);
    }
}
