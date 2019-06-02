package com.beastbikes.android.modules.cycling.stage.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class StageMapListBaseActivity$1 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ StageMapListBaseActivity f10768a;

    StageMapListBaseActivity$1(StageMapListBaseActivity stageMapListBaseActivity) {
        this.f10768a = stageMapListBaseActivity;
    }

    public void onAnimationStart(Animation animation) {
        StageMapListBaseActivity.a(this.f10768a).setVisibility(0);
    }

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
