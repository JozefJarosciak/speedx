package com.beastbikes.android.modules.user.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class MedalsActivity$2 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ MedalsActivity f11724a;

    MedalsActivity$2(MedalsActivity medalsActivity) {
        this.f11724a = medalsActivity;
    }

    public void onAnimationStart(Animation animation) {
        MedalsActivity.j(this.f11724a).setVisibility(8);
    }

    public void onAnimationEnd(Animation animation) {
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
