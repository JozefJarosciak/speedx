package com.baidu.mapapi.map;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.ViewGroup.LayoutParams;

/* renamed from: com.baidu.mapapi.map.o */
class C1130o implements AnimatorUpdateListener {
    /* renamed from: a */
    final /* synthetic */ LayoutParams f3264a;
    /* renamed from: b */
    final /* synthetic */ SwipeDismissTouchListener f3265b;

    C1130o(SwipeDismissTouchListener swipeDismissTouchListener, LayoutParams layoutParams) {
        this.f3265b = swipeDismissTouchListener;
        this.f3264a = layoutParams;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f3264a.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f3265b.f3115e.setLayoutParams(this.f3264a);
    }
}
