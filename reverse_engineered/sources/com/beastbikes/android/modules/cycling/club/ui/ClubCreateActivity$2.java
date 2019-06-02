package com.beastbikes.android.modules.cycling.club.ui;

import android.os.CountDownTimer;
import com.beastbikes.android.C1373R;

class ClubCreateActivity$2 extends CountDownTimer {
    /* renamed from: a */
    final /* synthetic */ ClubCreateActivity f9532a;

    ClubCreateActivity$2(ClubCreateActivity clubCreateActivity, long j, long j2) {
        this.f9532a = clubCreateActivity;
        super(j, j2);
    }

    public void onTick(long j) {
        ClubCreateActivity.d(this.f9532a, (int) (j / 1000));
        ClubCreateActivity.h(this.f9532a).setText((j / 1000) + "s");
    }

    public void onFinish() {
        ClubCreateActivity.h(this.f9532a).setClickable(true);
        ClubCreateActivity.h(this.f9532a).setBackgroundResource(C1373R.drawable.bg_verificationcodebtn);
        ClubCreateActivity.h(this.f9532a).setText(this.f9532a.getResources().getString(C1373R.string.get_verification_code));
    }
}
