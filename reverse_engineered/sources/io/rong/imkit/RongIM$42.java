package io.rong.imkit;

import io.rong.imkit.model.Event.QuitGroupEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;

class RongIM$42 extends RongIMClient$OperationCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$groupId;

    RongIM$42(RongIM rongIM, String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        this.this$0 = rongIM;
        this.val$groupId = str;
        this.val$callback = rongIMClient$OperationCallback;
    }

    public void onSuccess() {
        RongContext.getInstance().getEventBus().post(new QuitGroupEvent(this.val$groupId));
        if (this.val$callback != null) {
            this.val$callback.onSuccess();
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
