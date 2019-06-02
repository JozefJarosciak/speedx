package com.tencent.wxop.stat;

import java.util.List;

class aw implements Runnable {
    /* renamed from: a */
    final /* synthetic */ List f16013a;
    /* renamed from: b */
    final /* synthetic */ boolean f16014b;
    /* renamed from: c */
    final /* synthetic */ boolean f16015c;
    /* renamed from: d */
    final /* synthetic */ au f16016d;

    aw(au auVar, List list, boolean z, boolean z2) {
        this.f16016d = auVar;
        this.f16013a = list;
        this.f16014b = z;
        this.f16015c = z2;
    }

    public void run() {
        this.f16016d.m17979a(this.f16013a, this.f16014b);
        if (this.f16015c) {
            this.f16013a.clear();
        }
    }
}
