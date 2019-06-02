package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4519g;

final class am implements Runnable {
    /* renamed from: a */
    final /* synthetic */ StatGameUser f15977a;
    /* renamed from: b */
    final /* synthetic */ Context f15978b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f15979c;

    am(StatGameUser statGameUser, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15977a = statGameUser;
        this.f15978b = context;
        this.f15979c = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f15977a == null) {
            StatServiceImpl.f15886q.error((Object) "The gameUser of StatService.reportGameUser() can not be null!");
        } else if (this.f15977a.getAccount() == null || this.f15977a.getAccount().length() == 0) {
            StatServiceImpl.f15886q.error((Object) "The account of gameUser on StatService.reportGameUser() can not be null or empty!");
        } else {
            try {
                new aq(new C4519g(this.f15978b, StatServiceImpl.m17874a(this.f15978b, false, this.f15979c), this.f15977a, this.f15979c)).m17959a();
            } catch (Throwable th) {
                StatServiceImpl.f15886q.m18011e(th);
                StatServiceImpl.m17880a(this.f15978b, th);
            }
        }
    }
}
