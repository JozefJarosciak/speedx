package com.tencent.wxop.stat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.tencent.wxop.stat.b */
class C4527b extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ C4525a f16025a;

    C4527b(C4525a c4525a) {
        this.f16025a = c4525a;
    }

    public void onReceive(Context context, Intent intent) {
        if (this.f16025a.f15946e != null) {
            this.f16025a.f15946e.m18024a(new C4528c(this));
        }
    }
}
