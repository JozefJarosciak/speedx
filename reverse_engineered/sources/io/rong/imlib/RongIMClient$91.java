package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$91 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;

    /* renamed from: io.rong.imlib.RongIMClient$91$1 */
    class C53241 extends Stub {
        C53241() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$91.this.val$callback != null) {
                RongIMClient$91.this.val$callback.onSuccess();
            }
        }

        public void onFailure(int i) throws RemoteException {
            RongIMClient$91.this.val$callback.onError(RongIMClient$ErrorCode.valueOf(i));
        }
    }

    RongIMClient$91(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).removeNotificationQuietHours(new C53241());
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
