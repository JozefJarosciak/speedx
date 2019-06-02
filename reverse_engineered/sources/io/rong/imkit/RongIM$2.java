package io.rong.imkit;

import android.content.Intent;
import java.util.TimerTask;

class RongIM$2 extends TimerTask {
    RongIM$2() {
    }

    public void run() {
        if (RongIM.access$300() && RongIM.access$400() != null && RongIM.access$100() != null) {
            Intent intent = new Intent("io.rong.intent.action.UI_READY");
            intent.setPackage(RongIM.access$100().getPackageName());
            RongIM.access$100().sendBroadcast(intent);
            cancel();
            RongIM.access$400().cancel();
            RongIM.access$402(null);
        }
    }
}
