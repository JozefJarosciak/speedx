package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;

class RongIMClient$ResultCallback$2 implements Runnable {
    final /* synthetic */ ResultCallback this$0;
    final /* synthetic */ RongIMClient$ErrorCode val$errorCode;

    RongIMClient$ResultCallback$2(ResultCallback resultCallback, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        this.this$0 = resultCallback;
        this.val$errorCode = rongIMClient$ErrorCode;
    }

    public void run() {
        this.this$0.onError(this.val$errorCode);
    }
}
