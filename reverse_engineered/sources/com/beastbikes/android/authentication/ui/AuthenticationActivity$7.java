package com.beastbikes.android.authentication.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.p165a.C2608b;

class AuthenticationActivity$7 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7282a;

    AuthenticationActivity$7(AuthenticationActivity authenticationActivity) {
        this.f7282a = authenticationActivity;
    }

    public void onAnimationStart(Animation animation) {
        AuthenticationActivity.d(this.f7282a).setVisibility(0);
        AuthenticationActivity.e(this.f7282a).setVisibility(8);
        AuthenticationActivity.f(this.f7282a).setVisibility(8);
        AuthenticationActivity.g(this.f7282a).setVisibility(8);
        AuthenticationActivity.h(this.f7282a).setAlpha(0.5f);
        C2608b.m12999a(this.f7282a, AuthenticationActivity.i(this.f7282a), (int) C1373R.drawable.authentication_bg);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        AuthenticationActivity.j(this.f7282a).setVisibility(0);
        AuthenticationActivity.k(this.f7282a).requestFocus();
        AuthenticationActivity.k(this.f7282a).setFocusable(true);
        AuthenticationActivity.l(this.f7282a);
    }
}
