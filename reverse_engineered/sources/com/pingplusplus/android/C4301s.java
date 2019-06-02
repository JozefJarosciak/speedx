package com.pingplusplus.android;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.pingplusplus.android.s */
class C4301s implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C4300q f15007a;

    C4301s(C4300q c4300q) {
        this.f15007a = c4300q;
    }

    public void onClick(View view) {
        if (this.f15007a.f14992c == null || !this.f15007a.f14992c.canGoBack() || this.f15007a.f15005p) {
            this.f15007a.f14993d.onBackPressed();
        } else {
            this.f15007a.f14992c.goBack();
        }
    }
}
