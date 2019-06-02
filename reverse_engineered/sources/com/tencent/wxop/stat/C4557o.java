package com.tencent.wxop.stat;

import android.content.Context;

/* renamed from: com.tencent.wxop.stat.o */
final class C4557o implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f16141a;

    C4557o(Context context) {
        this.f16141a = context;
    }

    public final void run() {
        StatServiceImpl.flushDataToDB(this.f16141a);
    }
}
