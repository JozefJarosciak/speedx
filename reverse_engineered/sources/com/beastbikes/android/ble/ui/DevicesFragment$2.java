package com.beastbikes.android.ble.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;

class DevicesFragment$2 extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ DevicesFragment f7570a;

    DevicesFragment$2(DevicesFragment devicesFragment) {
        this.f7570a = devicesFragment;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.beastbikes.android.ble.connected.action".equals(action)) {
            DevicesFragment.a().info("BroadcastReceiver 已连接");
            C1614a b = C1661h.m8999a().m9004b();
            if (b != null) {
                DevicesFragment.a(this.f7570a, b.m8728a());
                DevicesFragment.d(this.f7570a).clear();
                DevicesFragment.e(this.f7570a);
            } else {
                return;
            }
        }
        if ("com.beastbikes.android.ble.disconnected.action".equals(action)) {
            DevicesFragment.a().info("BroadcastReceiver 已断开");
            DevicesFragment.a(this.f7570a, "");
            DevicesFragment.d(this.f7570a).clear();
            DevicesFragment.e(this.f7570a);
        }
    }
}
