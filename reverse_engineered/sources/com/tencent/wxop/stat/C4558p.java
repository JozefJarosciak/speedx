package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4517d;

/* renamed from: com.tencent.wxop.stat.p */
final class C4558p implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f16142a;
    /* renamed from: b */
    final /* synthetic */ Context f16143b;
    /* renamed from: c */
    final /* synthetic */ StatSpecifyReportedInfo f16144c;

    C4558p(String str, Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f16142a = str;
        this.f16143b = context;
        this.f16144c = statSpecifyReportedInfo;
    }

    public final void run() {
        try {
            if (StatServiceImpl.m17882a(this.f16142a)) {
                StatServiceImpl.f15886q.error((Object) "Error message in StatService.reportError() is empty.");
            } else {
                new aq(new C4517d(this.f16143b, StatServiceImpl.m17874a(this.f16143b, false, this.f16144c), this.f16142a, 0, StatConfig.getMaxReportEventLength(), null, this.f16144c)).m17959a();
            }
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f16143b, th);
        }
    }
}
