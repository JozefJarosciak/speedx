package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;

class RongIMClient$ResultCallback$3 implements Runnable {
    final /* synthetic */ ResultCallback this$0;
    final /* synthetic */ Object val$t;

    RongIMClient$ResultCallback$3(ResultCallback resultCallback, Object obj) {
        this.this$0 = resultCallback;
        this.val$t = obj;
    }

    public void run() {
        this.this$0.onSuccess(this.val$t);
    }
}
