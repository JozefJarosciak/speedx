package com.google.android.gms.common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzo;
import com.google.android.gms.common.util.zzi;
import org.apache.commons.cli.HelpFormatter;

public class zzc {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final zzc qV = new zzc();

    zzc() {
    }

    public static zzc zzand() {
        return qV;
    }

    private String zzn(@Nullable Context context, @Nullable String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("gcore_");
        stringBuilder.append(GOOGLE_PLAY_SERVICES_VERSION_CODE);
        stringBuilder.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        stringBuilder.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        if (context != null) {
            stringBuilder.append(context.getPackageName());
        }
        stringBuilder.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        if (context != null) {
            try {
                stringBuilder.append(context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
            } catch (NameNotFoundException e) {
            }
        }
        return stringBuilder.toString();
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return zza(context, i, i2, null);
    }

    public String getErrorString(int i) {
        return zze.getErrorString(i);
    }

    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return zze.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        int isGooglePlayServicesAvailable = zze.isGooglePlayServicesAvailable(context);
        return zze.zzc(context, isGooglePlayServicesAvailable) ? 18 : isGooglePlayServicesAvailable;
    }

    public boolean isUserResolvableError(int i) {
        return zze.isUserRecoverableError(i);
    }

    @Nullable
    public PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        if (zzi.zzck(context) && i == 2) {
            i = 42;
        }
        Intent zza = zza(context, i, str);
        return zza == null ? null : PendingIntent.getActivity(context, i2, zza, 268435456);
    }

    @Nullable
    public Intent zza(Context context, int i, @Nullable String str) {
        switch (i) {
            case 1:
            case 2:
                return zzo.zzad("com.google.android.gms", zzn(context, str));
            case 3:
                return zzo.zzhp("com.google.android.gms");
            case 42:
                return zzo.zzasw();
            default:
                return null;
        }
    }

    public int zzbn(Context context) {
        return zze.zzbn(context);
    }

    public void zzbo(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        zze.zzbb(context);
    }

    public void zzbp(Context context) {
        zze.zzbp(context);
    }

    public boolean zzc(Context context, int i) {
        return zze.zzc(context, i);
    }

    @Nullable
    @Deprecated
    public Intent zzfa(int i) {
        return zza(null, i, null);
    }

    public boolean zzm(Context context, String str) {
        return zze.zzm(context, str);
    }
}
