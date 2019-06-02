package io.rong.imkit;

import io.rong.imlib.model.Message;

public interface RongIM$OnSendMessageListener {
    Message onSend(Message message);

    boolean onSent(Message message, RongIM$SentMessageErrorCode rongIM$SentMessageErrorCode);
}
