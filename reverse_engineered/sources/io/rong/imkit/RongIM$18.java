package io.rong.imkit;

import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;
import io.rong.imlib.model.Discussion;

class RongIM$18 extends RongIMClient$OperationCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$discussionId;
    final /* synthetic */ String val$name;

    RongIM$18(RongIM rongIM, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, String str2) {
        this.this$0 = rongIM;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$discussionId = str;
        this.val$name = str2;
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }

    public void onSuccess() {
        if (this.val$callback != null) {
            RongUserInfoManager.getInstance().setDiscussionInfo(new Discussion(this.val$discussionId, this.val$name));
            this.val$callback.onSuccess();
        }
    }
}
