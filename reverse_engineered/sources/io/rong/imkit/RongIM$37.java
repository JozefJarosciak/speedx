package io.rong.imkit;

import io.rong.imkit.model.ConversationKey;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIM$37 extends ResultCallback<ConversationNotificationStatus> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ String val$targetId;

    RongIM$37(RongIM rongIM, String str, ConversationType conversationType, ResultCallback resultCallback) {
        this.this$0 = rongIM;
        this.val$targetId = str;
        this.val$conversationType = conversationType;
        this.val$callback = resultCallback;
    }

    public void onSuccess(ConversationNotificationStatus conversationNotificationStatus) {
        RongContext.getInstance().setConversationNotifyStatusToCache(ConversationKey.obtain(this.val$targetId, this.val$conversationType), conversationNotificationStatus);
        if (this.val$callback != null) {
            this.val$callback.onSuccess(conversationNotificationStatus);
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
