package com.beastbikes.android.widget.smoothprogressbar;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import com.beastbikes.android.C1373R;

/* compiled from: SmoothProgressDrawable */
/* renamed from: com.beastbikes.android.widget.smoothprogressbar.c */
public class C2746c extends Drawable implements Animatable {
    /* renamed from: A */
    private int[] f12841A;
    /* renamed from: B */
    private float[] f12842B;
    /* renamed from: C */
    private final Runnable f12843C;
    /* renamed from: a */
    private final Rect f12844a;
    /* renamed from: b */
    private C2745b f12845b;
    /* renamed from: c */
    private Interpolator f12846c;
    /* renamed from: d */
    private Rect f12847d;
    /* renamed from: e */
    private Paint f12848e;
    /* renamed from: f */
    private int[] f12849f;
    /* renamed from: g */
    private int f12850g;
    /* renamed from: h */
    private boolean f12851h;
    /* renamed from: i */
    private float f12852i;
    /* renamed from: j */
    private float f12853j;
    /* renamed from: k */
    private int f12854k;
    /* renamed from: l */
    private int f12855l;
    /* renamed from: m */
    private float f12856m;
    /* renamed from: n */
    private float f12857n;
    /* renamed from: o */
    private float f12858o;
    /* renamed from: p */
    private boolean f12859p;
    /* renamed from: q */
    private boolean f12860q;
    /* renamed from: r */
    private boolean f12861r;
    /* renamed from: s */
    private float f12862s;
    /* renamed from: t */
    private boolean f12863t;
    /* renamed from: u */
    private boolean f12864u;
    /* renamed from: v */
    private int f12865v;
    /* renamed from: w */
    private int f12866w;
    /* renamed from: x */
    private float f12867x;
    /* renamed from: y */
    private Drawable f12868y;
    /* renamed from: z */
    private boolean f12869z;

    /* compiled from: SmoothProgressDrawable */
    /* renamed from: com.beastbikes.android.widget.smoothprogressbar.c$1 */
    class C27431 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2746c f12825a;

        C27431(C2746c c2746c) {
            this.f12825a = c2746c;
        }

