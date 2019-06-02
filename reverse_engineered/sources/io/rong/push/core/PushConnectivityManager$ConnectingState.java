package io.rong.push.core;

import android.os.Message;
import io.rong.push.common.RLog;
import io.rong.push.common.stateMachine.State;

class PushConnectivityManager$ConnectingState extends State {
    final /* synthetic */ PushConnectivityManager this$0;

    private PushConnectivityManager$ConnectingState(PushConnectivityManager pushConnectivityManager) {
        this.this$0 = pushConnectivityManager;
    }

    public void enter() {
        RLog.d("PushConnectivityManager", "enter " + getClass().getSimpleName());
    }

    public boolean processMessage(Message message) {
        RLog.d("PushConnectivityManager", getClass().getSimpleName() + ": process msg = " + message.what);
        switch (message.what) {
            case 1:
            case 9:
                PushConnectivityManager.access$1400(this.this$0, message);
                break;
            case 2:
                this.this$0.setNextHeartbeat();
                PushConnectivityManager.access$1300(this.this$0, PushConnectivityManager.access$1200(this.this$0));
                break;
            case 3:
            case 4:
                PushConnectivityManager.access$500(this.this$0).reset();
                PushConnectivityManager.access$1600(this.this$0, PushConnectivityManager.access$1500(this.this$0));
                break;
            case 7:
                this.this$0.stopPingTimer();
                break;
        }
        return true;
    }
}
