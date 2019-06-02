package io.rong.imkit;

import io.rong.imkit.model.Event.RemoteMessageRecallEvent;
import io.rong.imlib.RongIMClient$RecallMessageListener;
import io.rong.message.RecallNotificationMessage;

class RongIM$6 implements RongIMClient$RecallMessageListener {
    RongIM$6() {
    }

    public void onMessageRecalled(int i, RecallNotificationMessage recallNotificationMessage) {
        RongContext.getInstance().getEventBus().post(new RemoteMessageRecallEvent(i, recallNotificationMessage, true));
    }
}
