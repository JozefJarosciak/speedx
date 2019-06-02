package com.beastbikes.android.modules.user.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class MedalsActivity$3 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ MedalsActivity f11725a;

    MedalsActivity$3(MedalsActivity medalsActivity) {
        this.f11725a = medalsActivity;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        MedalsActivity.j(this.f11725a).setVisibility(0);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
