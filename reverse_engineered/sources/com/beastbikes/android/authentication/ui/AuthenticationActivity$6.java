package com.beastbikes.android.authentication.ui;

import android.support.v4.view.ViewPager.OnPageChangeListener;

class AuthenticationActivity$6 implements OnPageChangeListener {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7281a;

    AuthenticationActivity$6(AuthenticationActivity authenticationActivity) {
        this.f7281a = authenticationActivity;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (i == 0) {
            AuthenticationActivity.b(this.f7281a).setSelected(true);
            AuthenticationActivity.c(this.f7281a).setSelected(false);
            return;
        }
        AuthenticationActivity.b(this.f7281a).setSelected(false);
        AuthenticationActivity.c(this.f7281a).setSelected(true);
    }

    public void onPageScrollStateChanged(int i) {
    }
}
