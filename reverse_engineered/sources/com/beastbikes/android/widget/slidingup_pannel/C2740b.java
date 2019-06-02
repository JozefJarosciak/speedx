package com.beastbikes.android.widget.slidingup_pannel;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import java.util.Arrays;

/* compiled from: ViewDragHelper */
/* renamed from: com.beastbikes.android.widget.slidingup_pannel.b */
public class C2740b {
    /* renamed from: x */
    private static final Interpolator f12798x = new C27381();
    /* renamed from: a */
    private int f12799a;
    /* renamed from: b */
    private int f12800b;
    /* renamed from: c */
    private int f12801c = -1;
    /* renamed from: d */
    private float[] f12802d;
    /* renamed from: e */
    private float[] f12803e;
    /* renamed from: f */
    private float[] f12804f;
    /* renamed from: g */
    private float[] f12805g;
    /* renamed from: h */
    private int[] f12806h;
    /* renamed from: i */
    private int[] f12807i;
    /* renamed from: j */
    private int[] f12808j;
    /* renamed from: k */
    private int f12809k;
    /* renamed from: l */
    private VelocityTracker f12810l;
    /* renamed from: m */
    private float f12811m;
    /* renamed from: n */
    private float f12812n;
    /* renamed from: o */
    private int f12813o;
    /* renamed from: p */
    private int f12814p;
    /* renamed from: q */
    private ScrollerCompat f12815q;
    /* renamed from: r */
    private final C2733a f12816r;
    /* renamed from: s */
    private View f12817s;
    /* renamed from: t */
    private boolean f12818t;
    /* renamed from: u */
    private final ViewGroup f12819u;
    /* renamed from: v */
    private boolean f12820v;
    /* renamed from: w */
    private int f12821w;
    /* renamed from: y */
    private final Runnable f12822y = new C27392(this);

    /* compiled from: ViewDragHelper */
    /* renamed from: com.beastbikes.android.widget.slidingup_pannel.b$a */
    public static abstract class C2733a {
        /* renamed from: a */
        public abstract boolean mo3552a(View view, int i);

        /* renamed from: a */
        public void mo3549a(int i) {
        }

        /* renamed from: a */
        public void mo3551a(View view, int i, int i2, int i3, int i4) {
        }

        /* renamed from: b */
        public void mo3553b(View view, int i) {
        }

        /* renamed from: a */
        public void mo3550a(View view, float f, float f2) {
        }

        /* renamed from: a */
        public void m13466a(int i, int i2) {
        }

        /* renamed from: b */
        public boolean m13474b(int i) {
            return false;
        }

        /* renamed from: b */
        public void m13472b(int i, int i2) {
        }

        /* renamed from: c */
        public int m13475c(int i) {
            return i;
        }

        /* renamed from: b */
        public int m13470b(View view) {
            return 0;
        }

        /* renamed from: a */
        public int mo3547a(View view) {
            return 0;
        }

        /* renamed from: b */
        public int m13471b(View view, int i, int i2) {
            return 0;
        }

        /* renamed from: a */
        public int mo3548a(View view, int i, int i2) {
            return 0;
        }
    }

    /* compiled from: ViewDragHelper */
    /* renamed from: com.beastbikes.android.widget.slidingup_pannel.b$1 */
    static class C27381 implements Interpolator {
        C27381() {
        }

        public float getInterpolation(float f) {
            float f2 = f - 1.0f;
            return (f2 * (((f2 * f2) * f2) * f2)) + 1.0f;
        }
    }

    /* compiled from: ViewDragHelper */
    /* renamed from: com.beastbikes.android.widget.slidingup_pannel.b$2 */
    class C27392 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2740b f12797a;

        C27392(C2740b c2740b) {
            this.f12797a = c2740b;
        }

