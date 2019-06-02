package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.CountDownTimer;
import com.beastbikes.android.authentication.AVUser;

class RankFragment$b extends CountDownTimer {
    /* renamed from: a */
    final /* synthetic */ RankFragment f10199a;

    public RankFragment$b(RankFragment rankFragment, long j, long j2) {
        this.f10199a = rankFragment;
        super(j, j2);
    }

    public void onFinish() {
        RankFragment.a(this.f10199a, false);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            RankFragment.a(this.f10199a, currentUser.getCity());
        }
    }

    public void onTick(long j) {
    }
}
