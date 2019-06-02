package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* renamed from: com.baidu.mapapi.map.m */
class C1128m extends AnimatorListenerAdapter {
    /* renamed from: a */
    final /* synthetic */ SwipeDismissTouchListener f3260a;

    C1128m(SwipeDismissTouchListener swipeDismissTouchListener) {
        this.f3260a = swipeDismissTouchListener;
    }

    public void onAnimationEnd(Animator animator) {
        this.f3260a.m4170a();
    }
}
