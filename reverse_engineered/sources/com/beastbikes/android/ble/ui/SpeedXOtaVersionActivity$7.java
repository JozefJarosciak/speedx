package com.beastbikes.android.ble.ui;

import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.dialog.C1802i;

class SpeedXOtaVersionActivity$7 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1802i f7801a;
    /* renamed from: b */
    final /* synthetic */ SpeedXOtaVersionActivity f7802b;

    SpeedXOtaVersionActivity$7(SpeedXOtaVersionActivity speedXOtaVersionActivity, C1802i c1802i) {
        this.f7802b = speedXOtaVersionActivity;
        this.f7801a = c1802i;
    }

    public void run() {
        if (this.f7801a.isShowing()) {
            this.f7801a.dismiss();
        }
        C1623g c = SpeedXOtaVersionActivity.d(this.f7802b).mo3167c();
        if (c != null) {
            c.mo3195a(2);
        }
        this.f7802b.finish();
    }
}
