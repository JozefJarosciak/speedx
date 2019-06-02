package com.baidu.platform.comapi.map;

import android.os.Handler;
import android.os.Message;

/* renamed from: com.baidu.platform.comapi.map.v */
class C1265v extends Handler {
    /* renamed from: a */
    final /* synthetic */ C1264u f3828a;

    C1265v(C1264u c1264u) {
        this.f3828a = c1264u;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (C1264u.f3824c != null) {
            this.f3828a.f3826d.m4803a(message);
        }
    }
}
