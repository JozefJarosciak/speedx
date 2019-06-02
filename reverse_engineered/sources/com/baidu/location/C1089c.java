package com.baidu.location;

import com.baidu.location.p041a.C1044b;

/* renamed from: com.baidu.location.c */
class C1089c extends Thread {
    /* renamed from: a */
    final /* synthetic */ LocationClient f2658a;

    C1089c(LocationClient locationClient) {
        this.f2658a = locationClient;
    }

    public void run() {
        if (this.f2658a.mloc == null) {
            this.f2658a.mloc = new C1044b(this.f2658a.mContext, this.f2658a.mOption, this.f2658a);
        }
        this.f2658a.mloc.m3689c();
    }
}
