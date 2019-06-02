package io.rong.imlib;

import io.rong.message.RecallNotificationMessage;

public interface RongIMClient$RecallMessageListener {
    void onMessageRecalled(int i, RecallNotificationMessage recallNotificationMessage);
}
