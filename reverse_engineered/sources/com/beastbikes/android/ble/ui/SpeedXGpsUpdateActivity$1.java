package com.beastbikes.android.ble.ui;

class SpeedXGpsUpdateActivity$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ double f7763a;
    /* renamed from: b */
    final /* synthetic */ double f7764b;
    /* renamed from: c */
    final /* synthetic */ SpeedXGpsUpdateActivity f7765c;

    SpeedXGpsUpdateActivity$1(SpeedXGpsUpdateActivity speedXGpsUpdateActivity, double d, double d2) {
        this.f7765c = speedXGpsUpdateActivity;
        this.f7763a = d;
        this.f7764b = d2;
    }

    public void run() {
        SpeedXGpsUpdateActivity.a(this.f7765c).m9327a((float) ((this.f7763a / this.f7764b) * 100.0d), true);
    }
}
