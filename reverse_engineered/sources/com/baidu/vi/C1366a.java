package com.baidu.vi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.baidu.vi.a */
final class C1366a extends BroadcastReceiver {
    C1366a() {
    }

    public void onReceive(Context context, Intent intent) {
        VDeviceAPI.onNetworkStateChanged();
    }
}
