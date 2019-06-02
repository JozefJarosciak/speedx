package io.rong.imlib;

import io.rong.imlib.model.Message;

public interface IRongCallback {

    public interface ISendMessageCallback {
        void onAttached(Message message);

        void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode);

        void onSuccess(Message message);
    }
}
