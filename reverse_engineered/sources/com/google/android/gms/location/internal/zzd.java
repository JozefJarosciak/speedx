package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.location.Location;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class zzd implements FusedLocationProviderApi {

    private static abstract class zza extends com.google.android.gms.location.LocationServices.zza<Status> {
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

    private static class zzb extends com.google.android.gms.location.internal.zzg.zza {
        private final com.google.android.gms.internal.zzpr.zzb<Status> zj;

        public zzb(com.google.android.gms.internal.zzpr.zzb<Status> zzb) {
            this.zj = zzb;
        }

        public void zza(FusedLocationProviderResult fusedLocationProviderResult) {
            this.zj.setResult(fusedLocationProviderResult.getStatus());
        }
    }

    public PendingResult<Status> flushLocations(GoogleApiClient googleApiClient) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(new zzb(this));
            }
        });
    }

    public Location getLastLocation(GoogleApiClient googleApiClient) {
        try {
            return LocationServices.zzj(googleApiClient).getLastLocation();
        } catch (Exception e) {
            return null;
        }
    }

    public LocationAvailability getLocationAvailability(GoogleApiClient googleApiClient) {
        try {
            return LocationServices.zzj(googleApiClient).zzbnl();
        } catch (Exception e) {
            return null;
        }
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(pendingIntent, new zzb(this));
            }
        });
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, final LocationCallback locationCallback) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(locationCallback, new zzb(this));
            }
        });
    }

    public PendingResult<Status> removeLocationUpdates(GoogleApiClient googleApiClient, final LocationListener locationListener) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(locationListener, new zzb(this));
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, final LocationRequest locationRequest, final PendingIntent pendingIntent) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(locationRequest, pendingIntent, new zzb(this));
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationCallback locationCallback, Looper looper) {
        final LocationRequest locationRequest2 = locationRequest;
        final LocationCallback locationCallback2 = locationCallback;
        final Looper looper2 = looper;
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(LocationRequestInternal.zzb(locationRequest2), locationCallback2, looper2, new zzb(this));
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, final LocationRequest locationRequest, final LocationListener locationListener) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(locationRequest, locationListener, null, new zzb(this));
            }
        });
    }

    public PendingResult<Status> requestLocationUpdates(GoogleApiClient googleApiClient, LocationRequest locationRequest, LocationListener locationListener, Looper looper) {
        final LocationRequest locationRequest2 = locationRequest;
        final LocationListener locationListener2 = locationListener;
        final Looper looper2 = looper;
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zza(locationRequest2, locationListener2, looper2, new zzb(this));
            }
        });
    }

    public PendingResult<Status> setMockLocation(GoogleApiClient googleApiClient, final Location location) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zzc(location);
                zzc(Status.sg);
            }
        });
    }

    public PendingResult<Status> setMockMode(GoogleApiClient googleApiClient, final boolean z) {
        return googleApiClient.zzd(new zza(this, googleApiClient) {
            final /* synthetic */ zzd adC;

            protected void zza(zzl zzl) throws RemoteException {
                zzl.zzbx(z);
                zzc(Status.sg);
            }
        });
    }
}
