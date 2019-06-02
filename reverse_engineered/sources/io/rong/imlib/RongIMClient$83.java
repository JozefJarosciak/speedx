package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IStringCallback.Stub;

class RongIMClient$83 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$GetBlacklistCallback val$callback;

    /* renamed from: io.rong.imlib.RongIMClient$83$1 */
    class C53161 extends Stub {
        C53161() {
        }

        public void onComplete(String str) throws RemoteException {
            if (RongIMClient$83.this.val$callback == null) {
                return;
            }
            if (str == null) {
                RongIMClient$83.this.val$callback.onCallback(null);
            } else {
                RongIMClient$83.this.val$callback.onCallback(str.split("\n"));
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$83.this.val$callback != null) {
                RongIMClient$83.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$83(RongIMClient rongIMClient, RongIMClient$GetBlacklistCallback rongIMClient$GetBlacklistCallback) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$GetBlacklistCallback;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).getBlacklist(new C53161());
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
