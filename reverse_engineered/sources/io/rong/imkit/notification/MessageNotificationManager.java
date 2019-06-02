package io.rong.imkit.notification;

import android.content.Context;
import android.text.TextUtils;
import io.rong.common.RLog;
import io.rong.common.SystemUtils;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongNotificationManager;
import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.utils.CommonUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.MentionedInfo;
import io.rong.imlib.model.MentionedInfo.MentionedType;
import io.rong.imlib.model.Message;
import java.util.Calendar;

public class MessageNotificationManager {
    private static final String TAG = "MessageNotificationManager";

    private static class SingletonHolder {
        static final MessageNotificationManager instance = new MessageNotificationManager();

        private SingletonHolder() {
        }
    }

    public static MessageNotificationManager getInstance() {
        return SingletonHolder.instance;
    }

    public void notifyIfNeed(final Context context, final Message message, final int i) {
        if (message.getContent().getMentionedInfo() != null) {
            MentionedInfo mentionedInfo = message.getContent().getMentionedInfo();
            if (mentionedInfo.getType().equals(MentionedType.ALL) || (mentionedInfo.getType().equals(MentionedType.PART) && mentionedInfo.getMentionedUserIdList() != null && mentionedInfo.getMentionedUserIdList().contains(RongIMClient.getInstance().getCurrentUserId()))) {
                notify(context, message, i);
                return;
            }
        }
        if (!isInQuietTime(context)) {
            if (RongContext.getInstance() != null) {
                ConversationNotificationStatus conversationNotifyStatusFromCache = RongContext.getInstance().getConversationNotifyStatusFromCache(ConversationKey.obtain(message.getTargetId(), message.getConversationType()));
                if (conversationNotifyStatusFromCache != null) {
                    if (conversationNotifyStatusFromCache == ConversationNotificationStatus.NOTIFY) {
                        notify(context, message, i);
                        return;
                    }
                    return;
                }
            }
            RongIM.getInstance().getConversationNotificationStatus(message.getConversationType(), message.getTargetId(), new ResultCallback<ConversationNotificationStatus>() {
                public void onSuccess(ConversationNotificationStatus conversationNotificationStatus) {
                    if (ConversationNotificationStatus.NOTIFY == conversationNotificationStatus) {
                        MessageNotificationManager.getInstance().notify(context, message, i);
                    }
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            });
        }
    }

    private void notify(Context context, Message message, int i) {
        Object obj = !SystemUtils.isAppRunningOnTop(context, context.getPackageName()) ? 1 : null;
        if (message.getConversationType() != ConversationType.CHATROOM) {
            if (obj != null) {
                RongNotificationManager.getInstance().onReceiveMessageFromApp(message);
            } else if (!CommonUtils.isInConversationPager(message.getTargetId(), message.getConversationType())) {
                MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
                if (messageTag != null && (messageTag.flag() & 3) == 3) {
                    MessageSounder.getInstance().messageReminder();
                }
            }
        }
    }

    private boolean isInQuietTime(Context context) {
        int parseInt;
        int parseInt2;
        int parseInt3;
        String notificationQuietHoursForStartTime = CommonUtils.getNotificationQuietHoursForStartTime(context);
        if (!TextUtils.isEmpty(notificationQuietHoursForStartTime) && notificationQuietHoursForStartTime.contains(":")) {
            String[] split = notificationQuietHoursForStartTime.split(":");
            try {
                if (split.length >= 3) {
                    parseInt = Integer.parseInt(split[0]);
                    try {
                        parseInt2 = Integer.parseInt(split[1]);
                        try {
                            parseInt3 = Integer.parseInt(split[2]);
                        } catch (NumberFormatException e) {
                            RLog.m19420e(TAG, "getConversationNotificationStatus NumberFormatException");
                            parseInt3 = -1;
                            if (parseInt != -1) {
                            }
                            return false;
                        }
                    } catch (NumberFormatException e2) {
                        parseInt2 = -1;
                        RLog.m19420e(TAG, "getConversationNotificationStatus NumberFormatException");
                        parseInt3 = -1;
                        if (parseInt != -1) {
                        }
                        return false;
                    }
                    if (parseInt != -1 || parseInt2 == -1 || parseInt3 == -1) {
                        return false;
                    }
                    Calendar instance = Calendar.getInstance();
                    instance.set(11, parseInt);
                    instance.set(12, parseInt2);
                    instance.set(13, parseInt3);
                    long notificationQuietHoursForSpanMinutes = (long) (CommonUtils.getNotificationQuietHoursForSpanMinutes(context) * 60);
                    long timeInMillis = instance.getTimeInMillis();
                    Calendar instance2 = Calendar.getInstance();
                    instance2.setTimeInMillis((notificationQuietHoursForSpanMinutes * 1000) + timeInMillis);
                    Calendar instance3 = Calendar.getInstance();
                    if (instance3.get(5) == instance2.get(5)) {
                        return instance3.after(instance) && instance3.before(instance2);
                    } else {
                        if (!instance3.before(instance)) {
                            return true;
                        }
                        instance2.set(5, instance3.get(5));
                        return instance3.before(instance2);
                    }
                }
            } catch (NumberFormatException e3) {
                parseInt2 = -1;
                parseInt = -1;
                RLog.m19420e(TAG, "getConversationNotificationStatus NumberFormatException");
                parseInt3 = -1;
                if (parseInt != -1) {
                }
                return false;
            }
        }
        parseInt3 = -1;
        parseInt2 = -1;
        parseInt = -1;
        if (parseInt != -1) {
        }
        return false;
    }
}
