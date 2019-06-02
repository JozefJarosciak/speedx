package io.rong.imlib;

import android.os.RemoteException;
import io.rong.common.RLog;
import io.rong.imlib.IOperationCallback.Stub;

class RongIMClient$DisconnectRunnable implements Runnable {
    boolean isReceivePush;
    final /* synthetic */ RongIMClient this$0;

    /* renamed from: io.rong.imlib.RongIMClient$DisconnectRunnable$1 */
    class C53451 extends Stub {
        C53451() {
        }

        public void onComplete() throws RemoteException {
            RongIMClient.access$500(RongIMClient$DisconnectRunnable.this.this$0).onStatusChange(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
            RongIMClient.access$602(RongIMClient$DisconnectRunnable.this.this$0, null);
            RongIMClient.access$702(RongIMClient$DisconnectRunnable.this.this$0, null);
        }

        public void onFailure(int i) throws RemoteException {
            RongIMClient.access$500(RongIMClient$DisconnectRunnable.this.this$0).onStatusChange(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
            RongIMClient.access$602(RongIMClient$DisconnectRunnable.this.this$0, null);
            RongIMClient.access$702(RongIMClient$DisconnectRunnable.this.this$0, null);
        }
    }

    public RongIMClient$DisconnectRunnable(RongIMClient rongIMClient, boolean z) {
        this.this$0 = rongIMClient;
        this.isReceivePush = z;
    }

    public void run() {
        try {
            RLog.m19419d("RongIMClient", "DisconnectRunnable do disconnect!");
            if (RongIMClient.access$400(this.this$0) == null) {
                RLog.m19420e("RongIMClient", "DisconnectRunnable mLibHandler is null!");
            } else {
                RongIMClient.access$400(this.this$0).disconnect(this.isReceivePush, new C53451());
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
