package io.rong.imkit;

import io.rong.imkit.model.Event.ConversationTopEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIM$11 extends ResultCallback<Boolean> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$id;
    final /* synthetic */ boolean val$isTop;
    final /* synthetic */ ConversationType val$type;

    RongIM$11(RongIM rongIM, ResultCallback resultCallback, ConversationType conversationType, String str, boolean z) {
        this.this$0 = rongIM;
        this.val$callback = resultCallback;
        this.val$type = conversationType;
        this.val$id = str;
        this.val$isTop = z;
    }

    public void onSuccess(Boolean bool) {
        if (this.val$callback != null) {
            this.val$callback.onSuccess(bool);
        }
        if (bool.booleanValue()) {
            RongContext.getInstance().getEventBus().post(new ConversationTopEvent(this.val$type, this.val$id, this.val$isTop));
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onFail(rongIMClient$ErrorCode);
        }
    }
}
