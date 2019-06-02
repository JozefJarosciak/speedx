package io.rong.imlib;

import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.ISendMessageCallback.Stub;
import io.rong.imlib.model.Message;

class RongIMClient$55 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ Message val$message;
    final /* synthetic */ String val$pushContent;
    final /* synthetic */ String val$pushData;
    final /* synthetic */ ISendMessageCallback val$sendMessageCallback;

    /* renamed from: io.rong.imlib.RongIMClient$55$1 */
    class C52871 implements Runnable {
        C52871() {
        }

        public void run() {
            if (RongIMClient$55.this.val$sendMessageCallback != null) {
                RongIMClient$55.this.val$sendMessageCallback.onError(RongIMClient$55.this.val$message, RongIMClient$ErrorCode.IPC_DISCONNECT);
            }
        }
    }

    /* renamed from: io.rong.imlib.RongIMClient$55$2 */
    class C52912 extends Stub {
        C52912() {
        }

        public void onAttached(final Message message) throws RemoteException {
            if (RongIMClient$55.this.val$sendMessageCallback != null) {
                RongIMClient.access$2100(RongIMClient$55.this.this$0, new Runnable() {
                    public void run() {
                        RongIMClient$55.this.val$sendMessageCallback.onAttached(message);
                    }
                });
            }
        }

        public void onSuccess(final Message message) throws RemoteException {
            if (RongIMClient$55.this.val$sendMessageCallback != null) {
                RongIMClient.access$2100(RongIMClient$55.this.this$0, new Runnable() {
                    public void run() {
                        RongIMClient$55.this.val$sendMessageCallback.onSuccess(message);
                    }
                });
            }
        }

        public void onError(final Message message, final int i) throws RemoteException {
            if (RongIMClient$55.this.val$sendMessageCallback != null) {
                RongIMClient.access$2100(RongIMClient$55.this.this$0, new Runnable() {
                    public void run() {
                        RongIMClient$55.this.val$sendMessageCallback.onError(message, RongIMClient$ErrorCode.valueOf(i));
                    }
                });
            }
        }
    }

    RongIMClient$55(RongIMClient rongIMClient, ISendMessageCallback iSendMessageCallback, Message message, String str, String str2) {
        this.this$0 = rongIMClient;
        this.val$sendMessageCallback = iSendMessageCallback;
        this.val$message = message;
        this.val$pushContent = str;
        this.val$pushData = str2;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) == null) {
            RongIMClient.access$2100(this.this$0, new C52871());
            return;
        }
        try {
            RongIMClient.access$400(this.this$0).sendLocationMessage(this.val$message, this.val$pushContent, this.val$pushData, new C52912());
        } catch (Exception e) {
            RLog.m19420e("RongIMClient", "sendMessage exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
