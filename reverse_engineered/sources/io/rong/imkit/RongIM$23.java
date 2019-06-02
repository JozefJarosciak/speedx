package io.rong.imkit;

import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;

class RongIM$23 extends ResultCallback<Message> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;

    RongIM$23(RongIM rongIM, ResultCallback resultCallback) {
        this.this$0 = rongIM;
        this.val$callback = resultCallback;
    }

    public void onSuccess(Message message) {
        if (this.val$callback != null) {
            this.val$callback.onSuccess(message);
        }
        RongContext.getInstance().getEventBus().post(message);
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
        RongContext.getInstance().getEventBus().post(rongIMClient$ErrorCode);
    }
}
