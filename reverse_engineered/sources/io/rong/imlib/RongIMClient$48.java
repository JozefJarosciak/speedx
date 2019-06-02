package io.rong.imlib;

import android.os.RemoteException;
import android.text.TextUtils;
import io.rong.imlib.IResultCallback.Stub;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.RemoteModelWrap;
import java.util.List;

class RongIMClient$48 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$CreateDiscussionCallback val$callback;
    final /* synthetic */ String val$name;
    final /* synthetic */ List val$userIdList;

    /* renamed from: io.rong.imlib.RongIMClient$48$1 */
    class C52831 extends Stub {
        C52831() {
        }

        public void onComplete(RemoteModelWrap remoteModelWrap) throws RemoteException {
            if (RongIMClient$48.this.val$callback == null) {
                return;
            }
            if (remoteModelWrap == null || remoteModelWrap.getContent() == null || !(remoteModelWrap.getContent() instanceof Discussion)) {
                RongIMClient$48.this.val$callback.onCallback(null);
            } else {
                RongIMClient$48.this.val$callback.onCallback(((Discussion) remoteModelWrap.getContent()).getId());
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$48.this.val$callback != null) {
                RongIMClient$48.this.val$callback.onFail(i);
            }
        }
    }

    RongIMClient$48(RongIMClient rongIMClient, RongIMClient$CreateDiscussionCallback rongIMClient$CreateDiscussionCallback, String str, List list) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$CreateDiscussionCallback;
        this.val$name = str;
        this.val$userIdList = list;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                String str = this.val$name;
                if (!TextUtils.isEmpty(this.val$name) && this.val$name.length() > 40) {
                    str = this.val$name.substring(0, 39);
                }
                RongIMClient.access$400(this.this$0).createDiscussion(str, this.val$userIdList, new C52831());
            } catch (RemoteException e) {
                if (this.val$callback != null) {
                    this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
