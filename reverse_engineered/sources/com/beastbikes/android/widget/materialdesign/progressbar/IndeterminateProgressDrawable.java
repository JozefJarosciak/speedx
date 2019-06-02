package com.beastbikes.android.widget.materialdesign.progressbar;

import android.animation.Animator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class IndeterminateProgressDrawable extends C2678d {
    /* renamed from: i */
    private static final RectF f12576i = new RectF(-21.0f, -21.0f, 21.0f, 21.0f);
    /* renamed from: j */
    private static final RectF f12577j = new RectF(-24.0f, -24.0f, 24.0f, 24.0f);
    /* renamed from: k */
    private static final RectF f12578k = new RectF(-19.0f, -19.0f, 19.0f, 19.0f);
    /* renamed from: l */
    private int f12579l;
    /* renamed from: m */
    private int f12580m;
    /* renamed from: n */
    private RingPathTransform f12581n = new RingPathTransform();
    /* renamed from: o */
    private RingRotation f12582o = new RingRotation();

    private static class RingPathTransform {
        /* renamed from: a */
        public float f12572a;
        /* renamed from: b */
        public float f12573b;
        /* renamed from: c */
        public float f12574c;

        private RingPathTransform() {
        }

        @Keep
        public void setTrimPathStart(float f) {
            this.f12572a = f;
        }

        @Keep
        public void setTrimPathEnd(float f) {
            this.f12573b = f;
        }

        @Keep
        public void setTrimPathOffset(float f) {
            this.f12574c = f;
        }
    }

    private static class RingRotation {
        /* renamed from: a */
        private float f12575a;

        private RingRotation() {
        }

        @Keep
        public void setRotation(float f) {
            this.f12575a = f;
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

    public IndeterminateProgressDrawable(Context context) {
        super(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.f12579l = Math.round(3.2f * f);
        this.f12580m = Math.round(f * 16.0f);
        this.a = new Animator[]{C2683a.m13329c(this.f12581n), C2683a.m13330d(this.f12582o)};
    }

    /* renamed from: a */
    private int m13316a() {
        return this.b ? this.f12580m : this.f12579l;
    }

    public int getIntrinsicWidth() {
        return m13316a();
    }

    public int getIntrinsicHeight() {
        return m13316a();
    }

    /* renamed from: a */
    protected void mo3529a(Paint paint) {
        paint.setStyle(Style.STROKE);
        paint.setStrokeWidth(4.0f);
        paint.setStrokeCap(Cap.SQUARE);
        paint.setStrokeJoin(Join.MITER);
    }

    /* renamed from: a */
    protected void mo3528a(Canvas canvas, int i, int i2, Paint paint) {
        if (this.b) {
            canvas.scale(((float) i) / f12577j.width(), ((float) i2) / f12577j.height());
            canvas.translate(f12577j.width() / 2.0f, f12577j.height() / 2.0f);
        } else {
            canvas.scale(((float) i) / f12576i.width(), ((float) i2) / f12576i.height());
            canvas.translate(f12576i.width() / 2.0f, f12576i.height() / 2.0f);
        }
        m13317a(canvas, paint);
    }

    /* renamed from: a */
    private void m13317a(Canvas canvas, Paint paint) {
        int save = canvas.save();
        canvas.rotate(this.f12582o.f12575a);
        Canvas canvas2 = canvas;
        canvas2.drawArc(f12578k, -90.0f + ((this.f12581n.f12574c + this.f12581n.f12572a) * 360.0f), 360.0f * (this.f12581n.f12573b - this.f12581n.f12572a), false, paint);
        canvas.restoreToCount(save);
    }
}
