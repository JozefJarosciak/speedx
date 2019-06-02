package com.p014a.p015a.p016a.p017a;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/* compiled from: TwoFingerGestureDetector */
/* renamed from: com.a.a.a.a.d */
public abstract class C0707d extends C0704a {
    /* renamed from: h */
    protected float f1699h;
    /* renamed from: i */
    protected float f1700i;
    /* renamed from: j */
    protected float f1701j;
    /* renamed from: k */
    protected float f1702k;
    /* renamed from: l */
    private final float f1703l;
    /* renamed from: m */
    private float f1704m;
    /* renamed from: n */
    private float f1705n;
    /* renamed from: o */
    private PointF f1706o;

    /* renamed from: a */
    protected abstract void mo2311a(int i, MotionEvent motionEvent);

    /* renamed from: b */
    protected abstract void mo2312b(int i, MotionEvent motionEvent);

    public C0707d(Context context) {
        super(context);
        this.f1703l = (float) ViewConfiguration.get(context).getScaledEdgeSlop();
    }

    /* renamed from: b */
    protected void mo2313b(MotionEvent motionEvent) {
        super.mo2313b(motionEvent);
        MotionEvent motionEvent2 = this.c;
        this.f1704m = -1.0f;
        this.f1705n = -1.0f;
        float x = motionEvent2.getX(0);
        float y = motionEvent2.getY(0);
        float x2 = motionEvent2.getX(1);
        float y2 = motionEvent2.getY(1) - y;
        this.f1699h = x2 - x;
        this.f1700i = y2;
        y2 = motionEvent.getX(0);
        x = motionEvent.getY(0);
        x = motionEvent.getY(1) - x;
        this.f1701j = motionEvent.getX(1) - y2;
        this.f1702k = x;
        this.f1706o = C0707d.m2770d(motionEvent);
    }

    /* renamed from: a */
    protected static float m2768a(MotionEvent motionEvent, int i) {
        float rawX = motionEvent.getRawX() - motionEvent.getX();
        if (i < motionEvent.getPointerCount()) {
            return rawX + motionEvent.getX(i);
        }
        return 0.0f;
    }

    /* renamed from: b */
    protected static float m2769b(MotionEvent motionEvent, int i) {
        float rawY = motionEvent.getRawY() - motionEvent.getY();
        if (i < motionEvent.getPointerCount()) {
            return rawY + motionEvent.getY(i);
        }
        return 0.0f;
    }

    /* renamed from: c */
    protected boolean mo2315c(MotionEvent motionEvent) {
        DisplayMetrics displayMetrics = this.a.getResources().getDisplayMetrics();
        float f = ((float) displayMetrics.widthPixels) - this.f1703l;
        float f2 = ((float) displayMetrics.heightPixels) - this.f1703l;
        float f3 = this.f1703l;
        float rawX = motionEvent.getRawX();
        float rawY = motionEvent.getRawY();
        float a = C0707d.m2768a(motionEvent, 1);
        float b = C0707d.m2769b(motionEvent, 1);
        boolean z = rawX < f3 || rawY < f3 || rawX > f || rawY > f2;
        boolean z2;
        if (a < f3 || b < f3 || a > f || b > f2) {
            z2 = true;
        } else {
            z2 = false;
        }
        if ((z && r2) || z || r2) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public static PointF m2770d(MotionEvent motionEvent) {
        float f = 0.0f;
        int pointerCount = motionEvent.getPointerCount();
        float f2 = 0.0f;
        for (int i = 0; i < pointerCount; i++) {
            f2 += motionEvent.getX(i);
            f += motionEvent.getY(i);
        }
        return new PointF(f2 / ((float) pointerCount), f / ((float) pointerCount));
    }

    /* renamed from: e */
    public float m2775e() {
        return this.f1706o.x;
    }

    /* renamed from: f */
    public float m2776f() {
        return this.f1706o.y;
    }
}
