package com.beastbikes.android.modules.cycling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.beastbikes.framework.android.p088g.C2799c;

final class SyncService$a extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ SyncService f8444a;

    private SyncService$a(SyncService syncService) {
        this.f8444a = syncService;
    }

    public void onReceive(Context context, Intent intent) {
        if (!TextUtils.isEmpty(intent.getAction())) {
            switch (C2799c.m13754b(context)) {
                case 1:
                case 9:
                    new Thread(SyncService.c(this.f8444a)).start();
                    return;
                default:
                    return;
            }
        }
    }
}
