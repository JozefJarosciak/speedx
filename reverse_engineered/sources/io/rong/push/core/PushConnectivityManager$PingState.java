package io.rong.push.core;

import android.os.Message;
import io.rong.push.common.RLog;
import io.rong.push.common.stateMachine.State;

class PushConnectivityManager$PingState extends State {
    final /* synthetic */ PushConnectivityManager this$0;

    private PushConnectivityManager$PingState(PushConnectivityManager pushConnectivityManager) {
        this.this$0 = pushConnectivityManager;
    }

    public void enter() {
        RLog.d("PushConnectivityManager", "enter " + getClass().getSimpleName());
        this.this$0.startPingTimer();
    }

    public boolean processMessage(Message message) {
        RLog.d("PushConnectivityManager", getClass().getSimpleName() + ": process msg = " + message.what);
        switch (message.what) {
            case 1:
                PushConnectivityManager.access$200(this.this$0);
                PushConnectivityManager.access$2100(this.this$0, PushConnectivityManager.access$300(this.this$0));
                break;
            case 2:
            case 7:
                this.this$0.stopPingTimer();
                this.this$0.setNextHeartbeat();
                PushConnectivityManager.access$1800(this.this$0, PushConnectivityManager.access$1200(this.this$0));
                break;
            case 3:
                PushConnectivityManager.access$500(this.this$0).disconnect();
                PushConnectivityManager.access$2200(this.this$0, PushConnectivityManager.access$1500(this.this$0));
                break;
            case 4:
                PushConnectivityManager.access$500(this.this$0).reset();
                PushConnectivityManager.access$2300(this.this$0, PushConnectivityManager.access$1500(this.this$0));
                break;
            case 5:
                PushConnectivityManager.access$500(this.this$0).reset();
                PushConnectivityManager.access$1900(this.this$0, PushConnectivityManager.access$1500(this.this$0));
                this.this$0.getHandler().sendEmptyMessage(1);
                break;
            case 6:
                this.this$0.stopPingTimer();
                PushConnectivityManager.access$500(this.this$0).reset();
                this.this$0.getHandler().sendEmptyMessage(1);
                PushConnectivityManager.access$2000(this.this$0, PushConnectivityManager.access$1500(this.this$0));
                break;
            case 9:
                break;
        }
        PushConnectivityManager.access$2400(this.this$0, message);
        return true;
    }
}
