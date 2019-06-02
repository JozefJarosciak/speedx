package com.beastbikes.android.authentication.ui;

import android.os.CountDownTimer;
import com.beastbikes.android.C1373R;

class AuthenticationActivity$5 extends CountDownTimer {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7280a;

    AuthenticationActivity$5(AuthenticationActivity authenticationActivity, long j, long j2) {
        this.f7280a = authenticationActivity;
        super(j, j2);
    }

    public void onTick(long j) {
        AuthenticationActivity.p(this.f7280a).setText((j / 1000) + "s");
    }

    public void onFinish() {
        AuthenticationActivity.p(this.f7280a).setClickable(true);
        AuthenticationActivity.p(this.f7280a).setText(this.f7280a.getResources().getString(C1373R.string.get_verification_code));
    }
}
