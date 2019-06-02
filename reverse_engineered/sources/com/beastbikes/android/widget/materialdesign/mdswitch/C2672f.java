package com.beastbikes.android.widget.materialdesign.mdswitch;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;

/* compiled from: ToolbarRippleDrawable */
/* renamed from: com.beastbikes.android.widget.materialdesign.mdswitch.f */
public class C2672f extends Drawable implements Animatable {
    /* renamed from: x */
    private static final float[] f12523x = new float[]{0.0f, 0.99f, 1.0f};
    /* renamed from: a */
    private boolean f12524a;
    /* renamed from: b */
    private Paint f12525b;
    /* renamed from: c */
    private Paint f12526c;
    /* renamed from: d */
    private RadialGradient f12527d;
    /* renamed from: e */
    private RadialGradient f12528e;
    /* renamed from: f */
    private Matrix f12529f;
    /* renamed from: g */
    private int f12530g;
    /* renamed from: h */
    private RectF f12531h;
    /* renamed from: i */
    private Path f12532i;
    /* renamed from: j */
    private int f12533j;
    /* renamed from: k */
    private int f12534k;
    /* renamed from: l */
    private float f12535l;
    /* renamed from: m */
    private PointF f12536m;
    /* renamed from: n */
    private float f12537n;
    /* renamed from: o */
    private int f12538o;
    /* renamed from: p */
    private int f12539p;
    /* renamed from: q */
    private int f12540q;
    /* renamed from: r */
    private int f12541r;
    /* renamed from: s */
    private float f12542s;
    /* renamed from: t */
    private int f12543t;
    /* renamed from: u */
    private long f12544u;
    /* renamed from: v */
    private boolean f12545v;
    /* renamed from: w */
    private int f12546w;
    /* renamed from: y */
    private final Runnable f12547y;

    public void setAlpha(int i) {
        this.f12530g = i;
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.f12526c.setColorFilter(colorFilter);
        this.f12525b.setColorFilter(colorFilter);
    }

    public int getOpacity() {
        return -3;
    }

    /* renamed from: a */
    public long m13287a() {
        switch (this.f12543t) {
            case 1:
                if (this.f12546w == 3) {
                    return ((long) Math.max(this.f12533j, this.f12540q)) - (SystemClock.uptimeMillis() - this.f12544u);
                }
                return -1;
            case 2:
                if (this.f12546w == 3) {
                    return ((long) (Math.max(this.f12533j, this.f12540q) * 2)) - (SystemClock.uptimeMillis() - this.f12544u);
                }
                return this.f12546w == 4 ? ((long) Math.max(this.f12533j, this.f12540q)) - (SystemClock.uptimeMillis() - this.f12544u) : -1;
            default:
                return -1;
        }
    }

    /* renamed from: a */
    private void m13282a(int i) {
        if (this.f12546w != i) {
            this.f12546w = i;
            if (this.f12546w == 0) {
                stop();
            } else if (this.f12546w != 2) {
                start();
            } else {
                stop();
            }
        }
    }

    /* renamed from: a */
    private boolean m13284a(float f, float f2, float f3) {
        if (this.f12536m.x == f && this.f12536m.y == f2 && this.f12537n == f3) {
            return false;
        }
        this.f12536m.set(f, f2);
        this.f12537n = f3;
        float f4 = this.f12537n / 16.0f;
        this.f12529f.reset();
        this.f12529f.postTranslate(f, f2);
        this.f12529f.postScale(f4, f4, f, f2);
        this.f12527d.setLocalMatrix(this.f12529f);
        if (this.f12528e != null) {
            this.f12528e.setLocalMatrix(this.f12529f);
        }
        return true;
    }

    protected void onBoundsChange(Rect rect) {
        this.f12531h.set((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
        this.f12532i.reset();
        this.f12532i.addRect(this.f12531h, Direction.CW);
    }

    public boolean isStateful() {
        return true;
    }

    protected boolean onStateChange(int[] iArr) {
        boolean a = C2674h.m13295a(iArr, 16842919);
        if (this.f12545v == a) {
            return false;
        }
        this.f12545v = a;
        if (this.f12545v) {
            Rect bounds = getBounds();
            if (this.f12546w == 0 || this.f12546w == 4) {
                if (this.f12538o == 1 || this.f12538o == -1) {
                    this.f12539p = m13281a(bounds.exactCenterX(), bounds.exactCenterY());
                }
                m13284a(bounds.exactCenterX(), bounds.exactCenterY(), 0.0f);
                m13282a(1);
                return true;
            } else if (this.f12538o != 0) {
                return true;
            } else {
                m13284a(bounds.exactCenterX(), bounds.exactCenterY(), this.f12537n);
                return true;
            }
        } else if (this.f12546w == 0) {
            return true;
        } else {
            if (this.f12546w == 2) {
                if (this.f12538o == 1 || this.f12538o == -1) {
                    m13284a(this.f12536m.x, this.f12536m.y, 0.0f);
                }
                m13282a(4);
                return true;
            }
            m13282a(3);
            return true;
        }
    }

    public void draw(Canvas canvas) {
        switch (this.f12538o) {
            case -1:
            case 0:
                m13283a(canvas);
                return;
            case 1:
                m13285b(canvas);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m13283a(Canvas canvas) {
        if (this.f12546w != 0) {
            if (this.f12535l > 0.0f) {
                this.f12526c.setColor(this.f12534k);
                this.f12526c.setAlpha(Math.round(((float) this.f12530g) * this.f12535l));
                canvas.drawPath(this.f12532i, this.f12526c);
            }
            if (this.f12537n > 0.0f && this.f12542s > 0.0f) {
                this.f12525b.setAlpha(Math.round(((float) this.f12530g) * this.f12542s));
                this.f12525b.setShader(this.f12527d);
                canvas.drawPath(this.f12532i, this.f12525b);
            }
        }
    }

    /* renamed from: b */
    private void m13285b(Canvas canvas) {
        if (this.f12546w == 0) {
            return;
        }
        if (this.f12546w == 4) {
            if (this.f12537n == 0.0f) {
                this.f12526c.setColor(this.f12541r);
                canvas.drawPath(this.f12532i, this.f12526c);
                return;
            }
            this.f12525b.setShader(this.f12528e);
            canvas.drawPath(this.f12532i, this.f12525b);
        } else if (this.f12537n > 0.0f) {
            this.f12525b.setShader(this.f12527d);
            canvas.drawPath(this.f12532i, this.f12525b);
        }
    }

    /* renamed from: a */
    private int m13281a(float f, float f2) {
        return (int) Math.round(Math.sqrt(Math.pow((double) ((f2 < this.f12531h.centerY() ? this.f12531h.bottom : this.f12531h.top) - f2), 2.0d) + Math.pow((double) ((f < this.f12531h.centerX() ? this.f12531h.right : this.f12531h.left) - f), 2.0d)));
    }

    /* renamed from: b */
    public void m13288b() {
        m13282a(0);
    }

    /* renamed from: c */
    private void m13286c() {
        this.f12544u = SystemClock.uptimeMillis();
    }

    public void start() {
        if (!isRunning()) {
            m13286c();
            scheduleSelf(this.f12547y, SystemClock.uptimeMillis() + 16);
            invalidateSelf();
        }
    }

    public void stop() {
        if (isRunning()) {
            this.f12524a = false;
            unscheduleSelf(this.f12547y);
            invalidateSelf();
        }
    }

    public boolean isRunning() {
        return this.f12524a;
    }

    public void scheduleSelf(Runnable runnable, long j) {
        this.f12524a = true;
        super.scheduleSelf(runnable, j);
    }
}
