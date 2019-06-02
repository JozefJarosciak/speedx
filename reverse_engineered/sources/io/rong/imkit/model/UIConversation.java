package io.rong.imkit.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import io.rong.common.RLog;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;
import io.rong.message.VoiceMessage;
import java.util.ArrayList;

public class UIConversation implements Parcelable {
    public static final Creator<UIConversation> CREATOR = new C51071();
    private Spannable conversationContent;
    private long conversationTime;
    private String conversationTitle;
    private ConversationType conversationType;
    private String draft;
    private boolean extraFlag;
    private boolean isGathered;
    private boolean isMentioned;
    private boolean isTop;
    private int latestMessageId;
    private UnreadRemindType mUnreadType = UnreadRemindType.REMIND_WITH_COUNTING;
    private MessageContent messageContent;
    private ArrayList<String> nicknameIds = new ArrayList();
    private boolean notificationBlockStatus;
    private Uri portrait;
    private String senderId;
    private Message$SentStatus sentStatus;
    private String targetId;
    private int unReadMessageCount;

    /* renamed from: io.rong.imkit.model.UIConversation$1 */
    static class C51071 implements Creator<UIConversation> {
        C51071() {
        }

        public UIConversation createFromParcel(Parcel parcel) {
            return new UIConversation();
        }

        public UIConversation[] newArray(int i) {
            return new UIConversation[i];
        }
    }

    public enum UnreadRemindType {
        NO_REMIND,
        REMIND_ONLY,
        REMIND_WITH_COUNTING
    }

    public boolean getExtraFlag() {
        return this.extraFlag;
    }

    public void setExtraFlag(boolean z) {
        this.extraFlag = z;
    }

    public void setUIConversationTitle(String str) {
        this.conversationTitle = str;
    }

    public String getUIConversationTitle() {
        return this.conversationTitle;
    }

    public void setIconUrl(Uri uri) {
        this.portrait = uri;
    }

    public Uri getIconUrl() {
        return this.portrait;
    }

    public void setConversationContent(Spannable spannable) {
        this.conversationContent = spannable;
    }

    public Spannable getConversationContent() {
        return this.conversationContent;
    }

    public void setMessageContent(MessageContent messageContent) {
        this.messageContent = messageContent;
    }

    public MessageContent getMessageContent() {
        return this.messageContent;
    }

    public void setUIConversationTime(long j) {
        this.conversationTime = j;
    }

    public long getUIConversationTime() {
        return this.conversationTime;
    }

    public void setUnReadMessageCount(int i) {
        this.unReadMessageCount = i;
    }

    public int getUnReadMessageCount() {
        return this.unReadMessageCount;
    }

    public void setTop(boolean z) {
        this.isTop = z;
    }

    public boolean isTop() {
        return this.isTop;
    }

    public void setConversationType(ConversationType conversationType) {
        this.conversationType = conversationType;
    }

    public ConversationType getConversationType() {
        return this.conversationType;
    }

    public void setSentStatus(Message$SentStatus message$SentStatus) {
        this.sentStatus = message$SentStatus;
    }

    public Message$SentStatus getSentStatus() {
        return this.sentStatus;
    }

    public void setConversationTargetId(String str) {
        this.targetId = str;
    }

    public String getConversationTargetId() {
        return this.targetId;
    }

    public void setConversationSenderId(String str) {
        this.senderId = str;
    }

    public String getConversationSenderId() {
        return this.senderId;
    }

    public void setConversationGatherState(boolean z) {
        this.isGathered = z;
    }

    public boolean getConversationGatherState() {
        return this.isGathered;
    }

    public void setNotificationBlockStatus(boolean z) {
        this.notificationBlockStatus = z;
    }

    public boolean getNotificationBlockStatus() {
        return this.notificationBlockStatus;
    }

    public void setDraft(String str) {
        this.draft = str;
    }

    public String getDraft() {
        return this.draft;
    }

    public void setLatestMessageId(int i) {
        this.latestMessageId = i;
    }

    public int getLatestMessageId() {
        return this.latestMessageId;
    }

    public void addNickname(String str) {
        this.nicknameIds.add(str);
    }

    public void removeNickName(String str) {
        this.nicknameIds.remove(str);
    }

    public boolean hasNickname(String str) {
        return this.nicknameIds.contains(str);
    }

    public void setMentionedFlag(boolean z) {
        this.isMentioned = z;
    }

    public boolean getMentionedFlag() {
        return this.isMentioned;
    }

