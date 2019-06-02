package io.rong.imkit;

import io.rong.imkit.model.Event.ConversationRemoveEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIM$10 extends ResultCallback<Boolean> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$targetId;
    final /* synthetic */ ConversationType val$type;

    RongIM$10(RongIM rongIM, ResultCallback resultCallback, ConversationType conversationType, String str) {
        this.this$0 = rongIM;
        this.val$callback = resultCallback;
        this.val$type = conversationType;
        this.val$targetId = str;
    }

    public void onSuccess(Boolean bool) {
        if (this.val$callback != null) {
            this.val$callback.onSuccess(bool);
        }
        if (bool.booleanValue()) {
            RongContext.getInstance().getEventBus().post(new ConversationRemoveEvent(this.val$type, this.val$targetId));
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onFail(rongIMClient$ErrorCode);
        }
    }
}
