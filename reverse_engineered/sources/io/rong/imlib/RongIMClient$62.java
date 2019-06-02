package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$SentStatus;

class RongIMClient$62 extends ResultCallback<Message> {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$SendImageMessageCallback val$callback;
    final /* synthetic */ RongIMClient$ResultCallback$Result val$result;
    final /* synthetic */ RongIMClient$UploadMediaCallback val$uploadMediaCallback;

    RongIMClient$62(RongIMClient rongIMClient, RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback, RongIMClient$UploadMediaCallback rongIMClient$UploadMediaCallback) {
        this.this$0 = rongIMClient;
        this.val$result = rongIMClient$ResultCallback$Result;
        this.val$callback = rongIMClient$SendImageMessageCallback;
        this.val$uploadMediaCallback = rongIMClient$UploadMediaCallback;
    }

    public void onSuccess(Message message) {
        if (message != null) {
            this.val$result.f17368t = message;
            message.setSentStatus(Message$SentStatus.SENDING);
            this.this$0.setMessageSentStatus(message.getMessageId(), Message$SentStatus.SENDING, null);
            if (this.val$callback != null) {
                this.val$callback.onAttachedCallback(message);
            }
            RongIMClient.access$2300(this.this$0, message, RongIMClient$MediaType.IMAGE, this.val$uploadMediaCallback);
            return;
        }
        throw new IllegalArgumentException("Message Content 为空！");
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onFail(rongIMClient$ErrorCode);
        }
    }
}
