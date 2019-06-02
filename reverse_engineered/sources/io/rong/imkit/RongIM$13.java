package io.rong.imkit;

import io.rong.imkit.model.Event.MessageDeleteEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;

class RongIM$13 extends ResultCallback<Boolean> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ int[] val$messageIds;

    RongIM$13(RongIM rongIM, int[] iArr, ResultCallback resultCallback) {
        this.this$0 = rongIM;
        this.val$messageIds = iArr;
        this.val$callback = resultCallback;
    }

    public void onSuccess(Boolean bool) {
        if (bool.booleanValue()) {
            RongContext.getInstance().getEventBus().post(new MessageDeleteEvent(this.val$messageIds));
        }
        if (this.val$callback != null) {
            this.val$callback.onCallback(bool);
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
