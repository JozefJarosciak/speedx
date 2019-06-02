package com.beastbikes.android.widget.convenientbanner;

import android.content.Context;
import android.widget.Scroller;

/* compiled from: ViewPagerScroller */
/* renamed from: com.beastbikes.android.widget.convenientbanner.a */
public class C2625a extends Scroller {
    /* renamed from: a */
    private int f12304a = 800;
    /* renamed from: b */
    private boolean f12305b;

    public C2625a(Context context) {
        super(context);
    }

    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        super.startScroll(i, i2, i3, i4, this.f12305b ? 0 : this.f12304a);
    }

    public void startScroll(int i, int i2, int i3, int i4) {
        super.startScroll(i, i2, i3, i4, this.f12305b ? 0 : this.f12304a);
    }

    /* renamed from: a */
    public int m13104a() {
        return this.f12304a;
    }

    /* renamed from: a */
    public void m13105a(int i) {
        this.f12304a = i;
    }
}
