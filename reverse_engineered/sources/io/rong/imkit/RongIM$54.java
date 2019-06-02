package io.rong.imkit;

import io.rong.common.RLog;
import io.rong.imkit.model.Event.MessageRecallEvent;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;
import io.rong.message.RecallNotificationMessage;

class RongIM$54 extends ResultCallback<RecallNotificationMessage> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ Message val$message;

    RongIM$54(RongIM rongIM, Message message) {
        this.this$0 = rongIM;
        this.val$message = message;
    }

    public void onSuccess(RecallNotificationMessage recallNotificationMessage) {
        RongContext.getInstance().getEventBus().post(new MessageRecallEvent(this.val$message.getMessageId(), recallNotificationMessage, true));
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RLog.m19419d(RongIM.access$500(), "recallMessage errorCode = " + rongIMClient$ErrorCode.getValue());
        RongContext.getInstance().getEventBus().post(new MessageRecallEvent(this.val$message.getMessageId(), null, false));
    }
}
