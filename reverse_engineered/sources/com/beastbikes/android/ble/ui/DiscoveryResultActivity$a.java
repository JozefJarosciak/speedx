package com.beastbikes.android.ble.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class DiscoveryResultActivity$a extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ DiscoveryResultActivity f7592a;

    private DiscoveryResultActivity$a(DiscoveryResultActivity discoveryResultActivity) {
        this.f7592a = discoveryResultActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals("com.beastbikes.android.ble.connected.action")) {
                if (DiscoveryResultActivity.e(this.f7592a) != null && DiscoveryResultActivity.e(this.f7592a).isShowing()) {
                    DiscoveryResultActivity.e(this.f7592a).dismiss();
                }
                this.f7592a.finish();
                this.f7592a.startActivity(new Intent(this.f7592a, SpeedForceActivity.class));
            }
            if ("com.beastbikes.android.connect.no.token".equals(action)) {
                if (DiscoveryResultActivity.e(this.f7592a) != null && DiscoveryResultActivity.e(this.f7592a).isShowing()) {
                    DiscoveryResultActivity.e(this.f7592a).dismiss();
                }
                Intent intent2 = new Intent(this.f7592a, SpeedXTrainingTargetActivity.class);
                intent2.putExtra("show_menu", true);
                this.f7592a.startActivity(intent2);
                this.f7592a.finish();
            }
        }
    }
}
