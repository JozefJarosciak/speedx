package io.rong.imkit.model;

import io.rong.imlib.RongIMClient$DiscussionInviteStatus;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.message.RecallNotificationMessage;
import java.util.ArrayList;
import java.util.List;

public class Event {

    public static class AddMemberToDiscussionEvent {
        String discussionId;
        List<String> userIdList;

        public AddMemberToDiscussionEvent(String str, List<String> list) {
            this.discussionId = str;
            this.userIdList = list;
        }

        public String getDiscussionId() {
            return this.discussionId;
        }

        public void setDiscussionId(String str) {
            this.discussionId = str;
        }

        public List<String> getUserIdList() {
            return this.userIdList;
        }

        public void setUserIdList(List<String> list) {
            this.userIdList = list;
        }
    }

    public static class AddToBlacklistEvent {
        String userId;

        public AddToBlacklistEvent(String str) {
            this.userId = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }
    }

    protected static class BaseConversationEvent {
        protected ConversationType mConversationType;
        protected String mTargetId;

        protected BaseConversationEvent() {
        }

        public ConversationType getConversationType() {
            return this.mConversationType;
        }

        public void setConversationType(ConversationType conversationType) {
            this.mConversationType = conversationType;
        }

        public String getTargetId() {
            return this.mTargetId;
        }

        public void setTargetId(String str) {
            this.mTargetId = str;
        }
    }

    public static class AudioListenedEvent extends BaseConversationEvent {
        private int latestMessageId;

        public /* bridge */ /* synthetic */ ConversationType getConversationType() {
            return super.getConversationType();
        }

        public /* bridge */ /* synthetic */ String getTargetId() {
            return super.getTargetId();
        }

        public /* bridge */ /* synthetic */ void setConversationType(ConversationType conversationType) {
            super.setConversationType(conversationType);
        }

        public /* bridge */ /* synthetic */ void setTargetId(String str) {
            super.setTargetId(str);
        }

        public AudioListenedEvent(ConversationType conversationType, String str, int i) {
            setConversationType(conversationType);
            setTargetId(str);
            this.latestMessageId = i;
        }

        public void setLatestMessageId(int i) {
            this.latestMessageId = i;
        }

        public int getLatestMessageId() {
            return this.latestMessageId;
        }
    }

    public static class ClearConversationEvent {
        private List<ConversationType> typeList = new ArrayList();

        public static ClearConversationEvent obtain(ConversationType... conversationTypeArr) {
            ClearConversationEvent clearConversationEvent = new ClearConversationEvent();
            clearConversationEvent.setTypes(conversationTypeArr);
            return clearConversationEvent;
        }

        public void setTypes(ConversationType[] conversationTypeArr) {
            if (conversationTypeArr != null && conversationTypeArr.length != 0) {
                this.typeList.clear();
                for (Object add : conversationTypeArr) {
                    this.typeList.add(add);
                }
            }
        }

        public List<ConversationType> getTypes() {
            return this.typeList;
        }
    }

    public static class ConnectEvent {
        private boolean isConnectSuccess;

        public static ConnectEvent obtain(boolean z) {
            ConnectEvent connectEvent = new ConnectEvent();
            connectEvent.setConnectStatus(z);
            return connectEvent;
        }

        public void setConnectStatus(boolean z) {
            this.isConnectSuccess = z;
        }

        public boolean getConnectStatus() {
            return this.isConnectSuccess;
        }
    }

    public static class ConversationNotificationEvent extends BaseConversationEvent {
        private ConversationNotificationStatus mStatus;

        public /* bridge */ /* synthetic */ ConversationType getConversationType() {
            return super.getConversationType();
        }

        public /* bridge */ /* synthetic */ String getTargetId() {
            return super.getTargetId();
        }

        public /* bridge */ /* synthetic */ void setConversationType(ConversationType conversationType) {
            super.setConversationType(conversationType);
        }

        public /* bridge */ /* synthetic */ void setTargetId(String str) {
            super.setTargetId(str);
        }

        public ConversationNotificationEvent(String str, ConversationType conversationType, ConversationNotificationStatus conversationNotificationStatus) {
            setTargetId(str);
            setConversationType(conversationType);
            setStatus(conversationNotificationStatus);
        }

        public static ConversationNotificationEvent obtain(String str, ConversationType conversationType, ConversationNotificationStatus conversationNotificationStatus) {
            return new ConversationNotificationEvent(str, conversationType, conversationNotificationStatus);
        }

        public ConversationNotificationStatus getStatus() {
            return this.mStatus;
        }

        public void setStatus(ConversationNotificationStatus conversationNotificationStatus) {
            this.mStatus = conversationNotificationStatus;
        }
    }

