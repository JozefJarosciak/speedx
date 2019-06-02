package io.fabric.sdk.android.services.p202b;

import android.content.Context;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: TimeBasedFileRollOverRunnable */
/* renamed from: io.fabric.sdk.android.services.b.m */
public class C4860m implements Runnable {
    /* renamed from: a */
    private final Context f17134a;
    /* renamed from: b */
    private final C4631j f17135b;

    public C4860m(Context context, C4631j c4631j) {
        this.f17134a = context;
        this.f17135b = c4631j;
    }

    public void run() {
        try {
            C4877i.m19157a(this.f17134a, "Performing time based file roll over.");
            if (!this.f17135b.mo6146d()) {
                this.f17135b.mo6145c();
            }
        } catch (Throwable e) {
            C4877i.m19158a(this.f17134a, "Failed to roll over file", e);
        }
    }
}
