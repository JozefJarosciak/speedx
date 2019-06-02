package io.rong.imlib;

import io.rong.imlib.RongIMClient.ResultCallback;

public abstract class RongIMClient$GetNotificationQuietHoursCallback extends ResultCallback<String> {
    public abstract void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode);

    public abstract void onSuccess(String str, int i);

    public final void onSuccess(String str) {
        throw new RuntimeException("not support");
    }

    void onCallback(final String str, final int i) {
        RongIMClient.access$1600().post(new Runnable() {
            public void run() {
                RongIMClient$GetNotificationQuietHoursCallback.this.onSuccess(str, i);
            }
        });
    }
}
