package io.rong.imkit;

import android.text.Spannable;
import android.text.TextUtils;
import io.rong.common.RLog;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MentionedInfo.MentionedType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.push.RongPushClient;
import io.rong.push.notification.PushNotificationMessage;
import java.util.concurrent.ConcurrentHashMap;

public class RongNotificationManager {
    private static final String TAG = "RongNotificationManager";
    private static RongNotificationManager sS = new RongNotificationManager();
    RongContext mContext;
    ConcurrentHashMap<String, Message> messageMap = new ConcurrentHashMap();

    private RongNotificationManager() {
    }

    public void init(RongContext rongContext) {
        this.mContext = rongContext;
        if (!rongContext.getEventBus().isRegistered(this)) {
            rongContext.getEventBus().register(this);
        }
    }

    public static RongNotificationManager getInstance() {
        if (sS == null) {
            sS = new RongNotificationManager();
        }
        return sS;
    }

    public void onReceiveMessageFromApp(Message message) {
        ConversationType conversationType = message.getConversationType();
        Object obj = null;
        CharSequence charSequence = "";
        MessageProvider messageTemplate = RongContext.getInstance().getMessageTemplate(message.getContent().getClass());
        if (messageTemplate != null) {
            Spannable contentSummary = messageTemplate.getContentSummary(message.getContent());
            ConversationKey obtain = ConversationKey.obtain(message.getTargetId(), message.getConversationType());
            if (obtain == null) {
                RLog.m19420e(TAG, "onReceiveMessageFromApp targetKey is null");
            }
            RLog.m19422i(TAG, "onReceiveMessageFromApp start");
            if (contentSummary == null) {
                RLog.m19422i(TAG, "onReceiveMessageFromApp Content is null. Return directly.");
            } else if (conversationType.equals(ConversationType.PRIVATE) || conversationType.equals(ConversationType.CUSTOMER_SERVICE) || conversationType.equals(ConversationType.CHATROOM) || conversationType.equals(ConversationType.SYSTEM)) {
                UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(message.getTargetId());
                if (userInfo != null) {
                    r0 = userInfo.getName();
                }
                if (!TextUtils.isEmpty(r0)) {
                    r1 = new PushNotificationMessage();
                    r1.setPushContent(contentSummary.toString());
                    r1.setConversationType(RongPushClient.ConversationType.setValue(message.getConversationType().getValue()));
                    r1.setTargetId(message.getTargetId());
                    r1.setTargetUserName(r0);
                    r1.setSenderId(message.getTargetId());
                    r1.setSenderName(r0);
                    r1.setObjectName(message.getObjectName());
                    r1.setPushFlag("false");
                    RongPushClient.sendNotification(this.mContext, r1);
                } else if (obtain != null) {
                    this.messageMap.put(obtain.getKey(), message);
                }
            } else if (conversationType.equals(ConversationType.GROUP)) {
                CharSequence name;
                Group groupInfo = RongUserInfoManager.getInstance().getGroupInfo(message.getTargetId());
                r6 = RongUserInfoManager.getInstance().getUserInfo(message.getSenderUserId());
                if (groupInfo != null) {
                    r2 = groupInfo.getName();
                } else {
                    r2 = null;
                }
                if (r6 != null) {
                    name = r6.getName();
                } else {
                    name = charSequence;
                }
                if (TextUtils.isEmpty(r2) || TextUtils.isEmpty(name)) {
                    if (TextUtils.isEmpty(r2) && obtain != null) {
                        this.messageMap.put(obtain.getKey(), message);
                    }
                    if (TextUtils.isEmpty(name)) {
                        r0 = ConversationKey.obtain(message.getSenderUserId(), conversationType);
                        if (r0 != null) {
                            this.messageMap.put(r0.getKey(), message);
                            return;
                        } else {
                            RLog.m19420e(TAG, "onReceiveMessageFromApp senderKey is null");
                            return;
                        }
                    }
                    return;
                }
                if (!isMentionedMessage(message)) {
                    r0 = name + " : " + contentSummary.toString();
                } else if (TextUtils.isEmpty(message.getContent().getMentionedInfo().getMentionedContent())) {
                    r0 = this.mContext.getString(C4974R.string.rc_message_content_mentioned) + name + " : " + contentSummary.toString();
                } else {
                    r0 = message.getContent().getMentionedInfo().getMentionedContent();
                }
                r1 = new PushNotificationMessage();
                r1.setPushContent(r0);
                r1.setConversationType(RongPushClient.ConversationType.setValue(message.getConversationType().getValue()));
                r1.setTargetId(message.getTargetId());
                r1.setTargetUserName(r2);
                r1.setObjectName(message.getObjectName());
                r1.setPushFlag("false");
                RongPushClient.sendNotification(this.mContext, r1);
            } else if (conversationType.equals(ConversationType.DISCUSSION)) {
                Discussion discussionInfo = RongUserInfoManager.getInstance().getDiscussionInfo(message.getTargetId());
                r6 = RongUserInfoManager.getInstance().getUserInfo(message.getSenderUserId());
                if (discussionInfo != null) {
                    r2 = discussionInfo.getName();
                } else {
                    r2 = null;
                }
                if (r6 != null) {
                    charSequence = r6.getName();
                }
                if (TextUtils.isEmpty(r2) || TextUtils.isEmpty(charSequence)) {
                    if (TextUtils.isEmpty(r2) && obtain != null) {
                        this.messageMap.put(obtain.getKey(), message);
                    }
                    if (TextUtils.isEmpty(charSequence)) {
                        r0 = ConversationKey.obtain(message.getSenderUserId(), conversationType);
                        if (r0 != null) {
                            this.messageMap.put(r0.getKey(), message);
                            return;
                        } else {
                            RLog.m19420e(TAG, "onReceiveMessageFromApp senderKey is null");
                            return;
                        }
                    }
                    return;
                }
                if (!isMentionedMessage(message)) {
                    r0 = charSequence + " : " + contentSummary.toString();
                } else if (TextUtils.isEmpty(message.getContent().getMentionedInfo().getMentionedContent())) {
                    r0 = this.mContext.getString(C4974R.string.rc_message_content_mentioned) + charSequence + " : " + contentSummary.toString();
                } else {
                    r0 = message.getContent().getMentionedInfo().getMentionedContent();
                }
                r1 = new PushNotificationMessage();
                r1.setPushContent(r0);
                r1.setConversationType(RongPushClient.ConversationType.setValue(message.getConversationType().getValue()));
                r1.setTargetId(message.getTargetId());
                r1.setTargetUserName(r2);
                r1.setObjectName(message.getObjectName());
                r1.setPushFlag("false");
                RongPushClient.sendNotification(this.mContext, r1);
            } else if (conversationType.getName().equals(ConversationType.PUBLIC_SERVICE.getName()) || conversationType.getName().equals(PublicServiceType.APP_PUBLIC_SERVICE.getName())) {
                if (obtain != null) {
                    PublicServiceProfile publicServiceInfoFromCache = RongContext.getInstance().getPublicServiceInfoFromCache(obtain.getKey());
                    if (publicServiceInfoFromCache != null) {
                        obj = publicServiceInfoFromCache.getName();
                    }
                }
                if (!TextUtils.isEmpty(obj)) {
                    r1 = new PushNotificationMessage();
                    r1.setPushContent(contentSummary.toString());
                    r1.setConversationType(RongPushClient.ConversationType.setValue(message.getConversationType().getValue()));
                    r1.setTargetId(message.getTargetId());
                    r1.setTargetUserName(obj);
                    r1.setObjectName(message.getObjectName());
                    r1.setPushFlag("false");
                    RongPushClient.sendNotification(this.mContext, r1);
                } else if (obtain != null) {
                    this.messageMap.put(obtain.getKey(), message);
                }
            }
        }
    }

