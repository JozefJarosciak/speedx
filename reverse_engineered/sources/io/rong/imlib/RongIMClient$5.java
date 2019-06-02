package io.rong.imlib;

import io.rong.imlib.model.Conversation;
import java.util.List;
import java.util.concurrent.CountDownLatch;

class RongIMClient$5 extends RongIMClient$SyncCallback<List<Conversation>> {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ CountDownLatch val$latch;
    final /* synthetic */ RongIMClient$ResultCallback$Result val$result;

    RongIMClient$5(RongIMClient rongIMClient, RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result, CountDownLatch countDownLatch) {
        this.this$0 = rongIMClient;
        this.val$result = rongIMClient$ResultCallback$Result;
        this.val$latch = countDownLatch;
    }

    public void onSuccess(List<Conversation> list) {
        this.val$result.f17368t = list;
        this.val$latch.countDown();
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        this.val$latch.countDown();
    }
}
