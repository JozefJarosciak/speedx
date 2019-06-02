package com.beastbikes.android.modules.user.ui.binding.widget;

import android.os.CountDownTimer;
import com.beastbikes.android.C1373R;

class ValidCodeView$a extends CountDownTimer {
    /* renamed from: a */
    final /* synthetic */ ValidCodeView f11904a;

    public ValidCodeView$a(ValidCodeView validCodeView, long j, long j2) {
        this.f11904a = validCodeView;
        super(j, j2);
    }

    public void onFinish() {
        ValidCodeView.a(this.f11904a).setText(C1373R.string.str_resend);
        ValidCodeView.a(this.f11904a, false);
        this.f11904a.setEnable(true);
    }

    public void onTick(long j) {
        ValidCodeView.a(this.f11904a, true);
        ValidCodeView.a(this.f11904a).setText((j / 1000) + "s");
    }
}
