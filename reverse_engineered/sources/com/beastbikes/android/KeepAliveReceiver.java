package com.beastbikes.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeepAliveReceiver extends BroadcastReceiver {
    /* renamed from: a */
    private static final Logger f4001a = LoggerFactory.getLogger(KeepAliveReceiver.class);
    /* renamed from: b */
    private long f4002b = 0;

    public void onReceive(Context context, Intent intent) {
        f4001a.error("KeepAliveReceiver: Action = " + intent.getAction());
        if (System.currentTimeMillis() - this.f4002b >= ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
            this.f4002b = System.currentTimeMillis();
            if (!TextUtils.isEmpty(C1398a.m5854a(context))) {
                f4001a.info("Action = " + intent.getAction());
                Intent intent2 = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
                intent2.setPackage(context.getPackageName());
                context.startService(intent2);
            }
        }
    }
}
