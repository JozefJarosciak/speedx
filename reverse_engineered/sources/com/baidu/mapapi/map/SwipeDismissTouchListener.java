package com.baidu.mapapi.map;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.LayoutParams;

public class SwipeDismissTouchListener implements OnTouchListener {
    /* renamed from: a */
    private int f3111a;
    /* renamed from: b */
    private int f3112b;
    /* renamed from: c */
    private int f3113c;
    /* renamed from: d */
    private long f3114d;
    /* renamed from: e */
    private View f3115e;
    /* renamed from: f */
    private DismissCallbacks f3116f;
    /* renamed from: g */
    private int f3117g = 1;
    /* renamed from: h */
    private float f3118h;
    /* renamed from: i */
    private float f3119i;
    /* renamed from: j */
    private boolean f3120j;
    /* renamed from: k */
    private int f3121k;
    /* renamed from: l */
    private Object f3122l;
    /* renamed from: m */
    private VelocityTracker f3123m;
    /* renamed from: n */
    private float f3124n;
    /* renamed from: o */
    private boolean f3125o;
    /* renamed from: p */
    private boolean f3126p;

    public interface DismissCallbacks {
        boolean canDismiss(Object obj);

        void onDismiss(View view, Object obj);

        void onNotify();
    }

    public SwipeDismissTouchListener(View view, Object obj, DismissCallbacks dismissCallbacks) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(view.getContext());
        this.f3111a = viewConfiguration.getScaledTouchSlop();
        this.f3112b = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f3113c = viewConfiguration.getScaledMaximumFlingVelocity();
        this.f3114d = (long) view.getContext().getResources().getInteger(17694720);
        this.f3115e = view;
        this.f3115e.getContext();
        this.f3122l = obj;
        this.f3116f = dismissCallbacks;
    }

    @TargetApi(11)
    /* renamed from: a */
    private void m4170a() {
        LayoutParams layoutParams = this.f3115e.getLayoutParams();
        ValueAnimator duration = ValueAnimator.ofInt(new int[]{this.f3115e.getHeight(), 1}).setDuration(this.f3114d);
        duration.addListener(new C1129n(this, layoutParams, r1));
        duration.addUpdateListener(new C1130o(this, layoutParams));
        duration.start();
    }

    @TargetApi(12)
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z = true;
        motionEvent.offsetLocation(this.f3124n, 0.0f);
        if (this.f3117g < 2) {
            this.f3117g = this.f3115e.getWidth();
        }
        float rawX;
        float xVelocity;
        switch (motionEvent.getActionMasked()) {
            case 0:
                this.f3118h = motionEvent.getRawX();
                this.f3119i = motionEvent.getRawY();
                if (!this.f3116f.canDismiss(this.f3122l)) {
                    return true;
                }
                this.f3125o = false;
                this.f3123m = VelocityTracker.obtain();
                this.f3123m.addMovement(motionEvent);
                return true;
            case 1:
                if (this.f3123m != null) {
                    boolean z2;
                    rawX = motionEvent.getRawX() - this.f3118h;
                    this.f3123m.addMovement(motionEvent);
                    this.f3123m.computeCurrentVelocity(1000);
                    xVelocity = this.f3123m.getXVelocity();
                    float abs = Math.abs(xVelocity);
                    float abs2 = Math.abs(this.f3123m.getYVelocity());
                    if (Math.abs(rawX) > ((float) (this.f3117g / 3)) && this.f3120j) {
                        z2 = rawX > 0.0f;
                    } else if (((float) this.f3112b) > abs || abs > ((float) this.f3113c) || abs2 >= abs || abs2 >= abs || !this.f3120j) {
                        z2 = false;
                        z = false;
                    } else {
                        z2 = ((xVelocity > 0.0f ? 1 : (xVelocity == 0.0f ? 0 : -1)) < 0) == ((rawX > 0.0f ? 1 : (rawX == 0.0f ? 0 : -1)) < 0);
                        if (this.f3123m.getXVelocity() <= 0.0f) {
                            z = false;
                        }
                        boolean z3 = z;
                        z = z2;
                        z2 = z3;
                    }
                    if (z) {
                        this.f3115e.animate().translationX(z2 ? (float) this.f3117g : (float) (-this.f3117g)).setDuration(this.f3114d).setListener(new C1128m(this));
                    } else if (this.f3120j) {
                        this.f3115e.animate().translationX(0.0f).setDuration(this.f3114d).setListener(null);
                    }
                    this.f3123m.recycle();
                    this.f3123m = null;
                    this.f3124n = 0.0f;
                    this.f3118h = 0.0f;
                    this.f3119i = 0.0f;
                    this.f3120j = false;
                    break;
                }
                break;
            case 2:
                if (this.f3123m != null) {
                    this.f3123m.addMovement(motionEvent);
                    xVelocity = motionEvent.getRawX() - this.f3118h;
                    rawX = motionEvent.getRawY() - this.f3119i;
                    if (Math.abs(xVelocity) > ((float) this.f3111a) && Math.abs(rawX) < Math.abs(xVelocity) / 2.0f) {
                        this.f3120j = true;
                        this.f3121k = xVelocity > 0.0f ? this.f3111a : -this.f3111a;
                        this.f3115e.getParent().requestDisallowInterceptTouchEvent(true);
                        if (!this.f3125o) {
                            this.f3125o = true;
                            this.f3116f.onNotify();
                        }
                        if (Math.abs(xVelocity) <= ((float) (this.f3117g / 3))) {
                            this.f3126p = false;
                        } else if (!this.f3126p) {
                            this.f3126p = true;
                            this.f3116f.onNotify();
                        }
                        MotionEvent obtain = MotionEvent.obtain(motionEvent);
                        obtain.setAction((motionEvent.getActionIndex() << 8) | 3);
                        this.f3115e.onTouchEvent(obtain);
                        obtain.recycle();
                    }
                    if (this.f3120j) {
                        this.f3124n = xVelocity;
                        this.f3115e.setTranslationX(xVelocity - ((float) this.f3121k));
                        return true;
                    }
                }
                break;
            case 3:
                if (this.f3123m != null) {
                    this.f3115e.animate().translationX(0.0f).setDuration(this.f3114d).setListener(null);
                    this.f3123m.recycle();
                    this.f3123m = null;
                    this.f3124n = 0.0f;
                    this.f3118h = 0.0f;
                    this.f3119i = 0.0f;
                    this.f3120j = false;
                    break;
                }
                break;
        }
        return false;
    }
}
