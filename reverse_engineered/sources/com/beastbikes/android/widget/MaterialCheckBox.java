package com.beastbikes.android.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

public class MaterialCheckBox extends View {
    /* renamed from: a */
    private Paint f12103a;
    /* renamed from: b */
    private Paint f12104b;
    /* renamed from: c */
    private Paint f12105c;
    /* renamed from: d */
    private int f12106d;
    /* renamed from: e */
    private int f12107e;
    /* renamed from: f */
    private int f12108f;
    /* renamed from: g */
    private int f12109g;
    /* renamed from: h */
    private int f12110h;
    /* renamed from: i */
    private int f12111i;
    /* renamed from: j */
    private int f12112j;
    /* renamed from: k */
    private float[] f12113k;
    /* renamed from: l */
    private int f12114l;
    /* renamed from: m */
    private boolean f12115m;
    /* renamed from: n */
    private float f12116n;
    /* renamed from: o */
    private boolean f12117o;
    /* renamed from: p */
    private boolean f12118p;
    /* renamed from: q */
    private C1930a f12119q;
    /* renamed from: r */
    private float f12120r;

    /* renamed from: com.beastbikes.android.widget.MaterialCheckBox$a */
    public interface C1930a {
        /* renamed from: a */
        void mo3291a(View view, boolean z);
    }

    /* renamed from: com.beastbikes.android.widget.MaterialCheckBox$1 */
    class C25891 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ MaterialCheckBox f12098a;

        C25891(MaterialCheckBox materialCheckBox) {
            this.f12098a = materialCheckBox;
        }

