package com.alipay.sdk.app.statistic;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.packet.impl.C0865c;
import com.alipay.sdk.util.C0881i;
import java.io.IOException;

/* renamed from: com.alipay.sdk.app.statistic.b */
final class C0824b implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f1942a;
    /* renamed from: b */
    final /* synthetic */ String f1943b;

    C0824b(Context context, String str) {
        this.f1942a = context;
        this.f1943b = str;
    }

    public final void run() {
        C0865c c0865c = new C0865c();
        try {
            String b = C0881i.m3451b(this.f1942a, C0823a.f1940a, null);
            if (!(TextUtils.isEmpty(b) || c0865c.mo2428a(this.f1942a, b) == null)) {
                C0881i.m3449a(this.f1942a, C0823a.f1940a);
            }
        } catch (Throwable th) {
        }
        try {
            if (!TextUtils.isEmpty(this.f1943b)) {
                c0865c.mo2428a(this.f1942a, this.f1943b);
            }
        } catch (IOException e) {
            C0881i.m3450a(this.f1942a, C0823a.f1940a, this.f1943b);
        } catch (Throwable th2) {
        }
    }
}
