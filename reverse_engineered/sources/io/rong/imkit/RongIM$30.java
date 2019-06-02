package io.rong.imkit;

import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.model.Message;

class RongIM$30 implements ISendMessageCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ISendMessageCallback val$callback;

    RongIM$30(RongIM rongIM, ISendMessageCallback iSendMessageCallback) {
        this.this$0 = rongIM;
        this.val$callback = iSendMessageCallback;
    }

    public void onAttached(Message message) {
        MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
        if (messageTag != null && (messageTag.flag() & 1) == 1) {
            RongContext.getInstance().getEventBus().post(message);
        }
        if (this.val$callback != null) {
            this.val$callback.onAttached(message);
        }
    }

    public void onSuccess(Message message) {
        RongIM.access$600(this.this$0, message, null);
        if (this.val$callback != null) {
            this.val$callback.onSuccess(message);
        }
    }

    public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RongIM.access$600(this.this$0, message, rongIMClient$ErrorCode);
        if (this.val$callback != null) {
            this.val$callback.onError(message, rongIMClient$ErrorCode);
        }
    }
}
