package com.tencent.wxop.stat;

import android.content.Context;

final class ad implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15957a;
    /* renamed from: b */
    final /* synthetic */ int f15958b;

    ad(Context context, int i) {
        this.f15957a = context;
        this.f15958b = i;
    }

    public final void run() {
        try {
            StatServiceImpl.flushDataToDB(this.f15957a);
            au.m17968a(this.f15957a).m17996a(this.f15958b);
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f15957a, th);
        }
    }
}
