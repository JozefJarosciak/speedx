package com.beastbikes.android.ble.ui;

class SpeedForceActivity$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SpeedForceActivity f7697a;

    SpeedForceActivity$1(SpeedForceActivity speedForceActivity) {
        this.f7697a = speedForceActivity;
    }

    public void run() {
        if (!this.f7697a.isFinishing() && !this.f7697a.isDestroyed() && SpeedForceActivity.a(this.f7697a) != null && SpeedForceActivity.a(this.f7697a).isShowing()) {
            SpeedForceActivity.a(this.f7697a).dismiss();
        }
    }
}
