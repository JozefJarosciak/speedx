package com.beastbikes.framework.ui.android.lib.pulltorefresh;

import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

public abstract class Smoothable implements Runnable {
    static final int ANIMATION_FPS = 16;
    private boolean continueRunning = true;
    private int currentY = -1;
    private final long duration;
    private final Handler handler;
    private final Interpolator interpolator;
    private final int scrollFromY;
    private final int scrollToY;
    private long startTime = -1;

    public abstract void doSmooth(int i);

    public Smoothable(Handler handler, long j, int i, int i2) {
        this.handler = handler;
        this.duration = j;
        this.scrollFromY = i;
        this.scrollToY = i2;
        this.interpolator = new AccelerateDecelerateInterpolator();
    }

    public void run() {
        if (this.startTime == -1) {
            this.startTime = System.currentTimeMillis();
        } else {
            float f = (float) (this.scrollFromY - this.scrollToY);
            this.currentY = this.scrollFromY - Math.round(this.interpolator.getInterpolation(((float) Math.max(Math.min(((System.currentTimeMillis() - this.startTime) * 1000) / this.duration, 1000), 0)) / 1000.0f) * f);
            doSmooth(this.currentY);
        }
        if (this.continueRunning && this.scrollToY != this.currentY) {
            this.handler.postDelayed(this, 16);
        }
    }

    public void stop() {
        this.continueRunning = false;
        this.handler.removeCallbacks(this);
    }
}
