package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: com.baidu.lbsapi.auth.h */
class C1027h extends Handler {
    /* renamed from: a */
    final /* synthetic */ LBSAuthManager f2287a;

    C1027h(LBSAuthManager lBSAuthManager, Looper looper) {
        this.f2287a = lBSAuthManager;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (C1017a.f2273a) {
            C1017a.m3589a("handleMessage !!");
        }
        LBSAuthManagerListener lBSAuthManagerListener = (LBSAuthManagerListener) LBSAuthManager.f2268f.get(message.getData().getString("listenerKey"));
        if (C1017a.f2273a) {
            C1017a.m3589a("handleMessage listener = " + lBSAuthManagerListener);
        }
        if (lBSAuthManagerListener != null) {
            lBSAuthManagerListener.onAuthResult(message.what, message.obj.toString());
        }
    }
}
