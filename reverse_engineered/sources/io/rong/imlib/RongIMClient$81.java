package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$81 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$userId;

    /* renamed from: io.rong.imlib.RongIMClient$81$1 */
    class C53141 extends Stub {
        C53141() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$81.this.val$callback != null) {
                RongIMClient$81.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$81.this.val$callback != null) {
                RongIMClient$81.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$81(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$userId = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).removeFromBlacklist(this.val$userId, new C53141());
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