    public static class ConversationRemoveEvent {
        String targetId;
        ConversationType type;

        public ConversationRemoveEvent(ConversationType conversationType, String str) {
            this.type = conversationType;
            this.targetId = str;
        }

        public ConversationType getType() {
            return this.type;
        }

        public void setType(ConversationType conversationType) {
            this.type = conversationType;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public void setTargetId(String str) {
            this.targetId = str;
        }
    }

    public static class ConversationTopEvent extends BaseConversationEvent {
        boolean isTop;

        public /* bridge */ /* synthetic */ ConversationType getConversationType() {
            return super.getConversationType();
        }

        public /* bridge */ /* synthetic */ String getTargetId() {
            return super.getTargetId();
        }

        public /* bridge */ /* synthetic */ void setConversationType(ConversationType conversationType) {
            super.setConversationType(conversationType);
        }

        public /* bridge */ /* synthetic */ void setTargetId(String str) {
            super.setTargetId(str);
        }

        public ConversationTopEvent(ConversationType conversationType, String str, boolean z) {
            setConversationType(conversationType);
            setTargetId(str);
            this.isTop = z;
        }

        public boolean isTop() {
            return this.isTop;
        }

        public void setTop(boolean z) {
            this.isTop = z;
        }
    }

    public static class ConversationUnreadEvent {
        String targetId;
        ConversationType type;

        public ConversationUnreadEvent(ConversationType conversationType, String str) {
            this.type = conversationType;
            this.targetId = str;
        }

        public ConversationType getType() {
            return this.type;
        }

        public void setType(ConversationType conversationType) {
            this.type = conversationType;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public void setTargetId(String str) {
            this.targetId = str;
        }
    }

    public static class CreateDiscussionEvent {
        String discussionId;
        String discussionName;
        List<String> userIdList;

        public CreateDiscussionEvent(String str, String str2, List<String> list) {
            this.discussionId = str;
            this.discussionName = str2;
            this.userIdList = list;
        }

        public String getDiscussionId() {
            return this.discussionId;
        }

        public void setDiscussionId(String str) {
            this.discussionId = str;
        }

        public String getDiscussionName() {
            return this.discussionName;
        }

        public void setDiscussionName(String str) {
            this.discussionName = str;
        }

        public List<String> getUserIdList() {
            return this.userIdList;
        }

        public void setUserIdList(List<String> list) {
            this.userIdList = list;
        }
    }

    public static class DiscussionInviteStatusEvent {
        String discussionId;
        RongIMClient$DiscussionInviteStatus status;

        public DiscussionInviteStatusEvent(String str, RongIMClient$DiscussionInviteStatus rongIMClient$DiscussionInviteStatus) {
            this.discussionId = str;
            this.status = rongIMClient$DiscussionInviteStatus;
        }

        public String getDiscussionId() {
            return this.discussionId;
        }

        public void setDiscussionId(String str) {
            this.discussionId = str;
        }

        public RongIMClient$DiscussionInviteStatus getStatus() {
            return this.status;
        }

        public void setStatus(RongIMClient$DiscussionInviteStatus rongIMClient$DiscussionInviteStatus) {
            this.status = rongIMClient$DiscussionInviteStatus;
        }
    }

    public static class GroupUserInfoEvent {
        private GroupUserInfo userInfo;

        public static GroupUserInfoEvent obtain(GroupUserInfo groupUserInfo) {
            GroupUserInfoEvent groupUserInfoEvent = new GroupUserInfoEvent();
            groupUserInfoEvent.userInfo = groupUserInfo;
            return groupUserInfoEvent;
        }

        public GroupUserInfo getUserInfo() {
            return this.userInfo;
        }
    }

    public static class InputViewEvent {
        private boolean isVisibility;

        public static InputViewEvent obtain(boolean z) {
            InputViewEvent inputViewEvent = new InputViewEvent();
            inputViewEvent.setIsVisibility(z);
            return inputViewEvent;
        }

        public boolean isVisibility() {
            return this.isVisibility;
        }

        public void setIsVisibility(boolean z) {
            this.isVisibility = z;
        }
    }

    public static class JoinChatRoomEvent {
        String chatRoomId;
        int defMessageCount;

        public JoinChatRoomEvent(String str, int i) {
            this.chatRoomId = str;
            this.defMessageCount = i;
        }

        public String getChatRoomId() {
            return this.chatRoomId;
        }

        public void setChatRoomId(String str) {
            this.chatRoomId = str;
        }

        public int getDefMessageCount() {
            return this.defMessageCount;
        }

        public void setDefMessageCount(int i) {
            this.defMessageCount = i;
        }
    }

