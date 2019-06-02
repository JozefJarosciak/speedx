package com.beastbikes.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class BottomScrollView extends ScrollView {
    /* renamed from: a */
    private C2092a f12063a;

    /* renamed from: com.beastbikes.android.widget.BottomScrollView$a */
    public interface C2092a {
        /* renamed from: a */
        void mo3396a(boolean z);
    }

    public BottomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BottomScrollView(Context context) {
        super(context);
    }

    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        super.onOverScrolled(i, i2, z, z2);
        if (i2 != 0 && this.f12063a != null) {
            this.f12063a.mo3396a(z2);
        }
    }

    public void setOnScrollToBottomLintener(C2092a c2092a) {
        this.f12063a = c2092a;
    }
}
