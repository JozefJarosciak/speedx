package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4539k;

/* renamed from: com.tencent.wxop.stat.n */
final class C4556n implements Runnable {
    /* renamed from: a */
    final /* synthetic */ Context f16140a;

    C4556n(Context context) {
        this.f16140a = context;
    }

    public final void run() {
        if (this.f16140a == null) {
            StatServiceImpl.f15886q.error((Object) "The Context of StatService.onStop() can not be null!");
            return;
        }
        StatServiceImpl.flushDataToDB(this.f16140a);
        if (!StatServiceImpl.m17881a()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (C4539k.m18037B(this.f16140a)) {
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f15886q.m18012i("onStop isBackgroundRunning flushDataToDB");
                }
                StatServiceImpl.commitEvents(this.f16140a, -1);
            }
        }
    }
}
