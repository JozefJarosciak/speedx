package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationStatusCodes;
import java.util.List;

public class zzl extends zzb {
    private final zzk adR;

    private static final class zza extends com.google.android.gms.location.internal.zzh.zza {
        private com.google.android.gms.internal.zzpr.zzb<Status> adS;

        public zza(com.google.android.gms.internal.zzpr.zzb<Status> zzb) {
            this.adS = zzb;
        }

        public void zza(int i, PendingIntent pendingIntent) {
            Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByPendingIntentResult");
        }

        public void zza(int i, String[] strArr) {
            if (this.adS == null) {
                Log.wtf("LocationClientImpl", "onAddGeofenceResult called multiple times");
                return;
            }
            this.adS.setResult(LocationStatusCodes.zztf(LocationStatusCodes.zzte(i)));
            this.adS = null;
        }

        public void zzb(int i, String[] strArr) {
            Log.wtf("LocationClientImpl", "Unexpected call to onRemoveGeofencesByRequestIdsResult");
        }
    }

    private static final class zzb extends com.google.android.gms.location.internal.zzh.zza {
        private com.google.android.gms.internal.zzpr.zzb<Status> adS;

        public zzb(com.google.android.gms.internal.zzpr.zzb<Status> zzb) {
            this.adS = zzb;
        }

        private void zzti(int i) {
            if (this.adS == null) {
                Log.wtf("LocationClientImpl", "onRemoveGeofencesResult called multiple times");
                return;
            }
            this.adS.setResult(LocationStatusCodes.zztf(LocationStatusCodes.zzte(i)));
            this.adS = null;
        }

        public void zza(int i, PendingIntent pendingIntent) {
            zzti(i);
        }

        public void zza(int i, String[] strArr) {
            Log.wtf("LocationClientImpl", "Unexpected call to onAddGeofencesResult");
        }

        public void zzb(int i, String[] strArr) {
            zzti(i);
        }
    }

    private static final class zzc extends com.google.android.gms.location.internal.zzj.zza {
        private com.google.android.gms.internal.zzpr.zzb<LocationSettingsResult> adS;

        public zzc(com.google.android.gms.internal.zzpr.zzb<LocationSettingsResult> zzb) {
            zzab.zzb(zzb != null, (Object) "listener can't be null.");
            this.adS = zzb;
        }

        public void zza(LocationSettingsResult locationSettingsResult) throws RemoteException {
            this.adS.setResult(locationSettingsResult);
            this.adS = null;
        }
    }

    public zzl(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str) {
        this(context, looper, connectionCallbacks, onConnectionFailedListener, str, zzg.zzcd(context));
    }

    public zzl(Context context, Looper looper, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, String str, zzg zzg) {
        super(context, looper, connectionCallbacks, onConnectionFailedListener, str, zzg);
        this.adR = new zzk(context, this.ady);
    }

    public void disconnect() {
        synchronized (this.adR) {
            if (isConnected()) {
                try {
                    this.adR.removeAllListeners();
                    this.adR.zzbnm();
                } catch (Throwable e) {
                    Log.e("LocationClientImpl", "Client disconnected before listeners could be cleaned up", e);
                }
            }
            super.disconnect();
        }
    }

    public Location getLastLocation() {
        return this.adR.getLastLocation();
    }

    public void zza(long j, PendingIntent pendingIntent) throws RemoteException {
        zzarv();
        zzab.zzaa(pendingIntent);
        zzab.zzb(j >= 0, (Object) "detectionIntervalMillis must be >= 0");
        ((zzi) zzarw()).zza(j, true, pendingIntent);
    }

    public void zza(PendingIntent pendingIntent, com.google.android.gms.internal.zzpr.zzb<Status> zzb) throws RemoteException {
        zzarv();
        zzab.zzb((Object) pendingIntent, (Object) "PendingIntent must be specified.");
        zzab.zzb((Object) zzb, (Object) "ResultHolder not provided.");
        ((zzi) zzarw()).zza(pendingIntent, new zzb(zzb), getContext().getPackageName());
    }

    public void zza(PendingIntent pendingIntent, zzg zzg) throws RemoteException {
        this.adR.zza(pendingIntent, zzg);
    }

    public void zza(GeofencingRequest geofencingRequest, PendingIntent pendingIntent, com.google.android.gms.internal.zzpr.zzb<Status> zzb) throws RemoteException {
        zzarv();
        zzab.zzb((Object) geofencingRequest, (Object) "geofencingRequest can't be null.");
        zzab.zzb((Object) pendingIntent, (Object) "PendingIntent must be specified.");
        zzab.zzb((Object) zzb, (Object) "ResultHolder not provided.");
        ((zzi) zzarw()).zza(geofencingRequest, pendingIntent, new zza(zzb));
    }

    public void zza(LocationCallback locationCallback, zzg zzg) throws RemoteException {
        this.adR.zza(locationCallback, zzg);
    }

    public void zza(LocationListener locationListener, zzg zzg) throws RemoteException {
        this.adR.zza(locationListener, zzg);
    }

    public void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzg zzg) throws RemoteException {
        this.adR.zza(locationRequest, pendingIntent, zzg);
    }

    public void zza(LocationRequest locationRequest, LocationListener locationListener, Looper looper, zzg zzg) throws RemoteException {
        synchronized (this.adR) {
            this.adR.zza(locationRequest, locationListener, looper, zzg);
        }
    }

    public void zza(LocationSettingsRequest locationSettingsRequest, com.google.android.gms.internal.zzpr.zzb<LocationSettingsResult> zzb, String str) throws RemoteException {
        boolean z = true;
        zzarv();
        zzab.zzb(locationSettingsRequest != null, (Object) "locationSettingsRequest can't be null nor empty.");
        if (zzb == null) {
            z = false;
        }
        zzab.zzb(z, (Object) "listener can't be null.");
        ((zzi) zzarw()).zza(locationSettingsRequest, new zzc(zzb), str);
    }

    public void zza(LocationRequestInternal locationRequestInternal, LocationCallback locationCallback, Looper looper, zzg zzg) throws RemoteException {
        synchronized (this.adR) {
            this.adR.zza(locationRequestInternal, locationCallback, looper, zzg);
        }
    }

    public void zza(zzg zzg) throws RemoteException {
        this.adR.zza(zzg);
    }

    public void zza(List<String> list, com.google.android.gms.internal.zzpr.zzb<Status> zzb) throws RemoteException {
        zzarv();
        boolean z = list != null && list.size() > 0;
        zzab.zzb(z, (Object) "geofenceRequestIds can't be null nor empty.");
        zzab.zzb((Object) zzb, (Object) "ResultHolder not provided.");
        ((zzi) zzarw()).zza((String[]) list.toArray(new String[0]), new zzb(zzb), getContext().getPackageName());
    }

    public void zzb(PendingIntent pendingIntent) throws RemoteException {
        zzarv();
        zzab.zzaa(pendingIntent);
        ((zzi) zzarw()).zzb(pendingIntent);
    }

    public LocationAvailability zzbnl() {
        return this.adR.zzbnl();
    }

    public void zzbx(boolean z) throws RemoteException {
        this.adR.zzbx(z);
    }

    public void zzc(Location location) throws RemoteException {
        this.adR.zzc(location);
    }
}
