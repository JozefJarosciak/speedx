package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatExtras;
import android.util.TypedValue;
import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import com.google.android.gms.R$drawable;
import com.google.android.gms.R$string;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzi;
import com.google.android.gms.common.util.zzs;

public final class GooglePlayServicesUtil extends zze {
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";

    private GooglePlayServicesUtil() {
    }

    @Deprecated
    public static Dialog getErrorDialog(int i, Activity activity, int i2) {
        return getErrorDialog(i, activity, i2, null);
    }

    @Deprecated
    public static Dialog getErrorDialog(int i, Activity activity, int i2, OnCancelListener onCancelListener) {
        return zza(i, activity, zzi.zza(activity, GoogleApiAvailability.getInstance().zza((Context) activity, i, DateTokenConverter.CONVERTER_KEY), i2), onCancelListener);
    }

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zze.getErrorPendingIntent(i, context, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return zze.getErrorString(i);
    }

    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(Context context) {
        return zze.getOpenSourceSoftwareLicenseInfo(context);
    }

    public static Context getRemoteContext(Context context) {
        return zze.getRemoteContext(context);
    }

    public static Resources getRemoteResource(Context context) {
        return zze.getRemoteResource(context);
    }

    @Deprecated
    public static int isGooglePlayServicesAvailable(Context context) {
        return zze.isGooglePlayServicesAvailable(context);
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        return zze.isUserRecoverableError(i);
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int i, Activity activity, int i2) {
        return showErrorDialogFragment(i, activity, i2, null);
    }

    @Deprecated
    public static boolean showErrorDialogFragment(int i, Activity activity, int i2, OnCancelListener onCancelListener) {
        return showErrorDialogFragment(i, activity, null, i2, onCancelListener);
    }

    public static boolean showErrorDialogFragment(int i, Activity activity, Fragment fragment, int i2, OnCancelListener onCancelListener) {
        Intent zza = GoogleApiAvailability.getInstance().zza((Context) activity, i, DateTokenConverter.CONVERTER_KEY);
        Dialog zza2 = zza(i, activity, fragment == null ? zzi.zza(activity, zza, i2) : zzi.zza(fragment, zza, i2), onCancelListener);
        if (zza2 == null) {
            return false;
        }
        zza(activity, onCancelListener, GMS_ERROR_DIALOG, zza2);
        return true;
    }

    @Deprecated
    public static void showErrorNotification(int i, Context context) {
        if (com.google.android.gms.common.util.zzi.zzck(context) && i == 2) {
            i = 42;
        }
        if (zzc(context, i) || zzd(context, i)) {
            zzbr(context);
        } else {
            zza(i, context);
        }
    }

    @TargetApi(14)
    public static Dialog zza(int i, Activity activity, zzi zzi, OnCancelListener onCancelListener) {
        Builder builder = null;
        if (i == 0) {
            return null;
        }
        if (com.google.android.gms.common.util.zzi.zzck(activity) && i == 2) {
            i = 42;
        }
        if (zzc(activity, i)) {
            i = 18;
        }
        if (zzs.zzavm()) {
            TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                builder = new Builder(activity, 5);
            }
        }
        if (builder == null) {
            builder = new Builder(activity);
        }
        builder.setMessage(zzh.zzc(activity, i, zze.zzbv(activity)));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        CharSequence zzh = zzh.zzh(activity, i);
        if (zzh != null) {
            builder.setPositiveButton(zzh, zzi);
        }
        zzh = zzh.zzf(activity, i);
        if (zzh != null) {
            builder.setTitle(zzh);
        }
        return builder.create();
    }

    private static void zza(int i, Context context) {
        zza(i, context, null);
    }

    static void zza(int i, Context context, PendingIntent pendingIntent) {
        zza(i, context, null, pendingIntent);
    }

    private static void zza(int i, Context context, String str) {
        zza(i, context, str, GoogleApiAvailability.getInstance().zza(context, i, 0, "n"));
    }

    @TargetApi(20)
    private static void zza(int i, Context context, String str, PendingIntent pendingIntent) {
        Notification build;
        int i2;
        Resources resources = context.getResources();
        String zzbv = zze.zzbv(context);
        CharSequence zzg = zzh.zzg(context, i);
        if (zzg == null) {
            zzg = resources.getString(R$string.common_google_play_services_notification_ticker);
        }
        CharSequence zzd = zzh.zzd(context, i, zzbv);
        if (com.google.android.gms.common.util.zzi.zzck(context)) {
            zzab.zzbm(zzs.zzavn());
            build = new Notification.Builder(context).setSmallIcon(R$drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle(new BigTextStyle().bigText(new StringBuilder((String.valueOf(zzg).length() + 1) + String.valueOf(zzd).length()).append(zzg).append(" ").append(zzd).toString())).addAction(R$drawable.common_full_open_on_phone, resources.getString(R$string.common_open_on_phone), pendingIntent).build();
        } else {
            CharSequence string = resources.getString(R$string.common_google_play_services_notification_ticker);
            if (zzs.zzavj()) {
                Notification build2;
                Notification.Builder autoCancel = new Notification.Builder(context).setSmallIcon(17301642).setContentTitle(zzg).setContentText(zzd).setContentIntent(pendingIntent).setTicker(string).setAutoCancel(true);
                if (zzs.zzavr()) {
                    autoCancel.setLocalOnly(true);
                }
                if (zzs.zzavn()) {
                    autoCancel.setStyle(new BigTextStyle().bigText(zzd));
                    build2 = autoCancel.build();
                } else {
                    build2 = autoCancel.getNotification();
                }
                if (VERSION.SDK_INT == 19) {
                    build2.extras.putBoolean(NotificationCompatExtras.EXTRA_LOCAL_ONLY, true);
                }
                build = build2;
            } else {
                build = new NotificationCompat.Builder(context).setSmallIcon(17301642).setTicker(string).setWhen(System.currentTimeMillis()).setAutoCancel(true).setContentIntent(pendingIntent).setContentTitle(zzg).setContentText(zzd).build();
            }
        }
        if (zze.zzfc(i)) {
            rl.set(false);
            i2 = 10436;
        } else {
            i2 = 39789;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        if (str != null) {
            notificationManager.notify(str, i2, build);
        } else {
            notificationManager.notify(i2, build);
        }
    }

    @TargetApi(11)
    public static void zza(Activity activity, OnCancelListener onCancelListener, String str, @NonNull Dialog dialog) {
        boolean z;
        try {
            z = activity instanceof FragmentActivity;
        } catch (NoClassDefFoundError e) {
            z = false;
        }
        if (z) {
            SupportErrorDialogFragment.newInstance(dialog, onCancelListener).show(((FragmentActivity) activity).getSupportFragmentManager(), str);
        } else if (zzs.zzavj()) {
            ErrorDialogFragment.newInstance(dialog, onCancelListener).show(activity.getFragmentManager(), str);
        } else {
            throw new RuntimeException("This Activity does not support Fragments.");
        }
    }

    private static void zzbr(Context context) {
        Handler googlePlayServicesUtil$zza = new GooglePlayServicesUtil$zza(context);
        googlePlayServicesUtil$zza.sendMessageDelayed(googlePlayServicesUtil$zza.obtainMessage(1), 120000);
    }

    @Deprecated
    public static boolean zzc(Context context, int i) {
        return zze.zzc(context, i);
    }

    @Deprecated
    public static boolean zzd(Context context, int i) {
        return zze.zzd(context, i);
    }

    @Deprecated
    public static Intent zzfb(int i) {
        return zze.zzfb(i);
    }
}
