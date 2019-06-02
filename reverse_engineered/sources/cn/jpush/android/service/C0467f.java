package cn.jpush.android.service;

import android.os.Handler;
import android.os.Message;
import cn.jpush.android.api.C0417m;

/* renamed from: cn.jpush.android.service.f */
final class C0467f extends Handler {
    /* renamed from: a */
    final /* synthetic */ DownloadService f848a;

    C0467f(DownloadService downloadService) {
        this.f848a = downloadService;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        C0417m.m1220b(this.f848a, message.what);
    }
}
