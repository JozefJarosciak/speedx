package com.beastbikes.android.widget.materialdesign.progressbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* compiled from: HorizontalProgressDrawable */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.c */
public class C2685c extends LayerDrawable implements C2675f, C2679k, C2676n {
    /* renamed from: a */
    private int f12594a;
    /* renamed from: b */
    private C2697l f12595b = ((C2697l) getDrawable(0));
    /* renamed from: c */
    private C2697l f12596c;
    /* renamed from: d */
    private C2697l f12597d;

    public C2685c(Context context) {
        super(new Drawable[]{new C2697l(context), new C2697l(context), new C2697l(context)});
        setId(0, 16908288);
        setId(1, 16908303);
        this.f12596c = (C2697l) getDrawable(1);
        this.f12594a = Math.round(C2698m.m13347b(16842803, context) * 255.0f);
        this.f12596c.setAlpha(this.f12594a);
        this.f12596c.m13344a(false);
        setId(2, 16908301);
        this.f12597d = (C2697l) getDrawable(2);
        this.f12597d.m13344a(false);
    }

    /* renamed from: a */
    public boolean mo3531a() {
        return this.f12595b.mo3531a();
    }

    /* renamed from: a */
    public void mo3530a(boolean z) {
        if (this.f12595b.mo3531a() != z) {
            this.f12595b.m13344a(z);
            this.f12596c.setAlpha(z ? this.f12594a : this.f12594a * 2);
        }
    }

    /* renamed from: b */
    public boolean mo3524b() {
        return this.f12595b.mo3524b();
    }

    /* renamed from: b */
    public void mo3523b(boolean z) {
        this.f12595b.mo3523b(z);
        this.f12596c.mo3523b(z);
        this.f12597d.mo3523b(z);
    }

    @SuppressLint({"NewApi"})
    public void setTint(@ColorInt int i) {
        this.f12595b.setTint(i);
        this.f12596c.setTint(i);
        this.f12597d.setTint(i);
    }

    @SuppressLint({"NewApi"})
    public void setTintList(@Nullable ColorStateList colorStateList) {
        this.f12595b.setTintList(colorStateList);
        this.f12596c.setTintList(colorStateList);
        this.f12597d.setTintList(colorStateList);
    }

    @SuppressLint({"NewApi"})
    public void setTintMode(@NonNull Mode mode) {
        this.f12595b.setTintMode(mode);
        this.f12596c.setTintMode(mode);
        this.f12597d.setTintMode(mode);
    }
}
