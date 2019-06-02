package com.beastbikes.android.widget.materialdesign.progressbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import com.beastbikes.android.C1373R;

/* compiled from: ProgressDrawableBase */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.j */
abstract class C2677j extends Drawable implements C2675f, C2676n {
    /* renamed from: a */
    private Paint f12552a;
    /* renamed from: b */
    protected boolean f12553b = true;
    /* renamed from: c */
    protected boolean f12554c;
    /* renamed from: d */
    protected int f12555d = 255;
    /* renamed from: e */
    protected ColorFilter f12556e;
    /* renamed from: f */
    protected ColorStateList f12557f;
    /* renamed from: g */
    protected Mode f12558g = Mode.SRC_IN;
    /* renamed from: h */
    protected PorterDuffColorFilter f12559h;

    /* renamed from: a */
    protected abstract void mo3528a(Canvas canvas, int i, int i2, Paint paint);

    /* renamed from: a */
    protected abstract void mo3529a(Paint paint);

    public C2677j(Context context) {
        setAutoMirrored(true);
        setTint(C2698m.m13346a(C1373R.attr.colorControlActivated, context));
    }

    /* renamed from: b */
    public boolean mo3524b() {
        return this.f12553b;
    }

    /* renamed from: b */
    public void mo3523b(boolean z) {
        if (this.f12553b != z) {
            this.f12553b = z;
            invalidateSelf();
        }
    }

    public boolean isAutoMirrored() {
        return this.f12554c;
    }

    public void setAutoMirrored(boolean z) {
        if (this.f12554c != z) {
            this.f12554c = z;
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.f12555d;
    }

    public void setAlpha(int i) {
        if (this.f12555d != i) {
            this.f12555d = i;
            invalidateSelf();
        }
    }

    public ColorFilter getColorFilter() {
        return this.f12556e;
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.f12556e = colorFilter;
        invalidateSelf();
    }

    public void setTint(@ColorInt int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(@Nullable ColorStateList colorStateList) {
        this.f12557f = colorStateList;
        this.f12559h = m13298a(this.f12557f, this.f12558g);
        invalidateSelf();
    }

    public void setTintMode(@NonNull Mode mode) {
        this.f12558g = mode;
        this.f12559h = m13298a(this.f12557f, this.f12558g);
        invalidateSelf();
    }

    /* renamed from: a */
    private PorterDuffColorFilter m13298a(ColorStateList colorStateList, Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public int getOpacity() {
        return -3;
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (bounds.width() != 0 && bounds.height() != 0) {
            if (this.f12552a == null) {
                this.f12552a = new Paint();
                this.f12552a.setAntiAlias(true);
                this.f12552a.setColor(ViewCompat.MEASURED_STATE_MASK);
                mo3529a(this.f12552a);
            }
            this.f12552a.setAlpha(this.f12555d);
            this.f12552a.setColorFilter(this.f12556e != null ? this.f12556e : this.f12559h);
            int save = canvas.save();
            canvas.translate((float) bounds.left, (float) bounds.top);
            if (mo3531a()) {
                canvas.translate((float) bounds.width(), 0.0f);
                canvas.scale(-1.0f, 1.0f);
            }
            mo3528a(canvas, bounds.width(), bounds.height(), this.f12552a);
            canvas.restoreToCount(save);
        }
    }

    /* renamed from: a */
    private boolean mo3531a() {
        if (this.f12554c && DrawableCompat.getLayoutDirection(this) == 1) {
            return true;
        }
        return false;
    }
}
