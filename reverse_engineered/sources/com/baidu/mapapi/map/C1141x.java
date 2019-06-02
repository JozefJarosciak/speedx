package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/* renamed from: com.baidu.mapapi.map.x */
class C1141x extends AnimatorListenerAdapter {
    /* renamed from: a */
    final /* synthetic */ View f3282a;
    /* renamed from: b */
    final /* synthetic */ WearMapView f3283b;

    C1141x(WearMapView wearMapView, View view) {
        this.f3283b = wearMapView;
        this.f3282a = view;
    }

    public void onAnimationEnd(Animator animator) {
        this.f3282a.setVisibility(4);
        super.onAnimationEnd(animator);
    }
}
