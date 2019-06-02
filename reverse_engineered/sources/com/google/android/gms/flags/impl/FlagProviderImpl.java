package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.flags.impl.zza.zzb;
import com.google.android.gms.flags.impl.zza.zzc;
import com.google.android.gms.flags.impl.zza.zzd;
import com.google.android.gms.internal.zzug.zza;

@DynamiteApi
public class FlagProviderImpl extends zza {
    private boolean zzamr = false;
    private SharedPreferences zzaxs;

    public boolean getBooleanFlagValue(String str, boolean z, int i) {
        return !this.zzamr ? z : zza.zza.zza(this.zzaxs, str, Boolean.valueOf(z)).booleanValue();
    }

    public int getIntFlagValue(String str, int i, int i2) {
        return !this.zzamr ? i : zzb.zza(this.zzaxs, str, Integer.valueOf(i)).intValue();
    }

    public long getLongFlagValue(String str, long j, int i) {
        return !this.zzamr ? j : zzc.zza(this.zzaxs, str, Long.valueOf(j)).longValue();
    }

    public String getStringFlagValue(String str, String str2, int i) {
        return !this.zzamr ? str2 : zzd.zza(this.zzaxs, str, str2);
    }

    public void init(com.google.android.gms.dynamic.zzd zzd) {
        Context context = (Context) zze.zzad(zzd);
        if (!this.zzamr) {
            try {
                this.zzaxs = zzb.zzn(context.createPackageContext("com.google.android.gms", 0));
                this.zzamr = true;
            } catch (NameNotFoundException e) {
            }
        }
    }
}
