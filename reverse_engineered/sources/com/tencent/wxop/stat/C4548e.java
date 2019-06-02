package com.tencent.wxop.stat;

import com.tencent.wxop.stat.common.C4539k;
import java.util.TimerTask;

/* renamed from: com.tencent.wxop.stat.e */
class C4548e extends TimerTask {
    /* renamed from: a */
    final /* synthetic */ C4547d f16121a;

    C4548e(C4547d c4547d) {
        this.f16121a = c4547d;
    }

    public void run() {
        if (StatConfig.isDebugEnable()) {
            C4539k.m18052b().m18012i("TimerTask run");
        }
        StatServiceImpl.m17893e(this.f16121a.f16120c);
        cancel();
        this.f16121a.m18111a();
    }
}
