package com.beastbikes.android.modules.cycling.route.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class RoutePlanActivity$4 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ RoutePlanActivity f10318a;

    RoutePlanActivity$4(RoutePlanActivity routePlanActivity) {
        this.f10318a = routePlanActivity;
    }

    public void onAnimationStart(Animation animation) {
        RoutePlanActivity.d(this.f10318a).setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        RoutePlanActivity.d(this.f10318a).setVisibility(0);
        RoutePlanActivity.d(this.f10318a).startAnimation(RoutePlanActivity.e(this.f10318a));
    }
}
