package com.github.mikephil.charting.listener;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.animation.AnimationUtils;
import com.github.mikephil.charting.charts.C2009b;
import com.github.mikephil.charting.charts.C3201d;
import com.github.mikephil.charting.data.C3225c;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.ChartTouchListener.ChartGesture;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3227b;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: BarLineChartTouchListener */
/* renamed from: com.github.mikephil.charting.listener.a */
public class C3284a extends ChartTouchListener<C2009b<? extends C3225c<? extends C3227b<? extends Entry>>>> {
    /* renamed from: f */
    private Matrix f14238f = new Matrix();
    /* renamed from: g */
    private Matrix f14239g = new Matrix();
    /* renamed from: h */
    private C3279e f14240h = C3279e.m15897a(0.0f, 0.0f);
    /* renamed from: i */
    private C3279e f14241i = C3279e.m15897a(0.0f, 0.0f);
    /* renamed from: j */
    private float f14242j = 1.0f;
    /* renamed from: k */
    private float f14243k = 1.0f;
    /* renamed from: l */
    private float f14244l = 1.0f;
    /* renamed from: m */
    private C3220e f14245m;
    /* renamed from: n */
    private VelocityTracker f14246n;
    /* renamed from: o */
    private long f14247o = 0;
    /* renamed from: p */
    private C3279e f14248p = C3279e.m15897a(0.0f, 0.0f);
    /* renamed from: q */
    private C3279e f14249q = C3279e.m15897a(0.0f, 0.0f);
    /* renamed from: r */
    private float f14250r;
    /* renamed from: s */
    private float f14251s;

