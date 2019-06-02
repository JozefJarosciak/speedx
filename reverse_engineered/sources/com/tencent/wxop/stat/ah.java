package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4513e;
import com.tencent.wxop.stat.p201a.C4523k;
import org.apache.commons.cli.HelpFormatter;

final class ah implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15964a;
    /* renamed from: b */
    final /* synthetic */ String f15965b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f15966c;

    ah(Context context, String str, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15964a = context;
        this.f15965b = str;
        this.f15966c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            Long l;
            StatServiceImpl.flushDataToDB(this.f15964a);
            synchronized (StatServiceImpl.f15884o) {
                l = (Long) StatServiceImpl.f15884o.remove(this.f15965b);
            }
            if (l != null) {
                Long valueOf = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                if (valueOf.longValue() <= 0) {
                    valueOf = Long.valueOf(1);
                }
                String j = StatServiceImpl.f15883n;
                if (j != null && j.equals(this.f15965b)) {
                    j = HelpFormatter.DEFAULT_OPT_PREFIX;
                }
                C4513e c4523k = new C4523k(this.f15964a, j, this.f15965b, StatServiceImpl.m17874a(this.f15964a, false, this.f15966c), valueOf, this.f15966c);
                if (!this.f15965b.equals(StatServiceImpl.f15882m)) {
                    StatServiceImpl.f15886q.warn("Invalid invocation since previous onResume on diff page.");
                }
                new aq(c4523k).m17959a();
                StatServiceImpl.f15883n = this.f15965b;
                return;
            }
            StatServiceImpl.f15886q.m18010e("Starttime for PageID:" + this.f15965b + " not found, lost onResume()?");
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f15964a, th);
        }
    }
}
