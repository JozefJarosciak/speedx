package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$SentStatus;

class RongIMClient$63 extends ResultCallback<Message> {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$SendImageMessageWithUploadListenerCallback val$callback;
    final /* synthetic */ Message val$message;
    final /* synthetic */ String val$pushContent;
    final /* synthetic */ String val$pushData;

    RongIMClient$63(RongIMClient rongIMClient, RongIMClient$SendImageMessageWithUploadListenerCallback rongIMClient$SendImageMessageWithUploadListenerCallback, String str, String str2, Message message) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$SendImageMessageWithUploadListenerCallback;
        this.val$pushContent = str;
        this.val$pushData = str2;
        this.val$message = message;
    }

    public void onSuccess(Message message) {
        if (message != null) {
            message.setSentStatus(Message$SentStatus.SENDING);
            this.this$0.setMessageSentStatus(message.getMessageId(), Message$SentStatus.SENDING, null);
            if (this.val$callback != null) {
                this.val$callback.onAttachedCallback(message, new RongIMClient$UploadImageStatusListener(this.this$0, message, this.val$pushContent, this.val$pushData, this.val$callback));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Message Content 为空！");
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(this.val$message, rongIMClient$ErrorCode);
        }
    }
}
