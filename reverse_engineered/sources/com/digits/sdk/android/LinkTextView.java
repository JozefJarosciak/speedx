package com.digits.sdk.android;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

public class LinkTextView extends TextView {
    public LinkTextView(Context context) {
        super(context);
        m13885a(context);
    }

    public LinkTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13885a(context);
    }

    public LinkTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m13885a(context);
    }

    /* renamed from: a */
    private void m13885a(Context context) {
        setTextColor(m13886b(context));
    }

    /* renamed from: b */
    private int m13886b(Context context) {
        TypedValue a = bu.m14177a(context.getTheme(), 16842907);
        if (a == null) {
            return bu.m14175a(context.getResources(), context.getTheme());
        }
        return a.data;
    }
}
