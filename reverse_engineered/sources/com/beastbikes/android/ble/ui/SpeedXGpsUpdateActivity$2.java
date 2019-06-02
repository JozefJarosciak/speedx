package com.beastbikes.android.ble.ui;

import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class SpeedXGpsUpdateActivity$2 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f7766a;
    /* renamed from: b */
    final /* synthetic */ SpeedXGpsUpdateActivity f7767b;

    SpeedXGpsUpdateActivity$2(SpeedXGpsUpdateActivity speedXGpsUpdateActivity, int i) {
        this.f7767b = speedXGpsUpdateActivity;
        this.f7766a = i;
    }

    public void run() {
        if (this.f7766a == 4 && this.f7767b.getWindow() != null) {
            SpeedXGpsUpdateActivity.a(this.f7767b, false);
            Toasts.show(this.f7767b, (int) C1373R.string.toast_ble_update_agps_success);
            this.f7767b.setResult(-1, this.f7767b.getIntent());
            this.f7767b.finish();
        }
        if (this.f7766a == 0) {
            SpeedXGpsUpdateActivity.b(this.f7767b, true);
        }
    }
}
