package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4539k;

/* renamed from: com.tencent.wxop.stat.m */
final class C4555m implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f16138a;
    /* renamed from: b */
    final /* synthetic */ StatSpecifyReportedInfo f16139b;

    C4555m(Context context, StatSpecifyReportedInfo statSpecifyReportedInfo) {
        this.f16138a = context;
        this.f16139b = statSpecifyReportedInfo;
    }

    public final void run() {
        if (this.f16138a == null) {
            StatServiceImpl.f15886q.error((Object) "The Context of StatService.onPause() can not be null!");
        } else {
            StatServiceImpl.trackEndPage(this.f16138a, C4539k.m18066h(this.f16138a), this.f16139b);
        }
    }
}
