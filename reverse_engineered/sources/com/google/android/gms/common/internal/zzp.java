package com.google.android.gms.common.internal;

import android.util.Log;

public final class zzp {
    public static final int yO = (23 - " PII_LOG".length());
    private static final String yP = null;
    private final String yQ;
    private final String yR;

    public zzp(String str) {
        this(str, null);
    }

    public zzp(String str, String str2) {
        zzab.zzb((Object) str, (Object) "log tag cannot be null");
        zzab.zzb(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, Integer.valueOf(23));
        this.yQ = str;
        if (str2 == null || str2.length() <= 0) {
            this.yR = null;
        } else {
            this.yR = str2;
        }
    }

    private String zzhq(String str) {
        return this.yR == null ? str : this.yR.concat(str);
    }

    public void zzae(String str, String str2) {
        if (zzgc(3)) {
            Log.d(str, zzhq(str2));
        }
    }

    public void zzaf(String str, String str2) {
        if (zzgc(5)) {
            Log.w(str, zzhq(str2));
        }
    }

    public void zzag(String str, String str2) {
        if (zzgc(6)) {
            Log.e(str, zzhq(str2));
        }
    }

    public void zzb(String str, String str2, Throwable th) {
        if (zzgc(4)) {
            Log.i(str, zzhq(str2), th);
        }
    }

    public void zzc(String str, String str2, Throwable th) {
        if (zzgc(5)) {
            Log.w(str, zzhq(str2), th);
        }
    }

    public void zzd(String str, String str2, Throwable th) {
        if (zzgc(6)) {
            Log.e(str, zzhq(str2), th);
        }
    }

    public void zze(String str, String str2, Throwable th) {
        if (zzgc(7)) {
            Log.e(str, zzhq(str2), th);
            Log.wtf(str, zzhq(str2), th);
        }
    }

    public boolean zzgc(int i) {
        return Log.isLoggable(this.yQ, i);
    }
}
