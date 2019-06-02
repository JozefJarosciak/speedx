package com.tencent.wxop.stat;

import android.content.Context;

final class an implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15980a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f15981b;

    an(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15980a = context;
        this.f15981b = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            StatServiceImpl.m17874a(this.f15980a, false, this.f15981b);
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
        }
    }
}
