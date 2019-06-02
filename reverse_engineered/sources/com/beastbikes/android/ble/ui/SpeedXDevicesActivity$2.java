package com.beastbikes.android.ble.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;

class SpeedXDevicesActivity$2 extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ SpeedXDevicesActivity f7719a;

    SpeedXDevicesActivity$2(SpeedXDevicesActivity speedXDevicesActivity) {
        this.f7719a = speedXDevicesActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.beastbikes.android.ble.connected.action".equals(action)) {
            SpeedXDevicesActivity.a().info("BroadcastReceiver 已连接");
            C1614a b = C1661h.m8999a().m9004b();
            if (b != null) {
                SpeedXDevicesActivity.a(this.f7719a, b.m8728a());
                SpeedXDevicesActivity.b(this.f7719a).clear();
                SpeedXDevicesActivity.g(this.f7719a);
            } else {
                return;
            }
        }
        if ("com.beastbikes.android.ble.disconnected.action".equals(action)) {
            SpeedXDevicesActivity.a().info("BroadcastReceiver 已断开");
            SpeedXDevicesActivity.a(this.f7719a, "");
            SpeedXDevicesActivity.b(this.f7719a).clear();
            SpeedXDevicesActivity.g(this.f7719a);
        }
    }
}
