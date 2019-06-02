package io.rong.imkit;

import io.rong.imkit.model.Event.OnReceiveMessageProgressEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$ResultCallback$Result;
import io.rong.imlib.RongIMClient$SendImageMessageCallback;
import io.rong.imlib.model.Message;

class RongIM$33 extends RongIMClient$SendImageMessageCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$SendImageMessageCallback val$callback;
    final /* synthetic */ RongIMClient$ResultCallback$Result val$result;

    RongIM$33(RongIM rongIM, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback, RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result) {
        this.this$0 = rongIM;
        this.val$callback = rongIMClient$SendImageMessageCallback;
        this.val$result = rongIMClient$ResultCallback$Result;
    }

    public void onAttached(Message message) {
        RongContext.getInstance().getEventBus().post(message);
        if (this.val$callback != null) {
            this.val$callback.onAttached(message);
        }
    }

    public void onProgress(Message message, int i) {
        if (this.val$result.f17368t != null) {
            ((OnReceiveMessageProgressEvent) this.val$result.f17368t).setMessage(message);
            ((OnReceiveMessageProgressEvent) this.val$result.f17368t).setProgress(i);
            RongContext.getInstance().getEventBus().post(this.val$result.f17368t);
            if (this.val$callback != null) {
                this.val$callback.onProgress(message, i);
            }
        }
    }

    public void onError(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RongIM.access$600(this.this$0, message, rongIMClient$ErrorCode);
        if (this.val$callback != null) {
            this.val$callback.onError(message, rongIMClient$ErrorCode);
        }
    }

    public void onSuccess(Message message) {
        RongIM.access$600(this.this$0, message, null);
        if (this.val$callback != null) {
            this.val$callback.onSuccess(message);
        }
    }
}
