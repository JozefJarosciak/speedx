package com.google.android.gms.common;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

class GooglePlayServicesUtil$zza extends Handler {
    private final Context zzaqj;

    GooglePlayServicesUtil$zza(Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.zzaqj = context.getApplicationContext();
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.zzaqj);
                if (GooglePlayServicesUtil.isUserRecoverableError(isGooglePlayServicesAvailable)) {
                    GooglePlayServicesUtil.zzb(isGooglePlayServicesAvailable, this.zzaqj);
                    return;
                }
                return;
            default:
                Log.w("GooglePlayServicesUtil", "Don't know how to handle this message: " + message.what);
                return;
        }
    }
}
