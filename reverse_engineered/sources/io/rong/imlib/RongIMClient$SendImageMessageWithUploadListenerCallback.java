package io.rong.imlib;

import io.rong.imlib.model.Message;

public abstract class RongIMClient$SendImageMessageWithUploadListenerCallback {
    public abstract void onAttached(Message message, RongIMClient$UploadImageStatusListener rongIMClient$UploadImageStatusListener);

    public abstract void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode);

    public abstract void onProgress(Message message, int i);

    public abstract void onSuccess(Message message);

    void onAttachedCallback(final Message message, final RongIMClient$UploadImageStatusListener rongIMClient$UploadImageStatusListener) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$SendImageMessageWithUploadListenerCallback.this.onAttached(message, rongIMClient$UploadImageStatusListener);
            }
        });
    }

    void onFail(final Message message, final RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$SendImageMessageWithUploadListenerCallback.this.onError(message, rongIMClient$ErrorCode);
            }
        });
    }
}