    public C3284a(C2009b<? extends C3225c<? extends C3227b<? extends Entry>>> c2009b, Matrix matrix, float f) {
        super(c2009b);
        this.f14238f = matrix;
        this.f14250r = C3283i.m15928a(f);
        this.f14251s = C3283i.m15928a(3.5f);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int i = 2;
        if (this.f14246n == null) {
            this.f14246n = VelocityTracker.obtain();
        }
        this.f14246n.addMovement(motionEvent);
        if (motionEvent.getActionMasked() == 3 && this.f14246n != null) {
            this.f14246n.recycle();
            this.f14246n = null;
        }
        if (this.b == 0) {
            this.d.onTouchEvent(motionEvent);
        }
        if (((C2009b) this.e).m10369l() || ((C2009b) this.e).m10370m() || ((C2009b) this.e).m10371n()) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    m15954a(motionEvent);
                    m15968a();
                    m15959c(motionEvent);
                    break;
                case 1:
                    VelocityTracker velocityTracker = this.f14246n;
                    int pointerId = motionEvent.getPointerId(0);
                    velocityTracker.computeCurrentVelocity(1000, (float) C3283i.m15949c());
                    float yVelocity = velocityTracker.getYVelocity(pointerId);
                    float xVelocity = velocityTracker.getXVelocity(pointerId);
                    if ((Math.abs(xVelocity) > ((float) C3283i.m15944b()) || Math.abs(yVelocity) > ((float) C3283i.m15944b())) && this.b == 1 && ((C2009b) this.e).x()) {
                        m15968a();
                        this.f14247o = AnimationUtils.currentAnimationTimeMillis();
                        this.f14248p.f14200a = motionEvent.getX();
                        this.f14248p.f14201b = motionEvent.getY();
                        this.f14249q.f14200a = xVelocity;
                        this.f14249q.f14201b = yVelocity;
                        C3283i.m15939a(this.e);
                    }
                    if (this.b == 2 || this.b == 3 || this.b == 4 || this.b == 5) {
                        ((C2009b) this.e).mo3894j();
                        ((C2009b) this.e).postInvalidate();
                    }
                    this.b = 0;
                    ((C2009b) this.e).A();
                    if (this.f14246n != null) {
                        this.f14246n.recycle();
                        this.f14246n = null;
                    }
                    m15957b(motionEvent);
                    break;
                case 2:
                    if (this.b != 1) {
                        if (this.b != 2 && this.b != 3 && this.b != 4) {
                            if (this.b == 0 && Math.abs(ChartTouchListener.m15953a(motionEvent.getX(), this.f14240h.f14200a, motionEvent.getY(), this.f14240h.f14201b)) > this.f14250r) {
                                if (!((C2009b) this.e).m10376s()) {
                                    if (((C2009b) this.e).m10369l()) {
                                        this.a = ChartGesture.DRAG;
                                        this.b = 1;
                                        break;
                                    }
                                } else if (!((C2009b) this.e).m10374q() && ((C2009b) this.e).m10369l()) {
                                    this.b = 1;
                                    break;
                                } else {
                                    this.a = ChartGesture.DRAG;
                                    if (((C2009b) this.e).m10368k()) {
                                        m15963f(motionEvent);
                                        break;
                                    }
                                }
                            }
                        }
                        ((C2009b) this.e).z();
                        if (((C2009b) this.e).m10370m() || ((C2009b) this.e).m10371n()) {
                            m15962e(motionEvent);
                            break;
                        }
                    }
                    ((C2009b) this.e).z();
                    m15961d(motionEvent);
                    break;
                    break;
                case 3:
                    this.b = 0;
                    m15957b(motionEvent);
                    break;
                case 5:
                    if (motionEvent.getPointerCount() >= 2) {
                        ((C2009b) this.e).z();
                        m15959c(motionEvent);
                        this.f14242j = C3284a.m15965h(motionEvent);
                        this.f14243k = C3284a.m15966i(motionEvent);
                        this.f14244l = C3284a.m15964g(motionEvent);
                        if (this.f14244l > 10.0f) {
                            if (((C2009b) this.e).m10375r()) {
                                this.b = 4;
                            } else if (((C2009b) this.e).m10370m() != ((C2009b) this.e).m10371n()) {
                                this.b = ((C2009b) this.e).m10370m() ? 2 : 3;
                            } else {
                                if (this.f14242j <= this.f14243k) {
                                    i = 3;
                                }
                                this.b = i;
                            }
                        }
                        C3284a.m15958a(this.f14241i, motionEvent);
                        break;
                    }
                    break;
                case 6:
                    C3283i.m15938a(motionEvent, this.f14246n);
                    this.b = 5;
                    break;
            }
            this.f14238f = ((C2009b) this.e).getViewPortHandler().m15848a(this.f14238f, this.e, true);
        }
        return true;
    }

    /* renamed from: c */
    private void m15959c(MotionEvent motionEvent) {
        this.f14239g.set(this.f14238f);
        this.f14240h.f14200a = motionEvent.getX();
        this.f14240h.f14201b = motionEvent.getY();
        this.f14245m = ((C2009b) this.e).m10359b(motionEvent.getX(), motionEvent.getY());
    }

    /* renamed from: d */
    private void m15961d(MotionEvent motionEvent) {
        float x;
        float y;
        this.a = ChartGesture.DRAG;
        this.f14238f.set(this.f14239g);
        C3285b onChartGestureListener = ((C2009b) this.e).getOnChartGestureListener();
        if (!m15960c()) {
            x = motionEvent.getX() - this.f14240h.f14200a;
            y = motionEvent.getY() - this.f14240h.f14201b;
        } else if (this.e instanceof C3201d) {
            x = -(motionEvent.getX() - this.f14240h.f14200a);
            y = motionEvent.getY() - this.f14240h.f14201b;
        } else {
            x = motionEvent.getX() - this.f14240h.f14200a;
            y = -(motionEvent.getY() - this.f14240h.f14201b);
        }
        this.f14238f.postTranslate(x, y);
        if (onChartGestureListener != null) {
            onChartGestureListener.m15975b(motionEvent, x, y);
        }
    }

    /* renamed from: e */
    private void m15962e(MotionEvent motionEvent) {
        Object obj = 1;
        if (motionEvent.getPointerCount() >= 2) {
            C3285b onChartGestureListener = ((C2009b) this.e).getOnChartGestureListener();
            float g = C3284a.m15964g(motionEvent);
            if (g > this.f14251s) {
                C3279e a = m15967a(this.f14241i.f14200a, this.f14241i.f14201b);
                C3275j viewPortHandler = ((C2009b) this.e).getViewPortHandler();
                if (this.b == 4) {
                    boolean y;
                    float f;
                    this.a = ChartGesture.PINCH_ZOOM;
                    g /= this.f14244l;
                    if (g >= 1.0f) {
                        obj = null;
                    }
                    boolean w;
                    if (obj != null) {
                        w = viewPortHandler.m15888w();
                    } else {
                        w = viewPortHandler.m15889x();
                    }
                    if (obj != null) {
                        y = viewPortHandler.m15890y();
                    } else {
                        y = viewPortHandler.m15891z();
                    }
                    if (((C2009b) this.e).m10370m()) {
                        f = g;
                    } else {
                        f = 1.0f;
                    }
                    if (!((C2009b) this.e).m10371n()) {
                        g = 1.0f;
                    }
                    if (y || r5) {
                        this.f14238f.set(this.f14239g);
                        this.f14238f.postScale(f, g, a.f14200a, a.f14201b);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.m15971a(motionEvent, f, g);
                        }
                    }
                } else if (this.b == 2 && ((C2009b) this.e).m10370m()) {
                    this.a = ChartGesture.X_ZOOM;
                    g = C3284a.m15965h(motionEvent) / this.f14242j;
                    if (g >= 1.0f) {
                        obj = null;
                    }
                    if (obj != null) {
                        r0 = viewPortHandler.m15888w();
                    } else {
                        r0 = viewPortHandler.m15889x();
                    }
                    if (r0) {
                        this.f14238f.set(this.f14239g);
                        this.f14238f.postScale(g, 1.0f, a.f14200a, a.f14201b);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.m15971a(motionEvent, g, 1.0f);
                        }
                    }
                } else if (this.b == 3 && ((C2009b) this.e).m10371n()) {
                    this.a = ChartGesture.Y_ZOOM;
                    g = C3284a.m15966i(motionEvent) / this.f14243k;
                    if ((g < 1.0f ? 1 : null) != null) {
                        r0 = viewPortHandler.m15890y();
                    } else {
                        r0 = viewPortHandler.m15891z();
                    }
                    if (r0) {
                        this.f14238f.set(this.f14239g);
                        this.f14238f.postScale(1.0f, g, a.f14200a, a.f14201b);
                        if (onChartGestureListener != null) {
                            onChartGestureListener.m15971a(motionEvent, 1.0f, g);
                        }
                    }
                }
                C3279e.m15900b(a);
            }
        }
    }

    /* renamed from: f */
    private void m15963f(MotionEvent motionEvent) {
        C3213d a = ((C2009b) this.e).a(motionEvent.getX(), motionEvent.getY());
        if (a != null && !a.m15426a(this.c)) {
            this.c = a;
            ((C2009b) this.e).a(a, true);
        }
    }

    /* renamed from: a */
    private static void m15958a(C3279e c3279e, MotionEvent motionEvent) {
        float y = motionEvent.getY(0) + motionEvent.getY(1);
        c3279e.f14200a = (motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f;
        c3279e.f14201b = y / 2.0f;
    }

    /* renamed from: g */
    private static float m15964g(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    /* renamed from: h */
    private static float m15965h(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getX(0) - motionEvent.getX(1));
    }

    /* renamed from: i */
    private static float m15966i(MotionEvent motionEvent) {
        return Math.abs(motionEvent.getY(0) - motionEvent.getY(1));
    }

    /* renamed from: a */
    public C3279e m15967a(float f, float f2) {
        float f3;
        C3275j viewPortHandler = ((C2009b) this.e).getViewPortHandler();
        float a = f - viewPortHandler.m15847a();
        if (m15960c()) {
            f3 = -(f2 - viewPortHandler.m15858c());
        } else {
            f3 = -((((float) ((C2009b) this.e).getMeasuredHeight()) - f2) - viewPortHandler.m15860d());
        }
        return C3279e.m15897a(a, f3);
    }

    /* renamed from: c */
    private boolean m15960c() {
        return (this.f14245m == null && ((C2009b) this.e).m10377t()) || (this.f14245m != null && ((C2009b) this.e).mo3344c(this.f14245m.mo3913A()));
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        float f = 1.4f;
        this.a = ChartGesture.DOUBLE_TAP;
        C3285b onChartGestureListener = ((C2009b) this.e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.m15974b(motionEvent);
        }
        if (((C2009b) this.e).m10372o() && ((C3225c) ((C2009b) this.e).getData()).m15597k() > 0) {
            C3279e a = m15967a(motionEvent.getX(), motionEvent.getY());
            C2009b c2009b = (C2009b) this.e;
            float f2 = ((C2009b) this.e).m10370m() ? 1.4f : 1.0f;
            if (!((C2009b) this.e).m10371n()) {
                f = 1.0f;
            }
            c2009b.m10355a(f2, f, a.f14200a, a.f14201b);
            if (((C2009b) this.e).y()) {
                Log.i("BarlineChartTouch", "Double-Tap, Zooming In, x: " + a.f14200a + ", y: " + a.f14201b);
            }
            C3279e.m15900b(a);
        }
        return super.onDoubleTap(motionEvent);
    }

    public void onLongPress(MotionEvent motionEvent) {
        this.a = ChartGesture.LONG_PRESS;
        C3285b onChartGestureListener = ((C2009b) this.e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.m15970a(motionEvent);
        }
    }

    public boolean onSingleTapUp(MotionEvent motionEvent) {
        this.a = ChartGesture.SINGLE_TAP;
        C3285b onChartGestureListener = ((C2009b) this.e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.m15977c(motionEvent);
        }
        if (!((C2009b) this.e).v()) {
            return false;
        }
        m15956a(((C2009b) this.e).a(motionEvent.getX(), motionEvent.getY()), motionEvent);
        return super.onSingleTapUp(motionEvent);
    }

    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.a = ChartGesture.FLING;
        C3285b onChartGestureListener = ((C2009b) this.e).getOnChartGestureListener();
        if (onChartGestureListener != null) {
            onChartGestureListener.m15972a(motionEvent, motionEvent2, f, f2);
        }
        return super.onFling(motionEvent, motionEvent2, f, f2);
    }

    /* renamed from: a */
    public void m15968a() {
        this.f14249q.f14200a = 0.0f;
        this.f14249q.f14201b = 0.0f;
    }

    /* renamed from: b */
    public void m15969b() {
        if (this.f14249q.f14200a != 0.0f || this.f14249q.f14201b != 0.0f) {
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            C3279e c3279e = this.f14249q;
            c3279e.f14200a = ((C2009b) this.e).getDragDecelerationFrictionCoef() * c3279e.f14200a;
            c3279e = this.f14249q;
            c3279e.f14201b = ((C2009b) this.e).getDragDecelerationFrictionCoef() * c3279e.f14201b;
            float f = ((float) (currentAnimationTimeMillis - this.f14247o)) / 1000.0f;
            float f2 = this.f14249q.f14200a * f;
            f *= this.f14249q.f14201b;
            C3279e c3279e2 = this.f14248p;
            c3279e2.f14200a = f2 + c3279e2.f14200a;
            c3279e = this.f14248p;
            c3279e.f14201b = f + c3279e.f14201b;
            MotionEvent obtain = MotionEvent.obtain(currentAnimationTimeMillis, currentAnimationTimeMillis, 2, this.f14248p.f14200a, this.f14248p.f14201b, 0);
            m15961d(obtain);
            obtain.recycle();
            this.f14238f = ((C2009b) this.e).getViewPortHandler().m15848a(this.f14238f, this.e, false);
            this.f14247o = currentAnimationTimeMillis;
            if (((double) Math.abs(this.f14249q.f14200a)) >= 0.01d || ((double) Math.abs(this.f14249q.f14201b)) >= 0.01d) {
                C3283i.m15939a(this.e);
                return;
            }
            ((C2009b) this.e).mo3894j();
            ((C2009b) this.e).postInvalidate();
            m15968a();
        }
    }
}
