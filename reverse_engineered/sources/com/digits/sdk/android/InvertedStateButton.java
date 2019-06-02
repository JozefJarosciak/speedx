package com.digits.sdk.android;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class InvertedStateButton extends StateButton {
    public InvertedStateButton(Context context) {
        this(context, null);
    }

    public InvertedStateButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InvertedStateButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    void mo3632a(Context context) {
        this.h = bu.m14175a(getResources(), context.getTheme());
        this.g = new C2918j(getResources());
        this.g.m14211b((View) this, this.h);
        this.g.m14212b(this.a, this.h);
        m13874a();
        m13878b();
    }

    int getTextColor() {
        return this.g.m14210b(this.h);
    }

    Drawable getProgressDrawable() {
        return bu.m14178a(this.h) ? getResources().getDrawable(C2876R.drawable.progress_light) : getResources().getDrawable(C2876R.drawable.progress_dark);
    }
}
