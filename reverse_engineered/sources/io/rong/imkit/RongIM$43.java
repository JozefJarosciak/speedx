package io.rong.imkit;

import io.rong.imkit.model.Event.JoinChatRoomEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;

class RongIM$43 extends RongIMClient$OperationCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$chatroomId;
    final /* synthetic */ int val$defMessageCount;

    RongIM$43(RongIM rongIM, String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        this.this$0 = rongIM;
        this.val$chatroomId = str;
        this.val$defMessageCount = i;
        this.val$callback = rongIMClient$OperationCallback;
    }

    public void onSuccess() {
        RongContext.getInstance().getEventBus().post(new JoinChatRoomEvent(this.val$chatroomId, this.val$defMessageCount));
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
