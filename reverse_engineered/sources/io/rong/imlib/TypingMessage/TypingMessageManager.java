package io.rong.imlib.TypingMessage;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Handler;
import android.os.Looper;
import io.rong.common.RLog;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$TypingStatusListener;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.MessageContent;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TypingMessageManager {
    private static int DISAPPEAR_INTERVAL = 6000;
    private static final String SEPARATOR = ";;;";
    private static final String TAG = "TypingMessageManager";
    private boolean isShowMessageTyping;
    private Handler mHandler;
    private HashMap<String, Long> mSendingConversation;
    private HashMap<String, LinkedHashMap<String, TypingStatus>> mTypingMap;
    private RongIMClient$TypingStatusListener sTypingStatusListener;

    private static class SingletonHolder {
        static TypingMessageManager sInstance = new TypingMessageManager();

        private SingletonHolder() {
        }
    }

    private TypingMessageManager() {
        this.isShowMessageTyping = false;
        this.mTypingMap = new HashMap();
        this.mSendingConversation = new HashMap();
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public static TypingMessageManager getInstance() {
        return SingletonHolder.sInstance;
    }

    public void init(Context context) {
        try {
            Resources resources = context.getResources();
            this.isShowMessageTyping = resources.getBoolean(resources.getIdentifier("rc_typing_status", "bool", context.getPackageName()));
        } catch (NotFoundException e) {
            RLog.m19420e(TAG, "getTypingStatus rc_typing_status not configure in rc_configuration.xml");
            e.printStackTrace();
        }
    }

    public boolean isShowMessageTyping() {
        return this.isShowMessageTyping;
    }

    public Collection<TypingStatus> getTypingUserListFromConversation(ConversationType conversationType, String str) {
        return ((LinkedHashMap) this.mTypingMap.get(conversationType.getName() + SEPARATOR + str)).values();
    }

    public void sendTypingMessage(ConversationType conversationType, String str, String str2) {
        final String str3 = conversationType.getName() + SEPARATOR + str;
        if (!conversationType.equals(ConversationType.PRIVATE)) {
            return;
        }
        if (this.mSendingConversation.containsKey(str3)) {
            RLog.m19419d(TAG, "sendTypingStatus typing message in this conversation is sending");
            return;
        }
        MessageContent typingStatusMessage = new TypingStatusMessage(str2, null);
        this.mSendingConversation.put(str3, Long.valueOf(0));
        RongIMClient.getInstance().sendMessage(conversationType, str, typingStatusMessage, null, null, null, new ResultCallback<Message>() {

            /* renamed from: io.rong.imlib.TypingMessage.TypingMessageManager$1$1 */
            class C53581 implements Runnable {
                C53581() {
                }

                public void run() {
                    TypingMessageManager.this.mSendingConversation.remove(str3);
                }
            }

            public void onSuccess(Message message) {
                TypingMessageManager.this.mHandler.postDelayed(new C53581(), (long) TypingMessageManager.DISAPPEAR_INTERVAL);
            }

            public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            }
        });
    }

    public void setTypingEnd(ConversationType conversationType, String str) {
        String str2 = conversationType.getName() + SEPARATOR + str;
        if (conversationType.equals(ConversationType.PRIVATE) && this.mSendingConversation.containsKey(str2)) {
            this.mSendingConversation.remove(str2);
        }
    }

    public void setTypingMessageStatusListener(RongIMClient$TypingStatusListener rongIMClient$TypingStatusListener) {
        this.sTypingStatusListener = rongIMClient$TypingStatusListener;
    }

    public boolean onReceiveMessage(Message message) {
        if ((message.getContent() instanceof TypingStatusMessage) && this.isShowMessageTyping) {
            getInstance().onReceiveTypingMessage(message);
            return true;
        }
        getInstance().onReceiveOtherMessage(message);
        return false;
    }

    private void onReceiveTypingMessage(Message message) {
        if (!message.getSenderUserId().equals(RongIMClient.getInstance().getCurrentUserId())) {
            final ConversationType conversationType = message.getConversationType();
            final String targetId = message.getTargetId();
            String typingContentType = ((TypingStatusMessage) message.getContent()).getTypingContentType();
            if (typingContentType != null) {
                final String senderUserId = message.getSenderUserId();
                final String str = conversationType.getName() + SEPARATOR + targetId;
                LinkedHashMap linkedHashMap;
                if (this.mTypingMap.containsKey(str)) {
                    linkedHashMap = (LinkedHashMap) this.mTypingMap.get(str);
                    if (linkedHashMap.get(senderUserId) == null) {
                        linkedHashMap.put(senderUserId, new TypingStatus(senderUserId, typingContentType, message.getSentTime()));
                        if (this.sTypingStatusListener != null) {
                            this.sTypingStatusListener.onTypingStatusChanged(conversationType, targetId, linkedHashMap.values());
                            return;
                        }
                        return;
                    }
                    return;
                }
                linkedHashMap = new LinkedHashMap();
                linkedHashMap.put(senderUserId, new TypingStatus(senderUserId, typingContentType, message.getSentTime()));
                if (this.sTypingStatusListener != null) {
                    this.sTypingStatusListener.onTypingStatusChanged(conversationType, targetId, linkedHashMap.values());
                }
                this.mTypingMap.put(str, linkedHashMap);
                this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        if (TypingMessageManager.this.mTypingMap.containsKey(str)) {
                            LinkedHashMap linkedHashMap = (LinkedHashMap) TypingMessageManager.this.mTypingMap.get(str);
                            if (linkedHashMap.get(senderUserId) != null) {
                                linkedHashMap.remove(senderUserId);
                                if (TypingMessageManager.this.sTypingStatusListener != null) {
                                    TypingMessageManager.this.sTypingStatusListener.onTypingStatusChanged(conversationType, targetId, linkedHashMap.values());
                                }
                                if (linkedHashMap.isEmpty()) {
                                    TypingMessageManager.this.mTypingMap.remove(str);
                                }
                            }
                        }
                    }
                }, (long) DISAPPEAR_INTERVAL);
            }
        }
    }

    private void onReceiveOtherMessage(Message message) {
        MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
        if (messageTag != null && (messageTag.flag() & 1) == 1) {
            ConversationType conversationType = message.getConversationType();
            String targetId = message.getTargetId();
            String senderUserId = message.getSenderUserId();
            String str = conversationType.getName() + SEPARATOR + targetId;
            if (this.mTypingMap.containsKey(str)) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) this.mTypingMap.get(str);
                if (linkedHashMap.get(senderUserId) != null) {
                    linkedHashMap.remove(senderUserId);
                    if (this.sTypingStatusListener != null) {
                        this.sTypingStatusListener.onTypingStatusChanged(conversationType, targetId, linkedHashMap.values());
                    }
                    if (linkedHashMap.isEmpty()) {
                        this.mTypingMap.remove(str);
                    }
                }
            }
        }
    }
}
