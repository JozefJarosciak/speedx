package com.beastbikes.android.ble.ui.painter;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.ble.ui.p100c.C1748a;
import com.beastbikes.android.ble.ui.painter.p101a.C1753a;
import com.beastbikes.android.ble.ui.painter.p101a.C1754b;
import com.beastbikes.android.ble.ui.painter.p102b.C1755a;
import com.beastbikes.android.ble.ui.painter.p102b.C1756b;
import com.beastbikes.android.ble.ui.painter.progress.C1758a;
import com.beastbikes.android.ble.ui.painter.progress.C1759b;

public class PowerView extends View {
    /* renamed from: a */
    private ValueAnimator f7929a;
    /* renamed from: b */
    private ValueAnimator f7930b;
    /* renamed from: c */
    private Interpolator f7931c = new AccelerateDecelerateInterpolator();
    /* renamed from: d */
    private C1755a f7932d;
    /* renamed from: e */
    private C1758a f7933e;
    /* renamed from: f */
    private C1753a f7934f;
    /* renamed from: g */
    private int f7935g = 0;
    /* renamed from: h */
    private float f7936h = ((float) this.f7935g);
    /* renamed from: i */
    private float f7937i = ((float) this.f7935g);
    /* renamed from: j */
    private int f7938j = 100;
    /* renamed from: k */
    private float f7939k;
    /* renamed from: l */
    private int f7940l = 1000;
    /* renamed from: m */
    private long f7941m = 350;
    /* renamed from: n */
    private int f7942n = 15;
    /* renamed from: o */
    private int f7943o = Color.parseColor("#222222");
    /* renamed from: p */
    private int f7944p = Color.parseColor("#18ae6a");
    /* renamed from: q */
    private int f7945q = -1;
    /* renamed from: r */
    private int f7946r = -1;
    /* renamed from: s */
    private int f7947s = Color.parseColor("#bbbbbb");
    /* renamed from: t */
    private String f7948t = "%";
    /* renamed from: u */
    private boolean f7949u = true;
    /* renamed from: v */
    private int f7950v;
    /* renamed from: w */
    private int f7951w;
    /* renamed from: x */
    private int f7952x;
    /* renamed from: y */
    private int f7953y;

    /* renamed from: com.beastbikes.android.ble.ui.painter.PowerView$a */
    private class C1750a implements AnimatorUpdateListener {
        /* renamed from: a */
        final /* synthetic */ PowerView f7927a;

        private C1750a(PowerView powerView) {
            this.f7927a = powerView;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Float f = (Float) valueAnimator.getAnimatedValue();
            this.f7927a.m9324b(f.floatValue());
            this.f7927a.f7937i = f.floatValue();
        }
    }

    /* renamed from: com.beastbikes.android.ble.ui.painter.PowerView$b */
    private class C1751b implements AnimatorUpdateListener {
        /* renamed from: a */
        final /* synthetic */ PowerView f7928a;

        private C1751b(PowerView powerView) {
            this.f7928a = powerView;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Float f = (Float) valueAnimator.getAnimatedValue();
            this.f7928a.m9318a(f.floatValue());
            this.f7928a.f7936h = f.floatValue();
        }
    }

