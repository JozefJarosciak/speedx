package com.beastbikes.android.widget.stickylistlibrary.p156a.p173b;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

/* compiled from: DimensionCalculator */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.a.b.a */
public class C2750a {
    /* renamed from: a */
    public void m13636a(Rect rect, View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof MarginLayoutParams) {
            m13635a(rect, (MarginLayoutParams) layoutParams);
        } else {
            rect.set(0, 0, 0, 0);
        }
    }

    /* renamed from: a */
    private void m13635a(Rect rect, MarginLayoutParams marginLayoutParams) {
        rect.set(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
    }
}
