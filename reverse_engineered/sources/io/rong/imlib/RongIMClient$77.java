package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$77 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$chatroomId;

    /* renamed from: io.rong.imlib.RongIMClient$77$1 */
    class C53121 extends Stub {
        C53121() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$77.this.val$callback != null) {
                RongIMClient$77.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$77.this.val$callback != null) {
                RongIMClient$77.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$77(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$chatroomId = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).quitChatRoom(this.val$chatroomId, new C53121());
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
