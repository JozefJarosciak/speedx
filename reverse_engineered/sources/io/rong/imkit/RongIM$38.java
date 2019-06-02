package io.rong.imkit;

import io.rong.imkit.model.ConversationKey;
import io.rong.imkit.model.Event.ConversationNotificationEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;

class RongIM$38 extends ResultCallback<ConversationNotificationStatus> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ ConversationType val$conversationType;
    final /* synthetic */ ConversationNotificationStatus val$notificationStatus;
    final /* synthetic */ String val$targetId;

    RongIM$38(RongIM rongIM, ResultCallback resultCallback, String str, ConversationType conversationType, ConversationNotificationStatus conversationNotificationStatus) {
        this.this$0 = rongIM;
        this.val$callback = resultCallback;
        this.val$targetId = str;
        this.val$conversationType = conversationType;
        this.val$notificationStatus = conversationNotificationStatus;
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }

    public void onSuccess(ConversationNotificationStatus conversationNotificationStatus) {
        RongContext.getInstance().getEventBus().post(new ConversationNotificationEvent(this.val$targetId, this.val$conversationType, this.val$notificationStatus));
        RongContext.getInstance().setConversationNotifyStatusToCache(ConversationKey.obtain(this.val$targetId, this.val$conversationType), conversationNotificationStatus);
        if (this.val$callback != null) {
            this.val$callback.onSuccess(conversationNotificationStatus);
        }
    }
}
