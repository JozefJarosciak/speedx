package io.rong.imlib;

import android.os.RemoteException;
import io.rong.imlib.ILongCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;

class RongIMClient$59 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$SendMessageCallback val$callback;
    final /* synthetic */ Message val$message;
    final /* synthetic */ ResultCallback val$resultCallback;

    /* renamed from: io.rong.imlib.RongIMClient$59$1 */
    class C52981 extends Stub {
        C52981() {
        }

        public void onComplete(long j) throws RemoteException {
            if (RongIMClient$59.this.val$callback != null) {
                RongIMClient$59.this.val$callback.onCallback(Integer.valueOf((int) j));
            }
        }

        public void onFailure(int i) throws RemoteException {
            if (RongIMClient$59.this.val$callback != null) {
                RongIMClient$59.this.val$callback.onFail(Integer.valueOf(RongIMClient$59.this.val$message.getMessageId()), RongIMClient$ErrorCode.valueOf(i));
            }
        }
    }

    RongIMClient$59(RongIMClient rongIMClient, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, Message message, ResultCallback resultCallback) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$SendMessageCallback;
        this.val$message = message;
        this.val$resultCallback = resultCallback;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                Message sendStatusMessage = RongIMClient.access$400(this.this$0).sendStatusMessage(this.val$message, new C52981());
                this.val$message.setMessageId(sendStatusMessage.getMessageId());
                if (this.val$resultCallback != null) {
                    this.val$resultCallback.onCallback(sendStatusMessage);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                if (this.val$resultCallback != null) {
                    this.val$resultCallback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
