package com.twitter.sdk.android.tweetui.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* compiled from: AnimationUtils */
/* renamed from: com.twitter.sdk.android.tweetui.internal.a */
public class C4721a {
    @TargetApi(12)
    /* renamed from: a */
    public static ViewPropertyAnimator m18593a(final View view, int i) {
        if (view.getVisibility() != 0) {
            return null;
        }
        view.clearAnimation();
        ViewPropertyAnimator animate = view.animate();
        animate.alpha(0.0f).setDuration((long) i).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(4);
                view.setAlpha(1.0f);
            }
        });
        return animate;
    }

    @TargetApi(12)
    /* renamed from: b */
    public static ViewPropertyAnimator m18594b(View view, int i) {
        if (view.getVisibility() != 0) {
            view.setAlpha(0.0f);
            view.setVisibility(0);
        }
        view.clearAnimation();
        ViewPropertyAnimator animate = view.animate();
        animate.alpha(1.0f).setDuration((long) i).setListener(null);
        return animate;
    }
}