    public static class JoinGroupEvent {
        String groupId;
        String groupName;

        public JoinGroupEvent(String str, String str2) {
            this.groupId = str;
            this.groupName = str2;
        }

        public String getGroupId() {
            return this.groupId;
        }

        public void setGroupId(String str) {
            this.groupId = str;
        }

        public String getGroupName() {
            return this.groupName;
        }

        public void setGroupName(String str) {
            this.groupName = str;
        }
    }

    public static class MessageDeleteEvent {
        List<Integer> messageIds;

        public MessageDeleteEvent(int... iArr) {
            if (iArr != null && iArr.length != 0) {
                this.messageIds = new ArrayList();
                for (int valueOf : iArr) {
                    this.messageIds.add(Integer.valueOf(valueOf));
                }
            }
        }

        public List<Integer> getMessageIds() {
            return this.messageIds;
        }

        public void setMessageIds(List<Integer> list) {
            this.messageIds = list;
        }
    }

    public static class MessageRecallEvent {
        private int mMessageId;
        private RecallNotificationMessage mRecallNotificationMessage;
        private boolean mRecallSuccess;

        public int getMessageId() {
            return this.mMessageId;
        }

        public RecallNotificationMessage getRecallNotificationMessage() {
            return this.mRecallNotificationMessage;
        }

        public boolean isRecallSuccess() {
            return this.mRecallSuccess;
        }

        public MessageRecallEvent(int i, RecallNotificationMessage recallNotificationMessage, boolean z) {
            this.mMessageId = i;
            this.mRecallNotificationMessage = recallNotificationMessage;
            this.mRecallSuccess = z;
        }
    }

    public static class MessageSentStatusEvent {
        int messageId;
        Message$SentStatus sentStatus;

        public MessageSentStatusEvent(int i, Message$SentStatus message$SentStatus) {
            this.messageId = i;
            this.sentStatus = message$SentStatus;
        }

        public int getMessageId() {
            return this.messageId;
        }

        public void setMessageId(int i) {
            this.messageId = i;
        }

        public Message$SentStatus getSentStatus() {
            return this.sentStatus;
        }

        public void setSentStatus(Message$SentStatus message$SentStatus) {
            this.sentStatus = message$SentStatus;
        }
    }

    public static class MessagesClearEvent {
        String targetId;
        ConversationType type;

        public MessagesClearEvent(ConversationType conversationType, String str) {
            this.type = conversationType;
            this.targetId = str;
        }

        public ConversationType getType() {
            return this.type;
        }

        public void setType(ConversationType conversationType) {
            this.type = conversationType;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public void setTargetId(String str) {
            this.targetId = str;
        }
    }

    public static class NotificationDiscussionInfoEvent {
        private String key;

        NotificationDiscussionInfoEvent(String str) {
            setKey(str);
        }

        public static NotificationDiscussionInfoEvent obtain(String str) {
            return new NotificationDiscussionInfoEvent(str);
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }
    }

    public static class NotificationGroupInfoEvent {
        private String key;

        NotificationGroupInfoEvent(String str) {
            setKey(str);
        }

        public static NotificationGroupInfoEvent obtain(String str) {
            return new NotificationGroupInfoEvent(str);
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }
    }

    public static class NotificationPublicServiceInfoEvent {
        private String key;

        NotificationPublicServiceInfoEvent(String str) {
            setKey(str);
        }

        public static NotificationPublicServiceInfoEvent obtain(String str) {
            return new NotificationPublicServiceInfoEvent(str);
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }
    }

    public static class NotificationUserInfoEvent {
        private String key;

        NotificationUserInfoEvent(String str) {
            setKey(str);
        }

        public static NotificationUserInfoEvent obtain(String str) {
            return new NotificationUserInfoEvent(str);
        }

        public String getKey() {
            return this.key;
        }

        public void setKey(String str) {
            this.key = str;
        }
    }

    public static class OnMessageSendErrorEvent {
        RongIMClient$ErrorCode errorCode;
        Message message;

        public OnMessageSendErrorEvent(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            this.message = message;
            this.errorCode = rongIMClient$ErrorCode;
        }

        public Message getMessage() {
            return this.message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public RongIMClient$ErrorCode getErrorCode() {
            return this.errorCode;
        }

        public void setErrorCode(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            this.errorCode = rongIMClient$ErrorCode;
        }
    }

    public static class OnReceiveMessageEvent {
        int left;
        Message message;

        public OnReceiveMessageEvent(Message message, int i) {
            this.message = message;
            this.left = i;
        }

        public Message getMessage() {
            return this.message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public int getLeft() {
            return this.left;
        }

        public void setLeft(int i) {
            this.left = i;
        }
    }

