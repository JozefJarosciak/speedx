package com.google.android.gms.maps;

import android.app.Activity;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

class SupportStreetViewPanoramaFragment$zzb extends zza<SupportStreetViewPanoramaFragment$zza> {
    private final Fragment Md;
    private final List<OnStreetViewPanoramaReadyCallback> ahH = new ArrayList();
    protected zzf<SupportStreetViewPanoramaFragment$zza> ahm;
    private Activity mActivity;

    SupportStreetViewPanoramaFragment$zzb(Fragment fragment) {
        this.Md = fragment;
    }

    private void setActivity(Activity activity) {
        this.mActivity = activity;
        zzbpt();
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (zzbcr() != null) {
            ((SupportStreetViewPanoramaFragment$zza) zzbcr()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
        } else {
            this.ahH.add(onStreetViewPanoramaReadyCallback);
        }
    }

    protected void zza(zzf<SupportStreetViewPanoramaFragment$zza> zzf) {
        this.ahm = zzf;
        zzbpt();
    }

    public void zzbpt() {
        if (this.mActivity != null && this.ahm != null && zzbcr() == null) {
            try {
                MapsInitializer.initialize(this.mActivity);
                this.ahm.zza(new SupportStreetViewPanoramaFragment$zza(this.Md, zzae.zzdk(this.mActivity).zzah(zze.zzae(this.mActivity))));
                for (OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : this.ahH) {
                    ((SupportStreetViewPanoramaFragment$zza) zzbcr()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                }
                this.ahH.clear();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            } catch (GooglePlayServicesNotAvailableException e2) {
            }
        }
    }
}
