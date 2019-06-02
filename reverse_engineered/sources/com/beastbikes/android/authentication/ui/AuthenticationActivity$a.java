package com.beastbikes.android.authentication.ui;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

class AuthenticationActivity$a extends PagerAdapter {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7285a;

    AuthenticationActivity$a(AuthenticationActivity authenticationActivity) {
        this.f7285a = authenticationActivity;
    }

    public int getCount() {
        return AuthenticationActivity.q(this.f7285a).size();
    }

    public Object instantiateItem(View view, int i) {
        ((ViewPager) view).addView((View) AuthenticationActivity.q(this.f7285a).get(i), 0);
        return AuthenticationActivity.q(this.f7285a).get(i);
    }

    public void destroyItem(View view, int i, Object obj) {
        ((ViewPager) view).removeView((View) AuthenticationActivity.q(this.f7285a).get(i));
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
