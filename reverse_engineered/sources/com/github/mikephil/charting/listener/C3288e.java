package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.C3203e;
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;

/* compiled from: PieRadarChartTouchListener */
/* renamed from: com.github.mikephil.charting.listener.e */
public class C3288e extends ChartTouchListener<C3203e<?>> {
    /* renamed from: f */
    private C3279e f14255f = C3279e.m15897a(0.0f, 0.0f);
    /* renamed from: g */
    private float f14256g = 0.0f;
    /* renamed from: h */
    private ArrayList<C3287a> f14257h = new ArrayList();
    /* renamed from: i */
    private long f14258i = 0;
    /* renamed from: j */
    private float f14259j = 0.0f;

    /* compiled from: PieRadarChartTouchListener */
    /* renamed from: com.github.mikephil.charting.listener.e$a */
    private class C3287a {
        /* renamed from: a */
        public long f14252a;
        /* renamed from: b */
        public float f14253b;
        /* renamed from: c */
        final /* synthetic */ C3288e f14254c;

        public C3287a(C3288e c3288e, long j, float f) {
            this.f14254c = c3288e;
            this.f14252a = j;
            this.f14253b = f;
        }
    }

    public C3288e(C3203e<?> c3203e) {
        super(c3203e);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.d.onTouchEvent(motionEvent) && ((C3203e) this.e).m15330i()) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            switch (motionEvent.getAction()) {
                case 0:
                    m15954a(motionEvent);
                    m15981a();
                    m15978c();
                    if (((C3203e) this.e).x()) {
                        m15979c(x, y);
                    }
                    m15982a(x, y);
                    this.f14255f.f14200a = x;
                    this.f14255f.f14201b = y;
                    break;
                case 1:
                    if (((C3203e) this.e).x()) {
                        m15981a();
                        m15979c(x, y);
                        this.f14259j = m15980d();
                        if (this.f14259j != 0.0f) {
                            this.f14258i = AnimationUtils.currentAnimationTimeMillis();
                            C3283i.m15939a(this.e);
                        }
                    }
                    ((C3203e) this.e).A();
                    this.b = 0;
                    m15957b(motionEvent);
                    break;
                case 2:
                    if (((C3203e) this.e).x()) {
                        m15979c(x, y);
                    }
                    if (this.b == 0 && ChartTouchListener.m15953a(x, this.f14255f.f14200a, y, this.f14255f.f14201b) > C3283i.m15928a(8.0f)) {
                        this.a = ChartGesture.ROTATE;
                        this.b = 6;
                        ((C3203e) this.e).z();
                    } else if (this.b == 6) {
                        m15984b(x, y);
                        ((C3203e) this.e).invalidate();
                    }
                    m15957b(motionEvent);
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.a = ChartGesture.LONG_PRESS;
        C3285b onChartGestureListener = ((C3203e) this.e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.m15970a(motionEvent);
        }
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return true;
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.a = ChartGesture.SINGLE_TAP;
        C3285b onChartGestureListener = ((C3203e) this.e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.m15977c(motionEvent);
        }
        if (!((C3203e) this.e).v()) {
            return false;
        }
        m15956a(((C3203e) this.e).a(motionEvent.getX(), motionEvent.getY()), motionEvent);
        return true;
    }

    /* renamed from: c */
    private void m15978c() {
        this.f14257h.clear();
    }

    /* renamed from: c */
    private void m15979c(float f, float f2) {
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        this.f14257h.add(new C3287a(this, currentAnimationTimeMillis, ((C3203e) this.e).m15326b(f, f2)));
        for (int size = this.f14257h.size(); size - 2 > 0 && currentAnimationTimeMillis - ((C3287a) this.f14257h.get(0)).f14252a > 1000; size--) {
            this.f14257h.remove(0);
        }
    }

    /* renamed from: d */
    private float m15980d() {
        if (this.f14257h.isEmpty()) {
            return 0.0f;
        }
        C3287a c3287a = (C3287a) this.f14257h.get(0);
        C3287a c3287a2 = (C3287a) this.f14257h.get(this.f14257h.size() - 1);
        C3287a c3287a3 = c3287a;
        for (int size = this.f14257h.size() - 1; size >= 0; size--) {
            c3287a3 = (C3287a) this.f14257h.get(size);
            if (c3287a3.f14253b != c3287a2.f14253b) {
                break;
            }
        }
        float f = ((float) (c3287a2.f14252a - c3287a.f14252a)) / 1000.0f;
        if (f == 0.0f) {
            f = 0.1f;
        }
        Object obj = c3287a2.f14253b >= c3287a3.f14253b ? 1 : null;
        Object obj2 = ((double) Math.abs(c3287a2.f14253b - c3287a3.f14253b)) > 270.0d ? obj == null ? 1 : null : obj;
        if (((double) (c3287a2.f14253b - c3287a.f14253b)) > 180.0d) {
            c3287a.f14253b = (float) (((double) c3287a.f14253b) + 360.0d);
        } else if (((double) (c3287a.f14253b - c3287a2.f14253b)) > 180.0d) {
            c3287a2.f14253b = (float) (((double) c3287a2.f14253b) + 360.0d);
        }
        float abs = Math.abs((c3287a2.f14253b - c3287a.f14253b) / f);
        if (obj2 == null) {
            return -abs;
        }
        return abs;
    }

    /* renamed from: a */
    public void m15982a(float f, float f2) {
        this.f14256g = ((C3203e) this.e).m15326b(f, f2) - ((C3203e) this.e).getRawRotationAngle();
    }

    /* renamed from: b */
    public void m15984b(float f, float f2) {
        ((C3203e) this.e).setRotationAngle(((C3203e) this.e).m15326b(f, f2) - this.f14256g);
    }

    /* renamed from: a */
    public void m15981a() {
        this.f14259j = 0.0f;
    }

    /* renamed from: b */
    public void m15983b() {
        if (this.f14259j != 0.0f) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.f14259j = ((C3203e) this.e).getDragDecelerationFrictionCoef() * this.f14259j;
            ((C3203e) this.e).setRotationAngle(((C3203e) this.e).getRotationAngle() + ((((float) (currentAnimationTimeMillis - this.f14258i)) / 1000.0f) * this.f14259j));
            this.f14258i = currentAnimationTimeMillis;
            if (((double) Math.abs(this.f14259j)) >= 0.001d) {
                C3283i.m15939a(this.e);
            } else {
                m15981a();
            }
        }
    }
}
