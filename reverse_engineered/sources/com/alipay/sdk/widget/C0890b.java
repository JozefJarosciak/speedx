package com.alipay.sdk.widget;

import com.alipay.sdk.widget.C0889a.C0888a;

/* renamed from: com.alipay.sdk.widget.b */
final class C0890b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0889a f2247a;

    C0890b(C0889a c0889a) {
        this.f2247a = c0889a;
    }

    public final void run() {
        if (this.f2247a.f2244e == null) {
            this.f2247a.f2244e = new C0888a(this.f2247a, this.f2247a.f2245f);
        }
        try {
            if (!this.f2247a.f2244e.isShowing()) {
                this.f2247a.f2244e.show();
            }
        } catch (Exception e) {
        }
    }
}
