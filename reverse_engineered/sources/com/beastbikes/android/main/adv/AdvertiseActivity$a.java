package com.beastbikes.android.main.adv;

import android.os.CountDownTimer;

class AdvertiseActivity$a extends CountDownTimer {
    /* renamed from: a */
    final /* synthetic */ AdvertiseActivity f8387a;

    public AdvertiseActivity$a(AdvertiseActivity advertiseActivity, long j, long j2) {
        this.f8387a = advertiseActivity;
        super(j, j2);
    }

    public void onFinish() {
        AdvertiseActivity.c(this.f8387a, 111, null);
        this.f8387a.finish();
    }

    public void onTick(long j) {
        AdvertiseActivity.c(this.f8387a).setText((j / 1000) + "s");
    }
}
