package io.rong.push.core;

import android.content.Intent;
import android.os.Message;
import io.rong.push.PushConst;
import io.rong.push.PushService;
import io.rong.push.common.RLog;
import io.rong.push.common.stateMachine.State;
import io.rong.push.core.PushClient.QueryCallback;
import io.rong.push.core.PushClient.QueryMethod;

class PushConnectivityManager$ConnectedState extends State {
    final /* synthetic */ PushConnectivityManager this$0;

    /* renamed from: io.rong.push.core.PushConnectivityManager$ConnectedState$1 */
    class C54091 implements QueryCallback {
        C54091() {
        }

        public void onSuccess(String str) {
            RLog.d("PushConnectivityManager", "setToken.onSuccess.");
            PushConnectivityManager.access$700(PushConnectivityManager$ConnectedState.this.this$0).getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0).edit().putString("pushTypeUsing", str).apply();
            PushConnectivityManager$ConnectedState.this.this$0.cancelHeartbeat();
            PushConnectivityManager$ConnectedState.this.this$0.getHandler().sendEmptyMessage(3);
            PushConnectivityManager.access$700(PushConnectivityManager$ConnectedState.this.this$0).stopService(new Intent(PushConnectivityManager.access$700(PushConnectivityManager$ConnectedState.this.this$0), PushService.class));
        }

        public void onFailure() {
            RLog.e("PushConnectivityManager", "setToken.onFailure.");
        }
    }

    private PushConnectivityManager$ConnectedState(PushConnectivityManager pushConnectivityManager) {
        this.this$0 = pushConnectivityManager;
    }

    public void enter() {
        RLog.d("PushConnectivityManager", "enter " + getClass().getSimpleName());
    }

    public boolean processMessage(Message message) {
        RLog.d("PushConnectivityManager", getClass().getSimpleName() + ": process msg = " + message.what);
        switch (message.what) {
            case 3:
                PushConnectivityManager.access$500(this.this$0).disconnect();
                break;
            case 4:
                PushConnectivityManager.access$500(this.this$0).reset();
                PushConnectivityManager.access$2800(this.this$0, PushConnectivityManager.access$1500(this.this$0));
                break;
            case 5:
                PushConnectivityManager.access$500(this.this$0).ping();
                PushConnectivityManager.access$2600(this.this$0, this.this$0.pingState);
                break;
            case 6:
                this.this$0.stopPingTimer();
                PushConnectivityManager.access$500(this.this$0).reset();
                PushConnectivityManager.access$2700(this.this$0, PushConnectivityManager.access$1500(this.this$0));
                this.this$0.getHandler().sendEmptyMessage(1);
                break;
            case 9:
                PushConnectivityManager.access$500(this.this$0).query(QueryMethod.SET_TOKEN, ((String) message.obj) + "|" + PushConnectivityManager.access$800(this.this$0), PushConnectivityManager.access$900(this.this$0), new C54091());
                break;
        }
        return true;
    }
}