    public void onRemoveNotification() {
        this.messageMap.clear();
        RongPushClient.clearAllPushNotifications(this.mContext);
    }

    public void onEventMainThread(UserInfo userInfo) {
        for (ConversationType conversationType : new ConversationType[]{ConversationType.PRIVATE, ConversationType.GROUP, ConversationType.DISCUSSION, ConversationType.CUSTOMER_SERVICE, ConversationType.CHATROOM, ConversationType.SYSTEM}) {
            String key = ConversationKey.obtain(userInfo.getUserId(), conversationType).getKey();
            if (this.messageMap.containsKey(key)) {
                String str;
                Message message = (Message) this.messageMap.get(key);
                Object obj = "";
                String str2 = "";
                Spannable contentSummary = RongContext.getInstance().getMessageTemplate(message.getContent().getClass()).getContentSummary(message.getContent());
                this.messageMap.remove(key);
                if (conversationType.equals(ConversationType.GROUP)) {
                    Group groupInfo = RongUserInfoManager.getInstance().getGroupInfo(message.getTargetId());
                    if (groupInfo != null) {
                        obj = groupInfo.getName();
                    }
                    if (!isMentionedMessage(message)) {
                        str = userInfo.getName() + " : " + contentSummary.toString();
                    } else if (TextUtils.isEmpty(message.getContent().getMentionedInfo().getMentionedContent())) {
                        str = this.mContext.getString(C4974R.string.rc_message_content_mentioned) + userInfo.getName() + " : " + contentSummary.toString();
                    } else {
                        str = message.getContent().getMentionedInfo().getMentionedContent();
                    }
                } else if (conversationType.equals(ConversationType.DISCUSSION)) {
                    Discussion discussionInfo = RongUserInfoManager.getInstance().getDiscussionInfo(message.getTargetId());
                    if (discussionInfo != null) {
                        obj = discussionInfo.getName();
                    }
                    if (!isMentionedMessage(message)) {
                        str = userInfo.getName() + " : " + contentSummary.toString();
                    } else if (TextUtils.isEmpty(message.getContent().getMentionedInfo().getMentionedContent())) {
                        str = this.mContext.getString(C4974R.string.rc_message_content_mentioned) + userInfo.getName() + " : " + contentSummary.toString();
                    } else {
                        str = message.getContent().getMentionedInfo().getMentionedContent();
                    }
                } else {
                    obj = userInfo.getName();
                    str = contentSummary.toString();
                }
                if (!TextUtils.isEmpty(obj)) {
                    PushNotificationMessage pushNotificationMessage = new PushNotificationMessage();
                    pushNotificationMessage.setPushContent(str);
                    pushNotificationMessage.setConversationType(RongPushClient.ConversationType.setValue(message.getConversationType().getValue()));
                    pushNotificationMessage.setTargetId(message.getTargetId());
                    pushNotificationMessage.setTargetUserName(obj);
                    pushNotificationMessage.setObjectName(message.getObjectName());
                    pushNotificationMessage.setPushFlag("false");
                    RongPushClient.sendNotification(this.mContext, pushNotificationMessage);
                } else {
                    return;
                }
            }
        }
    }

