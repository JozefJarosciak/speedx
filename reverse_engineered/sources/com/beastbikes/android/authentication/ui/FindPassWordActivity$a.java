package com.beastbikes.android.authentication.ui;

import android.os.CountDownTimer;
import com.beastbikes.android.C1373R;

class FindPassWordActivity$a extends CountDownTimer {
    /* renamed from: a */
    final /* synthetic */ FindPassWordActivity f7303a;

    public FindPassWordActivity$a(FindPassWordActivity findPassWordActivity, long j, long j2) {
        this.f7303a = findPassWordActivity;
        super(j, j2);
    }

    public void onFinish() {
        FindPassWordActivity.e(this.f7303a).setText(this.f7303a.getString(C1373R.string.activity_find_pass_word_phone_sendcode_str));
        FindPassWordActivity.e(this.f7303a).setClickable(true);
    }

    public void onTick(long j) {
        FindPassWordActivity.e(this.f7303a).setClickable(false);
        FindPassWordActivity.e(this.f7303a).setText((j / 1000) + "s");
    }
}
