package io.rong.imlib;

import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.ISendMessageCallback.Stub;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Message;

class RongIMClient$57 implements Runnable {
    final /* synthetic */ RongIMClient this$0;
    final /* synthetic */ RongIMClient$SendMessageCallback val$callback;
    final /* synthetic */ Message val$message;
    final /* synthetic */ String val$pushContent;
    final /* synthetic */ String val$pushData;
    final /* synthetic */ ResultCallback val$resultCallback;

    /* renamed from: io.rong.imlib.RongIMClient$57$1 */
    class C52921 extends Stub {
        C52921() {
        }

        public void onAttached(Message message) throws RemoteException {
            if (RongIMClient$57.this.val$resultCallback != null) {
                RLog.m19419d("RongIMClient", "onAttached");
                RongIMClient$57.this.val$resultCallback.onCallback(message);
            }
        }

        public void onSuccess(Message message) throws RemoteException {
            if (RongIMClient$57.this.val$callback != null) {
                RongIMClient$57.this.val$callback.onCallback(Integer.valueOf(message.getMessageId()));
            }
            RongIMClient.access$102(RongIMClient$57.this.this$0, 0);
        }

        public void onError(Message message, int i) throws RemoteException {
            if (RongIMClient$57.this.val$callback != null) {
                RongIMClient$57.this.val$callback.onFail(Integer.valueOf(message.getMessageId()), RongIMClient$ErrorCode.valueOf(i));
            }
            if (RongIMClient.access$2200().contains(Integer.valueOf(i))) {
                if (RongIMClient.access$300(RongIMClient$57.this.this$0) != null) {
                    RongIMClient.access$1600().removeCallbacks(RongIMClient.access$300(RongIMClient$57.this.this$0));
                    RongIMClient.access$302(RongIMClient$57.this.this$0, null);
                }
                RongIMClient.access$302(RongIMClient$57.this.this$0, new RongIMClient$ReconnectRunnable(RongIMClient$57.this.this$0));
                RongIMClient.access$1600().postDelayed(RongIMClient.access$300(RongIMClient$57.this.this$0), 1000);
            }
        }
    }

    RongIMClient$57(RongIMClient rongIMClient, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, Message message, String str, String str2, ResultCallback resultCallback) {
        this.this$0 = rongIMClient;
        this.val$callback = rongIMClient$SendMessageCallback;
        this.val$message = message;
        this.val$pushContent = str;
        this.val$pushData = str2;
        this.val$resultCallback = resultCallback;
    }

    public void run() {
        if (RongIMClient.access$400(this.this$0) != null) {
            try {
                RongIMClient.access$400(this.this$0).sendMessage(this.val$message, this.val$pushContent, this.val$pushData, new C52921());
            } catch (RemoteException e) {
                e.printStackTrace();
                if (this.val$resultCallback != null) {
                    this.val$resultCallback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            } catch (NullPointerException e2) {
                RLog.m19420e("RongIMClient", "sendMessage NullPointerException");
                e2.printStackTrace();
                if (this.val$resultCallback != null) {
                    this.val$resultCallback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
                }
            }
        } else if (this.val$callback != null) {
            this.val$callback.onFail(RongIMClient$ErrorCode.IPC_DISCONNECT);
        }
    }
}
