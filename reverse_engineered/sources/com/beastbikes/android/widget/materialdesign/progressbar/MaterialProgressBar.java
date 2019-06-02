package com.beastbikes.android.widget.materialdesign.progressbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;
import com.beastbikes.android.R$styleable;

public class MaterialProgressBar extends ProgressBar {
    /* renamed from: a */
    private static final String f12587a = MaterialProgressBar.class.getSimpleName();
    /* renamed from: b */
    private int f12588b;
    /* renamed from: c */
    private C2682a f12589c = new C2682a();

    /* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.MaterialProgressBar$a */
    private static class C2682a {
        /* renamed from: a */
        boolean f12583a;
        /* renamed from: b */
        ColorStateList f12584b;
        /* renamed from: c */
        boolean f12585c;
        /* renamed from: d */
        Mode f12586d;

        private C2682a() {
        }
    }

    public MaterialProgressBar(Context context) {
        super(context);
        m13323a(context, null, 0, 0);
    }

    public MaterialProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13323a(context, attributeSet, 0, 0);
    }

    public MaterialProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13323a(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public MaterialProgressBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        m13323a(context, attributeSet, i, i2);
    }

    /* renamed from: a */
    private void m13323a(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.MaterialProgressBar, i, i2);
        this.f12588b = obtainStyledAttributes.getInt(1, 0);
        boolean z = obtainStyledAttributes.getBoolean(2, false);
        boolean z2 = obtainStyledAttributes.getBoolean(3, true);
        boolean z3 = obtainStyledAttributes.getBoolean(4, this.f12588b == 1);
        if (obtainStyledAttributes.hasValue(0)) {
            this.f12589c.f12584b = obtainStyledAttributes.getColorStateList(0);
            this.f12589c.f12583a = true;
        }
        if (obtainStyledAttributes.hasValue(5)) {
            this.f12589c.f12586d = C2684b.m13331a(obtainStyledAttributes.getInt(5, -1), null);
            this.f12589c.f12585c = true;
        }
        obtainStyledAttributes.recycle();
        switch (this.f12588b) {
            case 0:
                if (isIndeterminate() && !z) {
                    setIndeterminateDrawable(new IndeterminateProgressDrawable(context));
                    break;
                }
                throw new UnsupportedOperationException("Determinate circular drawable is not yet supported");
            case 1:
                if (isIndeterminate() || z) {
                    setIndeterminateDrawable(new IndeterminateHorizontalProgressDrawable(context));
                }
                if (!isIndeterminate() || z) {
                    setProgressDrawable(new C2685c(context));
                    break;
                }
            default:
                throw new IllegalArgumentException("Unknown progress style: " + this.f12588b);
        }
        setUseIntrinsicPadding(z2);
        setShowTrack(z3);
    }

    public int getProgressStyle() {
        return this.f12588b;
    }

    public Drawable getDrawable() {
        return isIndeterminate() ? getIndeterminateDrawable() : getProgressDrawable();
    }

    public boolean getUseIntrinsicPadding() {
        Drawable drawable = getDrawable();
        if (drawable instanceof C2675f) {
            return ((C2675f) drawable).mo3524b();
        }
        throw new IllegalStateException("Drawable does not implement IntrinsicPaddingDrawable");
    }

    public void setUseIntrinsicPadding(boolean z) {
        Drawable drawable = getDrawable();
        if (drawable instanceof C2675f) {
            ((C2675f) drawable).mo3523b(z);
            return;
        }
        throw new IllegalStateException("Drawable does not implement IntrinsicPaddingDrawable");
    }

    public boolean getShowTrack() {
        Drawable drawable = getDrawable();
        if (drawable instanceof C2679k) {
            return ((C2679k) drawable).mo3531a();
        }
        return false;
    }

    public void setShowTrack(boolean z) {
        Drawable drawable = getDrawable();
        if (drawable instanceof C2679k) {
            ((C2679k) drawable).mo3530a(z);
        } else if (z) {
            throw new IllegalStateException("Drawable does not implement ShowTrackDrawable");
        }
    }

    public void setProgressDrawable(Drawable drawable) {
        super.setProgressDrawable(drawable);
        if (this.f12589c != null) {
            m13325b();
        }
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        super.setIndeterminateDrawable(drawable);
        if (this.f12589c != null) {
            m13326c();
        }
    }

    @Nullable
    public ColorStateList getProgressTintList() {
        return this.f12589c.f12584b;
    }

    public void setProgressTintList(@Nullable ColorStateList colorStateList) {
        this.f12589c.f12584b = colorStateList;
        this.f12589c.f12583a = true;
        m13322a();
    }

    @Nullable
    public Mode getProgressTintMode() {
        return this.f12589c.f12586d;
    }

    public void setProgressTintMode(@Nullable Mode mode) {
        this.f12589c.f12586d = mode;
        this.f12589c.f12585c = true;
        m13322a();
    }

    /* renamed from: a */
    private void m13322a() {
        m13325b();
        m13326c();
    }

    /* renamed from: b */
    private void m13325b() {
        if (this.f12589c.f12583a || this.f12589c.f12585c) {
            Drawable progressDrawable = getProgressDrawable();
            if (progressDrawable != null) {
                m13324a(progressDrawable, this.f12589c);
            }
        }
    }

    /* renamed from: c */
    private void m13326c() {
        if (this.f12589c.f12583a || this.f12589c.f12585c) {
            Drawable indeterminateDrawable = getIndeterminateDrawable();
            if (indeterminateDrawable != null) {
                m13324a(indeterminateDrawable, this.f12589c);
            }
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private void m13324a(Drawable drawable, C2682a c2682a) {
        if (c2682a.f12583a || c2682a.f12585c) {
            if (c2682a.f12583a) {
                if (drawable instanceof C2676n) {
                    ((C2676n) drawable).setTintList(c2682a.f12584b);
                } else {
                    Log.w(f12587a, "drawable did not implement TintableDrawable, it won't be tinted below Lollipop");
                    if (VERSION.SDK_INT >= 21) {
                        drawable.setTintList(c2682a.f12584b);
                    }
                }
            }
            if (c2682a.f12585c) {
                if (drawable instanceof C2676n) {
                    ((C2676n) drawable).setTintMode(c2682a.f12586d);
                } else {
                    Log.w(f12587a, "drawable did not implement TintableDrawable, it won't be tinted below Lollipop");
                    if (VERSION.SDK_INT >= 21) {
                        drawable.setTintMode(c2682a.f12586d);
                    }
                }
            }
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
        }
    }
}
