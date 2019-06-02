package io.rong.imlib;

import io.rong.imlib.model.Message;
import java.util.concurrent.CountDownLatch;

class RongIMClient$60 extends RongIMClient$SyncCallback<Message> {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ CountDownLatch val$latch;
    final /* synthetic */ RongIMClient$ResultCallback$Result val$result;

    RongIMClient$60(RongIMClient rongIMClient, RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result, CountDownLatch countDownLatch) {
        this.this$0 = rongIMClient;
        this.val$result = rongIMClient$ResultCallback$Result;
        this.val$latch = countDownLatch;
    }

    public void onSuccess(Message message) {
        this.val$result.f17368t = message;
        this.val$latch.countDown();
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        this.val$latch.countDown();
    }
}
