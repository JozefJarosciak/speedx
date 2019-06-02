package io.rong.imkit;

import io.rong.imkit.model.Event.MessageSentStatusEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message$SentStatus;

class RongIM$17 extends ResultCallback<Boolean> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ int val$messageId;
    final /* synthetic */ Message$SentStatus val$sentStatus;

    RongIM$17(RongIM rongIM, ResultCallback resultCallback, int i, Message$SentStatus message$SentStatus) {
        this.this$0 = rongIM;
        this.val$callback = resultCallback;
        this.val$messageId = i;
        this.val$sentStatus = message$SentStatus;
    }

    public void onSuccess(Boolean bool) {
        if (this.val$callback != null) {
            this.val$callback.onSuccess(bool);
        }
        if (bool.booleanValue()) {
            RongContext.getInstance().getEventBus().post(new MessageSentStatusEvent(this.val$messageId, this.val$sentStatus));
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
