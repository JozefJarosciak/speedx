package cn.jpush.android.ui;

import android.os.Handler;
import android.os.Message;
import cn.jpush.android.data.C0429c;

/* renamed from: cn.jpush.android.ui.f */
final class C0487f extends Handler {
    /* renamed from: a */
    final /* synthetic */ PushActivity f923a;

    C0487f(PushActivity pushActivity) {
        this.f923a = pushActivity;
    }

    public final void handleMessage(Message message) {
        C0429c c0429c = (C0429c) message.obj;
        switch (message.what) {
            case 1:
                this.f923a.setRequestedOrientation(1);
                PushActivity.m1542a(this.f923a, c0429c);
                return;
            case 2:
                this.f923a.m1545b();
                return;
            default:
                return;
        }
    }
}
