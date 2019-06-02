package io.rong.imkit;

import io.rong.imkit.model.Event.RemoveMemberFromDiscussionEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;

class RongIM$21 extends RongIMClient$OperationCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$discussionId;
    final /* synthetic */ String val$userId;

    RongIM$21(RongIM rongIM, String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        this.this$0 = rongIM;
        this.val$discussionId = str;
        this.val$userId = str2;
        this.val$callback = rongIMClient$OperationCallback;
    }

    public void onSuccess() {
        RongContext.getInstance().getEventBus().post(new RemoveMemberFromDiscussionEvent(this.val$discussionId, this.val$userId));
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
