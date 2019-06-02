package com.alipay.sdk.widget;

/* renamed from: com.alipay.sdk.widget.c */
final class C0891c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0889a f2248a;

    C0891c(C0889a c0889a) {
        this.f2248a = c0889a;
    }

    public final void run() {
        if (this.f2248a.f2244e != null) {
            try {
                this.f2248a.f2244e.dismiss();
            } catch (Exception e) {
            }
        }
    }
}
