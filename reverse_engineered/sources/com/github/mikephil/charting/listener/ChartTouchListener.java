package com.github.mikephil.charting.listener;

import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import com.github.mikephil.charting.charts.C1475c;
import com.github.mikephil.charting.p181d.C3213d;

public abstract class ChartTouchListener<T extends C1475c<?>> extends SimpleOnGestureListener implements OnTouchListener {
    /* renamed from: a */
    protected ChartGesture f14233a = ChartGesture.NONE;
    /* renamed from: b */
    protected int f14234b = 0;
    /* renamed from: c */
    protected C3213d f14235c;
    /* renamed from: d */
    protected GestureDetector f14236d;
    /* renamed from: e */
    protected T f14237e;

    public enum ChartGesture {
        NONE,
        DRAG,
        X_ZOOM,
        Y_ZOOM,
        PINCH_ZOOM,
        ROTATE,
        SINGLE_TAP,
        DOUBLE_TAP,
        LONG_PRESS,
        FLING
    }

    public ChartTouchListener(T t) {
        this.f14237e = t;
        this.f14236d = new GestureDetector(t.getContext(), this);
    }

    /* renamed from: a */
    public void m15954a(MotionEvent motionEvent) {
        C3285b onChartGestureListener = this.f14237e.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.m15973a(motionEvent, this.f14233a);
        }
    }

    /* renamed from: b */
    public void m15957b(MotionEvent motionEvent) {
        C3285b onChartGestureListener = this.f14237e.getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.m15976b(motionEvent, this.f14233a);
        }
    }

    /* renamed from: a */
    public void m15955a(C3213d c3213d) {
        this.f14235c = c3213d;
    }

    /* renamed from: a */
    protected void m15956a(C3213d c3213d, MotionEvent motionEvent) {
        if (c3213d == null || c3213d.m15426a(this.f14235c)) {
            this.f14237e.a(null, true);
            this.f14235c = null;
            return;
        }
        this.f14237e.a(c3213d, true);
        this.f14235c = c3213d;
    }

    /* renamed from: a */
    protected static float m15953a(float f, float f2, float f3, float f4) {
        float f5 = f - f2;
        float f6 = f3 - f4;
        return (float) Math.sqrt((double) ((f5 * f5) + (f6 * f6)));
    }
}
