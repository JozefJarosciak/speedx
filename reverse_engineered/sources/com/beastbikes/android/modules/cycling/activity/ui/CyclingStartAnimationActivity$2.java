package com.beastbikes.android.modules.cycling.activity.ui;

import android.view.animation.Animation;

class CyclingStartAnimationActivity$2 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Animation f8687a;
    /* renamed from: b */
    final /* synthetic */ CyclingStartAnimationActivity f8688b;

    CyclingStartAnimationActivity$2(CyclingStartAnimationActivity cyclingStartAnimationActivity, Animation animation) {
        this.f8688b = cyclingStartAnimationActivity;
        this.f8687a = animation;
    }

    public void run() {
        CyclingStartAnimationActivity.c(this.f8688b).startAnimation(this.f8687a);
    }
}
