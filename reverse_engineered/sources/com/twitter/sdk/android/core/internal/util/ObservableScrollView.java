package com.twitter.sdk.android.core.internal.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ObservableScrollView extends ScrollView {
    /* renamed from: a */
    C4644a f16363a;

    /* renamed from: com.twitter.sdk.android.core.internal.util.ObservableScrollView$a */
    public interface C4644a {
        /* renamed from: a */
        void mo6156a(int i);
    }

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ObservableScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public ObservableScrollView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.f16363a != null) {
            this.f16363a.mo6156a(i2);
        }
    }

    public void setScrollViewListener(C4644a c4644a) {
        this.f16363a = c4644a;
    }
}
