package com.beastbikes.android.modules.social.im.ui.conversation;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class LocationSelectActivity$1 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ LocationSelectActivity f11185a;

    LocationSelectActivity$1(LocationSelectActivity locationSelectActivity) {
        this.f11185a = locationSelectActivity;
    }

    public void onAnimationStart(Animation animation) {
        LocationSelectActivity.a(this.f11185a).setVisibility(8);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        LocationSelectActivity.a(this.f11185a).setVisibility(0);
        LocationSelectActivity.a(this.f11185a).startAnimation(LocationSelectActivity.b(this.f11185a));
    }
}
