package io.rong.imkit;

import io.rong.imkit.model.Event.DiscussionInviteStatusEvent;
import io.rong.imlib.RongIMClient$DiscussionInviteStatus;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;

class RongIM$39 extends RongIMClient$OperationCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$discussionId;
    final /* synthetic */ RongIMClient$DiscussionInviteStatus val$status;

    RongIM$39(RongIM rongIM, String str, RongIMClient$DiscussionInviteStatus rongIMClient$DiscussionInviteStatus, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        this.this$0 = rongIM;
        this.val$discussionId = str;
        this.val$status = rongIMClient$DiscussionInviteStatus;
        this.val$callback = rongIMClient$OperationCallback;
    }

    public void onSuccess() {
        RongContext.getInstance().getEventBus().post(new DiscussionInviteStatusEvent(this.val$discussionId, this.val$status));
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
