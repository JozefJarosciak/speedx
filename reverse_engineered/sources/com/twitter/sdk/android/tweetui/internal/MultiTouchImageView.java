package com.twitter.sdk.android.tweetui.internal;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class MultiTouchImageView extends ImageView {
    /* renamed from: a */
    final ScaleGestureDetector f16543a;
    /* renamed from: b */
    final GestureDetector f16544b;
    /* renamed from: c */
    final Matrix f16545c;
    /* renamed from: d */
    final Matrix f16546d;
    /* renamed from: e */
    final Matrix f16547e;
    /* renamed from: f */
    final RectF f16548f;
    /* renamed from: g */
    final RectF f16549g;
    /* renamed from: h */
    final float[] f16550h;

    /* renamed from: com.twitter.sdk.android.tweetui.internal.MultiTouchImageView$1 */
    class C47051 extends SimpleOnScaleGestureListener {
        /* renamed from: a */
        final /* synthetic */ MultiTouchImageView f16538a;

        C47051(MultiTouchImageView multiTouchImageView) {
            this.f16538a = multiTouchImageView;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            this.f16538a.m18527a(scaleGestureDetector.getScaleFactor(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            this.f16538a.m18535e();
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            if (this.f16538a.getScale() < 1.0f) {
                this.f16538a.m18533c();
                this.f16538a.m18535e();
            }
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetui.internal.MultiTouchImageView$2 */
    class C47062 extends SimpleOnGestureListener {
        /* renamed from: a */
        final /* synthetic */ MultiTouchImageView f16539a;

        C47062(MultiTouchImageView multiTouchImageView) {
            this.f16539a = multiTouchImageView;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.f16539a.m18526a(-f, -f2);
            this.f16539a.m18535e();
            return true;
        }

        public boolean onDoubleTap(MotionEvent motionEvent) {
            if (this.f16539a.getScale() > 1.0f) {
                this.f16539a.m18528a(this.f16539a.getScale(), 1.0f, motionEvent.getX(), motionEvent.getY());
            } else {
                this.f16539a.m18528a(this.f16539a.getScale(), 2.0f, motionEvent.getX(), motionEvent.getY());
            }
            return true;
        }
    }

    public MultiTouchImageView(Context context) {
        this(context, null);
    }

    public MultiTouchImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MultiTouchImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f16545c = new Matrix();
        this.f16546d = new Matrix();
        this.f16547e = new Matrix();
        this.f16548f = new RectF();
        this.f16549g = new RectF();
        this.f16550h = new float[9];
        this.f16543a = new ScaleGestureDetector(context, new C47051(this));
        this.f16544b = new GestureDetector(context, new C47062(this));
    }

    /* renamed from: a */
    boolean m18530a() {
        Drawable drawable = getDrawable();
        return drawable != null && drawable.getIntrinsicWidth() > 0;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (m18530a()) {
            m18531b();
            m18529a(getDrawable());
            m18535e();
        }
    }

    /* renamed from: b */
    void m18531b() {
        this.f16548f.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
    }

    /* renamed from: a */
    void m18529a(Drawable drawable) {
        RectF rectF = new RectF(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
        this.f16546d.reset();
        this.f16546d.setRectToRect(rectF, this.f16548f, ScaleToFit.CENTER);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!m18530a()) {
            return false;
        }
        getParent().requestDisallowInterceptTouchEvent(true);
        boolean onTouchEvent = this.f16543a.onTouchEvent(motionEvent);
        if (this.f16544b.onTouchEvent(motionEvent) || onTouchEvent) {
            onTouchEvent = true;
        } else {
            onTouchEvent = false;
        }
        if (onTouchEvent || super.onTouchEvent(motionEvent)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    void m18527a(float f, float f2, float f3) {
        this.f16547e.postScale(f, f, f2, f3);
    }

    float getScale() {
        this.f16547e.getValues(this.f16550h);
        return this.f16550h[0];
    }

    /* renamed from: a */
    void m18526a(float f, float f2) {
        this.f16547e.postTranslate(f, f2);
    }

    /* renamed from: c */
    void m18533c() {
        this.f16547e.reset();
    }

    /* renamed from: d */
    void m18534d() {
        float height;
        float f = 0.0f;
        RectF a = m18525a(getDrawMatrix());
        if (a.height() <= this.f16548f.height()) {
            height = ((this.f16548f.height() - a.height()) / 2.0f) - a.top;
        } else if (a.top > 0.0f) {
            height = -a.top;
        } else if (a.bottom < this.f16548f.height()) {
            height = this.f16548f.height() - a.bottom;
        } else {
            height = 0.0f;
        }
        if (a.width() <= this.f16548f.width()) {
            f = ((this.f16548f.width() - a.width()) / 2.0f) - a.left;
        } else if (a.left > 0.0f) {
            f = -a.left;
        } else if (a.right < this.f16548f.width()) {
            f = this.f16548f.width() - a.right;
        }
        m18526a(f, height);
    }

    /* renamed from: a */
    RectF m18525a(Matrix matrix) {
        Drawable drawable = getDrawable();
        if (drawable != null) {
            this.f16549g.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
            matrix.mapRect(this.f16549g);
        }
        return this.f16549g;
    }

    Matrix getDrawMatrix() {
        this.f16545c.set(this.f16546d);
        this.f16545c.postConcat(this.f16547e);
        return this.f16545c;
    }

    /* renamed from: e */
    void m18535e() {
        m18534d();
        setScaleType(ScaleType.MATRIX);
        setImageMatrix(getDrawMatrix());
    }

    /* renamed from: a */
    void m18528a(float f, float f2, float f3, float f4) {
        if (VERSION.SDK_INT >= 11) {
            m18532b(f, f2, f3, f4);
            return;
        }
        m18527a(f2 / getScale(), f3, f4);
        m18535e();
    }

    @TargetApi(11)
    /* renamed from: b */
    void m18532b(float f, float f2, final float f3, final float f4) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
        ofFloat.setDuration(300);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.addUpdateListener(new AnimatorUpdateListener(this) {
            /* renamed from: c */
            final /* synthetic */ MultiTouchImageView f16542c;

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f16542c.m18527a(((Float) valueAnimator.getAnimatedValue()).floatValue() / this.f16542c.getScale(), f3, f4);
                this.f16542c.m18535e();
            }
        });
        ofFloat.start();
    }
}
