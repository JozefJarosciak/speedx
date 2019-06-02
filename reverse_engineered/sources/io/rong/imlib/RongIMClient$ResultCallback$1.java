package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;

class RongIMClient$ResultCallback$1 implements Runnable {
    final /* synthetic */ ResultCallback this$0;
    final /* synthetic */ int val$errorCode;

    RongIMClient$ResultCallback$1(ResultCallback resultCallback, int i) {
        this.this$0 = resultCallback;
        this.val$errorCode = i;
    }

    public void run() {
        this.this$0.onError(RongIMClient$ErrorCode.valueOf(this.val$errorCode));
    }
}
