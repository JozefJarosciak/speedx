package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.beastbikes.android.R$styleable;
import org.apache.http.HttpStatus;

/* compiled from: RippleDrawable */
/* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.b */
public class C2665b extends Drawable implements Animatable, OnTouchListener {
    /* renamed from: A */
    private static final float[] f12485A = new float[]{0.0f, 0.99f, 1.0f};
    /* renamed from: B */
    private final Runnable f12486B;
    /* renamed from: a */
    private boolean f12487a;
    /* renamed from: b */
    private Paint f12488b;
    /* renamed from: c */
    private Paint f12489c;
    /* renamed from: d */
    private C2664b f12490d;
    /* renamed from: e */
    private RadialGradient f12491e;
    /* renamed from: f */
    private RadialGradient f12492f;
    /* renamed from: g */
    private Matrix f12493g;
    /* renamed from: h */
    private int f12494h;
    /* renamed from: i */
    private Drawable f12495i;
    /* renamed from: j */
    private RectF f12496j;
    /* renamed from: k */
    private Path f12497k;
    /* renamed from: l */
    private int f12498l;
    /* renamed from: m */
    private int f12499m;
    /* renamed from: n */
    private float f12500n;
    /* renamed from: o */
    private PointF f12501o;
    /* renamed from: p */
    private float f12502p;
    /* renamed from: q */
    private int f12503q;
    /* renamed from: r */
    private int f12504r;
    /* renamed from: s */
    private int f12505s;
    /* renamed from: t */
    private int f12506t;
    /* renamed from: u */
    private float f12507u;
    /* renamed from: v */
    private int f12508v;
    /* renamed from: w */
    private Interpolator f12509w;
    /* renamed from: x */
    private Interpolator f12510x;
    /* renamed from: y */
    private long f12511y;
    /* renamed from: z */
    private int f12512z;

    /* compiled from: RippleDrawable */
    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.b$1 */
    class C26621 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2665b f12459a;

        C26621(C2665b c2665b) {
            this.f12459a = c2665b;
        }

