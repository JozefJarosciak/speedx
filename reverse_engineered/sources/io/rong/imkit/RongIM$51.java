package io.rong.imkit;

import io.rong.imkit.utils.CommonUtils;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$GetNotificationQuietHoursCallback;

class RongIM$51 extends RongIMClient$GetNotificationQuietHoursCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$GetNotificationQuietHoursCallback val$callback;

    RongIM$51(RongIM rongIM, RongIMClient$GetNotificationQuietHoursCallback rongIMClient$GetNotificationQuietHoursCallback) {
        this.this$0 = rongIM;
        this.val$callback = rongIMClient$GetNotificationQuietHoursCallback;
    }

    public void onSuccess(String str, int i) {
        CommonUtils.saveNotificationQuietHours(RongIM.access$100(), str, i);
        if (this.val$callback != null) {
            this.val$callback.onSuccess(str, i);
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
