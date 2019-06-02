package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$75 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$chatroomId;
    final /* synthetic */ int val$defMessageCount;

    /* renamed from: io.rong.imlib.RongIMClient$75$1 */
    class C53101 extends Stub {
        C53101() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$75.this.val$callback != null) {
                RongIMClient$75.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$75.this.val$callback != null) {
                RongIMClient$75.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$75(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, int i) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$chatroomId = str;
        this.val$defMessageCount = i;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).reJoinChatRoom(this.val$chatroomId, this.val$defMessageCount, new C53101());
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
