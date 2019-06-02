package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;

public abstract class RongIMClient$UploadMediaCallback extends ResultCallback<Message> {
    public abstract void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode);

    public abstract void onProgress(Message message, int i);

    void onProgressCallback(final Message message, final int i) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$UploadMediaCallback.this.onProgress(message, i);
            }
        });
    }

    void onFail(final Message message, final RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RongIMClient.access$1600().postDelayed(new Runnable() {
            public void run() {
                RongIMClient$UploadMediaCallback.this.onError(message, rongIMClient$ErrorCode);
            }
        }, 100);
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
    }
}
