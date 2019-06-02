package com.beastbikes.android.ble.ui;

import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.dialog.C1802i;

class SpeedXGpsUpdateActivity$7 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1802i f7776a;
    /* renamed from: b */
    final /* synthetic */ SpeedXGpsUpdateActivity f7777b;

    SpeedXGpsUpdateActivity$7(SpeedXGpsUpdateActivity speedXGpsUpdateActivity, C1802i c1802i) {
        this.f7777b = speedXGpsUpdateActivity;
        this.f7776a = c1802i;
    }

    public void run() {
        if (this.f7776a.isShowing()) {
            this.f7776a.dismiss();
        }
        if (SpeedXGpsUpdateActivity.d(this.f7777b) != null) {
            C1623g c = SpeedXGpsUpdateActivity.d(this.f7777b).mo3167c();
            if (c != null) {
                c.mo3195a(1);
            }
        }
        this.f7777b.finish();
    }
}
