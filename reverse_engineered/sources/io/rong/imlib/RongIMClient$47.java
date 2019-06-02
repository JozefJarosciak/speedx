package io.rong.imlib;

import android.os.RemoteException;
import android.text.TextUtils;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$47 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$OperationCallback val$callback;
    final /* synthetic */ String val$discussionId;
    final /* synthetic */ String val$name;

    /* renamed from: io.rong.imlib.RongIMClient$47$1 */
    class C52821 extends Stub {
        C52821() {
        }

        public void onComplete() throws RemoteException {
            if (RongIMClient$47.this.val$callback != null) {
                RongIMClient$47.this.val$callback.onCallback();
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$47.this.val$callback != null) {
                RongIMClient$47.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$47(RongIMClient rongIMClient, RongIMClient$OperationCallback rongIMClient$OperationCallback, String str, String str2) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$OperationCallback;
        this.val$name = str;
        this.val$discussionId = str2;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                String str = this.val$name;
                if (!TextUtils.isEmpty(this.val$name) && this.val$name.length() > 40) {
                    str = this.val$name.substring(0, 39);
                }
                RongIMClient.access$400(this.this$0).setDiscussionName(this.val$discussionId, str, new C52821());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
