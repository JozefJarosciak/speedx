package com.google.android.gms.maps;

import android.content.Context;
import android.os.RemoteException;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

class StreetViewPanoramaView$zzb extends zza<StreetViewPanoramaView$zza> {
    private final List<OnStreetViewPanoramaReadyCallback> ahH = new ArrayList();
    private final StreetViewPanoramaOptions ahT;
    protected zzf<StreetViewPanoramaView$zza> ahm;
    private final ViewGroup aht;
    private final Context mContext;

    StreetViewPanoramaView$zzb(ViewGroup viewGroup, Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        this.aht = viewGroup;
        this.mContext = context;
        this.ahT = streetViewPanoramaOptions;
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        if (zzbcr() != null) {
            ((StreetViewPanoramaView$zza) zzbcr()).getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
        } else {
            this.ahH.add(onStreetViewPanoramaReadyCallback);
        }
    }

    protected void zza(zzf<StreetViewPanoramaView$zza> zzf) {
        this.ahm = zzf;
        zzbpt();
    }

    public void zzbpt() {
        if (this.ahm != null && zzbcr() == null) {
            try {
                this.ahm.zza(new StreetViewPanoramaView$zza(this.aht, zzae.zzdk(this.mContext).zza(zze.zzae(this.mContext), this.ahT)));
                for (OnStreetViewPanoramaReadyCallback streetViewPanoramaAsync : this.ahH) {
                    ((StreetViewPanoramaView$zza) zzbcr()).getStreetViewPanoramaAsync(streetViewPanoramaAsync);
                }
                this.ahH.clear();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            } catch (GooglePlayServicesNotAvailableException e2) {
            }
        }
    }
}
