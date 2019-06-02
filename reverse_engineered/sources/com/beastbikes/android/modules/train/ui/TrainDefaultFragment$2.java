package com.beastbikes.android.modules.train.ui;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

class TrainDefaultFragment$2 implements PageTransformer {
    /* renamed from: a */
    final /* synthetic */ TrainDefaultFragment f11261a;

    TrainDefaultFragment$2(TrainDefaultFragment trainDefaultFragment) {
        this.f11261a = trainDefaultFragment;
    }

    public void transformPage(View view, float f) {
        int height = view.getHeight();
        if (f >= -1.0f && f <= 0.0f) {
            view.setTranslationY((((float) (-height)) * f) * 0.1f);
        } else if (f > 0.0f && f <= 1.0f) {
            view.setTranslationY((((float) height) * f) * 0.1f);
        }
    }
}
