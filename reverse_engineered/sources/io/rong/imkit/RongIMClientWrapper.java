package io.rong.imkit;

import io.rong.imlib.AnnotationNotFoundException;
import io.rong.imlib.RongIMClient$BlacklistStatus;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import io.rong.imlib.RongIMClient$CreateDiscussionCallback;
import io.rong.imlib.RongIMClient$DiscussionInviteStatus;
import io.rong.imlib.RongIMClient$DownloadMediaCallback;
import io.rong.imlib.RongIMClient$GetBlacklistCallback;
import io.rong.imlib.RongIMClient$GetNotificationQuietHoursCallback;
import io.rong.imlib.RongIMClient$MediaType;
import io.rong.imlib.RongIMClient$OperationCallback;
import io.rong.imlib.RongIMClient$SearchType;
import io.rong.imlib.RongIMClient$SendImageMessageCallback;
import io.rong.imlib.RongIMClient$SendImageMessageWithUploadListenerCallback;
import io.rong.imlib.RongIMClient$SendMessageCallback;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.ConnectionStatusListener;
import io.rong.imlib.RongIMClient.OnReceiveMessageListener;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$ReceivedStatus;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.PublicServiceProfileList;
import io.rong.imlib.model.UserData;
import java.util.List;

public class RongIMClientWrapper {
    @Deprecated
    public RongIMClientWrapper connect(String str, ConnectCallback connectCallback) {
        RongIM.connect(str, connectCallback);
        return this;
    }

    @Deprecated
    public static void setConnectionStatusListener(ConnectionStatusListener connectionStatusListener) {
        RongIM.setConnectionStatusListener(connectionStatusListener);
    }

    @Deprecated
    public static void registerMessageType(Class<? extends MessageContent> cls) throws AnnotationNotFoundException {
        RongIM.registerMessageType(cls);
    }

    @Deprecated
    public RongIMClient$ConnectionStatusListener$ConnectionStatus getCurrentConnectionStatus() {
        return RongIM.getInstance().getCurrentConnectionStatus();
    }

    @Deprecated
    public void disconnect() {
        RongIM.getInstance().disconnect();
    }

    @Deprecated
    public void disconnect(boolean z) {
        RongIM.getInstance().disconnect(z);
    }

    @Deprecated
    public void logout() {
        RongIM.getInstance().logout();
    }

    @Deprecated
    public static void setOnReceiveMessageListener(OnReceiveMessageListener onReceiveMessageListener) {
        RongIM.setOnReceiveMessageListener(onReceiveMessageListener);
    }

    @Deprecated
    public void getConversationList(ResultCallback<List<Conversation>> resultCallback) {
        RongIM.getInstance().getConversationList((ResultCallback) resultCallback);
    }

    @Deprecated
    public List<Conversation> getConversationList() {
        return RongIM.getInstance().getConversationList();
    }

    @Deprecated
    public void getConversationList(ResultCallback<List<Conversation>> resultCallback, ConversationType... conversationTypeArr) {
        RongIM.getInstance().getConversationList(resultCallback, conversationTypeArr);
    }

    @Deprecated
    public List<Conversation> getConversationList(ConversationType... conversationTypeArr) {
        return RongIM.getInstance().getConversationList(conversationTypeArr);
    }

    @Deprecated
    public void getConversation(ConversationType conversationType, String str, ResultCallback<Conversation> resultCallback) {
        RongIM.getInstance().getConversation(conversationType, str, resultCallback);
    }

    @Deprecated
    public Conversation getConversation(ConversationType conversationType, String str) {
        return RongIM.getInstance().getConversation(conversationType, str);
    }

    @Deprecated
    public void removeConversation(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().removeConversation(conversationType, str, resultCallback);
    }

    @Deprecated
    public boolean removeConversation(ConversationType conversationType, String str) {
        return RongIM.getInstance().removeConversation(conversationType, str);
    }

    @Deprecated
    public void setConversationToTop(ConversationType conversationType, String str, boolean z, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().setConversationToTop(conversationType, str, z, resultCallback);
    }

    @Deprecated
    public boolean setConversationToTop(ConversationType conversationType, String str, boolean z) {
        return RongIM.getInstance().setConversationToTop(conversationType, str, z);
    }

    @Deprecated
    public void getTotalUnreadCount(ResultCallback<Integer> resultCallback) {
        RongIM.getInstance().getTotalUnreadCount(resultCallback);
    }

    @Deprecated
    public int getTotalUnreadCount() {
        return RongIM.getInstance().getTotalUnreadCount();
    }

