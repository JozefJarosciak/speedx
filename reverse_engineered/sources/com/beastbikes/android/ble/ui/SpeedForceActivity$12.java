package com.beastbikes.android.ble.ui;

import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;

class SpeedForceActivity$12 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SpeedForceActivity f7676a;

    SpeedForceActivity$12(SpeedForceActivity speedForceActivity) {
        this.f7676a = speedForceActivity;
    }

    public void run() {
        if (SpeedForceActivity.a(this.f7676a) == null) {
            SpeedForceActivity.a(this.f7676a, new C1802i(this.f7676a, (int) C1373R.string.empty, true));
        }
        if (!SpeedForceActivity.a(this.f7676a).isShowing()) {
            SpeedForceActivity.a(this.f7676a).show();
        }
    }
}
