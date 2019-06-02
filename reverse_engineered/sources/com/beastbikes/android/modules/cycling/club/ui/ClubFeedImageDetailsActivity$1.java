package com.beastbikes.android.modules.cycling.club.ui;

import android.support.v4.view.ViewPager.OnPageChangeListener;

class ClubFeedImageDetailsActivity$1 implements OnPageChangeListener {
    /* renamed from: a */
    final /* synthetic */ ClubFeedImageDetailsActivity f9607a;

    ClubFeedImageDetailsActivity$1(ClubFeedImageDetailsActivity clubFeedImageDetailsActivity) {
        this.f9607a = clubFeedImageDetailsActivity;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        ClubFeedImageDetailsActivity.a(this.f9607a, i);
        this.f9607a.setTitle((i + 1) + "/" + ClubFeedImageDetailsActivity.a(this.f9607a).size());
        ClubFeedImageDetailsActivity.b(this.f9607a, i);
    }

    public void onPageScrollStateChanged(int i) {
    }
}
