package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;

abstract class RongIMClient$SyncCallback<T> extends ResultCallback<T> {
    RongIMClient$SyncCallback() {
    }

    public void onFail(int i) {
        onError(RongIMClient$ErrorCode.valueOf(i));
    }

    public void onFail(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        onError(rongIMClient$ErrorCode);
    }

    public void onCallback(T t) {
        onSuccess(t);
    }
}