        public void run() {
            if (this.f12825a.m13621f()) {
                this.f12825a.f12853j = this.f12825a.f12853j + (this.f12825a.f12858o * 0.01f);
                this.f12825a.f12852i = this.f12825a.f12852i + (this.f12825a.f12858o * 0.01f);
                if (this.f12825a.f12853j >= 1.0f) {
                    this.f12825a.stop();
                }
            } else if (this.f12825a.m13620e()) {
                this.f12825a.f12852i = this.f12825a.f12852i + (this.f12825a.f12857n * 0.01f);
            } else {
                this.f12825a.f12852i = this.f12825a.f12852i + (this.f12825a.f12856m * 0.01f);
            }
            if (this.f12825a.f12852i >= this.f12825a.f12862s) {
                this.f12825a.f12860q = true;
                this.f12825a.f12852i = this.f12825a.f12852i - this.f12825a.f12862s;
            }
            if (this.f12825a.isRunning()) {
                this.f12825a.scheduleSelf(this.f12825a.f12843C, SystemClock.uptimeMillis() + 16);
            }
            this.f12825a.invalidateSelf();
        }
    }

    /* compiled from: SmoothProgressDrawable */
    /* renamed from: com.beastbikes.android.widget.smoothprogressbar.c$a */
    public static class C2744a {
        /* renamed from: a */
        private Interpolator f12826a;
        /* renamed from: b */
        private int f12827b;
        /* renamed from: c */
        private int[] f12828c;
        /* renamed from: d */
        private float f12829d;
        /* renamed from: e */
        private float f12830e;
        /* renamed from: f */
        private float f12831f;
        /* renamed from: g */
        private boolean f12832g;
        /* renamed from: h */
        private boolean f12833h;
        /* renamed from: i */
        private float f12834i;
        /* renamed from: j */
        private int f12835j;
        /* renamed from: k */
        private boolean f12836k;
        /* renamed from: l */
        private boolean f12837l;
        /* renamed from: m */
        private boolean f12838m;
        /* renamed from: n */
        private Drawable f12839n;
        /* renamed from: o */
        private C2745b f12840o;

        public C2744a(Context context) {
            this(context, false);
        }

        public C2744a(Context context, boolean z) {
            m13562a(context, z);
        }

        /* renamed from: a */
        public C2746c m13569a() {
            if (this.f12837l) {
                this.f12839n = C2742b.m13556a(this.f12828c, this.f12834i);
            }
            return new C2746c(this.f12826a, this.f12827b, this.f12835j, this.f12828c, this.f12834i, this.f12829d, this.f12830e, this.f12831f, this.f12832g, this.f12833h, this.f12840o, this.f12836k, this.f12839n, this.f12838m);
        }

        /* renamed from: a */
        private void m13562a(Context context, boolean z) {
            Resources resources = context.getResources();
            this.f12826a = new AccelerateInterpolator();
            if (z) {
                this.f12827b = 4;
                this.f12829d = 1.0f;
                this.f12832g = false;
                this.f12836k = false;
                this.f12828c = new int[]{-13388315};
                this.f12835j = 4;
                this.f12834i = 4.0f;
            } else {
                this.f12827b = resources.getInteger(C1373R.integer.spb_default_sections_count);
                this.f12829d = Float.parseFloat(resources.getString(C1373R.string.spb_default_speed));
                this.f12832g = resources.getBoolean(C1373R.bool.spb_default_reversed);
                this.f12836k = resources.getBoolean(C1373R.bool.spb_default_progressiveStart_activated);
                this.f12828c = new int[]{resources.getColor(C1373R.color.spb_default_color)};
                this.f12835j = resources.getDimensionPixelSize(C1373R.dimen.spb_default_stroke_separator_length);
                this.f12834i = (float) resources.getDimensionPixelOffset(C1373R.dimen.spb_default_stroke_width);
            }
            this.f12830e = this.f12829d;
            this.f12831f = this.f12829d;
            this.f12838m = false;
        }

        /* renamed from: a */
        public C2744a m13566a(Interpolator interpolator) {
            C2742b.m13560a((Object) interpolator, "Interpolator");
            this.f12826a = interpolator;
            return this;
        }

        /* renamed from: a */
        public C2744a m13564a(int i) {
            C2742b.m13559a(i, "Sections count");
            this.f12827b = i;
            return this;
        }

        /* renamed from: b */
        public C2744a m13572b(int i) {
            C2742b.m13558a((float) i, "Separator length");
            this.f12835j = i;
            return this;
        }

        /* renamed from: c */
        public C2744a m13575c(int i) {
            this.f12828c = new int[]{i};
            return this;
        }

        /* renamed from: a */
        public C2744a m13568a(int[] iArr) {
            C2742b.m13561a(iArr);
            this.f12828c = iArr;
            return this;
        }

        /* renamed from: a */
        public C2744a m13563a(float f) {
            C2742b.m13558a(f, "Width");
            this.f12834i = f;
            return this;
        }

        /* renamed from: b */
        public C2744a m13571b(float f) {
            C2742b.m13557a(f);
            this.f12829d = f;
            return this;
        }

        /* renamed from: c */
        public C2744a m13574c(float f) {
            C2742b.m13557a(f);
            this.f12830e = f;
            return this;
        }

        /* renamed from: d */
        public C2744a m13577d(float f) {
            C2742b.m13557a(f);
            this.f12831f = f;
            return this;
        }

        /* renamed from: a */
        public C2744a m13567a(boolean z) {
            this.f12832g = z;
            return this;
        }

        /* renamed from: b */
        public C2744a m13573b(boolean z) {
            this.f12833h = z;
            return this;
        }

        /* renamed from: c */
        public C2744a m13576c(boolean z) {
            this.f12836k = z;
            return this;
        }

        /* renamed from: a */
        public C2744a m13565a(Drawable drawable) {
            this.f12839n = drawable;
            return this;
        }

        /* renamed from: b */
        public C2744a m13570b() {
            this.f12837l = true;
            return this;
        }

        /* renamed from: d */
        public C2744a m13578d(boolean z) {
            this.f12838m = z;
            return this;
        }
    }

    /* compiled from: SmoothProgressDrawable */
    /* renamed from: com.beastbikes.android.widget.smoothprogressbar.c$b */
    public interface C2745b {
        /* renamed from: a */
        void m13579a();

        /* renamed from: b */
        void m13580b();
    }

    private C2746c(Interpolator interpolator, int i, int i2, int[] iArr, float f, float f2, float f3, float f4, boolean z, boolean z2, C2745b c2745b, boolean z3, Drawable drawable, boolean z4) {
        this.f12844a = new Rect();
        this.f12843C = new C27431(this);
        this.f12851h = false;
        this.f12846c = interpolator;
        this.f12855l = i;
        this.f12865v = 0;
        this.f12866w = this.f12855l;
        this.f12854k = i2;
        this.f12856m = f2;
        this.f12857n = f3;
        this.f12858o = f4;
        this.f12859p = z;
        this.f12849f = iArr;
        this.f12850g = 0;
        this.f12861r = z2;
        this.f12863t = false;
        this.f12868y = drawable;
        this.f12867x = f;
        this.f12862s = 1.0f / ((float) this.f12855l);
        this.f12848e = new Paint();
        this.f12848e.setStrokeWidth(f);
        this.f12848e.setStyle(Style.STROKE);
        this.f12848e.setDither(false);
        this.f12848e.setAntiAlias(false);
        this.f12864u = z3;
        this.f12845b = c2745b;
        this.f12869z = z4;
        m13608b();
    }

    /* renamed from: a */
    public void m13604a(Interpolator interpolator) {
        if (interpolator == null) {
            throw new IllegalArgumentException("Interpolator cannot be null");
        }
        this.f12846c = interpolator;
        invalidateSelf();
    }

    /* renamed from: a */
    public void m13607a(int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("Colors cannot be null or empty");
        }
        this.f12850g = 0;
        this.f12849f = iArr;
        m13608b();
        invalidateSelf();
    }

    /* renamed from: a */
    public void m13602a(int i) {
        m13607a(new int[]{i});
    }

    /* renamed from: a */
    public void m13601a(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Speed must be >= 0");
        }
        this.f12856m = f;
        invalidateSelf();
    }

    /* renamed from: b */
    public void m13609b(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("SpeedProgressiveStart must be >= 0");
        }
        this.f12857n = f;
        invalidateSelf();
    }

    /* renamed from: c */
    public void m13613c(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("SpeedProgressiveStop must be >= 0");
        }
        this.f12858o = f;
        invalidateSelf();
    }

    /* renamed from: b */
    public void m13610b(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("SectionsCount must be > 0");
        }
        this.f12855l = i;
        this.f12862s = 1.0f / ((float) this.f12855l);
        this.f12852i %= this.f12862s;
        m13608b();
        invalidateSelf();
    }

    /* renamed from: c */
    public void m13614c(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("SeparatorLength must be >= 0");
        }
        this.f12854k = i;
        invalidateSelf();
    }

    /* renamed from: d */
    public void m13617d(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("The strokeWidth must be >= 0");
        }
        this.f12848e.setStrokeWidth(f);
        invalidateSelf();
    }

    /* renamed from: a */
    public void m13606a(boolean z) {
        if (this.f12859p != z) {
            this.f12859p = z;
            invalidateSelf();
        }
    }

    /* renamed from: b */
    public void m13611b(boolean z) {
        if (this.f12861r != z) {
            this.f12861r = z;
            invalidateSelf();
        }
    }

    /* renamed from: a */
    public void m13603a(Drawable drawable) {
        if (this.f12868y != drawable) {
            this.f12868y = drawable;
            invalidateSelf();
        }
    }

    /* renamed from: a */
    public float m13600a() {
        return this.f12867x;
    }

    /* renamed from: c */
    public void m13615c(boolean z) {
        this.f12864u = z;
    }

    /* renamed from: d */
    public void m13619d(boolean z) {
        if (this.f12869z != z) {
            this.f12869z = z;
            m13608b();
            invalidateSelf();
        }
    }

    /* renamed from: b */
    protected void m13608b() {
        if (this.f12869z) {
            this.f12841A = new int[(this.f12855l + 2)];
            this.f12842B = new float[(this.f12855l + 2)];
            return;
        }
        this.f12848e.setShader(null);
        this.f12841A = null;
        this.f12842B = null;
    }

    public void draw(Canvas canvas) {
        this.f12847d = getBounds();
        canvas.clipRect(this.f12847d);
        if (this.f12860q) {
            this.f12850g = m13596f(this.f12850g);
            this.f12860q = false;
            if (m13621f()) {
                this.f12865v++;
                if (this.f12865v > this.f12855l) {
                    stop();
                    return;
                }
            }
            if (this.f12866w < this.f12855l) {
                this.f12866w++;
            }
        }
        if (this.f12869z) {
            m13583a(canvas);
        }
        m13589b(canvas);
    }

    /* renamed from: a */
    private void m13583a(Canvas canvas) {
        float abs;
        float f = 1.0f / ((float) this.f12855l);
        int i = this.f12850g;
        this.f12842B[0] = 0.0f;
        this.f12842B[this.f12842B.length - 1] = 1.0f;
        int i2 = i - 1;
        if (i2 < 0) {
            i2 += this.f12849f.length;
        }
        this.f12841A[0] = this.f12849f[i2];
        int i3 = i;
        for (i2 = 0; i2 < this.f12855l; i2++) {
            this.f12842B[i2 + 1] = this.f12846c.getInterpolation((((float) i2) * f) + this.f12852i);
            this.f12841A[i2 + 1] = this.f12849f[i3];
            i3 = (i3 + 1) % this.f12849f.length;
        }
        this.f12841A[this.f12841A.length - 1] = this.f12849f[i3];
        if (this.f12859p) {
            abs = (float) (this.f12861r ? Math.abs(this.f12847d.left - this.f12847d.right) / 2 : this.f12847d.left);
        } else {
            abs = (float) this.f12847d.left;
        }
        if (this.f12861r) {
            f = (float) (this.f12859p ? this.f12847d.left : Math.abs(this.f12847d.left - this.f12847d.right) / 2);
        } else {
            f = (float) this.f12847d.right;
        }
        this.f12848e.setShader(new LinearGradient(abs, ((float) this.f12847d.centerY()) - (this.f12867x / 2.0f), f, (this.f12867x / 2.0f) + ((float) this.f12847d.centerY()), this.f12841A, this.f12842B, this.f12861r ? TileMode.MIRROR : TileMode.CLAMP));
    }

    /* renamed from: b */
    private void m13589b(Canvas canvas) {
        if (this.f12859p) {
            canvas.translate((float) this.f12847d.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        int width = this.f12847d.width();
        if (this.f12861r) {
            width /= 2;
        }
        int i = (this.f12854k + width) + this.f12855l;
        int centerY = this.f12847d.centerY();
        float f = 1.0f / ((float) this.f12855l);
        float f2 = 0.0f;
        int i2 = this.f12850g;
        if (this.f12865v == this.f12866w && this.f12866w == this.f12855l) {
            f2 = (float) canvas.getWidth();
        }
        int i3 = 0;
        float f3 = 0.0f;
        float f4 = f2;
        float f5 = 0.0f;
        while (i3 <= this.f12866w) {
            float min;
            float max;
            float f6;
            f2 = (((float) i3) * f) + this.f12852i;
            float max2 = Math.max(0.0f, f2 - f);
            float abs = (float) ((int) (Math.abs(this.f12846c.getInterpolation(max2) - this.f12846c.getInterpolation(Math.min(f2, 1.0f))) * ((float) i)));
            if (abs + max2 < ((float) i)) {
                min = Math.min(abs, (float) this.f12854k);
            } else {
                min = 0.0f;
            }
            float f7 = f5 + (abs > min ? abs - min : 0.0f);
            if (f7 > f5 && i3 >= this.f12865v) {
                max = Math.max(this.f12846c.getInterpolation(Math.min(this.f12853j, 1.0f)) * ((float) i), Math.min((float) width, f5));
                m13585a(canvas, width, max, (float) centerY, Math.min((float) width, f7), (float) centerY, i2);
                if (i3 == this.f12865v) {
                    max -= (float) this.f12854k;
                    if (i3 != this.f12866w) {
                        max2 = f5 + abs;
                    } else {
                        max2 = f3;
                    }
                    f6 = f7 + min;
                    i2 = m13594e(i2);
                    i3++;
                    f3 = max2;
                    f4 = max;
                    f5 = f6;
                }
            }
            max = f4;
            if (i3 != this.f12866w) {
                max2 = f3;
            } else {
                max2 = f5 + abs;
            }
            f6 = f7 + min;
            i2 = m13594e(i2);
            i3++;
            f3 = max2;
            f4 = max;
            f5 = f6;
        }
        m13584a(canvas, f4, f3);
    }

    /* renamed from: a */
    private void m13585a(Canvas canvas, int i, float f, float f2, float f3, float f4, int i2) {
        this.f12848e.setColor(this.f12849f[i2]);
        if (!this.f12861r) {
            canvas.drawLine(f, f2, f3, f4, this.f12848e);
        } else if (this.f12859p) {
            canvas.drawLine(((float) i) + f, f2, ((float) i) + f3, f4, this.f12848e);
            canvas.drawLine(((float) i) - f, f2, ((float) i) - f3, f4, this.f12848e);
        } else {
            canvas.drawLine(f, f2, f3, f4, this.f12848e);
            canvas.drawLine(((float) (i * 2)) - f, f2, ((float) (i * 2)) - f3, f4, this.f12848e);
        }
    }

    /* renamed from: a */
    private void m13584a(Canvas canvas, float f, float f2) {
        if (this.f12868y != null) {
            this.f12844a.top = (int) ((((float) canvas.getHeight()) - this.f12867x) / 2.0f);
            this.f12844a.bottom = (int) ((((float) canvas.getHeight()) + this.f12867x) / 2.0f);
            this.f12844a.left = 0;
            this.f12844a.right = this.f12861r ? canvas.getWidth() / 2 : canvas.getWidth();
            this.f12868y.setBounds(this.f12844a);
            if (isRunning()) {
                if (m13621f() || m13620e()) {
                    if (f <= f2) {
                        float f3 = f2;
                        f2 = f;
                        f = f3;
                    }
                    if (f2 > 0.0f) {
                        if (this.f12861r) {
                            canvas.save();
                            canvas.translate((float) (canvas.getWidth() / 2), 0.0f);
                            if (this.f12859p) {
                                m13590b(canvas, 0.0f, f2);
                                canvas.scale(-1.0f, 1.0f);
                                m13590b(canvas, 0.0f, f2);
                            } else {
                                m13590b(canvas, ((float) (canvas.getWidth() / 2)) - f2, (float) (canvas.getWidth() / 2));
                                canvas.scale(-1.0f, 1.0f);
                                m13590b(canvas, ((float) (canvas.getWidth() / 2)) - f2, (float) (canvas.getWidth() / 2));
                            }
                            canvas.restore();
                        } else {
                            m13590b(canvas, 0.0f, f2);
                        }
                    }
                    if (f > ((float) canvas.getWidth())) {
                        return;
                    }
                    if (this.f12861r) {
                        canvas.save();
                        canvas.translate((float) (canvas.getWidth() / 2), 0.0f);
                        if (this.f12859p) {
                            m13590b(canvas, f, (float) (canvas.getWidth() / 2));
                            canvas.scale(-1.0f, 1.0f);
                            m13590b(canvas, f, (float) (canvas.getWidth() / 2));
                        } else {
                            m13590b(canvas, 0.0f, ((float) (canvas.getWidth() / 2)) - f);
                            canvas.scale(-1.0f, 1.0f);
                            m13590b(canvas, 0.0f, ((float) (canvas.getWidth() / 2)) - f);
                        }
                        canvas.restore();
                        return;
                    }
                    m13590b(canvas, f, (float) canvas.getWidth());
                }
            } else if (this.f12861r) {
                canvas.save();
                canvas.translate((float) (canvas.getWidth() / 2), 0.0f);
                m13590b(canvas, 0.0f, (float) this.f12844a.width());
                canvas.scale(-1.0f, 1.0f);
                m13590b(canvas, 0.0f, (float) this.f12844a.width());
                canvas.restore();
            } else {
                m13590b(canvas, 0.0f, (float) this.f12844a.width());
            }
        }
    }

    /* renamed from: b */
    private void m13590b(Canvas canvas, float f, float f2) {
        int save = canvas.save();
        canvas.clipRect(f, (float) ((int) ((((float) canvas.getHeight()) - this.f12867x) / 2.0f)), f2, (float) ((int) ((((float) canvas.getHeight()) + this.f12867x) / 2.0f)));
        this.f12868y.draw(canvas);
        canvas.restoreToCount(save);
    }

    /* renamed from: e */
    private int m13594e(int i) {
        int i2 = i + 1;
        if (i2 >= this.f12849f.length) {
            return 0;
        }
        return i2;
    }

    /* renamed from: f */
    private int m13596f(int i) {
        int i2 = i - 1;
        if (i2 < 0) {
            return this.f12849f.length - 1;
        }
        return i2;
    }

    /* renamed from: c */
    public void m13612c() {
        m13618d(0);
    }

    /* renamed from: d */
    public void m13618d(int i) {
        m13598g(i);
        start();
    }

    /* renamed from: g */
    private void m13598g(int i) {
        m13599h(i);
        this.f12852i = 0.0f;
        this.f12863t = false;
        this.f12853j = 0.0f;
        this.f12865v = 0;
        this.f12866w = 0;
        this.f12850g = i;
    }

    /* renamed from: d */
    public void m13616d() {
        this.f12863t = true;
        this.f12865v = 0;
    }

    public void setAlpha(int i) {
        this.f12848e.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f12848e.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -2;
    }

    public void start() {
        if (this.f12864u) {
            m13598g(0);
        }
        if (!isRunning()) {
            if (this.f12845b != null) {
                this.f12845b.m13580b();
            }
            scheduleSelf(this.f12843C, SystemClock.uptimeMillis() + 16);
            invalidateSelf();
        }
    }

    public void stop() {
        if (isRunning()) {
            if (this.f12845b != null) {
                this.f12845b.m13579a();
            }
            this.f12851h = false;
            unscheduleSelf(this.f12843C);
        }
    }

    public void scheduleSelf(Runnable runnable, long j) {
        this.f12851h = true;
        super.scheduleSelf(runnable, j);
    }

    public boolean isRunning() {
        return this.f12851h;
    }

    /* renamed from: e */
    public boolean m13620e() {
        return this.f12866w < this.f12855l;
    }

    /* renamed from: f */
    public boolean m13621f() {
        return this.f12863t;
    }

    /* renamed from: a */
    public void m13605a(C2745b c2745b) {
        this.f12845b = c2745b;
    }

    /* renamed from: h */
    private void m13599h(int i) {
        if (i < 0 || i >= this.f12849f.length) {
            throw new IllegalArgumentException(String.format("Index %d not valid", new Object[]{Integer.valueOf(i)}));
        }
    }
}
