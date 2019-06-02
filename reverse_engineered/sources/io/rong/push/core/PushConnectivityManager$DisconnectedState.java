package io.rong.push.core;

import android.os.Message;
import io.rong.push.common.RLog;
import io.rong.push.common.stateMachine.State;

class PushConnectivityManager$DisconnectedState extends State {
    final /* synthetic */ PushConnectivityManager this$0;

    private PushConnectivityManager$DisconnectedState(PushConnectivityManager pushConnectivityManager) {
        this.this$0 = pushConnectivityManager;
    }

    public void enter() {
        RLog.d("PushConnectivityManager", "enter " + getClass().getSimpleName());
    }

    public boolean processMessage(Message message) {
        RLog.d("PushConnectivityManager", getClass().getSimpleName() + ": process msg = " + message.what);
        switch (message.what) {
            case 1:
            case 5:
            case 8:
            case 10:
                break;
            case 4:
                PushConnectivityManager.access$500(this.this$0).reset();
                break;
            case 7:
                this.this$0.stopPingTimer();
                break;
            case 9:
                PushConnectivityManager.access$100(this.this$0, message);
                break;
        }
        PushConnectivityManager.access$200(this.this$0);
        PushConnectivityManager.access$400(this.this$0, PushConnectivityManager.access$300(this.this$0));
        return true;
    }
}
