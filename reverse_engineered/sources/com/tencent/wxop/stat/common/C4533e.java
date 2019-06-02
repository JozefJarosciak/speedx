package com.tencent.wxop.stat.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: com.tencent.wxop.stat.common.e */
public class C4533e {
    /* renamed from: a */
    ExecutorService f16072a;

    public C4533e() {
        this.f16072a = null;
        this.f16072a = Executors.newSingleThreadExecutor();
    }

    /* renamed from: a */
    public void m18024a(Runnable runnable) {
        this.f16072a.execute(runnable);
    }
}
