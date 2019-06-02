package com.beastbikes.android.ble.ui;

import java.util.TimerTask;

class DiscoveryActivity$a extends TimerTask {
    /* renamed from: a */
    final /* synthetic */ DiscoveryActivity f7590a;

    private DiscoveryActivity$a(DiscoveryActivity discoveryActivity) {
        this.f7590a = discoveryActivity;
    }

    public void run() {
        DiscoveryActivity.b(this.f7590a).cancel();
        if (DiscoveryActivity.c(this.f7590a)) {
            DiscoveryActivity.d(this.f7590a);
        }
        DiscoveryActivity.a(this.f7590a, null);
    }
}
