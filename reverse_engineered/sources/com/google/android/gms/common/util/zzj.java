package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.os.SystemClock;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;

public final class zzj {
    private static IntentFilter AP = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    private static long AQ;
    private static float AR = Float.NaN;

    @TargetApi(20)
    public static boolean zzb(PowerManager powerManager) {
        return zzs.zzavr() ? powerManager.isInteractive() : powerManager.isScreenOn();
    }

    @TargetApi(20)
    public static int zzcm(Context context) {
        int i = 1;
        if (context == null || context.getApplicationContext() == null) {
            return -1;
        }
        Intent registerReceiver = context.getApplicationContext().registerReceiver(null, AP);
        int i2 = ((registerReceiver == null ? 0 : registerReceiver.getIntExtra("plugged", 0)) & 7) != 0 ? 1 : 0;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        if (powerManager == null) {
            return -1;
        }
        int i3 = (zzb(powerManager) ? 1 : 0) << 1;
        if (i2 == 0) {
            i = 0;
        }
        return i3 | i;
    }

    public static synchronized float zzcn(Context context) {
        float f;
        synchronized (zzj.class) {
            if (SystemClock.elapsedRealtime() - AQ >= ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD || Float.isNaN(AR)) {
                Intent registerReceiver = context.getApplicationContext().registerReceiver(null, AP);
                if (registerReceiver != null) {
                    AR = ((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1));
                }
                AQ = SystemClock.elapsedRealtime();
                f = AR;
            } else {
                f = AR;
            }
        }
        return f;
    }
}
