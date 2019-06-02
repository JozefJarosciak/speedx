package io.rong.imlib;

import io.rong.imlib.model.Message;

public abstract class RongIMClient$SendImageMessageCallback extends RongIMClient$SendMessageCallback {
    public abstract void onAttached(Message message);

    public abstract void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode);

    public abstract void onProgress(Message message, int i);

    public abstract void onSuccess(Message message);

    void onProgressCallback(final Message message, final int i) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$SendImageMessageCallback.this.onProgress(message, i);
            }
        });
    }

    void onAttachedCallback(final Message message) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$SendImageMessageCallback.this.onAttached(message);
            }
        });
    }

    void onFail(final Message message, final RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$SendImageMessageCallback.this.onError(message, rongIMClient$ErrorCode);
            }
        });
    }

    public void onSuccess(Integer num) {
    }

    public void onError(Integer num, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
    }
}