    @Deprecated
    public void getUnreadCount(ConversationType conversationType, String str, ResultCallback<Integer> resultCallback) {
        RongIM.getInstance().getUnreadCount(conversationType, str, resultCallback);
    }

    @Deprecated
    public int getUnreadCount(ConversationType conversationType, String str) {
        return RongIM.getInstance().getUnreadCount(conversationType, str);
    }

    @Deprecated
    public void getUnreadCount(ResultCallback<Integer> resultCallback, ConversationType... conversationTypeArr) {
        RongIM.getInstance().getUnreadCount((ResultCallback) resultCallback, conversationTypeArr);
    }

    @Deprecated
    public int getUnreadCount(ConversationType... conversationTypeArr) {
        return RongIM.getInstance().getUnreadCount(conversationTypeArr);
    }

    @Deprecated
    public void getUnreadCount(ConversationType[] conversationTypeArr, ResultCallback<Integer> resultCallback) {
        RongIM.getInstance().getUnreadCount(conversationTypeArr, (ResultCallback) resultCallback);
    }

    @Deprecated
    public List<Message> getLatestMessages(ConversationType conversationType, String str, int i) {
        return RongIM.getInstance().getLatestMessages(conversationType, str, i);
    }

    @Deprecated
    public void getLatestMessages(ConversationType conversationType, String str, int i, ResultCallback<List<Message>> resultCallback) {
        RongIM.getInstance().getLatestMessages(conversationType, str, i, resultCallback);
    }

    @Deprecated
    public List<Message> getHistoryMessages(ConversationType conversationType, String str, int i, int i2) {
        return RongIM.getInstance().getHistoryMessages(conversationType, str, i, i2);
    }

