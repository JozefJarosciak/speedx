package com.baidu.location.p043b;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.C1102f;

/* renamed from: com.baidu.location.b.e */
class C1080e extends Handler {
    /* renamed from: a */
    final /* synthetic */ C1079d f2625a;

    C1080e(C1079d c1079d) {
        this.f2625a = c1079d;
    }

    public void handleMessage(Message message) {
        if (C1102f.isServing) {
            switch (message.what) {
                case 1:
                    this.f2625a.m3925e((Location) message.obj);
                    return;
                case 2:
                    if (this.f2625a.f2610j != null) {
                        this.f2625a.f2610j.m3888a((String) message.obj);
                        return;
                    }
                    return;
                case 3:
                    this.f2625a.m3907a("&og=1", (Location) message.obj);
                    return;
                case 4:
                    this.f2625a.m3907a("&og=2", (Location) message.obj);
                    return;
                default:
                    return;
            }
        }
    }
}
