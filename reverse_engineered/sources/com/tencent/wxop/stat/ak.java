package com.tencent.wxop.stat;

import android.content.Context;

final class ak implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f15971a;
    /* renamed from: b */
    final /* synthetic */ Context f15972b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f15973c;

    ak(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15971a = str;
        this.f15972b = context;
        this.f15973c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f15971a == null || this.f15971a.trim().length() == 0) {
            StatServiceImpl.f15886q.m18014w("qq num is null or empty.");
            return;
        }
        StatConfig.f15844f = this.f15971a;
        StatServiceImpl.m17885b(this.f15972b, new StatAccount(this.f15971a), this.f15973c);
    }
}
