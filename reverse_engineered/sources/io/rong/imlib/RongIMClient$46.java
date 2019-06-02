package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.IResultCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.RemoteModelWrap;

class RongIMClient$46 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ResultCallback val$callback;
    final /* synthetic */ String val$discussionId;

    /* renamed from: io.rong.imlib.RongIMClient$46$1 */
    class C52811 extends Stub {
        C52811() {
        }

        public void onComplete(RemoteModelWrap remoteModelWrap) throws RemoteException {
            if (RongIMClient$46.this.val$callback == null) {
                return;
            }
            if (remoteModelWrap == null || remoteModelWrap.getContent() == null || !(remoteModelWrap.getContent() instanceof Discussion)) {
                RongIMClient$46.this.val$callback.onCallback(null);
            } else {
                RongIMClient$46.this.val$callback.onCallback((Discussion) remoteModelWrap.getContent());
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$46.this.val$callback != null) {
                RongIMClient$46.this.val$callback.onFail(RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$46(RongIMClient rongIMClient, ResultCallback resultCallback, String str) {
        this.this$0 = rongIMClient;
        this.val$callback = resultCallback;
        this.val$discussionId = str;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).getDiscussion(this.val$discussionId, new C52811());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
