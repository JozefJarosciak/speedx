package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.zzru;

public class zzd {
    public static int zza(PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return -1;
        }
        Bundle bundle = packageInfo.applicationInfo.metaData;
        return bundle != null ? bundle.getInt("com.google.android.gms.version", -1) : -1;
    }

    public static boolean zzabc() {
        return false;
    }

    public static int zzo(Context context, String str) {
        return zza(zzp(context, str));
    }

    @Nullable
    public static PackageInfo zzp(Context context, String str) {
        try {
            return zzru.zzcq(context).getPackageInfo(str, 128);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    @TargetApi(12)
    public static boolean zzq(Context context, String str) {
        if (!zzs.zzavk()) {
            return false;
        }
        if ("com.google.android.gms".equals(str) && zzabc()) {
            return false;
        }
        try {
            return (zzru.zzcq(context).getApplicationInfo(str, 0).flags & 2097152) != 0;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
