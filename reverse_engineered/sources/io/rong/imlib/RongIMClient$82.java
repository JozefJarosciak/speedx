package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IIntegerCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;

class RongIMClient$82 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$userId;

    /* renamed from: io.rong.imlib.RongIMClient$82$1 */
    class C53151 extends Stub {
        C53151() {
        }

        public void onComplete(int i) throws RemoteException {
            if (RongIMClient$82.this.val$callback != null) {
                RongIMClient$82.this.val$callback.onCallback(RongIMClient$BlacklistStatus.setValue(i));
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$82.this.val$callback != null) {
                RongIMClient$82.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$82(RongIMClient rongIMClient, ResultCallback resultCallback, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$userId = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).getBlacklistStatus(this.val$userId, new C53151());
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
