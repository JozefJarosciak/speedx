package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;
import java.util.List;

class RongIMClient$69 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ List val$groups;

    /* renamed from: io.rong.imlib.RongIMClient$69$1 */
    class C53051 extends Stub {
        C53051() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$69.this.val$callback != null) {
                RongIMClient$69.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$69.this.val$callback != null) {
                RongIMClient$69.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$69(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, List list) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$groups = list;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).syncGroup(this.val$groups, new C53051());
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
