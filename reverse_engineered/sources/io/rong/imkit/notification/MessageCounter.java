package io.rong.imkit.notification;

import android.os.Handler;
import android.os.Looper;
import io.rong.common.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ConversationInfo;
import io.rong.imkit.model.ConversationTypeFilter;
import io.rong.imkit.model.ConversationTypeFilter.Level;
import io.rong.imkit.model.Event.ConversationRemoveEvent;
import io.rong.imkit.model.Event.ConversationUnreadEvent;
import io.rong.imkit.model.Event.OnReceiveMessageEvent;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageCounter {
    RongContext mContext;
    List<Counter> mCounters = new ArrayList();
    Handler mHandler = new Handler(Looper.getMainLooper());

    public static class Counter {
        int mCount;
        ConversationTypeFilter mFilter;

        public Counter(ConversationTypeFilter conversationTypeFilter) {
            this.mFilter = conversationTypeFilter;
        }

        void onIncreased() {
            int i = this.mCount + 1;
            this.mCount = i;
            onMessageIncreased(i);
        }

        public void onMessageIncreased(int i) {
        }

        public ConversationTypeFilter getFilter() {
            return this.mFilter;
        }

        boolean isCount(Message message) {
            return this.mFilter.hasFilter(message);
        }
    }

    public MessageCounter(RongContext rongContext) {
        this.mContext = rongContext;
        rongContext.getEventBus().register(this);
    }

    public void registerMessageCounter(final Counter counter) {
        this.mCounters.add(counter);
        if (counter.getFilter().getLevel().equals(Level.ALL)) {
            RongIM.getInstance().getTotalUnreadCount(new ResultCallback<Integer>() {
                int currentConversationMsgCount = 0;

                public void onSuccess(Integer num) {
                    for (ConversationInfo conversationInfo : RongContext.getInstance().getCurrentConversationList()) {
                        this.currentConversationMsgCount = RongIM.getInstance().getUnreadCount(conversationInfo.getConversationType(), conversationInfo.getTargetId()) + this.currentConversationMsgCount;
                    }
                    int intValue = num.intValue() - this.currentConversationMsgCount;
                    counter.mCount = intValue;
                    counter.onMessageIncreased(intValue);
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            });
        } else if (counter.getFilter().getLevel().equals(Level.CONVERSATION_TYPE)) {
            ConversationType[] conversationTypeArr = (ConversationType[]) counter.getFilter().getConversationTypeList().toArray(new ConversationType[counter.getFilter().getConversationTypeList().size()]);
            RLog.m19419d("registerMessageCounter", "RongIM.getInstance() :" + conversationTypeArr.length);
            RongIM.getInstance().getUnreadCount(conversationTypeArr, new ResultCallback<Integer>() {
                public void onSuccess(Integer num) {
                    int i = 0;
                    for (ConversationInfo conversationInfo : RongContext.getInstance().getCurrentConversationList()) {
                        i = RongIM.getInstance().getUnreadCount(conversationInfo.getConversationType(), conversationInfo.getTargetId()) + i;
                    }
                    int intValue = num.intValue() - i;
                    counter.mCount = intValue;
                    counter.onMessageIncreased(intValue);
                }

                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                }
            });
        }
    }

    public void unregisterMessageCounter(MessageCounter messageCounter) {
        this.mCounters.remove(messageCounter);
    }

    public void clearCache() {
        for (Counter counter : this.mCounters) {
            counter.mCount = 0;
            counter.onMessageIncreased(0);
        }
    }

    public void onEventBackgroundThread(OnReceiveMessageEvent onReceiveMessageEvent) {
        Message message = onReceiveMessageEvent.getMessage();
        for (ConversationInfo conversationInfo : RongContext.getInstance().getCurrentConversationList()) {
            if (message.getConversationType() == conversationInfo.getConversationType() && conversationInfo.getTargetId() != null && conversationInfo.getTargetId().equals(message.getTargetId())) {
                return;
            }
        }
        if (message.getContent() != null) {
            MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
            if (messageTag != null && (messageTag.flag() & 3) == 3) {
                for (final Counter counter : this.mCounters) {
                    if (counter.isCount(message)) {
                        if (onReceiveMessageEvent.getLeft() != 0) {
                            this.mHandler.post(new Runnable() {
                                public void run() {
                                    counter.onIncreased();
                                }
                            });
                        } else {
                            RongIM.getInstance().getUnreadCount((ConversationType[]) counter.getFilter().getConversationTypeList().toArray(new ConversationType[counter.getFilter().getConversationTypeList().size()]), new ResultCallback<Integer>() {
                                public void onSuccess(Integer num) {
                                    int i = 0;
                                    for (ConversationInfo conversationInfo : RongContext.getInstance().getCurrentConversationList()) {
                                        i = RongIM.getInstance().getUnreadCount(conversationInfo.getConversationType(), conversationInfo.getTargetId()) + i;
                                    }
                                    int intValue = num.intValue() - i;
                                    counter.mCount = intValue;
                                    counter.onMessageIncreased(intValue);
                                }

                                public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                                }
                            });
                        }
                    }
                }
            }
        }
    }

    public void onEvent(ConversationRemoveEvent conversationRemoveEvent) {
        this.mContext.getEventBus().post(new ConversationUnreadEvent(conversationRemoveEvent.getType(), conversationRemoveEvent.getTargetId()));
    }

    public void onEvent(ConversationUnreadEvent conversationUnreadEvent) {
        for (final Counter counter : this.mCounters) {
            if (counter.getFilter().getLevel().equals(Level.ALL)) {
                RongIM.getInstance().getUnreadCount(new ResultCallback<Integer>() {
                    public void onSuccess(Integer num) {
                        counter.mCount = num.intValue();
                        counter.onMessageIncreased(num.intValue());
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                }, new ConversationType[0]);
            } else if (counter.getFilter().getLevel().equals(Level.CONVERSATION_TYPE)) {
                RongIM.getInstance().getUnreadCount((ConversationType[]) counter.getFilter().getConversationTypeList().toArray(new ConversationType[counter.getFilter().getConversationTypeList().size()]), new ResultCallback<Integer>() {
                    public void onSuccess(Integer num) {
                        counter.mCount = num.intValue();
                        counter.onMessageIncreased(num.intValue());
                    }

                    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
                    }
                });
            }
        }
    }
}