        public void onClick(View view) {
            this.f12098a.setChecked(!this.f12098a.m12956a());
        }
    }

    /* renamed from: com.beastbikes.android.widget.MaterialCheckBox$2 */
    class C25902 implements AnimatorUpdateListener {
        /* renamed from: a */
        final /* synthetic */ MaterialCheckBox f12099a;

        C25902(MaterialCheckBox materialCheckBox) {
            this.f12099a = materialCheckBox;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            float f = 1.0f - floatValue;
            this.f12099a.f12110h = (int) (((float) this.f12099a.f12109g) + (((float) (this.f12099a.f12111i - this.f12099a.f12109g)) * f));
            this.f12099a.f12103a.setColor(this.f12099a.m12934a(f, this.f12099a.f12106d, this.f12099a.f12107e));
            this.f12099a.invalidate();
            if (floatValue >= 1.0f) {
                this.f12099a.f12118p = false;
                if (this.f12099a.f12119q != null) {
                    this.f12099a.f12115m = false;
                    this.f12099a.f12119q.mo3291a(this.f12099a, this.f12099a.f12115m);
                }
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.MaterialCheckBox$3 */
    class C25913 implements AnimatorUpdateListener {
        /* renamed from: a */
        final /* synthetic */ MaterialCheckBox f12100a;

        C25913(MaterialCheckBox materialCheckBox) {
            this.f12100a = materialCheckBox;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f12100a.f12110h = (int) (10.0f + (((float) (this.f12100a.f12111i - 10)) * floatValue));
            this.f12100a.f12103a.setColor(this.f12100a.m12934a(floatValue, this.f12100a.f12106d, this.f12100a.f12107e));
            this.f12100a.invalidate();
            if (floatValue >= 1.0f) {
                this.f12100a.f12118p = false;
                this.f12100a.f12117o = false;
                this.f12100a.m12947d();
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.MaterialCheckBox$4 */
    class C25924 implements AnimatorUpdateListener {
        /* renamed from: a */
        final /* synthetic */ MaterialCheckBox f12101a;

        C25924(MaterialCheckBox materialCheckBox) {
            this.f12101a = materialCheckBox;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f12101a.f12116n = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f12101a.invalidate();
            if (this.f12101a.f12116n >= 1.0f) {
                this.f12101a.f12118p = false;
                if (this.f12101a.f12119q != null) {
                    this.f12101a.f12115m = true;
                    this.f12101a.f12119q.mo3291a(this.f12101a, this.f12101a.f12115m);
                }
            }
        }
    }

    /* renamed from: com.beastbikes.android.widget.MaterialCheckBox$5 */
    class C25935 implements AnimatorUpdateListener {
        /* renamed from: a */
        final /* synthetic */ MaterialCheckBox f12102a;

        C25935(MaterialCheckBox materialCheckBox) {
            this.f12102a = materialCheckBox;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            this.f12102a.f12116n = 1.0f - floatValue;
            this.f12102a.invalidate();
            if (floatValue >= 1.0f) {
                this.f12102a.f12118p = false;
                this.f12102a.m12941b();
            }
        }
    }

    public MaterialCheckBox(Context context) {
        this(context, null);
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12106d = -7829368;
        this.f12107e = -16776961;
        this.f12108f = -1;
        this.f12113k = new float[8];
        this.f12114l = 200;
        m12938a(context);
    }

    @TargetApi(21)
    public MaterialCheckBox(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f12106d = -7829368;
        this.f12107e = -16776961;
        this.f12108f = -1;
        this.f12113k = new float[8];
        this.f12114l = 200;
    }

    /* renamed from: a */
    private void m12938a(Context context) {
        this.f12107e = getResources().getColor(C1373R.color.red_color);
        this.f12108f = getResources().getColor(C1373R.color.bg_theme_black_color);
        this.f12106d = getResources().getColor(C1373R.color.gray);
        int a = m12955a(2.0f);
        this.f12109g = a;
        this.f12110h = a;
        this.f12103a = new Paint(1);
        this.f12103a.setColor(this.f12106d);
        this.f12103a.setStrokeWidth((float) this.f12110h);
        this.f12104b = new Paint(1);
        this.f12104b.setColor(this.f12108f);
        this.f12104b.setStrokeWidth((float) m12955a(2.0f));
        this.f12105c = new Paint(1);
        this.f12105c.setColor(getResources().getColor(C1373R.color.bg_theme_black_color));
        this.f12105c.setStrokeWidth((float) this.f12110h);
        setOnClickListener(new C25891(this));
        this.f12117o = true;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int max = Math.max(i, i2);
        this.f12111i = max;
        this.f12112j = max;
        this.f12113k[0] = 0.26719576f * ((float) this.f12111i);
        this.f12113k[1] = 0.5f * ((float) this.f12111i);
        this.f12113k[2] = 0.43121693f * ((float) this.f12111i);
        this.f12113k[3] = 0.66402113f * ((float) this.f12111i);
        this.f12113k[4] = 0.39417988f * ((float) this.f12111i);
        this.f12113k[5] = 0.66137564f * ((float) this.f12111i);
        this.f12113k[6] = 0.73544973f * ((float) this.f12111i);
        this.f12113k[7] = 0.3227513f * ((float) this.f12111i);
        this.f12120r = 0.15079366f * ((float) this.f12111i);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(new RectF(this.f12120r, this.f12120r, ((float) this.f12111i) - this.f12120r, ((float) this.f12112j) - this.f12120r), (float) this.f12109g, (float) this.f12109g, this.f12103a);
        Canvas canvas2;
        if (this.f12117o) {
            canvas2 = canvas;
            canvas2.drawRect(((float) this.f12110h) + this.f12120r, ((float) this.f12110h) + this.f12120r, (((float) this.f12111i) - this.f12120r) - ((float) this.f12110h), (((float) this.f12112j) - this.f12120r) - ((float) this.f12110h), this.f12105c);
        } else if (this.f12116n <= 0.0f) {
        } else {
            if (this.f12116n < 0.33333334f) {
                canvas2 = canvas;
                canvas2.drawLine(this.f12113k[0], this.f12113k[1], this.f12113k[0] + ((this.f12113k[2] - this.f12113k[0]) * this.f12116n), this.f12113k[1] + ((this.f12113k[3] - this.f12113k[1]) * this.f12116n), this.f12104b);
                return;
            }
            float f = this.f12113k[4] + ((this.f12113k[6] - this.f12113k[4]) * this.f12116n);
            float f2 = this.f12113k[5] + ((this.f12113k[7] - this.f12113k[5]) * this.f12116n);
            canvas.drawLine(this.f12113k[0], this.f12113k[1], this.f12113k[2], this.f12113k[3], this.f12104b);
            canvas.drawLine(this.f12113k[4], this.f12113k[5], f, f2, this.f12104b);
        }
    }

    public void setBackgroundColor(int i) {
        this.f12107e = i;
    }

    public void setDoneShapeColor(int i) {
        this.f12108f = i;
        this.f12104b.setColor(i);
    }

    public void setBorderColor(int i) {
        this.f12106d = i;
    }

    public void setBorderWidth(int i) {
        this.f12109g = i;
    }

    /* renamed from: a */
    public boolean m12956a() {
        return this.f12115m;
    }

    public void setChecked(boolean z) {
        this.f12115m = z;
        if (z) {
            m12944c();
        } else {
            m12949e();
        }
    }

    /* renamed from: b */
    private void m12941b() {
        if (!this.f12118p) {
            this.f12118p = true;
            this.f12117o = true;
            ValueAnimator duration = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration((long) this.f12114l);
            duration.addUpdateListener(new C25902(this));
            duration.start();
        }
    }

    /* renamed from: c */
    private void m12944c() {
        if (!this.f12118p) {
            this.f12118p = true;
            this.f12117o = true;
            ValueAnimator duration = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration((long) this.f12114l);
            duration.addUpdateListener(new C25913(this));
            duration.start();
        }
    }

    /* renamed from: d */
    private void m12947d() {
        if (!this.f12118p) {
            this.f12118p = true;
            this.f12116n = 0.0f;
            this.f12117o = false;
            ValueAnimator duration = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration((long) this.f12114l);
            duration.addUpdateListener(new C25924(this));
            duration.start();
        }
    }

    /* renamed from: e */
    private void m12949e() {
        if (!this.f12118p) {
            this.f12118p = true;
            this.f12116n = 1.0f;
            this.f12117o = false;
            ValueAnimator duration = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration((long) this.f12114l);
            duration.addUpdateListener(new C25935(this));
            duration.start();
        }
    }

    public void setOnCheckedChangedListener(C1930a c1930a) {
        this.f12119q = c1930a;
    }

    /* renamed from: a */
    private int m12934a(float f, int i, int i2) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        int i6 = i & 255;
        return ((((i3 + ((int) (((float) (((i2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((i2 >> 16) & 255) - i4)) * f))) << 16)) | ((((int) (((float) (((i2 >> 8) & 255) - i5)) * f)) + i5) << 8)) | (((int) (((float) ((i2 & 255) - i6)) * f)) + i6);
    }

    /* renamed from: a */
    public int m12955a(float f) {
        return (int) (((((float) getContext().getResources().getDisplayMetrics().densityDpi) / 160.0f) * f) + 0.5f);
    }
}
