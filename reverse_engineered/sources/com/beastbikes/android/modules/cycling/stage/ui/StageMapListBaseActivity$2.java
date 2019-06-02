package com.beastbikes.android.modules.cycling.stage.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class StageMapListBaseActivity$2 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ StageMapListBaseActivity f10769a;

    StageMapListBaseActivity$2(StageMapListBaseActivity stageMapListBaseActivity) {
        this.f10769a = stageMapListBaseActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        StageMapListBaseActivity.a(this.f10769a).setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
