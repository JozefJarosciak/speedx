package com.beastbikes.android.widget.p131d;

import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/* compiled from: MultiActionTextView */
/* renamed from: com.beastbikes.android.widget.d.b */
public class C2637b {
    /* renamed from: a */
    public static void m13132a(TextView textView, SpannableStringBuilder spannableStringBuilder, int i) {
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableStringBuilder, BufferType.SPANNABLE);
        textView.setLinkTextColor(i);
    }

    /* renamed from: a */
    public static void m13133a(final C2634a c2634a) {
        c2634a.m13130e().setSpan(new C2635c(false) {
            public void onClick(View view) {
                c2634a.m13131f().mo3417a(c2634a);
            }
        }, c2634a.m13127c(), c2634a.m13129d(), 33);
    }
}