    public void onEventMainThread(Group group) {
        String key = ConversationKey.obtain(group.getId(), ConversationType.GROUP).getKey();
        if (this.messageMap.containsKey(key)) {
            Message message = (Message) this.messageMap.get(key);
            String str = "";
            Spannable contentSummary = RongContext.getInstance().getMessageTemplate(message.getContent().getClass()).getContentSummary(message.getContent());
            this.messageMap.remove(key);
            UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(message.getSenderUserId());
            if (userInfo != null) {
                str = userInfo.getName();
                if (TextUtils.isEmpty(str)) {
                    return;
                }
            }
            PushNotificationMessage pushNotificationMessage = new PushNotificationMessage();
            pushNotificationMessage.setPushContent(str + " : " + contentSummary.toString());
            pushNotificationMessage.setConversationType(RongPushClient.ConversationType.setValue(message.getConversationType().getValue()));
            pushNotificationMessage.setTargetId(message.getTargetId());
            pushNotificationMessage.setTargetUserName(group.getName());
            pushNotificationMessage.setObjectName(message.getObjectName());
            pushNotificationMessage.setPushFlag("false");
            RongPushClient.sendNotification(this.mContext, pushNotificationMessage);
        }
    }

    public void onEventMainThread(Discussion discussion) {
        String key = ConversationKey.obtain(discussion.getId(), ConversationType.DISCUSSION).getKey();
        if (this.messageMap.containsKey(key)) {
            String str = "";
            Message message = (Message) this.messageMap.get(key);
            Spannable contentSummary = RongContext.getInstance().getMessageTemplate(message.getContent().getClass()).getContentSummary(message.getContent());
            this.messageMap.remove(key);
            UserInfo userInfo = RongUserInfoManager.getInstance().getUserInfo(message.getSenderUserId());
            if (userInfo != null) {
                str = userInfo.getName();
                if (TextUtils.isEmpty(str)) {
                    return;
                }
            }
            PushNotificationMessage pushNotificationMessage = new PushNotificationMessage();
            pushNotificationMessage.setPushContent(str + " : " + contentSummary.toString());
            pushNotificationMessage.setConversationType(RongPushClient.ConversationType.setValue(message.getConversationType().getValue()));
            pushNotificationMessage.setTargetId(message.getTargetId());
            pushNotificationMessage.setTargetUserName(discussion.getName());
            pushNotificationMessage.setObjectName(message.getObjectName());
            pushNotificationMessage.setPushFlag("false");
            RongPushClient.sendNotification(this.mContext, pushNotificationMessage);
        }
    }

    public void onEventMainThread(PublicServiceProfile publicServiceProfile) {
        String key = ConversationKey.obtain(publicServiceProfile.getTargetId(), publicServiceProfile.getConversationType()).getKey();
        if (this.messageMap.containsKey(key)) {
            Message message = (Message) this.messageMap.get(key);
            Spannable contentSummary = RongContext.getInstance().getMessageTemplate(message.getContent().getClass()).getContentSummary(message.getContent());
            PushNotificationMessage pushNotificationMessage = new PushNotificationMessage();
            pushNotificationMessage.setPushContent(contentSummary.toString());
            pushNotificationMessage.setConversationType(RongPushClient.ConversationType.setValue(message.getConversationType().getValue()));
            pushNotificationMessage.setTargetId(message.getTargetId());
            pushNotificationMessage.setTargetUserName(publicServiceProfile.getName());
            pushNotificationMessage.setObjectName(message.getObjectName());
            pushNotificationMessage.setPushFlag("false");
            RongPushClient.sendNotification(this.mContext, pushNotificationMessage);
            this.messageMap.remove(key);
        }
    }

    private boolean isMentionedMessage(Message message) {
        MentionedInfo mentionedInfo = message.getContent().getMentionedInfo();
        if (mentionedInfo == null || (!mentionedInfo.getType().equals(MentionedType.ALL) && (!mentionedInfo.getType().equals(MentionedType.PART) || mentionedInfo.getMentionedUserIdList() == null || !mentionedInfo.getMentionedUserIdList().contains(RongIMClient.getInstance().getCurrentUserId())))) {
            return false;
        }
        return true;
    }
}
