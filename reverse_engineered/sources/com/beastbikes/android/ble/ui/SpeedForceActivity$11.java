package com.beastbikes.android.ble.ui;

class SpeedForceActivity$11 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SpeedForceActivity f7675a;

    SpeedForceActivity$11(SpeedForceActivity speedForceActivity) {
        this.f7675a = speedForceActivity;
    }

    public void run() {
        if (!this.f7675a.isFinishing() && !this.f7675a.isDestroyed() && SpeedForceActivity.a(this.f7675a) != null && SpeedForceActivity.a(this.f7675a).isShowing()) {
            SpeedForceActivity.a(this.f7675a).dismiss();
        }
    }
}
