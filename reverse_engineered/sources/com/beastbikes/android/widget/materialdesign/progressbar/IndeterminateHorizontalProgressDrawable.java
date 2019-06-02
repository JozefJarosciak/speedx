package com.beastbikes.android.widget.materialdesign.progressbar;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class IndeterminateHorizontalProgressDrawable extends C2678d implements C2679k {
    /* renamed from: i */
    private static final RectF f12561i = new RectF(-180.0f, -1.0f, 180.0f, 1.0f);
    /* renamed from: j */
    private static final RectF f12562j = new RectF(-180.0f, -5.0f, 180.0f, 5.0f);
    /* renamed from: k */
    private static final RectF f12563k = new RectF(-144.0f, -1.0f, 144.0f, 1.0f);
    /* renamed from: l */
    private static final RectTransformX f12564l = new RectTransformX(-522.6f, 0.1f);
    /* renamed from: m */
    private static final RectTransformX f12565m = new RectTransformX(-197.6f, 0.1f);
    /* renamed from: n */
    private int f12566n;
    /* renamed from: o */
    private int f12567o;
    /* renamed from: p */
    private boolean f12568p = true;
    /* renamed from: q */
    private float f12569q;
    /* renamed from: r */
    private RectTransformX f12570r = new RectTransformX(f12564l);
    /* renamed from: s */
    private RectTransformX f12571s = new RectTransformX(f12565m);

    private static class RectTransformX {
        /* renamed from: a */
        public float f12550a;
        /* renamed from: b */
        public float f12551b;

        public RectTransformX(float f, float f2) {
            this.f12550a = f;
            this.f12551b = f2;
        }

        public RectTransformX(RectTransformX rectTransformX) {
            this.f12550a = rectTransformX.f12550a;
            this.f12551b = rectTransformX.f12551b;
        }

        @Keep
        public void setTranslateX(float f) {
            this.f12550a = f;
        }

        @Keep
        public void setScaleX(float f) {
            this.f12551b = f;
        }
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ void mo3523b(boolean z) {
        super.mo3523b(z);
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ boolean mo3524b() {
        return super.mo3524b();
    }

    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    public /* bridge */ /* synthetic */ boolean isRunning() {
        return super.isRunning();
    }

    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(@Nullable ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    public /* bridge */ /* synthetic */ void setTint(@ColorInt int i) {
        super.setTint(i);
    }

    public /* bridge */ /* synthetic */ void setTintList(@Nullable ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    public /* bridge */ /* synthetic */ void setTintMode(@NonNull Mode mode) {
        super.setTintMode(mode);
    }

    public /* bridge */ /* synthetic */ void start() {
        super.start();
    }

    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }

    public IndeterminateHorizontalProgressDrawable(Context context) {
        super(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.f12566n = Math.round(3.2f * f);
        this.f12567o = Math.round(f * 16.0f);
        this.f12569q = C2698m.m13347b(16842803, context);
        this.a = new Animator[]{C2683a.m13327a(this.f12570r), C2683a.m13328b(this.f12571s)};
    }

    /* renamed from: a */
    public boolean mo3531a() {
        return this.f12568p;
    }

    /* renamed from: a */
    public void mo3530a(boolean z) {
        if (this.f12568p != z) {
            this.f12568p = z;
            invalidateSelf();
        }
    }

    public int getIntrinsicHeight() {
        return this.b ? this.f12567o : this.f12566n;
    }

    /* renamed from: a */
    protected void mo3529a(Paint paint) {
        paint.setStyle(Style.FILL);
    }

    /* renamed from: a */
    protected void mo3528a(Canvas canvas, int i, int i2, Paint paint) {
        if (this.b) {
            canvas.scale(((float) i) / f12562j.width(), ((float) i2) / f12562j.height());
            canvas.translate(f12562j.width() / 2.0f, f12562j.height() / 2.0f);
        } else {
            canvas.scale(((float) i) / f12561i.width(), ((float) i2) / f12561i.height());
            canvas.translate(f12561i.width() / 2.0f, f12561i.height() / 2.0f);
        }
        if (this.f12568p) {
            paint.setAlpha(Math.round(((float) this.d) * this.f12569q));
            m13307a(canvas, paint);
            paint.setAlpha(this.d);
        }
        m13308a(canvas, this.f12571s, paint);
        m13308a(canvas, this.f12570r, paint);
    }

    /* renamed from: a */
    private static void m13307a(Canvas canvas, Paint paint) {
        canvas.drawRect(f12561i, paint);
    }

    /* renamed from: a */
    private static void m13308a(Canvas canvas, RectTransformX rectTransformX, Paint paint) {
        int save = canvas.save();
        canvas.translate(rectTransformX.f12550a, 0.0f);
        canvas.scale(rectTransformX.f12551b, 1.0f);
        canvas.drawRect(f12563k, paint);
        canvas.restoreToCount(save);
    }
}
