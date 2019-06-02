package cn.jpush.android.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.service.c */
final class C0464c extends Handler {
    /* renamed from: a */
    final /* synthetic */ C0463b f842a;
    /* renamed from: b */
    private C0465d f843b = null;

    public C0464c(C0463b c0463b, Looper looper, C0465d c0465d) {
        this.f842a = c0463b;
        super(looper);
        this.f843b = c0465d;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        if (this.f842a.f837a) {
            ac.m1576a();
            return;
        }
        if (this.f843b != null) {
            this.f843b.mo2222a(this.f842a.f839d, this.f842a.f840e);
        }
        this.f842a.f838c.sendEmptyMessageDelayed(0, 2000);
    }
}
