package com.baidu.platform.comapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.platform.comapi.util.C1281e;

/* renamed from: com.baidu.platform.comapi.d */
public class C1222d extends BroadcastReceiver {
    /* renamed from: a */
    public static final String f3616a = C1222d.class.getSimpleName();

    /* renamed from: a */
    public void m4574a(Context context) {
        String currentNetMode = NetworkUtil.getCurrentNetMode(context);
        if (!C1281e.m4856e().equals(currentNetMode)) {
            C1281e.m4847a(currentNetMode);
        }
    }

    public void onReceive(Context context, Intent intent) {
        m4574a(context);
        NetworkUtil.updateNetworkProxy(context);
    }
}
