package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IGetNotificationQuietHoursCallback.Stub;

class RongIMClient$93 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$GetNotificationQuietHoursCallback val$callback;

    /* renamed from: io.rong.imlib.RongIMClient$93$1 */
    class C53251 extends Stub {
        C53251() {
        }

        public void onSuccess(String str, int i) {
            if (RongIMClient$93.this.val$callback != null) {
                RongIMClient$93.this.val$callback.onSuccess(str, i);
            }
        }

        public void onError(int i) {
            if (RongIMClient$93.this.val$callback != null) {
                RongIMClient$93.this.val$callback.onError(RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$93(RongIMClient rongIMClient, RongIMClient$GetNotificationQuietHoursCallback rongIMClient$GetNotificationQuietHoursCallback) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$GetNotificationQuietHoursCallback;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).getNotificationQuietHours(new C53251());
            } catch (RemoteException e) {
                e.printStackTrace();
                if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
