package io.rong.imkit;

import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;

class RongIM$12 extends ResultCallback<Integer> {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ ResultCallback val$callback;

    RongIM$12(RongIM rongIM, ResultCallback resultCallback) {
        this.this$0 = rongIM;
        this.val$callback = resultCallback;
    }

    public void onSuccess(Integer num) {
        if (this.val$callback != null) {
            this.val$callback.onSuccess(num);
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onFail(rongIMClient$ErrorCode);
        }
    }
}
