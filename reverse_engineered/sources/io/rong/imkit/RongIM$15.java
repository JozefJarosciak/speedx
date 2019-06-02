package io.rong.imkit;

import io.rong.imkit.model.Event.MessagesClearEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIM$15 extends ResultCallback<Boolean> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$targetId;

    RongIM$15(RongIM rongIM, ConversationType conversationType, String str, ResultCallback resultCallback) {
        this.this$0 = rongIM;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
        this.val$callback = resultCallback;
    }

    public void onSuccess(Boolean bool) {
        if (bool.booleanValue()) {
            RongContext.getInstance().getEventBus().post(new MessagesClearEvent(this.val$conversationType, this.val$targetId));
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
