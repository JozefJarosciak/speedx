package com.beastbikes.android.modules.preferences.ui.offlineMap;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class OfflineMapActivity$b implements OnPageChangeListener {
    /* renamed from: a */
    int f11020a = ((OfflineMapActivity.l(this.f11021b) * 2) + OfflineMapActivity.m(this.f11021b));
    /* renamed from: b */
    final /* synthetic */ OfflineMapActivity f11021b;

    public OfflineMapActivity$b(OfflineMapActivity offlineMapActivity) {
        this.f11021b = offlineMapActivity;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        boolean z;
        boolean z2 = true;
        Animation translateAnimation = new TranslateAnimation((float) (this.f11020a * OfflineMapActivity.n(this.f11021b)), (float) (this.f11020a * i), 0.0f, 0.0f);
        OfflineMapActivity.a(this.f11021b, i);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(300);
        OfflineMapActivity.o(this.f11021b).startAnimation(translateAnimation);
        TextView p = OfflineMapActivity.p(this.f11021b);
        if (i == 0) {
            z = true;
        } else {
            z = false;
        }
        p.setSelected(z);
        TextView q = OfflineMapActivity.q(this.f11021b);
        if (1 != i) {
            z2 = false;
        }
        q.setSelected(z2);
    }
}
