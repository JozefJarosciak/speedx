package io.rong.imkit;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$ResultCallback$Result;
import io.rong.imlib.RongIMClient$SendMessageCallback;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$SentStatus;

class RongIM$29 extends RongIMClient$SendMessageCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$SendMessageCallback val$callback;
    final /* synthetic */ RongIMClient$ResultCallback$Result val$result;

    RongIM$29(RongIM rongIM, RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback) {
        this.this$0 = rongIM;
        this.val$result = rongIMClient$ResultCallback$Result;
        this.val$callback = rongIMClient$SendMessageCallback;
    }

    public void onSuccess(Integer num) {
        if (this.val$result.f17368t != null) {
            ((Message) this.val$result.f17368t).setSentStatus(Message$SentStatus.SENT);
            long sendTimeByMessageId = RongIMClient.getInstance().getSendTimeByMessageId(num.intValue());
            if (sendTimeByMessageId != 0) {
                ((Message) this.val$result.f17368t).setSentTime(sendTimeByMessageId);
            }
            RongIM.access$600(this.this$0, (Message) this.val$result.f17368t, null);
            if (this.val$callback != null) {
                this.val$callback.onSuccess(num);
            }
        }
    }

    public void onError(Integer num, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$result.f17368t != null) {
            ((Message) this.val$result.f17368t).setSentStatus(Message$SentStatus.FAILED);
            RongIM.access$600(this.this$0, (Message) this.val$result.f17368t, rongIMClient$ErrorCode);
            if (this.val$callback != null) {
                this.val$callback.onError(num, rongIMClient$ErrorCode);
            }
        }
    }
}
