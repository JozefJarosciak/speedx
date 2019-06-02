package com.google.android.gms.common.util;

import android.os.SystemClock;

public final class zzh implements zze {
    private static zzh AK;

    public static synchronized zze zzavi() {
        zze zze;
        synchronized (zzh.class) {
            if (AK == null) {
                AK = new zzh();
            }
            zze = AK;
        }
        return zze;
    }

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public long nanoTime() {
        return System.nanoTime();
    }
}
