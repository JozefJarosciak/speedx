package com.beastbikes.android.ble.ui;

class SpeedXOtaVersionActivity$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ SpeedXOtaVersionActivity f7789a;

    SpeedXOtaVersionActivity$1(SpeedXOtaVersionActivity speedXOtaVersionActivity) {
        this.f7789a = speedXOtaVersionActivity;
    }

    public void run() {
        SpeedXOtaVersionActivity.c(this.f7789a).m9327a((float) ((SpeedXOtaVersionActivity.a(this.f7789a) / SpeedXOtaVersionActivity.b(this.f7789a)) * 100.0d), true);
    }
}
