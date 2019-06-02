package com.google.android.gms.location.internal;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzd;
import com.google.android.gms.location.zze;
import java.util.HashMap;
import java.util.Map;

public class zzk {
    private Map<LocationListener, zzc> Py = new HashMap();
    private ContentProviderClient adL = null;
    private boolean adM = false;
    private Map<LocationCallback, zza> adN = new HashMap();
    private final zzp<zzi> ady;
    private final Context mContext;

    private static class zza extends com.google.android.gms.location.zzd.zza {
        private Handler adO;

        zza(final LocationCallback locationCallback, Looper looper) {
            if (looper == null) {
                looper = Looper.myLooper();
                zzab.zza(looper != null, (Object) "Can't create handler inside thread that has not called Looper.prepare()");
            }
            this.adO = new Handler(this, looper) {
                final /* synthetic */ zza adP;

                public void handleMessage(Message message) {
                    switch (message.what) {
                        case 0:
                            locationCallback.onLocationResult((LocationResult) message.obj);
                            return;
                        case 1:
                            locationCallback.onLocationAvailability((LocationAvailability) message.obj);
                            return;
                        default:
                            return;
                    }
                }
            };
        }

        private void zzb(int i, Object obj) {
            if (this.adO == null) {
                Log.e("LocationClientHelper", "Received a data in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = i;
            obtain.obj = obj;
            this.adO.sendMessage(obtain);
        }

        public void onLocationAvailability(LocationAvailability locationAvailability) {
            zzb(1, locationAvailability);
        }

        public void onLocationResult(LocationResult locationResult) {
            zzb(0, locationResult);
        }

        public void release() {
            this.adO = null;
        }
    }

    private static class zzb extends Handler {
        private final LocationListener adQ;

        public zzb(LocationListener locationListener) {
            this.adQ = locationListener;
        }

        public zzb(LocationListener locationListener, Looper looper) {
            super(looper);
            this.adQ = locationListener;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.adQ.onLocationChanged(new Location((Location) message.obj));
                    return;
                default:
                    Log.e("LocationClientHelper", "unknown message in LocationHandler.handleMessage");
                    return;
            }
        }
    }

    private static class zzc extends com.google.android.gms.location.zze.zza {
        private Handler adO;

        zzc(LocationListener locationListener, Looper looper) {
            if (looper == null) {
                zzab.zza(Looper.myLooper() != null, (Object) "Can't create handler inside thread that has not called Looper.prepare()");
            }
            this.adO = looper == null ? new zzb(locationListener) : new zzb(locationListener, looper);
        }

        public void onLocationChanged(Location location) {
            if (this.adO == null) {
                Log.e("LocationClientHelper", "Received a location in client after calling removeLocationUpdates.");
                return;
            }
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = location;
            this.adO.sendMessage(obtain);
        }

        public void release() {
            this.adO = null;
        }
    }

    public zzk(Context context, zzp<zzi> zzp) {
        this.mContext = context;
        this.ady = zzp;
    }

    private zza zza(LocationCallback locationCallback, Looper looper) {
        zza zza;
        synchronized (this.adN) {
            zza = (zza) this.adN.get(locationCallback);
            if (zza == null) {
                zza = new zza(locationCallback, looper);
            }
            this.adN.put(locationCallback, zza);
        }
        return zza;
    }

    private zzc zza(LocationListener locationListener, Looper looper) {
        zzc zzc;
        synchronized (this.Py) {
            zzc = (zzc) this.Py.get(locationListener);
            if (zzc == null) {
                zzc = new zzc(locationListener, looper);
            }
            this.Py.put(locationListener, zzc);
        }
        return zzc;
    }

    public Location getLastLocation() {
        this.ady.zzarv();
        try {
            return ((zzi) this.ady.zzarw()).zzkn(this.mContext.getPackageName());
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public void removeAllListeners() {
        try {
            synchronized (this.Py) {
                for (zze zze : this.Py.values()) {
                    if (zze != null) {
                        ((zzi) this.ady.zzarw()).zza(LocationRequestUpdateData.zza(zze, null));
                    }
                }
                this.Py.clear();
            }
            synchronized (this.adN) {
                for (zzd zzd : this.adN.values()) {
                    if (zzd != null) {
                        ((zzi) this.ady.zzarw()).zza(LocationRequestUpdateData.zza(zzd, null));
                    }
                }
                this.adN.clear();
            }
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public void zza(PendingIntent pendingIntent, zzg zzg) throws RemoteException {
        this.ady.zzarv();
        ((zzi) this.ady.zzarw()).zza(LocationRequestUpdateData.zzb(pendingIntent, zzg));
    }

    public void zza(LocationCallback locationCallback, zzg zzg) throws RemoteException {
        this.ady.zzarv();
        zzab.zzb((Object) locationCallback, (Object) "Invalid null callback");
        synchronized (this.adN) {
            zzd zzd = (zza) this.adN.remove(locationCallback);
            if (zzd != null) {
                zzd.release();
                ((zzi) this.ady.zzarw()).zza(LocationRequestUpdateData.zza(zzd, zzg));
            }
        }
    }

    public void zza(LocationListener locationListener, zzg zzg) throws RemoteException {
        this.ady.zzarv();
        zzab.zzb((Object) locationListener, (Object) "Invalid null listener");
        synchronized (this.Py) {
            zze zze = (zzc) this.Py.remove(locationListener);
            if (zze != null) {
                zze.release();
                ((zzi) this.ady.zzarw()).zza(LocationRequestUpdateData.zza(zze, zzg));
            }
        }
    }

    public void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzg zzg) throws RemoteException {
        this.ady.zzarv();
        ((zzi) this.ady.zzarw()).zza(LocationRequestUpdateData.zza(LocationRequestInternal.zzb(locationRequest), pendingIntent, zzg));
    }

    public void zza(LocationRequest locationRequest, LocationListener locationListener, Looper looper, zzg zzg) throws RemoteException {
        this.ady.zzarv();
        ((zzi) this.ady.zzarw()).zza(LocationRequestUpdateData.zza(LocationRequestInternal.zzb(locationRequest), zza(locationListener, looper), zzg));
    }

    public void zza(LocationRequestInternal locationRequestInternal, LocationCallback locationCallback, Looper looper, zzg zzg) throws RemoteException {
        this.ady.zzarv();
        ((zzi) this.ady.zzarw()).zza(LocationRequestUpdateData.zza(locationRequestInternal, zza(locationCallback, looper), zzg));
    }

    public void zza(zzg zzg) throws RemoteException {
        this.ady.zzarv();
        ((zzi) this.ady.zzarw()).zza(zzg);
    }

    public LocationAvailability zzbnl() {
        this.ady.zzarv();
        try {
            return ((zzi) this.ady.zzarw()).zzko(this.mContext.getPackageName());
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public void zzbnm() {
        if (this.adM) {
            try {
                zzbx(false);
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
    }

    public void zzbx(boolean z) throws RemoteException {
        this.ady.zzarv();
        ((zzi) this.ady.zzarw()).zzbx(z);
        this.adM = z;
    }

    public void zzc(Location location) throws RemoteException {
        this.ady.zzarv();
        ((zzi) this.ady.zzarw()).zzc(location);
    }
}
