package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import com.google.android.gms.R$string;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.internal.zzqj;
import com.google.android.gms.internal.zzqj.zza;
import com.google.android.gms.internal.zzqp;
import com.google.android.gms.internal.zzqu;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.umeng.onlineconfig.OnlineConfigAgent;

public class GoogleApiAvailability extends zzc {
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzc.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    private static final GoogleApiAvailability qU = new GoogleApiAvailability();

    GoogleApiAvailability() {
    }

    public static GoogleApiAvailability getInstance() {
        return qU;
    }

    public Dialog getErrorDialog(Activity activity, int i, int i2) {
        return GooglePlayServicesUtil.getErrorDialog(i, activity, i2);
    }

    public Dialog getErrorDialog(Activity activity, int i, int i2, OnCancelListener onCancelListener) {
        return GooglePlayServicesUtil.getErrorDialog(i, activity, i2, onCancelListener);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, int i, int i2) {
        return super.getErrorResolutionPendingIntent(context, i, i2);
    }

    @Nullable
    public PendingIntent getErrorResolutionPendingIntent(Context context, ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            return connectionResult.getResolution();
        }
        int errorCode = connectionResult.getErrorCode();
        if (zzi.zzck(context) && errorCode == 2) {
            errorCode = 42;
        }
        return getErrorResolutionPendingIntent(context, errorCode, 0);
    }

    public final String getErrorString(int i) {
        return super.getErrorString(i);
    }

    @Nullable
    public String getOpenSourceSoftwareLicenseInfo(Context context) {
        return super.getOpenSourceSoftwareLicenseInfo(context);
    }

    public int isGooglePlayServicesAvailable(Context context) {
        return super.isGooglePlayServicesAvailable(context);
    }

    public final boolean isUserResolvableError(int i) {
        return super.isUserResolvableError(i);
    }

    @MainThread
    public Task<Void> makeGooglePlayServicesAvailable(Activity activity) {
        zzab.zzhj("makeGooglePlayServicesAvailable must be called from the main thread");
        int isGooglePlayServicesAvailable = isGooglePlayServicesAvailable(activity);
        if (isGooglePlayServicesAvailable == 0) {
            return Tasks.forResult(null);
        }
        zzqu zzu = zzqu.zzu(activity);
        zzu.zzk(new ConnectionResult(isGooglePlayServicesAvailable, null));
        return zzu.getTask();
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2) {
        return GooglePlayServicesUtil.showErrorDialogFragment(i, activity, i2);
    }

    public boolean showErrorDialogFragment(Activity activity, int i, int i2, OnCancelListener onCancelListener) {
        return GooglePlayServicesUtil.showErrorDialogFragment(i, activity, i2, onCancelListener);
    }

    public void showErrorNotification(Context context, int i) {
        if (i == 6) {
            Log.e("GoogleApiAvailability", "showErrorNotification(context, errorCode) is called for RESOLUTION_REQUIRED when showErrorNotification(context, result) should be called");
        }
        if (isUserResolvableError(i)) {
            GooglePlayServicesUtil.showErrorNotification(i, context);
        }
    }

    public void showErrorNotification(Context context, ConnectionResult connectionResult) {
        PendingIntent errorResolutionPendingIntent = getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent != null) {
            GooglePlayServicesUtil.zza(connectionResult.getErrorCode(), context, errorResolutionPendingIntent);
        }
    }

    public Dialog zza(Activity activity, OnCancelListener onCancelListener) {
        View progressBar = new ProgressBar(activity, null, 16842874);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        Builder builder = new Builder(activity);
        builder.setView(progressBar);
        String zzbv = zze.zzbv(activity);
        builder.setMessage(activity.getResources().getString(R$string.common_google_play_services_updating_text, new Object[]{zzbv}));
        builder.setTitle(R$string.common_google_play_services_updating_title);
        builder.setPositiveButton("", null);
        Dialog create = builder.create();
        GooglePlayServicesUtil.zza(activity, onCancelListener, "GooglePlayServicesUpdatingDialog", create);
        return create;
    }

    @Nullable
    public PendingIntent zza(Context context, int i, int i2, @Nullable String str) {
        return super.zza(context, i, i2, str);
    }

    @Nullable
    public Intent zza(Context context, int i, @Nullable String str) {
        return super.zza(context, i, str);
    }

    @Nullable
    public zzqj zza(Context context, zza zza) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme(OnlineConfigAgent.KEY_PACKAGE);
        BroadcastReceiver zzqj = new zzqj(zza);
        context.registerReceiver(zzqj, intentFilter);
        zzqj.setContext(context);
        if (zzm(context, "com.google.android.gms")) {
            return zzqj;
        }
        zza.zzaor();
        zzqj.unregister();
        return null;
    }

    public void zza(Context context, ConnectionResult connectionResult, int i) {
        PendingIntent errorResolutionPendingIntent = getErrorResolutionPendingIntent(context, connectionResult);
        if (errorResolutionPendingIntent != null) {
            GooglePlayServicesUtil.zza(connectionResult.getErrorCode(), context, GoogleApiActivity.zza(context, errorResolutionPendingIntent, i));
        }
    }

    public boolean zza(Activity activity, @NonNull zzqp zzqp, int i, int i2, OnCancelListener onCancelListener) {
        Dialog zza = GooglePlayServicesUtil.zza(i, activity, com.google.android.gms.common.internal.zzi.zza(zzqp, zza((Context) activity, i, DateTokenConverter.CONVERTER_KEY), i2), onCancelListener);
        if (zza == null) {
            return false;
        }
        GooglePlayServicesUtil.zza(activity, onCancelListener, GooglePlayServicesUtil.GMS_ERROR_DIALOG, zza);
        return true;
    }

    public int zzbn(Context context) {
        return super.zzbn(context);
    }

    public boolean zzc(Context context, int i) {
        return super.zzc(context, i);
    }

    @Nullable
    @Deprecated
    public Intent zzfa(int i) {
        return super.zzfa(i);
    }
}
