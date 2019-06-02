package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;
import io.rong.imlib.model.UserData;

class RongIMClient$94 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ UserData val$userData;

    /* renamed from: io.rong.imlib.RongIMClient$94$1 */
    class C53261 extends Stub {
        C53261() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$94.this.val$callback != null) {
                RongIMClient$94.this.val$callback.onSuccess();
            }
        }

        public void onFailure(int i) throws RemoteException {
            RongIMClient$94.this.val$callback.onError(RongIMClient$ErrorCode.valueOf(i));
        }
    }

    RongIMClient$94(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, UserData userData) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$userData = userData;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).setUserData(this.val$userData, new C53261());
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
