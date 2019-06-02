package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;

/* compiled from: NavigationIntervalView */
/* renamed from: com.beastbikes.android.ble.ui.widget.b */
public class C1766b extends LinearLayout {
    public C1766b(Context context) {
        super(context);
        m9387a(context);
    }

    public C1766b(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m9387a(context);
    }

    public C1766b(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9387a(context);
    }

    /* renamed from: a */
    private void m9387a(Context context) {
        C1766b.inflate(context, C1373R.layout.layout_navigatioin_vertical_dash_line_and_horizontal_line, this);
    }
}
