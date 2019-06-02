package com.beastbikes.android.modules.cycling.activity.ui;

import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class CyclingStartAnimationActivity$4 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ Animation f8691a;
    /* renamed from: b */
    final /* synthetic */ CyclingStartAnimationActivity f8692b;

    CyclingStartAnimationActivity$4(CyclingStartAnimationActivity cyclingStartAnimationActivity, Animation animation) {
        this.f8692b = cyclingStartAnimationActivity;
        this.f8691a = animation;
    }

    public void onAnimationStart(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        Object charSequence = CyclingStartAnimationActivity.c(this.f8692b).getText().toString();
        if (TextUtils.isDigitsOnly(charSequence)) {
            int parseInt = Integer.parseInt(charSequence);
            if (parseInt == 1) {
                CyclingStartAnimationActivity.c(this.f8692b).setText("GO");
            } else {
                CyclingStartAnimationActivity.c(this.f8692b).setText("" + (parseInt - 1));
            }
            CyclingStartAnimationActivity.c(this.f8692b).startAnimation(this.f8691a);
            return;
        }
        CyclingStartAnimationActivity.c(this.f8692b).setVisibility(4);
    }

    public void onAnimationRepeat(Animation animation) {
    }
}
