package io.rong.imkit;

import io.rong.imkit.model.Event.OnReceiveMessageEvent;
import io.rong.imkit.notification.MessageNotificationManager;
import io.rong.imkit.utils.CommonUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient.OnReceiveMessageListener;
import io.rong.imlib.model.Message;

class RongIM$4 implements OnReceiveMessageListener {
    RongIM$4() {
    }

    public boolean onReceived(Message message, int i) {
        boolean onReceived;
        if (RongIM.sMessageListener != null) {
            onReceived = RongIM.sMessageListener.onReceived(message, i);
        } else {
            onReceived = false;
        }
        MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
        if (messageTag != null && (messageTag.flag() == 3 || messageTag.flag() == 1)) {
            RongContext.getInstance().getEventBus().post(new OnReceiveMessageEvent(message, i));
            if (!(message.getContent() == null || message.getContent().getUserInfo() == null)) {
                CommonUtils.refreshUserInfoIfNeed(RongContext.getInstance(), message.getContent().getUserInfo());
            }
            if (onReceived || message.getSenderUserId().equals(RongIM.getInstance().getCurrentUserId())) {
                return true;
            }
            MessageNotificationManager.getInstance().notifyIfNeed(RongContext.getInstance(), message, i);
            return false;
        } else if (message.getMessageId() <= 0) {
            return false;
        } else {
            RongContext.getInstance().getEventBus().post(new OnReceiveMessageEvent(message, i));
            return false;
        }
    }
}
