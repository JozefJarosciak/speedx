package com.beastbikes.android.modules.cycling.activity.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class CyclingStartAnimationActivity$3 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ Runnable f8689a;
    /* renamed from: b */
    final /* synthetic */ CyclingStartAnimationActivity f8690b;

    CyclingStartAnimationActivity$3(CyclingStartAnimationActivity cyclingStartAnimationActivity, Runnable runnable) {
        this.f8690b = cyclingStartAnimationActivity;
        this.f8689a = runnable;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        CyclingStartAnimationActivity.d(this.f8690b).postDelayed(this.f8689a, 300);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
