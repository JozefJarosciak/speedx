package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4539k;

final class aj implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f15969a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f15970b;

    aj(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f15969a = context;
        this.f15970b = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f15969a == null) {
            StatServiceImpl.f15886q.error((Object) "The Context of StatService.onResume() can not be null!");
        } else {
            StatServiceImpl.trackBeginPage(this.f15969a, C4539k.m18066h(this.f15969a), this.f15970b);
        }
    }
}
