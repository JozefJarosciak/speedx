package com.beastbikes.android.modules.cycling.ranking.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.cycling.ranking.ui.p132a.C2182a;

class RankFragment$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ RankFragment f10195a;

    RankFragment$3(RankFragment rankFragment) {
        this.f10195a = rankFragment;
    }

    public void onClick(View view) {
        C2182a.m11188a(this.f10195a.getActivity(), RankFragment.f(this.f10195a), RankFragment.c(this.f10195a)).showAsDropDown(RankFragment.e(this.f10195a), 0, 0);
    }
}
