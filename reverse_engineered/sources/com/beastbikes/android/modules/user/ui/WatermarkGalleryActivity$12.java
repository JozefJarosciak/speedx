package com.beastbikes.android.modules.user.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class WatermarkGalleryActivity$12 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ WatermarkGalleryActivity f11804a;

    WatermarkGalleryActivity$12(WatermarkGalleryActivity watermarkGalleryActivity) {
        this.f11804a = watermarkGalleryActivity;
    }

    public void onAnimationStart(Animation animation) {
        WatermarkGalleryActivity.x(this.f11804a).setVisibility(0);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        WatermarkGalleryActivity.w(this.f11804a).setVisibility(8);
    }
}