    public static class OnReceiveMessageProgressEvent {
        Message message;
        int progress;

        public int getProgress() {
            return this.progress;
        }

        public Message getMessage() {
            return this.message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public void setProgress(int i) {
            this.progress = i;
        }
    }

    public static class PlayAudioEvent {
        public MessageContent content;
        public boolean continuously;

        public static PlayAudioEvent obtain() {
            return new PlayAudioEvent();
        }
    }

    public static class PublicServiceFollowableEvent extends BaseConversationEvent {
        private boolean isFollow = false;

        public /* bridge */ /* synthetic */ ConversationType getConversationType() {
            return super.getConversationType();
        }

        public /* bridge */ /* synthetic */ String getTargetId() {
            return super.getTargetId();
        }

        public /* bridge */ /* synthetic */ void setConversationType(ConversationType conversationType) {
            super.setConversationType(conversationType);
        }

        public /* bridge */ /* synthetic */ void setTargetId(String str) {
            super.setTargetId(str);
        }

        public PublicServiceFollowableEvent(String str, ConversationType conversationType, boolean z) {
            setTargetId(str);
            setConversationType(conversationType);
            setIsFollow(z);
        }

        public static PublicServiceFollowableEvent obtain(String str, ConversationType conversationType, boolean z) {
            return new PublicServiceFollowableEvent(str, conversationType, z);
        }

        public boolean isFollow() {
            return this.isFollow;
        }

        public void setIsFollow(boolean z) {
            this.isFollow = z;
        }
    }

    public static class QuitChatRoomEvent {
        String chatRoomId;

        public QuitChatRoomEvent(String str) {
            this.chatRoomId = str;
        }

        public String getChatRoomId() {
            return this.chatRoomId;
        }

        public void setChatRoomId(String str) {
            this.chatRoomId = str;
        }
    }

    public static class QuitDiscussionEvent {
        String discussionId;

        public QuitDiscussionEvent(String str) {
            this.discussionId = str;
        }

        public String getDiscussionId() {
            return this.discussionId;
        }

        public void setDiscussionId(String str) {
            this.discussionId = str;
        }
    }

    public static class QuitGroupEvent {
        String groupId;

        public QuitGroupEvent(String str) {
            this.groupId = str;
        }

        public String getGroupId() {
            return this.groupId;
        }

        public void setGroupId(String str) {
            this.groupId = str;
        }
    }

    public static class ReadReceiptEvent {
        private Message readReceiptMessage;

        public ReadReceiptEvent(Message message) {
            this.readReceiptMessage = message;
        }

        public Message getMessage() {
            return this.readReceiptMessage;
        }
    }

    public static class RemoteMessageRecallEvent {
        private int mMessageId;
        private RecallNotificationMessage mRecallNotificationMessage;
        private boolean mRecallSuccess;

        public int getMessageId() {
            return this.mMessageId;
        }

        public RecallNotificationMessage getRecallNotificationMessage() {
            return this.mRecallNotificationMessage;
        }

        public boolean isRecallSuccess() {
            return this.mRecallSuccess;
        }

        public RemoteMessageRecallEvent(int i, RecallNotificationMessage recallNotificationMessage, boolean z) {
            this.mMessageId = i;
            this.mRecallNotificationMessage = recallNotificationMessage;
            this.mRecallSuccess = z;
        }
    }

    public static class RemoveFromBlacklistEvent {
        String userId;

        public RemoveFromBlacklistEvent(String str) {
            this.userId = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }
    }

    public static class RemoveMemberFromDiscussionEvent {
        String discussionId;
        String userId;

        public RemoveMemberFromDiscussionEvent(String str, String str2) {
            this.discussionId = str;
            this.userId = str2;
        }

        public String getDiscussionId() {
            return this.discussionId;
        }

        public void setDiscussionId(String str) {
            this.discussionId = str;
        }

        public String getUserId() {
            return this.userId;
        }

        public void setUserId(String str) {
            this.userId = str;
        }
    }

    public static class SyncGroupEvent {
        List<Group> groups;

        public SyncGroupEvent(List<Group> list) {
            this.groups = list;
        }

        public List<Group> getGroups() {
            return this.groups;
        }

        public void setGroups(List<Group> list) {
            this.groups = list;
        }
    }

    public static class VoiceInputOperationEvent {
        public static int STATUS_DEFAULT = -1;
        public static int STATUS_INPUTING = 0;
        public static int STATUS_INPUT_COMPLETE = 1;
        private int status;

        public VoiceInputOperationEvent(int i) {
            setStatus(i);
        }

        public static VoiceInputOperationEvent obtain(int i) {
            return new VoiceInputOperationEvent(i);
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }
    }
}
