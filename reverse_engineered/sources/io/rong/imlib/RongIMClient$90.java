package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$90 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ int val$spanMinutes;
    final /* synthetic */ String val$startTime;

    /* renamed from: io.rong.imlib.RongIMClient$90$1 */
    class C53231 extends Stub {
        C53231() {
        }

        public void onComplete() throws RemoteException {
            RongIMClient$90.this.val$callback.onSuccess();
        }

        public void onFailure(int i) throws RemoteException {
            RongIMClient$90.this.val$callback.onError(RongIMClient$ErrorCode.valueOf(i));
        }
    }

    RongIMClient$90(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, int i) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$startTime = str;
        this.val$spanMinutes = i;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).setNotificationQuietHours(this.val$startTime, this.val$spanMinutes, new C53231());
            } catch (RemoteException e) {
                e.printStackTrace();
                this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