        public void run() {
            this.f12797a.m13536a(0);
        }
    }

    /* renamed from: a */
    public static C2740b m13518a(ViewGroup viewGroup, Interpolator interpolator, C2733a c2733a) {
        return new C2740b(viewGroup.getContext(), viewGroup, interpolator, c2733a);
    }

    /* renamed from: a */
    public static C2740b m13517a(ViewGroup viewGroup, float f, Interpolator interpolator, C2733a c2733a) {
        C2740b a = C2740b.m13518a(viewGroup, interpolator, c2733a);
        a.f12800b = (int) (((float) a.f12800b) * (1.0f / f));
        return a;
    }

    private C2740b(Context context, ViewGroup viewGroup, Interpolator interpolator, C2733a c2733a) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        } else if (c2733a == null) {
            throw new IllegalArgumentException("Callback may not be null");
        } else {
            this.f12819u = viewGroup;
            this.f12816r = c2733a;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.f12813o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.f12800b = viewConfiguration.getScaledTouchSlop();
            this.f12811m = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.f12812n = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            if (interpolator == null) {
                interpolator = f12798x;
            }
            this.f12815q = ScrollerCompat.create(context, interpolator);
        }
    }

    /* renamed from: a */
    public void m13535a(float f) {
        this.f12812n = f;
    }

    /* renamed from: a */
    public int m13534a() {
        return this.f12799a;
    }

    /* renamed from: a */
    public void m13537a(View view, int i) {
        if (view.getParent() != this.f12819u) {
            throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f12819u + ")");
        }
        this.f12817s = view;
        this.f12801c = i;
        this.f12816r.mo3553b(view, i);
        m13536a(1);
    }

    /* renamed from: b */
    public int m13542b() {
        return this.f12800b;
    }

    /* renamed from: c */
    public void m13550c() {
        this.f12801c = -1;
        m13532f();
        if (this.f12810l != null) {
            this.f12810l.recycle();
            this.f12810l = null;
        }
    }

    /* renamed from: d */
    public void m13551d() {
        m13550c();
        if (this.f12799a == 2) {
            int currX = this.f12815q.getCurrX();
            int currY = this.f12815q.getCurrY();
            this.f12815q.abortAnimation();
            int currX2 = this.f12815q.getCurrX();
            int currY2 = this.f12815q.getCurrY();
            this.f12816r.mo3551a(this.f12817s, currX2, currY2, currX2 - currX, currY2 - currY);
        }
        m13536a(0);
    }

    /* renamed from: a */
    public boolean m13540a(View view, int i, int i2) {
        this.f12817s = view;
        this.f12801c = -1;
        return m13522a(i, i2, 0, 0);
    }

    /* renamed from: a */
    public boolean m13538a(int i, int i2) {
        if (this.f12818t) {
            return m13522a(i, i2, (int) VelocityTrackerCompat.getXVelocity(this.f12810l, this.f12801c), (int) VelocityTrackerCompat.getYVelocity(this.f12810l, this.f12801c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* renamed from: a */
    private boolean m13522a(int i, int i2, int i3, int i4) {
        int left = this.f12817s.getLeft();
        int top = this.f12817s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.f12815q.abortAnimation();
            m13536a(0);
            return false;
        }
        this.f12815q.startScroll(left, top, i5, i6, m13516a(this.f12817s, i5, i6, i3, i4));
        m13536a(2);
        return true;
    }

    /* renamed from: a */
    private int m13516a(View view, int i, int i2, int i3, int i4) {
        int b = m13525b(i3, (int) this.f12812n, (int) this.f12811m);
        int b2 = m13525b(i4, (int) this.f12812n, (int) this.f12811m);
        int abs = Math.abs(i);
        int abs2 = Math.abs(i2);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i5 = abs3 + abs4;
        int i6 = abs + abs2;
        return (int) (((b2 != 0 ? ((float) abs4) / ((float) i5) : ((float) abs2) / ((float) i6)) * ((float) m13515a(i2, b2, this.f12816r.mo3547a(view)))) + ((b != 0 ? ((float) abs3) / ((float) i5) : ((float) abs) / ((float) i6)) * ((float) m13515a(i, b, this.f12816r.m13470b(view)))));
    }

    /* renamed from: a */
    private int m13515a(int i, int i2, int i3) {
        if (i == 0) {
            return 0;
        }
        int width = this.f12819u.getWidth();
        int i4 = width / 2;
        float b = (m13524b(Math.min(1.0f, ((float) Math.abs(i)) / ((float) width))) * ((float) i4)) + ((float) i4);
        i4 = Math.abs(i2);
        if (i4 > 0) {
            width = Math.round(Math.abs(b / ((float) i4)) * 1000.0f) * 4;
        } else {
            width = (int) (((((float) Math.abs(i)) / ((float) i3)) + 1.0f) * 256.0f);
        }
        return Math.min(width, 600);
    }

    /* renamed from: b */
    private int m13525b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs <= i3) {
            return i;
        }
        if (i <= 0) {
            return -i3;
        }
        return i3;
    }

    /* renamed from: a */
    private float m13514a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs <= f3) {
            return f;
        }
        if (f <= 0.0f) {
            return -f3;
        }
        return f3;
    }

    /* renamed from: b */
    private float m13524b(float f) {
        return (float) Math.sin((double) ((float) (((double) (f - 0.5f)) * 0.4712389167638204d)));
    }

    /* renamed from: a */
    public boolean m13541a(boolean z) {
        if (this.f12817s == null) {
            return false;
        }
        if (this.f12799a == 2) {
            boolean computeScrollOffset = this.f12815q.computeScrollOffset();
            int currX = this.f12815q.getCurrX();
            int currY = this.f12815q.getCurrY();
            int left = currX - this.f12817s.getLeft();
            int top = currY - this.f12817s.getTop();
            if (computeScrollOffset || top == 0) {
                boolean isFinished;
                if (left != 0) {
                    this.f12817s.offsetLeftAndRight(left);
                }
                if (top != 0) {
                    this.f12817s.offsetTopAndBottom(top);
                }
                if (!(left == 0 && top == 0)) {
                    this.f12816r.mo3551a(this.f12817s, currX, currY, left, top);
                }
                if (computeScrollOffset && currX == this.f12815q.getFinalX() && currY == this.f12815q.getFinalY()) {
                    this.f12815q.abortAnimation();
                    isFinished = this.f12815q.isFinished();
                } else {
                    isFinished = computeScrollOffset;
                }
                if (!isFinished) {
                    if (z) {
                        this.f12819u.post(this.f12822y);
                    } else {
                        m13536a(0);
                    }
                }
            } else {
                this.f12817s.setTop(0);
                return true;
            }
        }
        return this.f12799a == 2;
    }

    /* renamed from: a */
    private void m13519a(float f, float f2) {
        this.f12818t = true;
        this.f12816r.mo3550a(this.f12817s, f, f2);
        this.f12818t = false;
        if (this.f12799a == 1) {
            m13536a(0);
        }
    }

    /* renamed from: f */
    private void m13532f() {
        if (this.f12802d != null) {
            Arrays.fill(this.f12802d, 0.0f);
            Arrays.fill(this.f12803e, 0.0f);
            Arrays.fill(this.f12804f, 0.0f);
            Arrays.fill(this.f12805g, 0.0f);
            Arrays.fill(this.f12806h, 0);
            Arrays.fill(this.f12807i, 0);
            Arrays.fill(this.f12808j, 0);
            this.f12809k = 0;
        }
    }

    /* renamed from: c */
    private void m13528c(int i) {
        if (this.f12802d != null && this.f12802d.length > i) {
            this.f12802d[i] = 0.0f;
            this.f12803e[i] = 0.0f;
            this.f12804f[i] = 0.0f;
            this.f12805g[i] = 0.0f;
            this.f12806h[i] = 0;
            this.f12807i[i] = 0;
            this.f12808j[i] = 0;
            this.f12809k &= (1 << i) ^ -1;
        }
    }

    /* renamed from: d */
    private void m13531d(int i) {
        if (this.f12802d == null || this.f12802d.length <= i) {
            Object obj = new float[(i + 1)];
            Object obj2 = new float[(i + 1)];
            Object obj3 = new float[(i + 1)];
            Object obj4 = new float[(i + 1)];
            Object obj5 = new int[(i + 1)];
            Object obj6 = new int[(i + 1)];
            Object obj7 = new int[(i + 1)];
            if (this.f12802d != null) {
                System.arraycopy(this.f12802d, 0, obj, 0, this.f12802d.length);
                System.arraycopy(this.f12803e, 0, obj2, 0, this.f12803e.length);
                System.arraycopy(this.f12804f, 0, obj3, 0, this.f12804f.length);
                System.arraycopy(this.f12805g, 0, obj4, 0, this.f12805g.length);
                System.arraycopy(this.f12806h, 0, obj5, 0, this.f12806h.length);
                System.arraycopy(this.f12807i, 0, obj6, 0, this.f12807i.length);
                System.arraycopy(this.f12808j, 0, obj7, 0, this.f12808j.length);
            }
            this.f12802d = obj;
            this.f12803e = obj2;
            this.f12804f = obj3;
            this.f12805g = obj4;
            this.f12806h = obj5;
            this.f12807i = obj6;
            this.f12808j = obj7;
        }
    }

    /* renamed from: a */
    private void m13520a(float f, float f2, int i) {
        m13531d(i);
        float[] fArr = this.f12802d;
        this.f12804f[i] = f;
        fArr[i] = f;
        fArr = this.f12803e;
        this.f12805g[i] = f2;
        fArr[i] = f2;
        this.f12806h[i] = m13530d((int) f, (int) f2);
        this.f12809k |= 1 << i;
    }

    /* renamed from: c */
    private void m13529c(MotionEvent motionEvent) {
        int pointerCount = MotionEventCompat.getPointerCount(motionEvent);
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = MotionEventCompat.getPointerId(motionEvent, i);
            float x = MotionEventCompat.getX(motionEvent, i);
            float y = MotionEventCompat.getY(motionEvent, i);
            if (this.f12804f != null && this.f12805g != null && this.f12804f.length > pointerId && this.f12805g.length > pointerId) {
                this.f12804f[pointerId] = x;
                this.f12805g[pointerId] = y;
            }
        }
    }

    /* renamed from: a */
    void m13536a(int i) {
        if (this.f12799a != i) {
            this.f12799a = i;
            this.f12816r.mo3549a(i);
            if (this.f12799a == 0) {
                this.f12817s = null;
            }
        }
    }

    /* renamed from: b */
    boolean m13547b(View view, int i) {
        if (view == this.f12817s && this.f12801c == i) {
            return true;
        }
        if (view == null || !this.f12816r.mo3552a(view, i)) {
            return false;
        }
        this.f12801c = i;
        m13537a(view, i);
        return true;
    }

    /* renamed from: a */
    public boolean m13539a(MotionEvent motionEvent) {
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            m13550c();
        }
        if (this.f12810l == null) {
            this.f12810l = VelocityTracker.obtain();
        }
        this.f12810l.addMovement(motionEvent);
        float y;
        int pointerId;
        switch (actionMasked) {
            case 0:
                float x = motionEvent.getX();
                y = motionEvent.getY();
                pointerId = MotionEventCompat.getPointerId(motionEvent, 0);
                m13520a(x, y, pointerId);
                View c = m13549c((int) x, (int) y);
                if (c == this.f12817s && this.f12799a == 2) {
                    m13547b(c, pointerId);
                }
                actionMasked = this.f12806h[pointerId];
                if ((this.f12814p & actionMasked) != 0) {
                    this.f12816r.m13466a(actionMasked & this.f12814p, pointerId);
                    break;
                }
                break;
            case 1:
            case 3:
                m13550c();
                break;
            case 2:
                actionIndex = MotionEventCompat.getPointerCount(motionEvent);
                for (actionMasked = 0; actionMasked < actionIndex && this.f12802d != null && this.f12803e != null; actionMasked++) {
                    pointerId = MotionEventCompat.getPointerId(motionEvent, actionMasked);
                    if (pointerId < this.f12802d.length && pointerId < this.f12803e.length) {
                        float x2 = MotionEventCompat.getX(motionEvent, actionMasked);
                        x2 -= this.f12802d[pointerId];
                        float y2 = MotionEventCompat.getY(motionEvent, actionMasked) - this.f12803e[pointerId];
                        m13526b(x2, y2, pointerId);
                        if (this.f12799a != 1) {
                            View c2 = m13549c((int) this.f12802d[pointerId], (int) this.f12803e[pointerId]);
                            if (c2 != null && m13523a(c2, x2, y2) && m13547b(c2, pointerId)) {
                            }
                        }
                    }
                }
                m13529c(motionEvent);
                break;
            case 5:
                actionMasked = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                float x3 = MotionEventCompat.getX(motionEvent, actionIndex);
                y = MotionEventCompat.getY(motionEvent, actionIndex);
                m13520a(x3, y, actionMasked);
                if (this.f12799a != 0) {
                    if (this.f12799a == 2) {
                        View c3 = m13549c((int) x3, (int) y);
                        if (c3 == this.f12817s) {
                            m13547b(c3, actionMasked);
                            break;
                        }
                    }
                }
                actionIndex = this.f12806h[actionMasked];
                if ((this.f12814p & actionIndex) != 0) {
                    this.f12816r.m13466a(actionIndex & this.f12814p, actionMasked);
                    break;
                }
                break;
            case 6:
                m13528c(MotionEventCompat.getPointerId(motionEvent, actionIndex));
                break;
        }
        if (this.f12799a == 1) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    public void m13544b(MotionEvent motionEvent) {
        int i = 0;
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
        if (actionMasked == 0) {
            m13550c();
        }
        if (this.f12810l == null) {
            this.f12810l = VelocityTracker.obtain();
        }
        this.f12810l.addMovement(motionEvent);
        float x;
        float y;
        int i2;
        switch (actionMasked) {
            case 0:
                x = motionEvent.getX();
                y = motionEvent.getY();
                i = MotionEventCompat.getPointerId(motionEvent, 0);
                View c = m13549c((int) x, (int) y);
                m13520a(x, y, i);
                m13547b(c, i);
                i2 = this.f12806h[i];
                if ((this.f12814p & i2) != 0) {
                    this.f12816r.m13466a(i2 & this.f12814p, i);
                    return;
                }
                return;
            case 1:
                if (this.f12799a == 1) {
                    m13533g();
                }
                m13550c();
                return;
            case 2:
                if (this.f12799a == 1) {
                    i = MotionEventCompat.findPointerIndex(motionEvent, this.f12801c);
                    x = MotionEventCompat.getX(motionEvent, i);
                    i2 = (int) (x - this.f12804f[this.f12801c]);
                    i = (int) (MotionEventCompat.getY(motionEvent, i) - this.f12805g[this.f12801c]);
                    m13527b(this.f12817s.getLeft() + i2, this.f12817s.getTop() + i, i2, i);
                    m13529c(motionEvent);
                    return;
                }
                i2 = MotionEventCompat.getPointerCount(motionEvent);
                while (i < i2) {
                    actionMasked = MotionEventCompat.getPointerId(motionEvent, i);
                    float x2 = MotionEventCompat.getX(motionEvent, i);
                    x2 -= this.f12802d[actionMasked];
                    float y2 = MotionEventCompat.getY(motionEvent, i) - this.f12803e[actionMasked];
                    m13526b(x2, y2, actionMasked);
                    if (this.f12799a != 1) {
                        View c2 = m13549c((int) this.f12802d[actionMasked], (int) this.f12803e[actionMasked]);
                        if (!m13523a(c2, x2, y2) || !m13547b(c2, actionMasked)) {
                            i++;
                        }
                    }
                    m13529c(motionEvent);
                    return;
                }
                m13529c(motionEvent);
                return;
            case 3:
                if (this.f12799a == 1) {
                    m13519a(0.0f, 0.0f);
                }
                m13550c();
                return;
            case 5:
                i = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                x = MotionEventCompat.getX(motionEvent, actionIndex);
                y = MotionEventCompat.getY(motionEvent, actionIndex);
                m13520a(x, y, i);
                if (this.f12799a == 0) {
                    m13547b(m13549c((int) x, (int) y), i);
                    i2 = this.f12806h[i];
                    if ((this.f12814p & i2) != 0) {
                        this.f12816r.m13466a(i2 & this.f12814p, i);
                        return;
                    }
                    return;
                } else if (m13546b((int) x, (int) y)) {
                    m13547b(this.f12817s, i);
                    return;
                } else {
                    return;
                }
            case 6:
                actionMasked = MotionEventCompat.getPointerId(motionEvent, actionIndex);
                if (this.f12799a == 1 && actionMasked == this.f12801c) {
                    actionIndex = MotionEventCompat.getPointerCount(motionEvent);
                    while (i < actionIndex) {
                        int pointerId = MotionEventCompat.getPointerId(motionEvent, i);
                        if (pointerId != this.f12801c) {
                            if (m13549c((int) MotionEventCompat.getX(motionEvent, i), (int) MotionEventCompat.getY(motionEvent, i)) == this.f12817s && m13547b(this.f12817s, pointerId)) {
                                i = this.f12801c;
                                if (i == -1) {
                                    m13533g();
                                }
                            }
                        }
                        i++;
                    }
                    i = -1;
                    if (i == -1) {
                        m13533g();
                    }
                }
                m13528c(actionMasked);
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m13526b(float f, float f2, int i) {
        int i2 = 1;
        if (!m13521a(f, f2, i, 1)) {
            i2 = 0;
        }
        if (m13521a(f2, f, i, 4)) {
            i2 |= 4;
        }
        if (m13521a(f, f2, i, 2)) {
            i2 |= 2;
        }
        if (m13521a(f2, f, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.f12807i;
            iArr[i] = iArr[i] | i2;
            this.f12816r.m13472b(i2, i);
        }
    }

    /* renamed from: a */
    private boolean m13521a(float f, float f2, int i, int i2) {
        float abs = Math.abs(f);
        float abs2 = Math.abs(f2);
        if ((this.f12806h[i] & i2) != i2 || (this.f12814p & i2) == 0 || (this.f12808j[i] & i2) == i2 || (this.f12807i[i] & i2) == i2) {
            return false;
        }
        if (abs <= ((float) this.f12800b) && abs2 <= ((float) this.f12800b)) {
            return false;
        }
        if (abs < abs2 * 0.5f && this.f12816r.m13474b(i2)) {
            int[] iArr = this.f12808j;
            iArr[i] = iArr[i] | i2;
            return false;
        } else if ((this.f12807i[i] & i2) != 0 || abs <= ((float) this.f12800b)) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: a */
    private boolean m13523a(View view, float f, float f2) {
        if (view == null) {
            return false;
        }
        boolean z;
        boolean z2;
        if (this.f12816r.m13470b(view) > 0) {
            z = true;
        } else {
            z = false;
        }
        if (this.f12816r.mo3547a(view) > 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && z2) {
            if ((f * f) + (f2 * f2) <= ((float) (this.f12800b * this.f12800b))) {
                return false;
            }
            return true;
        } else if (z) {
            if (Math.abs(f) <= ((float) this.f12800b)) {
                return false;
            }
            return true;
        } else if (!z2) {
            return false;
        } else {
            if (Math.abs(f2) <= ((float) this.f12800b)) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: e */
    public boolean m13552e() {
        return this.f12799a == 1;
    }

    /* renamed from: g */
    private void m13533g() {
        this.f12810l.computeCurrentVelocity(1000, this.f12811m);
        m13519a(m13514a(VelocityTrackerCompat.getXVelocity(this.f12810l, this.f12801c), this.f12812n, this.f12811m), m13514a(VelocityTrackerCompat.getYVelocity(this.f12810l, this.f12801c), this.f12812n, this.f12811m));
    }

    /* renamed from: b */
    private void m13527b(int i, int i2, int i3, int i4) {
        int b;
        int a;
        int left = this.f12817s.getLeft();
        int top = this.f12817s.getTop();
        if (i3 != 0) {
            b = this.f12816r.m13471b(this.f12817s, i, i3);
            this.f12817s.offsetLeftAndRight(b - left);
        } else {
            b = i;
        }
        if (i4 != 0) {
            a = this.f12816r.mo3548a(this.f12817s, i2, i4);
            if (!this.f12820v && Math.abs(top - this.f12821w) < 10 && a - top > 0) {
                return;
            }
            if (a - top > 0) {
                this.f12817s.offsetTopAndBottom(a - top);
            }
        } else {
            a = i2;
        }
        if (i3 != 0 || i4 != 0) {
            int i5 = b - left;
            int i6 = a - top;
            if (i6 < 0) {
                this.f12816r.mo3551a(this.f12817s, b, a, i5, i6);
            } else {
                this.f12816r.mo3551a(this.f12817s, b, a, i5, i6);
            }
        }
    }

    /* renamed from: b */
    public boolean m13546b(int i, int i2) {
        return m13548b(this.f12817s, i, i2);
    }

    /* renamed from: b */
    public boolean m13548b(View view, int i, int i2) {
        if (view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom()) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    public View m13549c(int i, int i2) {
        for (int childCount = this.f12819u.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f12819u.getChildAt(this.f12816r.m13475c(childCount));
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: d */
    private int m13530d(int i, int i2) {
        int i3 = 0;
        if (i < this.f12819u.getLeft() + this.f12813o) {
            i3 = 1;
        }
        if (i2 < this.f12819u.getTop() + this.f12813o) {
            i3 |= 4;
        }
        if (i > this.f12819u.getRight() - this.f12813o) {
            i3 |= 2;
        }
        if (i2 > this.f12819u.getBottom() - this.f12813o) {
            return i3 | 8;
        }
        return i3;
    }

    /* renamed from: b */
    public void m13543b(int i) {
        this.f12821w = i;
    }

    /* renamed from: b */
    public void m13545b(boolean z) {
        this.f12820v = z;
    }
}
