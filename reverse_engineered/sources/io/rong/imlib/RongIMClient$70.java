package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$70 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$groupId;
    final /* synthetic */ String val$groupName;

    /* renamed from: io.rong.imlib.RongIMClient$70$1 */
    class C53061 extends Stub {
        C53061() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$70.this.val$callback != null) {
                RongIMClient$70.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$70.this.val$callback != null) {
                RongIMClient$70.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$70(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, String str2) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$groupId = str;
        this.val$groupName = str2;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).joinGroup(this.val$groupId, this.val$groupName, new C53061());
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
