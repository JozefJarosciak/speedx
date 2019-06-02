package com.baidu.mapapi.map;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.ViewGroup.LayoutParams;

/* renamed from: com.baidu.mapapi.map.n */
class C1129n extends AnimatorListenerAdapter {
    /* renamed from: a */
    final /* synthetic */ LayoutParams f3261a;
    /* renamed from: b */
    final /* synthetic */ int f3262b;
    /* renamed from: c */
    final /* synthetic */ SwipeDismissTouchListener f3263c;

    C1129n(SwipeDismissTouchListener swipeDismissTouchListener, LayoutParams layoutParams, int i) {
        this.f3263c = swipeDismissTouchListener;
        this.f3261a = layoutParams;
        this.f3262b = i;
    }

    public void onAnimationEnd(Animator animator) {
        this.f3263c.f3116f.onDismiss(this.f3263c.f3115e, this.f3263c.f3122l);
        this.f3263c.f3115e.setTranslationX(0.0f);
        this.f3261a.height = this.f3262b;
        this.f3263c.f3115e.setLayoutParams(this.f3261a);
    }
}
