package io.rong.imkit;

import io.rong.imkit.model.Event.ConversationUnreadEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIM$16 extends ResultCallback<Boolean> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$targetId;

    RongIM$16(RongIM rongIM, ResultCallback resultCallback, ConversationType conversationType, String str) {
        this.this$0 = rongIM;
        this.val$callback = resultCallback;
        this.val$conversationType = conversationType;
        this.val$targetId = str;
    }

    public void onSuccess(Boolean bool) {
        if (this.val$callback != null) {
            this.val$callback.onSuccess(bool);
        }
        RongContext.getInstance().getEventBus().post(new ConversationUnreadEvent(this.val$conversationType, this.val$targetId));
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
