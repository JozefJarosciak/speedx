package com.beastbikes.android.widget.materialdesign.progressbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import com.beastbikes.android.widget.materialdesign.progressbar.C2693e.C2686a;
import com.beastbikes.android.widget.materialdesign.progressbar.C2693e.C2687b;
import com.beastbikes.android.widget.materialdesign.progressbar.C2693e.C2688c;
import com.beastbikes.android.widget.materialdesign.progressbar.C2693e.C2689d;
import com.beastbikes.android.widget.materialdesign.progressbar.C2693e.C2690e;
import com.beastbikes.android.widget.materialdesign.progressbar.C2693e.C2691f;
import com.beastbikes.android.widget.materialdesign.progressbar.C2693e.C2692g;

/* compiled from: Animators */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.a */
class C2683a {
    /* renamed from: a */
    private static final Path f12590a = new Path();
    /* renamed from: b */
    private static final Path f12591b = new Path();
    /* renamed from: c */
    private static final Path f12592c = new Path();
    /* renamed from: d */
    private static final Path f12593d = new Path();

    static {
        f12590a.moveTo(-522.6f, 0.0f);
        f12590a.rCubicTo(48.89972f, 0.0f, 166.02657f, 0.0f, 301.2173f, 0.0f);
        f12590a.rCubicTo(197.58128f, 0.0f, 420.9827f, 0.0f, 420.9827f, 0.0f);
        f12591b.moveTo(0.0f, 0.1f);
        f12591b.lineTo(1.0f, 0.8268492f);
        f12591b.lineTo(2.0f, 0.1f);
        f12592c.moveTo(-197.6f, 0.0f);
        f12592c.rCubicTo(14.28182f, 0.0f, 85.07782f, 0.0f, 135.54689f, 0.0f);
        f12592c.rCubicTo(54.26191f, 0.0f, 90.42461f, 0.0f, 168.24332f, 0.0f);
        f12592c.rCubicTo(144.72154f, 0.0f, 316.40982f, 0.0f, 316.40982f, 0.0f);
        f12593d.moveTo(0.0f, 0.1f);
        f12593d.lineTo(1.0f, 0.5713795f);
        f12593d.lineTo(2.0f, 0.90995026f);
        f12593d.lineTo(3.0f, 0.1f);
    }

    /* renamed from: a */
    public static Animator m13327a(Object obj) {
        ObjectAnimator a = C2694g.m13336a(obj, "translateX", null, f12590a);
        a.setDuration(2000);
        a.setInterpolator(C2687b.f12600a);
        a.setRepeatCount(-1);
        ObjectAnimator a2 = C2694g.m13336a(obj, null, "scaleX", f12591b);
        a2.setDuration(2000);
        a2.setInterpolator(C2686a.f12598a);
        a2.setRepeatCount(-1);
        Animator animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{a, a2});
        return animatorSet;
    }

    /* renamed from: b */
    public static Animator m13328b(Object obj) {
        ObjectAnimator a = C2694g.m13336a(obj, "translateX", null, f12592c);
        a.setDuration(2000);
        a.setInterpolator(C2689d.f12604a);
        a.setRepeatCount(-1);
        ObjectAnimator a2 = C2694g.m13336a(obj, null, "scaleX", f12593d);
        a2.setDuration(2000);
        a2.setInterpolator(C2688c.f12602a);
        a2.setRepeatCount(-1);
        Animator animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{a, a2});
        return animatorSet;
    }

    /* renamed from: c */
    public static Animator m13329c(Object obj) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(obj, "trimPathStart", new float[]{0.0f, 0.75f});
        ofFloat.setDuration(1333);
        ofFloat.setInterpolator(C2692g.f12609a);
        ofFloat.setRepeatCount(-1);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(obj, "trimPathEnd", new float[]{0.0f, 0.75f});
        ofFloat2.setDuration(1333);
        ofFloat2.setInterpolator(C2691f.f12607a);
        ofFloat2.setRepeatCount(-1);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(obj, "trimPathOffset", new float[]{0.0f, 0.25f});
        ofFloat3.setDuration(1333);
        ofFloat3.setInterpolator(C2690e.f12606a);
        ofFloat3.setRepeatCount(-1);
        Animator animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        return animatorSet;
    }

    /* renamed from: d */
    public static Animator m13330d(Object obj) {
        Animator ofFloat = ObjectAnimator.ofFloat(obj, "rotation", new float[]{0.0f, 720.0f});
        ofFloat.setDuration(6665);
        ofFloat.setInterpolator(C2690e.f12606a);
        ofFloat.setRepeatCount(-1);
        return ofFloat;
    }
}