    public static UIConversation obtain(Conversation conversation, boolean z) {
        if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongContext hasn't been initialized !!");
        } else if (RongContext.getInstance().getConversationTemplate(conversation.getConversationType().getName()) == null) {
            throw new IllegalArgumentException("the conversation type hasn't been registered! type:" + conversation.getConversationType());
        } else {
            Uri uri;
            MessageContent latestMessage = conversation.getLatestMessage();
            if (TextUtils.isEmpty(conversation.getPortraitUrl())) {
                uri = null;
            } else {
                uri = Uri.parse(conversation.getPortraitUrl());
            }
            String conversationTitle = conversation.getConversationTitle();
            if (uri == null || conversationTitle == null) {
                conversationTitle = RongContext.getInstance().getConversationTemplate(conversation.getConversationType().getName()).getTitle(conversation.getTargetId());
                uri = RongContext.getInstance().getConversationTemplate(conversation.getConversationType().getName()).getPortraitUri(conversation.getTargetId());
            }
            UIConversation uIConversation = new UIConversation();
            uIConversation.setMessageContent(latestMessage);
            uIConversation.setUnReadMessageCount(conversation.getUnreadMessageCount());
            uIConversation.setUIConversationTime(conversation.getSentTime());
            uIConversation.setConversationGatherState(z);
            if (!z || RongContext.getInstance() == null) {
                uIConversation.setUIConversationTitle(conversationTitle);
                uIConversation.setIconUrl(uri);
            } else {
                uIConversation.setUIConversationTitle(RongContext.getInstance().getGatheredConversationTitle(conversation.getConversationType()));
                uIConversation.setIconUrl(null);
            }
            uIConversation.setConversationType(conversation.getConversationType());
            uIConversation.setTop(conversation.isTop());
            uIConversation.setSentStatus(conversation.getSentStatus());
            uIConversation.setConversationTargetId(conversation.getTargetId());
            uIConversation.setConversationSenderId(conversation.getSenderUserId());
            uIConversation.setLatestMessageId(conversation.getLatestMessageId());
            uIConversation.setDraft(conversation.getDraft());
            if (conversation.getMentionedCount() > 0) {
                uIConversation.isMentioned = true;
            } else {
                uIConversation.isMentioned = false;
            }
            if (!TextUtils.isEmpty(conversation.getDraft())) {
                uIConversation.setSentStatus(null);
            }
            uIConversation.setConversationContent(uIConversation.buildConversationContent(uIConversation));
            return uIConversation;
        }
    }

    public static UIConversation obtain(Message message, boolean z) {
        Uri uri;
        Uri uri2;
        boolean z2;
        String str = "";
        UserInfo userInfo = message.getContent().getUserInfo();
        ConversationType conversationType = message.getConversationType();
        if (userInfo != null && message.getTargetId().equals(userInfo.getUserId()) && (conversationType.equals(ConversationType.PRIVATE) || conversationType.equals(ConversationType.SYSTEM))) {
            Uri portraitUri = userInfo.getPortraitUri();
            str = userInfo.getName();
            RongIMClient.getInstance().updateConversationInfo(message.getConversationType(), message.getTargetId(), str, portraitUri != null ? portraitUri.toString() : "", null);
            uri = portraitUri;
        } else {
            uri = null;
        }
        if (RongContext.getInstance() == null || !(uri == null || r3 == null)) {
            uri2 = uri;
        } else {
            str = RongContext.getInstance().getConversationTemplate(message.getConversationType().getName()).getTitle(message.getTargetId());
            uri2 = RongContext.getInstance().getConversationTemplate(message.getConversationType().getName()).getPortraitUri(message.getTargetId());
        }
        MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
        UIConversation uIConversation = new UIConversation();
        if (messageTag != null && (messageTag.flag() & 3) == 3 && message.getMessageDirection().equals(Message$MessageDirection.RECEIVE)) {
            uIConversation.setUnReadMessageCount(1);
        }
        uIConversation.setMessageContent(message.getContent());
        uIConversation.setUIConversationTime(message.getSentTime());
        if (z) {
            uIConversation.setUIConversationTitle(RongContext.getInstance().getGatheredConversationTitle(message.getConversationType()));
            uIConversation.setIconUrl(null);
        } else {
            uIConversation.setUIConversationTitle(str);
            uIConversation.setIconUrl(uri2);
        }
        uIConversation.setConversationType(message.getConversationType());
        uIConversation.setConversationTargetId(message.getTargetId());
        if (message.getMessageDirection() == Message$MessageDirection.SEND) {
            uIConversation.setConversationSenderId(RongIM.getInstance().getCurrentUserId());
        } else {
            uIConversation.setConversationSenderId(message.getSenderUserId());
        }
        uIConversation.setSentStatus(message.getSentStatus());
        uIConversation.setLatestMessageId(message.getMessageId());
        uIConversation.setConversationGatherState(z);
        uIConversation.setConversationContent(uIConversation.buildConversationContent(uIConversation));
        if (message.getContent().getMentionedInfo() == null) {
            z2 = false;
        } else {
            z2 = true;
        }
        uIConversation.setMentionedFlag(z2);
        return uIConversation;
    }

    public SpannableStringBuilder buildConversationContent(UIConversation uIConversation) {
        boolean conversationGatherState = uIConversation.getConversationGatherState();
        String name = uIConversation.getConversationType().getName();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (uIConversation.getMessageContent() == null) {
            spannableStringBuilder.append("");
            return spannableStringBuilder;
        }
        ProviderTag messageProviderTag = RongContext.getInstance().getMessageProviderTag(uIConversation.getMessageContent().getClass());
        MessageProvider messageTemplate = RongContext.getInstance().getMessageTemplate(uIConversation.getMessageContent().getClass());
        if (messageProviderTag == null || messageTemplate == null) {
            RLog.m19420e("UIMessage", "Can not find ProviderTag");
            spannableStringBuilder.append("");
            return spannableStringBuilder;
        }
        CharSequence contentSummary = messageTemplate.getContentSummary(uIConversation.getMessageContent());
        boolean showSummaryWithName = messageProviderTag.showSummaryWithName();
        if (contentSummary == null) {
            spannableStringBuilder.append("");
            return spannableStringBuilder;
        }
        if (uIConversation.getMessageContent() instanceof VoiceMessage) {
            Conversation conversation = RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId());
            if (conversation != null) {
                boolean isListened = conversation.getReceivedStatus().isListened();
                if (conversation.getSenderUserId().equals(RongIMClient.getInstance().getCurrentUserId()) || isListened) {
                    contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_text_color_secondary)), 0, contentSummary.length(), 33);
                } else {
                    contentSummary.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(C4974R.color.rc_voice_color)), 0, contentSummary.length(), 33);
                }
            }
        }
        if (showSummaryWithName) {
            if (ConversationType.GROUP.getName().equals(name) || ConversationType.DISCUSSION.getName().equals(name)) {
                Conversation conversation2 = RongIM.getInstance().getConversation(uIConversation.getConversationType(), uIConversation.getConversationTargetId());
                if (conversation2 != null && conversation2.getSenderUserId().equals(RongIMClient.getInstance().getCurrentUserId())) {
                    return spannableStringBuilder.append(contentSummary);
                }
            }
            CharSequence conversationSenderId = uIConversation.getConversationSenderId();
            if (conversationGatherState) {
                conversationSenderId = RongContext.getInstance().getConversationTemplate(name).getTitle(uIConversation.getConversationTargetId());
                if (conversationSenderId == null) {
                    conversationSenderId = uIConversation.getConversationTargetId();
                }
                spannableStringBuilder.append(conversationSenderId).append(" : ").append(contentSummary);
            } else if (ConversationType.GROUP.getName().equals(name)) {
                GroupUserInfo groupUserInfo = RongUserInfoManager.getInstance().getGroupUserInfo(uIConversation.targetId, conversationSenderId);
                if (groupUserInfo != null) {
                    r1 = groupUserInfo.getNickname();
                } else {
                    UserInfo userInfo = uIConversation.getMessageContent().getUserInfo();
                    if (userInfo == null || userInfo.getName() == null) {
                        r1 = RongContext.getInstance().getConversationTemplate(ConversationType.PRIVATE.getName()).getTitle(conversationSenderId);
                    } else {
                        r1 = userInfo.getName();
                    }
                }
                if (r1 != null) {
                    r0 = r1;
                } else if (conversationSenderId == null) {
                    conversationSenderId = "";
                }
                spannableStringBuilder.append(conversationSenderId).append(" : ").append(contentSummary);
            } else if (!ConversationType.DISCUSSION.getName().equals(name)) {
                return spannableStringBuilder.append(contentSummary);
            } else {
                r1 = RongContext.getInstance().getConversationTemplate(ConversationType.PRIVATE.getName()).getTitle(uIConversation.getConversationSenderId());
                if (r1 != null) {
                    r0 = r1;
                } else if (conversationSenderId == null) {
                    conversationSenderId = "";
                }
                spannableStringBuilder.append(conversationSenderId).append(" : ").append(contentSummary);
            }
            return spannableStringBuilder;
        }
        spannableStringBuilder.append(contentSummary);
        return spannableStringBuilder;
    }

    public void setUnreadType(UnreadRemindType unreadRemindType) {
        this.mUnreadType = unreadRemindType;
    }

    public UnreadRemindType getUnReadType() {
        return this.mUnreadType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }
}
