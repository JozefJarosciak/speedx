package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4513e;
import com.tencent.wxop.stat.p201a.C4515b;
import com.tencent.wxop.stat.p201a.C4516c;

/* renamed from: com.tencent.wxop.stat.x */
final class C4566x implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f16162a;
    /* renamed from: b */
    final /* synthetic */ C4516c f16163b;
    /* renamed from: c */
    final /* synthetic */ Context f16164c;
    /* renamed from: d */
    final /* synthetic */ StatSpecifyReportedInfo f16165d;

    C4566x(String str, C4516c c4516c, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f16162a = str;
        this.f16163b = c4516c;
        this.f16164c = context;
        this.f16165d = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            if (StatServiceImpl.m17882a(this.f16162a)) {
                StatServiceImpl.f15886q.error((Object) "The event_id of StatService.trackCustomEndEvent() can not be null or empty.");
                return;
            }
            Long l = (Long) StatServiceImpl.f15874e.remove(this.f16163b);
            if (l != null) {
                C4513e c4515b = new C4515b(this.f16164c, StatServiceImpl.m17874a(this.f16164c, false, this.f16165d), this.f16163b.f15910a, this.f16165d);
                c4515b.m17916b().f15911b = this.f16163b.f15911b;
                l = Long.valueOf((System.currentTimeMillis() - l.longValue()) / 1000);
                c4515b.m17914a(Long.valueOf(l.longValue() == 0 ? 1 : l.longValue()).longValue());
                new aq(c4515b).m17959a();
                return;
            }
            StatServiceImpl.f15886q.error("No start time found for custom event: " + this.f16163b.toString() + ", lost trackCustomBeginEvent()?");
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f16164c, th);
        }
    }
}
