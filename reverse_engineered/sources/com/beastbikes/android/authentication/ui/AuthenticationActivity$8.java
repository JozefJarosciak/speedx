package com.beastbikes.android.authentication.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.p165a.C2608b;

class AuthenticationActivity$8 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7283a;

    AuthenticationActivity$8(AuthenticationActivity authenticationActivity) {
        this.f7283a = authenticationActivity;
    }

    public void onAnimationStart(Animation animation) {
        AuthenticationActivity.e(this.f7283a).setVisibility(8);
        AuthenticationActivity.d(this.f7283a).setVisibility(8);
        AuthenticationActivity.g(this.f7283a).setVisibility(8);
        AuthenticationActivity.h(this.f7283a).setAlpha(0.5f);
        C2608b.m12999a(this.f7283a, AuthenticationActivity.i(this.f7283a), (int) C1373R.drawable.authentication_bg);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        AuthenticationActivity.f(this.f7283a).setVisibility(0);
        AuthenticationActivity.j(this.f7283a).setVisibility(0);
        AuthenticationActivity.m(this.f7283a).requestFocus();
        AuthenticationActivity.m(this.f7283a).setFocusable(true);
        AuthenticationActivity.l(this.f7283a);
    }
}
