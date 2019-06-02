package io.rong.imkit;

import io.rong.imkit.model.Event.ClearConversationEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIM$46 extends ResultCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType[] val$conversationTypes;

    RongIM$46(RongIM rongIM, ConversationType[] conversationTypeArr, ResultCallback resultCallback) {
        this.this$0 = rongIM;
        this.val$conversationTypes = conversationTypeArr;
        this.val$callback = resultCallback;
    }

    public void onSuccess(Object obj) {
        RongContext.getInstance().getEventBus().post(ClearConversationEvent.obtain(this.val$conversationTypes));
        if (this.val$callback != null) {
            this.val$callback.onSuccess(obj);
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
