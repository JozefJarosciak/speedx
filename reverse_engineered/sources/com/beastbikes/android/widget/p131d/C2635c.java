package com.beastbikes.android.widget.p131d;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

/* compiled from: MultiActionTextViewClickableSpan */
/* renamed from: com.beastbikes.android.widget.d.c */
public abstract class C2635c extends ClickableSpan {
    /* renamed from: a */
    private boolean f12338a;

    public C2635c(boolean z) {
        this.f12338a = z;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setColor(textPaint.linkColor);
        textPaint.setUnderlineText(this.f12338a);
    }
}