        public void run() {
            switch (this.f12459a.f12503q) {
                case -1:
                case 0:
                    this.f12459a.m13249e();
                    return;
                case 1:
                    this.f12459a.m13250f();
                    return;
                default:
                    return;
            }
        }
    }

    /* compiled from: RippleDrawable */
    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.b$a */
    public static class C2663a {
        /* renamed from: a */
        private Drawable f12460a;
        /* renamed from: b */
        private int f12461b;
        /* renamed from: c */
        private int f12462c;
        /* renamed from: d */
        private int f12463d;
        /* renamed from: e */
        private int f12464e;
        /* renamed from: f */
        private int f12465f;
        /* renamed from: g */
        private int f12466g;
        /* renamed from: h */
        private int f12467h;
        /* renamed from: i */
        private Interpolator f12468i;
        /* renamed from: j */
        private Interpolator f12469j;
        /* renamed from: k */
        private int f12470k;
        /* renamed from: l */
        private int f12471l;
        /* renamed from: m */
        private int f12472m;
        /* renamed from: n */
        private int f12473n;
        /* renamed from: o */
        private int f12474o;
        /* renamed from: p */
        private int f12475p;
        /* renamed from: q */
        private int f12476q;
        /* renamed from: r */
        private int f12477r;
        /* renamed from: s */
        private int f12478s;

        public C2663a() {
            this.f12461b = 200;
            this.f12465f = HttpStatus.SC_BAD_REQUEST;
        }

        public C2663a(Context context, int i) {
            this(context, null, 0, i);
        }

        public C2663a(Context context, AttributeSet attributeSet, int i, int i2) {
            this.f12461b = 200;
            this.f12465f = HttpStatus.SC_BAD_REQUEST;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RippleDrawable, i, i2);
            m13222b(obtainStyledAttributes.getColor(0, 0));
            m13218a(obtainStyledAttributes.getInteger(1, context.getResources().getInteger(17694721)));
            m13224c(obtainStyledAttributes.getInteger(8, 0));
            m13225d(obtainStyledAttributes.getInteger(19, 0));
            int a = C2671e.m13277a(obtainStyledAttributes, 2);
            if (a < 16 || a > 31) {
                m13226e(obtainStyledAttributes.getDimensionPixelSize(2, C2671e.m13275a(context, 48)));
            } else {
                m13226e(obtainStyledAttributes.getInteger(2, -1));
            }
            m13228g(obtainStyledAttributes.getColor(3, C2671e.m13280d(context, 0)));
            m13227f(obtainStyledAttributes.getInteger(4, context.getResources().getInteger(17694721)));
            a = obtainStyledAttributes.getResourceId(5, 0);
            if (a != 0) {
                m13220a(AnimationUtils.loadInterpolator(context, a));
            }
            a = obtainStyledAttributes.getResourceId(6, 0);
            if (a != 0) {
                m13223b(AnimationUtils.loadInterpolator(context, a));
            }
            m13229h(obtainStyledAttributes.getInteger(7, 0));
            m13230i(obtainStyledAttributes.getDimensionPixelSize(9, 0));
            m13231j(obtainStyledAttributes.getDimensionPixelSize(10, this.f12471l));
            m13232k(obtainStyledAttributes.getDimensionPixelSize(11, this.f12472m));
            m13234m(obtainStyledAttributes.getDimensionPixelSize(13, this.f12474o));
            m13233l(obtainStyledAttributes.getDimensionPixelSize(12, this.f12473n));
            m13235n(obtainStyledAttributes.getDimensionPixelSize(14, 0));
            m13236o(obtainStyledAttributes.getDimensionPixelSize(15, this.f12475p));
            m13238q(obtainStyledAttributes.getDimensionPixelSize(17, this.f12477r));
            m13237p(obtainStyledAttributes.getDimensionPixelSize(16, this.f12476q));
            m13239r(obtainStyledAttributes.getDimensionPixelSize(18, this.f12478s));
            obtainStyledAttributes.recycle();
        }

        /* renamed from: a */
        public C2665b m13221a() {
            if (this.f12468i == null) {
                this.f12468i = new AccelerateInterpolator();
            }
            if (this.f12469j == null) {
                this.f12469j = new DecelerateInterpolator();
            }
            return new C2665b(this.f12460a, this.f12461b, this.f12462c, this.f12463d, this.f12467h, this.f12464e, this.f12465f, this.f12466g, this.f12468i, this.f12469j, this.f12470k, this.f12471l, this.f12472m, this.f12474o, this.f12473n, this.f12475p, this.f12476q, this.f12477r, this.f12478s);
        }

        /* renamed from: a */
        public C2663a m13219a(Drawable drawable) {
            this.f12460a = drawable;
            return this;
        }

        /* renamed from: a */
        public C2663a m13218a(int i) {
            this.f12461b = i;
            return this;
        }

        /* renamed from: b */
        public C2663a m13222b(int i) {
            this.f12462c = i;
            return this;
        }

        /* renamed from: c */
        public C2663a m13224c(int i) {
            this.f12463d = i;
            return this;
        }

        /* renamed from: d */
        public C2663a m13225d(int i) {
            this.f12467h = i;
            return this;
        }

        /* renamed from: e */
        public C2663a m13226e(int i) {
            this.f12464e = i;
            return this;
        }

        /* renamed from: f */
        public C2663a m13227f(int i) {
            this.f12465f = i;
            return this;
        }

        /* renamed from: g */
        public C2663a m13228g(int i) {
            this.f12466g = i;
            return this;
        }

        /* renamed from: a */
        public C2663a m13220a(Interpolator interpolator) {
            this.f12468i = interpolator;
            return this;
        }

        /* renamed from: b */
        public C2663a m13223b(Interpolator interpolator) {
            this.f12469j = interpolator;
            return this;
        }

        /* renamed from: h */
        public C2663a m13229h(int i) {
            this.f12470k = i;
            return this;
        }

        /* renamed from: i */
        public C2663a m13230i(int i) {
            this.f12471l = i;
            this.f12472m = i;
            this.f12473n = i;
            this.f12474o = i;
            return this;
        }

        /* renamed from: j */
        public C2663a m13231j(int i) {
            this.f12471l = i;
            return this;
        }

        /* renamed from: k */
        public C2663a m13232k(int i) {
            this.f12472m = i;
            return this;
        }

        /* renamed from: l */
        public C2663a m13233l(int i) {
            this.f12473n = i;
            return this;
        }

        /* renamed from: m */
        public C2663a m13234m(int i) {
            this.f12474o = i;
            return this;
        }

        /* renamed from: n */
        public C2663a m13235n(int i) {
            this.f12475p = i;
            this.f12476q = i;
            this.f12477r = i;
            this.f12478s = i;
            return this;
        }

        /* renamed from: o */
        public C2663a m13236o(int i) {
            this.f12475p = i;
            return this;
        }

        /* renamed from: p */
        public C2663a m13237p(int i) {
            this.f12476q = i;
            return this;
        }

        /* renamed from: q */
        public C2663a m13238q(int i) {
            this.f12477r = i;
            return this;
        }

        /* renamed from: r */
        public C2663a m13239r(int i) {
            this.f12478s = i;
            return this;
        }
    }

    /* compiled from: RippleDrawable */
    /* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.b$b */
    public static class C2664b {
        /* renamed from: a */
        final int f12479a;
        /* renamed from: b */
        final float[] f12480b = new float[8];
        /* renamed from: c */
        final int f12481c;
        /* renamed from: d */
        final int f12482d;
        /* renamed from: e */
        final int f12483e;
        /* renamed from: f */
        final int f12484f;

        public C2664b(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.f12479a = i;
            this.f12480b[0] = (float) i2;
            this.f12480b[1] = (float) i2;
            this.f12480b[2] = (float) i3;
            this.f12480b[3] = (float) i3;
            this.f12480b[4] = (float) i4;
            this.f12480b[5] = (float) i4;
            this.f12480b[6] = (float) i5;
            this.f12480b[7] = (float) i5;
            this.f12481c = i6;
            this.f12482d = i7;
            this.f12483e = i8;
            this.f12484f = i9;
        }
    }

    private C2665b(Drawable drawable, int i, int i2, int i3, int i4, int i5, int i6, int i7, Interpolator interpolator, Interpolator interpolator2, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        this.f12487a = false;
        this.f12494h = 255;
        this.f12512z = 0;
        this.f12486B = new C26621(this);
        m13254a(drawable);
        this.f12498l = i;
        this.f12499m = i2;
        this.f12503q = i3;
        m13252a(i4);
        this.f12504r = i5;
        this.f12505s = i6;
        this.f12506t = i7;
        if (this.f12503q == 0 && this.f12504r <= 0) {
            this.f12503q = -1;
        }
        this.f12509w = interpolator;
        this.f12510x = interpolator2;
        m13253a(i8, i9, i10, i11, i12, i13, i14, i15, i16);
        this.f12489c = new Paint(1);
        this.f12489c.setStyle(Style.FILL);
        this.f12488b = new Paint(1);
        this.f12488b.setStyle(Style.FILL);
        this.f12497k = new Path();
        this.f12496j = new RectF();
        this.f12501o = new PointF();
        this.f12493g = new Matrix();
        this.f12491e = new RadialGradient(0.0f, 0.0f, 16.0f, new int[]{this.f12506t, this.f12506t, 0}, f12485A, TileMode.CLAMP);
        if (this.f12503q == 1) {
            this.f12492f = new RadialGradient(0.0f, 0.0f, 16.0f, new int[]{0, C2661a.m13215a(this.f12506t, 0.0f), this.f12506t}, f12485A, TileMode.CLAMP);
        }
    }

    /* renamed from: a */
    public void m13254a(Drawable drawable) {
        this.f12495i = drawable;
        if (this.f12495i != null) {
            this.f12495i.setBounds(getBounds());
        }
    }

    /* renamed from: a */
    public Drawable m13251a() {
        return this.f12495i;
    }

    /* renamed from: a */
    public void m13252a(int i) {
        this.f12508v = i;
    }

    /* renamed from: a */
    public void m13253a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.f12490d = new C2664b(i, i2, i3, i4, i5, i6, i7, i8, i9);
    }

    public void setAlpha(int i) {
        this.f12494h = i;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f12489c.setColorFilter(colorFilter);
        this.f12488b.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -3;
    }

    /* renamed from: b */
    public long m13255b() {
        switch (this.f12508v) {
            case 1:
                if (this.f12512z == 3) {
                    return ((long) Math.max(this.f12498l, this.f12505s)) - (SystemClock.uptimeMillis() - this.f12511y);
                }
                return -1;
            case 2:
                if (this.f12512z == 3) {
                    return ((long) (Math.max(this.f12498l, this.f12505s) * 2)) - (SystemClock.uptimeMillis() - this.f12511y);
                }
                return this.f12512z == 4 ? ((long) Math.max(this.f12498l, this.f12505s)) - (SystemClock.uptimeMillis() - this.f12511y) : -1;
            default:
                return -1;
        }
    }

    /* renamed from: b */
    private void m13244b(int i) {
        if (this.f12512z == i) {
            return;
        }
        if (this.f12512z != 0 || i == 1) {
            this.f12512z = i;
            if (this.f12512z == 0 || this.f12512z == 2) {
                stop();
            } else {
                start();
            }
        }
    }

    /* renamed from: a */
    private boolean m13243a(float f, float f2, float f3) {
        if (this.f12501o.x == f && this.f12501o.y == f2 && this.f12502p == f3) {
            return false;
        }
        this.f12501o.set(f, f2);
        this.f12502p = f3;
        float f4 = this.f12502p / 16.0f;
        this.f12493g.reset();
        this.f12493g.postTranslate(f, f2);
        this.f12493g.postScale(f4, f4, f, f2);
        this.f12491e.setLocalMatrix(this.f12493g);
        if (this.f12492f != null) {
            this.f12492f.setLocalMatrix(this.f12493g);
        }
        return true;
    }

    protected void onBoundsChange(Rect rect) {
        if (this.f12495i != null) {
            this.f12495i.setBounds(rect);
        }
        this.f12496j.set((float) (rect.left + this.f12490d.f12481c), (float) (rect.top + this.f12490d.f12482d), (float) (rect.right - this.f12490d.f12483e), (float) (rect.bottom - this.f12490d.f12484f));
        this.f12497k.reset();
        switch (this.f12490d.f12479a) {
            case 0:
                this.f12497k.addRoundRect(this.f12496j, this.f12490d.f12480b, Direction.CW);
                return;
            case 1:
                this.f12497k.addOval(this.f12496j, Direction.CW);
                return;
            default:
                return;
        }
    }

    public boolean isStateful() {
        return this.f12495i != null && this.f12495i.isStateful();
    }

    protected boolean onStateChange(int[] iArr) {
        return this.f12495i != null && this.f12495i.setState(iArr);
    }

    public void draw(Canvas canvas) {
        if (this.f12495i != null) {
            this.f12495i.draw(canvas);
        }
        switch (this.f12503q) {
            case -1:
            case 0:
                m13242a(canvas);
                return;
            case 1:
                m13245b(canvas);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m13242a(Canvas canvas) {
        if (this.f12512z != 0) {
            if (this.f12500n > 0.0f) {
                this.f12489c.setColor(this.f12499m);
                this.f12489c.setAlpha(Math.round(((float) this.f12494h) * this.f12500n));
                canvas.drawPath(this.f12497k, this.f12489c);
            }
            if (this.f12502p > 0.0f && this.f12507u > 0.0f) {
                this.f12488b.setAlpha(Math.round(((float) this.f12494h) * this.f12507u));
                this.f12488b.setShader(this.f12491e);
                canvas.drawPath(this.f12497k, this.f12488b);
            }
        }
    }

    /* renamed from: b */
    private void m13245b(Canvas canvas) {
        if (this.f12512z == 0) {
            return;
        }
        if (this.f12512z == 4) {
            if (this.f12502p == 0.0f) {
                this.f12489c.setColor(this.f12506t);
                canvas.drawPath(this.f12497k, this.f12489c);
                return;
            }
            this.f12488b.setShader(this.f12492f);
            canvas.drawPath(this.f12497k, this.f12488b);
        } else if (this.f12502p > 0.0f) {
            this.f12488b.setShader(this.f12491e);
            canvas.drawPath(this.f12497k, this.f12488b);
        }
    }

    /* renamed from: a */
    private int m13240a(float f, float f2) {
        return (int) Math.round(Math.sqrt(Math.pow((double) ((f2 < this.f12496j.centerY() ? this.f12496j.bottom : this.f12496j.top) - f2), 2.0d) + Math.pow((double) ((f < this.f12496j.centerX() ? this.f12496j.right : this.f12496j.left) - f), 2.0d)));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
            case 2:
                if (this.f12512z != 0 && this.f12512z != 4) {
                    if (this.f12503q == 0 && m13243a(motionEvent.getX(), motionEvent.getY(), this.f12502p)) {
                        invalidateSelf();
                        break;
                    }
                }
                if (this.f12503q == 1 || this.f12503q == -1) {
                    this.f12504r = m13240a(motionEvent.getX(), motionEvent.getY());
                }
                m13243a(motionEvent.getX(), motionEvent.getY(), 0.0f);
                m13244b(1);
                break;
                break;
            case 1:
            case 3:
                if (this.f12512z != 0) {
                    if (this.f12512z != 2) {
                        m13244b(3);
                        break;
                    }
                    if (this.f12503q == 1 || this.f12503q == -1) {
                        m13243a(this.f12501o.x, this.f12501o.y, 0.0f);
                    }
                    m13244b(4);
                    break;
                }
                break;
        }
        return true;
    }

    /* renamed from: c */
    public void m13256c() {
        m13244b(0);
    }

    /* renamed from: d */
    private void m13248d() {
        this.f12511y = SystemClock.uptimeMillis();
    }

    public void start() {
        if (!isRunning()) {
            m13248d();
            scheduleSelf(this.f12486B, SystemClock.uptimeMillis() + 16);
            invalidateSelf();
        }
    }

    public void stop() {
        this.f12487a = false;
        unscheduleSelf(this.f12486B);
        invalidateSelf();
    }

    public boolean isRunning() {
        return (this.f12512z == 0 || this.f12512z == 2 || !this.f12487a) ? false : true;
    }

    public void scheduleSelf(Runnable runnable, long j) {
        this.f12487a = true;
        super.scheduleSelf(runnable, j);
    }

    /* renamed from: e */
    private void m13249e() {
        int i = 4;
        float min;
        if (this.f12512z != 4) {
            min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.f12511y)) / ((float) this.f12498l));
            this.f12500n = (this.f12509w.getInterpolation(min) * ((float) Color.alpha(this.f12499m))) / 255.0f;
            float min2 = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.f12511y)) / ((float) this.f12505s));
            this.f12507u = this.f12509w.getInterpolation(min2);
            m13243a(this.f12501o.x, this.f12501o.y, ((float) this.f12504r) * this.f12509w.getInterpolation(min2));
            if (min == 1.0f && min2 == 1.0f) {
                this.f12511y = SystemClock.uptimeMillis();
                if (this.f12512z == 1) {
                    i = 2;
                }
                m13244b(i);
            }
        } else {
            float min3 = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.f12511y)) / ((float) this.f12498l));
            this.f12500n = ((1.0f - this.f12510x.getInterpolation(min3)) * ((float) Color.alpha(this.f12499m))) / 255.0f;
            min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.f12511y)) / ((float) this.f12505s));
            this.f12507u = 1.0f - this.f12510x.getInterpolation(min);
            m13243a(this.f12501o.x, this.f12501o.y, ((float) this.f12504r) * ((0.5f * this.f12510x.getInterpolation(min)) + 1.0f));
            if (min3 == 1.0f && min == 1.0f) {
                m13244b(0);
            }
        }
        if (isRunning()) {
            scheduleSelf(this.f12486B, SystemClock.uptimeMillis() + 16);
        }
        invalidateSelf();
    }

    /* renamed from: f */
    private void m13250f() {
        float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.f12511y)) / ((float) this.f12505s));
        if (this.f12512z != 4) {
            m13243a(this.f12501o.x, this.f12501o.y, ((float) this.f12504r) * this.f12509w.getInterpolation(min));
            if (min == 1.0f) {
                this.f12511y = SystemClock.uptimeMillis();
                if (this.f12512z == 1) {
                    m13244b(2);
                } else {
                    m13243a(this.f12501o.x, this.f12501o.y, 0.0f);
                    m13244b(4);
                }
            }
        } else {
            m13243a(this.f12501o.x, this.f12501o.y, ((float) this.f12504r) * this.f12510x.getInterpolation(min));
            if (min == 1.0f) {
                m13244b(0);
            }
        }
        if (isRunning()) {
            scheduleSelf(this.f12486B, SystemClock.uptimeMillis() + 16);
        }
        invalidateSelf();
    }
}
