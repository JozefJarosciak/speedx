package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;

public abstract class zzm {
    private static zzm yA;
    private static final Object yz = new Object();

    public static zzm zzce(Context context) {
        synchronized (yz) {
            if (yA == null) {
                yA = new zzn(context.getApplicationContext());
            }
        }
        return yA;
    }

    public abstract boolean zza(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract boolean zza(String str, String str2, ServiceConnection serviceConnection, String str3);

    public abstract void zzb(ComponentName componentName, ServiceConnection serviceConnection, String str);

    public abstract void zzb(String str, String str2, ServiceConnection serviceConnection, String str3);
}
