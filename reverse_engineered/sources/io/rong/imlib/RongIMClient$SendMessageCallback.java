package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;

public abstract class RongIMClient$SendMessageCallback extends ResultCallback<Integer> {
    public abstract void onError(Integer num, RongIMClient$ErrorCode rongIMClient$ErrorCode);

    public final void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
    }

    public final void onFail(int i) {
        super.onFail(i);
    }

    public final void onFail(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        super.onFail(rongIMClient$ErrorCode);
    }

    public final void onFail(final Integer num, final int i) {
        RongIMClient.access$1600().postDelayed(new Runnable() {
            public void run() {
                RongIMClient$SendMessageCallback.this.onError(num, RongIMClient$ErrorCode.valueOf(i));
            }
        }, 100);
    }

    public final void onFail(final Integer num, final RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        RongIMClient.access$1600().postDelayed(new Runnable() {
            public void run() {
                RongIMClient$SendMessageCallback.this.onError(num, rongIMClient$ErrorCode);
            }
        }, 100);
    }
}
