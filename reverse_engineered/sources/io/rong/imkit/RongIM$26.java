package io.rong.imkit;

import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$ResultCallback$Result;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;

class RongIM$26 extends ResultCallback<Message> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$ResultCallback$Result val$result;
    final /* synthetic */ ResultCallback val$resultCallback;

    RongIM$26(RongIM rongIM, RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result, ResultCallback resultCallback) {
        this.this$0 = rongIM;
        this.val$result = rongIMClient$ResultCallback$Result;
        this.val$resultCallback = resultCallback;
    }

    public void onSuccess(Message message) {
        MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
        if (messageTag != null && (messageTag.flag() & 1) == 1) {
            RongContext.getInstance().getEventBus().post(message);
        }
        this.val$result.f17368t = message;
        if (this.val$resultCallback != null) {
            this.val$resultCallback.onSuccess(message);
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RongContext.getInstance().getEventBus().post(rongIMClient$ErrorCode);
        if (this.val$resultCallback != null) {
            this.val$resultCallback.onError(rongIMClient$ErrorCode);
        }
    }
}
