package com.tencent.wxop.stat;

import java.util.List;

class av implements Runnable {
    /* renamed from: a */
    final /* synthetic */ List f16008a;
    /* renamed from: b */
    final /* synthetic */ int f16009b;
    /* renamed from: c */
    final /* synthetic */ boolean f16010c;
    /* renamed from: d */
    final /* synthetic */ boolean f16011d;
    /* renamed from: e */
    final /* synthetic */ au f16012e;

    av(au auVar, List list, int i, boolean z, boolean z2) {
        this.f16012e = auVar;
        this.f16008a = list;
        this.f16009b = i;
        this.f16010c = z;
        this.f16011d = z2;
    }

    public void run() {
        this.f16012e.m17978a(this.f16008a, this.f16009b, this.f16010c);
        if (this.f16011d) {
            this.f16008a.clear();
        }
    }
}