    @Deprecated
    public List<Message> getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2) {
        return RongIM.getInstance().getHistoryMessages(conversationType, str, str2, i, i2);
    }

    @Deprecated
    public void getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2, ResultCallback<List<Message>> resultCallback) {
        RongIM.getInstance().getHistoryMessages(conversationType, str, str2, i, i2, resultCallback);
    }

    @Deprecated
    public void getHistoryMessages(ConversationType conversationType, String str, int i, int i2, ResultCallback<List<Message>> resultCallback) {
        RongIM.getInstance().getHistoryMessages(conversationType, str, i, i2, (ResultCallback) resultCallback);
    }

    @Deprecated
    public void getRemoteHistoryMessages(ConversationType conversationType, String str, long j, int i, ResultCallback<List<Message>> resultCallback) {
        RongIM.getInstance().getRemoteHistoryMessages(conversationType, str, j, i, resultCallback);
    }

    @Deprecated
    public boolean deleteMessages(int[] iArr) {
        return RongIM.getInstance().deleteMessages(iArr);
    }

    @Deprecated
    public void deleteMessages(int[] iArr, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().deleteMessages(iArr, resultCallback);
    }

    @Deprecated
    public boolean clearMessages(ConversationType conversationType, String str) {
        return RongIM.getInstance().clearMessages(conversationType, str);
    }

    @Deprecated
    public void clearMessages(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().clearMessages(conversationType, str, resultCallback);
    }

    @Deprecated
    public boolean clearMessagesUnreadStatus(ConversationType conversationType, String str) {
        return RongIM.getInstance().clearMessagesUnreadStatus(conversationType, str);
    }

    @Deprecated
    public void clearMessagesUnreadStatus(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().clearMessagesUnreadStatus(conversationType, str, resultCallback);
    }

    @Deprecated
    public boolean setMessageExtra(int i, String str) {
        return RongIM.getInstance().setMessageExtra(i, str);
    }

    @Deprecated
    public void setMessageExtra(int i, String str, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().setMessageExtra(i, str, resultCallback);
    }

    @Deprecated
    public boolean setMessageReceivedStatus(int i, Message$ReceivedStatus message$ReceivedStatus) {
        return RongIM.getInstance().setMessageReceivedStatus(i, message$ReceivedStatus);
    }

    @Deprecated
    public void setMessageReceivedStatus(int i, Message$ReceivedStatus message$ReceivedStatus, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().setMessageReceivedStatus(i, message$ReceivedStatus, resultCallback);
    }

    @Deprecated
    public boolean setMessageSentStatus(int i, Message$SentStatus message$SentStatus) {
        return RongIM.getInstance().setMessageSentStatus(i, message$SentStatus);
    }

    @Deprecated
    public void setMessageSentStatus(int i, Message$SentStatus message$SentStatus, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().setMessageSentStatus(i, message$SentStatus, resultCallback);
    }

    @Deprecated
    public String getTextMessageDraft(ConversationType conversationType, String str) {
        return RongIM.getInstance().getTextMessageDraft(conversationType, str);
    }

    @Deprecated
    public boolean saveTextMessageDraft(ConversationType conversationType, String str, String str2) {
        return RongIM.getInstance().saveTextMessageDraft(conversationType, str, str2);
    }

    @Deprecated
    public boolean clearTextMessageDraft(ConversationType conversationType, String str) {
        return RongIM.getInstance().clearTextMessageDraft(conversationType, str);
    }

    @Deprecated
    public void getTextMessageDraft(ConversationType conversationType, String str, ResultCallback<String> resultCallback) {
        RongIM.getInstance().getTextMessageDraft(conversationType, str, resultCallback);
    }

    @Deprecated
    public void saveTextMessageDraft(ConversationType conversationType, String str, String str2, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().saveTextMessageDraft(conversationType, str, str2, resultCallback);
    }

    @Deprecated
    public void clearTextMessageDraft(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIM.getInstance().clearTextMessageDraft(conversationType, str, resultCallback);
    }

    @Deprecated
    public void getDiscussion(String str, ResultCallback<Discussion> resultCallback) {
        RongIM.getInstance().getDiscussion(str, resultCallback);
    }

    @Deprecated
    public void setDiscussionName(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().setDiscussionName(str, str2, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void createDiscussion(String str, List<String> list, RongIMClient$CreateDiscussionCallback rongIMClient$CreateDiscussionCallback) {
        RongIM.getInstance().createDiscussion(str, list, rongIMClient$CreateDiscussionCallback);
    }

    @Deprecated
    public void addMemberToDiscussion(String str, List<String> list, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().addMemberToDiscussion(str, list, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void removeMemberFromDiscussion(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().removeMemberFromDiscussion(str, str2, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void quitDiscussion(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().quitDiscussion(str, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void insertMessage(ConversationType conversationType, String str, String str2, MessageContent messageContent, ResultCallback<Message> resultCallback) {
        RongIM.getInstance().insertMessage(conversationType, str, str2, messageContent, resultCallback);
    }

    @Deprecated
    public Message insertMessage(ConversationType conversationType, String str, String str2, MessageContent messageContent) {
        return RongIM.getInstance().insertMessage(conversationType, str, str2, messageContent);
    }

    @Deprecated
    public Message sendMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback) {
        return RongIM.getInstance().sendMessage(conversationType, str, messageContent, str2, str3, rongIMClient$SendMessageCallback);
    }

    @Deprecated
    public void sendMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, ResultCallback<Message> resultCallback) {
        RongIM.getInstance().sendMessage(conversationType, str, messageContent, str2, str3, rongIMClient$SendMessageCallback, resultCallback);
    }

    @Deprecated
    public void sendMessage(Message message, String str, String str2, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, ResultCallback<Message> resultCallback) {
        RongIM.getInstance().sendMessage(message, str, str2, rongIMClient$SendMessageCallback, resultCallback);
    }

    @Deprecated
    public Message sendMessage(Message message, String str, String str2, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback) {
        return RongIM.getInstance().sendMessage(message, str, str2, rongIMClient$SendMessageCallback);
    }

    @Deprecated
    public void sendImageMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback) {
        RongIM.getInstance().sendImageMessage(conversationType, str, messageContent, str2, str3, rongIMClient$SendImageMessageCallback);
    }

    @Deprecated
    public void sendImageMessage(Message message, String str, String str2, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback) {
        RongIM.getInstance().sendImageMessage(message, str, str2, rongIMClient$SendImageMessageCallback);
    }

    @Deprecated
    public void sendImageMessage(Message message, String str, String str2, RongIMClient$SendImageMessageWithUploadListenerCallback rongIMClient$SendImageMessageWithUploadListenerCallback) {
        RongIM.getInstance().sendImageMessage(message, str, str2, rongIMClient$SendImageMessageWithUploadListenerCallback);
    }

    @Deprecated
    public void downloadMedia(ConversationType conversationType, String str, RongIMClient$MediaType rongIMClient$MediaType, String str2, RongIMClient$DownloadMediaCallback rongIMClient$DownloadMediaCallback) {
        RongIM.getInstance().downloadMedia(conversationType, str, rongIMClient$MediaType, str2, rongIMClient$DownloadMediaCallback);
    }

    @Deprecated
    public void downloadMedia(String str, RongIMClient$DownloadMediaCallback rongIMClient$DownloadMediaCallback) {
        RongIM.getInstance().downloadMedia(str, rongIMClient$DownloadMediaCallback);
    }

    @Deprecated
    public void getConversationNotificationStatus(ConversationType conversationType, String str, ResultCallback<ConversationNotificationStatus> resultCallback) {
        RongIM.getInstance().getConversationNotificationStatus(conversationType, str, resultCallback);
    }

    @Deprecated
    public void setConversationNotificationStatus(ConversationType conversationType, String str, ConversationNotificationStatus conversationNotificationStatus, ResultCallback<ConversationNotificationStatus> resultCallback) {
        RongIM.getInstance().setConversationNotificationStatus(conversationType, str, conversationNotificationStatus, resultCallback);
    }

    @Deprecated
    public void setDiscussionInviteStatus(String str, RongIMClient$DiscussionInviteStatus rongIMClient$DiscussionInviteStatus, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().setDiscussionInviteStatus(str, rongIMClient$DiscussionInviteStatus, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void syncGroup(List<Group> list, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().syncGroup(list, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void joinGroup(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().joinGroup(str, str2, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void quitGroup(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().quitGroup(str, rongIMClient$OperationCallback);
    }

    @Deprecated
    public String getCurrentUserId() {
        return RongIM.getInstance().getCurrentUserId();
    }

    @Deprecated
    public long getDeltaTime() {
        return RongIM.getInstance().getDeltaTime();
    }

    @Deprecated
    public void joinChatRoom(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().joinChatRoom(str, i, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void joinExistChatRoom(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().joinExistChatRoom(str, i, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void quitChatRoom(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().quitChatRoom(str, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void clearConversations(ResultCallback resultCallback, ConversationType... conversationTypeArr) {
        RongIM.getInstance().clearConversations(resultCallback, conversationTypeArr);
    }

    @Deprecated
    public boolean clearConversations(ConversationType... conversationTypeArr) {
        return RongIM.getInstance().clearConversations(conversationTypeArr);
    }

    @Deprecated
    public void addToBlacklist(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().addToBlacklist(str, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void removeFromBlacklist(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().removeFromBlacklist(str, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void getBlacklistStatus(String str, ResultCallback<RongIMClient$BlacklistStatus> resultCallback) {
        RongIM.getInstance().getBlacklistStatus(str, resultCallback);
    }

    @Deprecated
    public void getBlacklist(RongIMClient$GetBlacklistCallback rongIMClient$GetBlacklistCallback) {
        RongIM.getInstance().getBlacklist(rongIMClient$GetBlacklistCallback);
    }

    @Deprecated
    public void setNotificationQuietHours(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().setNotificationQuietHours(str, i, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void removeNotificationQuietHours(RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().removeNotificationQuietHours(rongIMClient$OperationCallback);
    }

    @Deprecated
    public void getNotificationQuietHours(RongIMClient$GetNotificationQuietHoursCallback rongIMClient$GetNotificationQuietHoursCallback) {
        RongIM.getInstance().getNotificationQuietHours(rongIMClient$GetNotificationQuietHoursCallback);
    }

    @Deprecated
    public void getPublicServiceProfile(PublicServiceType publicServiceType, String str, ResultCallback<PublicServiceProfile> resultCallback) {
        RongIM.getInstance().getPublicServiceProfile(publicServiceType, str, resultCallback);
    }

    @Deprecated
    public void searchPublicService(RongIMClient$SearchType rongIMClient$SearchType, String str, ResultCallback<PublicServiceProfileList> resultCallback) {
        RongIM.getInstance().searchPublicService(rongIMClient$SearchType, str, resultCallback);
    }

    @Deprecated
    public void searchPublicServiceByType(PublicServiceType publicServiceType, RongIMClient$SearchType rongIMClient$SearchType, String str, ResultCallback<PublicServiceProfileList> resultCallback) {
        RongIM.getInstance().searchPublicServiceByType(publicServiceType, rongIMClient$SearchType, str, resultCallback);
    }

    @Deprecated
    public void subscribePublicService(PublicServiceType publicServiceType, String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().subscribePublicService(publicServiceType, str, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void unsubscribePublicService(PublicServiceType publicServiceType, String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().unsubscribePublicService(publicServiceType, str, rongIMClient$OperationCallback);
    }

    @Deprecated
    public void getPublicServiceList(ResultCallback<PublicServiceProfileList> resultCallback) {
        RongIM.getInstance().getPublicServiceList(resultCallback);
    }

    @Deprecated
    public void syncUserData(UserData userData, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIM.getInstance().syncUserData(userData, rongIMClient$OperationCallback);
    }
}
