package io.rong.message;

import io.rong.imlib.model.Message;

public interface IHandleMessageListener {
    void onHandleResult(Message message, int i);
}
