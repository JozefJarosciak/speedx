package com.tencent.wxop.stat;

import android.content.Context;

final class ai implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15967a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f15968b;

    ai(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15967a = context;
        this.f15968b = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            StatServiceImpl.stopSession();
            StatServiceImpl.m17874a(this.f15967a, true, this.f15968b);
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f15967a, th);
        }
    }
}
