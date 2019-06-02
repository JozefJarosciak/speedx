package io.rong.imkit;

import io.rong.imkit.utils.CommonUtils;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$OperationCallback;

class RongIM$49 extends RongIMClient$OperationCallback {
    final /* synthetic */ RongIM this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ int val$spanMinutes;
    final /* synthetic */ String val$startTime;

    RongIM$49(RongIM rongIM, String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        this.this$0 = rongIM;
        this.val$startTime = str;
        this.val$spanMinutes = i;
        this.val$callback = rongIMClient$OperationCallback;
    }

    public void onSuccess() {
        CommonUtils.saveNotificationQuietHours(RongIM.access$100(), this.val$startTime, this.val$spanMinutes);
        if (this.val$callback != null) {
            this.val$callback.onSuccess();
        }
    }

    public void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        if (this.val$callback != null) {
            this.val$callback.onError(rongIMClient$ErrorCode);
        }
    }
}
