package com.google.android.gms.maps;

import android.app.Activity;
import android.app.Fragment;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

class StreetViewPanoramaFragment$zzb extends zza<StreetViewPanoramaFragment$zza> {
    private final Fragment Ma;
    private final List<OnStreetViewPanoramaReadyCallback> ahH = new ArrayList();
    protected zzf<StreetViewPanoramaFragment$zza> ahm;
    private Activity mActivity;

    StreetViewPanoramaFragment$zzb(Fragment fragment) {
        this.Ma = fragment;
    }

    private void setActivity(Activity activity) {
        this.mActivity = activity;
        zzbpt();
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (zzbcr() != null) {
            ((StreetViewPanoramaFragment$zza) zzbcr()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
        } else {
            this.ahH.add(onStreetViewPanoramaReadyCallback);
        }
    }

    protected void zza(zzf<StreetViewPanoramaFragment$zza> zzf) {
        this.ahm = zzf;
        zzbpt();
    }

    public void zzbpt() {
        if (this.mActivity != null && this.ahm != null && zzbcr() == null) {
            try {
                MapsInitializer.initialize(this.mActivity);
                this.ahm.zza(new StreetViewPanoramaFragment$zza(this.Ma, zzae.zzdk(this.mActivity).zzah(zze.zzae(this.mActivity))));
                for (OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : this.ahH) {
                    ((StreetViewPanoramaFragment$zza) zzbcr()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                }
                this.ahH.clear();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            } catch (GooglePlayServicesNotAvailableException e2) {
            }
        }
    }
}
