package com.beastbikes.android.modules.user.ui;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

class WatermarkGalleryActivity$11 implements AnimationListener {
    /* renamed from: a */
    final /* synthetic */ WatermarkGalleryActivity f11803a;

    WatermarkGalleryActivity$11(WatermarkGalleryActivity watermarkGalleryActivity) {
        this.f11803a = watermarkGalleryActivity;
    }

    public void onAnimationStart(Animation animation) {
        WatermarkGalleryActivity.w(this.f11803a).setVisibility(0);
    }

    public void onAnimationRepeat(Animation animation) {
    }

    public void onAnimationEnd(Animation animation) {
        WatermarkGalleryActivity.x(this.f11803a).setVisibility(8);
    }
}
