package io.rong.imlib;

import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.ISendMessageCallback.Stub;
import io.rong.imlib.model.Message;

class RongIMClient$58 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ ISendMessageCallback val$callback;
    final /* synthetic */ Message val$message;
    final /* synthetic */ String val$pushContent;
    final /* synthetic */ String val$pushData;

    /* renamed from: io.rong.imlib.RongIMClient$58$1 */
    class C52931 implements Runnable {
        C52931() {
        }

        public void run() {
            if (RongIMClient$58.this.val$callback != null) {
                RongIMClient$58.this.val$callback.onError(RongIMClient$58.this.val$message, RongIMClient$ErrorCode.IPC_DISCONNECT);
            }
        }
    }

    /* renamed from: io.rong.imlib.RongIMClient$58$2 */
    class C52972 extends Stub {
        C52972() {
        }

        public void onAttached(final Message message) throws RemoteException {
            if (RongIMClient$58.this.val$callback != null) {
                RongIMClient.access$2100(RongIMClient$58.this.this$0, new Runnable() {
                    public void run() {
                        RongIMClient$58.this.val$callback.onAttached(message);
                    }
                });
            }
        }

        public void onSuccess(final Message message) throws RemoteException {
            if (RongIMClient$58.this.val$callback != null) {
                RongIMClient.access$2100(RongIMClient$58.this.this$0, new Runnable() {
                    public void run() {
                        RongIMClient$58.this.val$callback.onSuccess(message);
                    }
                });
            }
        }

        public void onError(final Message message, final int i) throws RemoteException {
            if (RongIMClient$58.this.val$callback != null) {
                RongIMClient.access$2100(RongIMClient$58.this.this$0, new Runnable() {
                    public void run() {
                        RongIMClient$58.this.val$callback.onError(message, RongIMClient$ErrorCode.valueOf(i));
                    }
                });
            }
        }
    }

    RongIMClient$58(RongIMClient rongIMClient, ISendMessageCallback iSendMessageCallback, Message message, String str, String str2) {
        this.this$0 = rongIMClient;
        this.val$callback = iSendMessageCallback;
        this.val$message = message;
        this.val$pushContent = str;
        this.val$pushData = str2;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) == null) {
            RongIMClient.access$2100(this.this$0, new C52931());
            return;
        }
        try {
            RongIMClient.access$400(this.this$0).sendMessage(this.val$message, this.val$pushContent, this.val$pushData, new C52972());
        } catch (Exception e) {
            RLog.m19420e("RongIMClient", "sendMessage exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
