package com.beastbikes.android.authentication.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.p165a.C2608b;

class AuthenticationActivity$9 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7284a;

    AuthenticationActivity$9(AuthenticationActivity authenticationActivity) {
        this.f7284a = authenticationActivity;
    }

    public void onAnimationStart(Animation animation) {
        AuthenticationActivity.e(this.f7284a).setVisibility(0);
        AuthenticationActivity.h(this.f7284a).setAlpha(0.5f);
        AuthenticationActivity.a(this.f7284a).requestFocus();
        AuthenticationActivity.a(this.f7284a).setFocusable(true);
        C2608b.m12999a(this.f7284a, AuthenticationActivity.i(this.f7284a), (int) C1373R.drawable.authentication_bg);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        AuthenticationActivity.d(this.f7284a).setVisibility(8);
        AuthenticationActivity.l(this.f7284a);
    }
}
