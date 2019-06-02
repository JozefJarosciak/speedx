package com.tencent.wxop.stat;

import android.content.Context;

final class al implements Runnable {
    /* renamed from: a */
    final /* synthetic */ StatAccount f15974a;
    /* renamed from: b */
    final /* synthetic */ Context f15975b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f15976c;

    al(StatAccount statAccount, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15974a = statAccount;
        this.f15975b = context;
        this.f15976c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f15974a == null || this.f15974a.getAccount().trim().length() == 0) {
            StatServiceImpl.f15886q.m18014w("account is null or empty.");
            return;
        }
        StatConfig.setQQ(this.f15975b, this.f15974a.getAccount());
        StatServiceImpl.m17885b(this.f15975b, this.f15974a, this.f15976c);
    }
}
