package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionApi;

public class zza implements ActivityRecognitionApi {

    private static abstract class zza extends com.google.android.gms.location.ActivityRecognition.zza<Status> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public Status zzb(Status status) {
            return status;
        }

        public /* synthetic */ Result zzc(Status status) {
            return zzb(status);
        }
    }

    public PendingResult<Status> removeActivityUpdates(GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zza adw;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zzb(pendingIntent);
                zzc(Status.sg);
            }
        });
    }

    public PendingResult<Status> requestActivityUpdates(GoogleApiClient googleApiClient, long j, PendingIntent pendingIntent) {
        final long j2 = j;
        final PendingIntent pendingIntent2 = pendingIntent;
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zza adw;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(j2, pendingIntent2);
                zzc(Status.sg);
            }
        });
    }
}
