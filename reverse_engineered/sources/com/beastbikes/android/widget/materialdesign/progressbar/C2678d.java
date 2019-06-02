package com.beastbikes.android.widget.materialdesign.progressbar;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Animatable;

/* compiled from: IndeterminateProgressDrawableBase */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.d */
abstract class C2678d extends C2677j implements Animatable {
    /* renamed from: a */
    protected Animator[] f12560a;

    public C2678d(Context context) {
        super(context);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (mo3531a()) {
            invalidateSelf();
        }
    }

    public void start() {
        if (!mo3531a()) {
            for (Animator start : this.f12560a) {
                start.start();
            }
            invalidateSelf();
        }
    }

    /* renamed from: a */
    private boolean mo3531a() {
        for (Animator isStarted : this.f12560a) {
            if (isStarted.isStarted()) {
                return true;
            }
        }
        return false;
    }

    public void stop() {
        for (Animator end : this.f12560a) {
            end.end();
        }
    }

    public boolean isRunning() {
        for (Animator isRunning : this.f12560a) {
            if (isRunning.isRunning()) {
                return true;
            }
        }
        return false;
    }
}
