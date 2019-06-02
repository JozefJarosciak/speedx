package com.alipay.sdk.data;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* renamed from: com.alipay.sdk.data.d */
final class C0850d implements Callable<String> {
    /* renamed from: a */
    final /* synthetic */ Context f2104a;
    /* renamed from: b */
    final /* synthetic */ HashMap f2105b;
    /* renamed from: c */
    final /* synthetic */ C0849c f2106c;

    C0850d(C0849c c0849c, Context context, HashMap hashMap) {
        this.f2106c = c0849c;
        this.f2104a = context;
        this.f2105b = hashMap;
    }

    public final /* synthetic */ Object call() throws Exception {
        C0849c c0849c = this.f2106c;
        return C0849c.m3267a(this.f2104a, this.f2105b);
    }

    /* renamed from: a */
    private String m3280a() throws Exception {
        C0849c c0849c = this.f2106c;
        return C0849c.m3267a(this.f2104a, this.f2105b);
    }
}
