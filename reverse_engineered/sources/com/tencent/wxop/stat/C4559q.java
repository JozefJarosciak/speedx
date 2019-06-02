package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4517d;
import com.tencent.wxop.stat.p201a.C4521i;

/* renamed from: com.tencent.wxop.stat.q */
final class C4559q implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f16145a;
    /* renamed from: b */
    final /* synthetic */ Throwable f16146b;

    C4559q(Context context, Throwable th) {
        this.f16145a = context;
        this.f16146b = th;
    }

    public final void run() {
        try {
            if (StatConfig.isEnableStatService()) {
                new aq(new C4517d(this.f16145a, StatServiceImpl.m17874a(this.f16145a, false, null), 99, this.f16146b, C4521i.f15932a)).m17959a();
            }
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18010e("reportSdkSelfException error: " + th);
        }
    }
}
