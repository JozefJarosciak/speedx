package com.beastbikes.android.ble.ui;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.beastbikes.android.ble.CentralService.C1596c;

class DiscoveryActivity$1 implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ DiscoveryActivity f7589a;

    DiscoveryActivity$1(DiscoveryActivity discoveryActivity) {
        this.f7589a = discoveryActivity;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        DiscoveryActivity.a().info("onServiceConnected");
        DiscoveryActivity.a(this.f7589a, true);
        DiscoveryActivity.a(this.f7589a, ((C1596c) iBinder).m8563a());
        DiscoveryActivity.a(this.f7589a).m8581a(this.f7589a);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        DiscoveryActivity.a().info("onServiceDisconnected");
        DiscoveryActivity.a(this.f7589a, false);
    }
}
