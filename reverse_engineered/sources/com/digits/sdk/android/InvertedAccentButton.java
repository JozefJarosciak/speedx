package com.digits.sdk.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InvertedAccentButton extends Button {
    public InvertedAccentButton(Context context) {
        this(context, null);
    }

    public InvertedAccentButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842824);
    }

    public InvertedAccentButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13873a();
    }

    /* renamed from: a */
    void m13873a() {
        int a = bu.m14175a(getResources(), getContext().getTheme());
        C2918j c2918j = new C2918j(getResources());
        c2918j.m14207a((View) this);
        c2918j.m14211b((View) this, a);
        c2918j.m14212b((TextView) this, a);
    }
}