    public PowerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9319a(context, attributeSet);
    }

    public PowerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9319a(context, attributeSet);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (measuredWidth <= measuredHeight) {
            measuredHeight = measuredWidth;
        }
        super.setMeasuredDimension(measuredHeight, measuredHeight);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f7932d.mo3231a(i2, i);
        this.f7933e.mo3231a(i2, i);
        this.f7934f.mo3231a(i2, i);
    }

    /* renamed from: a */
    private void m9319a(Context context, AttributeSet attributeSet) {
        this.f7950v = C1748a.m9316a(10.0f, context);
        this.f7951w = C1748a.m9316a(54.0f, context);
        this.f7952x = C1748a.m9316a(18.0f, context);
        this.f7953y = C1748a.m9316a(25.0f, context);
        this.f7942n = C1748a.m9316a(15.0f, getContext());
        m9320a(context.obtainStyledAttributes(attributeSet, R$styleable.PowerView));
        setLayerType(1, null);
        this.f7932d = new C1756b(this.f7943o, this.f7942n, getContext(), this.f7950v);
        this.f7933e = new C1759b(this.f7944p, (float) this.f7938j, this.f7942n, getContext(), this.f7950v);
        m9317a();
        this.f7934f = new C1754b(this.f7946r, this.f7947s, getContext(), this.f7953y, this.f7951w, this.f7948t, this.f7952x);
    }

    /* renamed from: a */
    private void m9320a(TypedArray typedArray) {
        this.f7943o = typedArray.getColor(0, this.f7943o);
        this.f7944p = typedArray.getColor(1, this.f7944p);
        this.f7945q = typedArray.getColor(4, this.f7945q);
        this.f7946r = typedArray.getColor(7, this.f7946r);
        this.f7947s = typedArray.getColor(8, this.f7947s);
        this.f7938j = typedArray.getInt(9, this.f7938j);
        this.f7948t = typedArray.getString(10);
        this.f7951w = (int) typedArray.getDimension(11, (float) this.f7951w);
        this.f7952x = (int) typedArray.getDimension(12, (float) this.f7952x);
        this.f7950v = (int) typedArray.getDimension(13, (float) this.f7950v);
        this.f7942n = (int) typedArray.getDimension(14, (float) this.f7942n);
        if (this.f7948t == null) {
            this.f7948t = "%";
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f7934f.mo3232a(canvas);
        this.f7932d.mo3232a(canvas);
        this.f7933e.mo3232a(canvas);
        invalidate();
    }

    public void setValue(float f) {
        this.f7939k = f;
        if (f <= ((float) this.f7938j) && f >= ((float) this.f7935g)) {
            m9323b();
            this.f7932d.mo3235a(f);
        }
    }

    /* renamed from: a */
    public void m9327a(float f, boolean z) {
        this.f7939k = f;
        if (f <= ((float) this.f7938j) && f >= ((float) this.f7935g)) {
            if (z) {
                m9323b();
            } else {
                m9318a(f);
                m9324b(f);
            }
            int i = this.f7944p;
            if (f > 0.0f && f <= 20.0f && this.f7949u) {
                i = Color.parseColor("#d62424");
            }
            this.f7933e.mo3230a(i);
        }
    }

    public float getValue() {
        return this.f7939k;
    }

    public void setChangeColor(boolean z) {
        this.f7949u = z;
    }

    public void setVersion(String str) {
        this.f7934f.mo3233a(str);
    }

    public void setTitle(int i) {
        this.f7934f.mo3234b(getContext().getString(i));
    }

    public void setTitle(String str) {
        this.f7934f.mo3234b(str);
    }

    /* renamed from: a */
    private void m9317a() {
        this.f7929a = new ValueAnimator();
        this.f7929a.setInterpolator(this.f7931c);
        this.f7929a.addUpdateListener(new C1751b());
        this.f7930b = new ValueAnimator();
        this.f7930b.setInterpolator(new AccelerateDecelerateInterpolator());
        this.f7930b.addUpdateListener(new C1750a());
    }

    /* renamed from: b */
    private void m9323b() {
        if (this.f7929a != null) {
            this.f7929a.setFloatValues(new float[]{this.f7936h, this.f7939k});
            this.f7929a.setDuration(((long) this.f7940l) + this.f7941m);
            this.f7929a.start();
            this.f7930b.setFloatValues(new float[]{this.f7937i, this.f7939k});
            this.f7930b.setDuration((long) this.f7940l);
            this.f7930b.start();
        }
    }

    public void setProgress(Interpolator interpolator) {
        this.f7931c = interpolator;
        if (this.f7929a != null) {
            this.f7929a.setInterpolator(interpolator);
        }
    }

    public float getMax() {
        return (float) this.f7938j;
    }

    public void setMax(int i) {
        this.f7938j = i;
    }

    /* renamed from: a */
    private void m9318a(float f) {
        this.f7933e.mo3236a(f);
        this.f7932d.mo3235a(f);
    }

    /* renamed from: b */
    private void m9324b(float f) {
        this.f7934f.mo3229a(f);
    }
}
