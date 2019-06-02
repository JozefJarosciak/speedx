package com.digits.sdk.android;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccentButton extends Button {
    public AccentButton(Context context) {
        this(context, null);
    }

    public AccentButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842824);
    }

    public AccentButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13832a();
    }

    /* renamed from: a */
    private void m13832a() {
        int a = bu.m14175a(getResources(), getContext().getTheme());
        C2918j c2918j = new C2918j(getResources());
        c2918j.m14207a((View) this);
        c2918j.m14208a((View) this, a);
        c2918j.m14209a((TextView) this, a);
    }
}
