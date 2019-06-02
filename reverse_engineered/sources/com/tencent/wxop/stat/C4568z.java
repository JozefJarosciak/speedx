package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4513e;
import com.tencent.wxop.stat.p201a.C4515b;
import com.tencent.wxop.stat.p201a.C4516c;

/* renamed from: com.tencent.wxop.stat.z */
final class C4568z implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f16169a;
    /* renamed from: b */
    final /* synthetic */ C4516c f16170b;
    /* renamed from: c */
    final /* synthetic */ Context f16171c;
    /* renamed from: d */
    final /* synthetic */ StatSpecifyReportedInfo f16172d;

    C4568z(String str, C4516c c4516c, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f16169a = str;
        this.f16170b = c4516c;
        this.f16171c = context;
        this.f16172d = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            if (StatServiceImpl.m17882a(this.f16169a)) {
                StatServiceImpl.f15886q.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
                return;
            }
            Long l = (Long) StatServiceImpl.f15874e.remove(this.f16170b);
            if (l != null) {
                C4513e c4515b = new C4515b(this.f16171c, StatServiceImpl.m17874a(this.f16171c, false, this.f16172d), this.f16170b.f15910a, this.f16172d);
                c4515b.m17916b().f15912c = this.f16170b.f15912c;
                l = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                c4515b.m17914a(Long.valueOf(l.longValue() <= 0 ? 1 : l.longValue()).longValue());
                new aq(c4515b).m17959a();
                return;
            }
            StatServiceImpl.f15886q.warn("No start time found for custom event: " + this.f16170b.toString() + ", lost trackCustomBeginKVEvent()?");
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f16171c, th);
        }
    }
}
