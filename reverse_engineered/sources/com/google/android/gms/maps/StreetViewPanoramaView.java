package com.google.android.gms.maps;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zza;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class StreetViewPanoramaView extends FrameLayout {
    private StreetViewPanorama ahD;
    private final StreetViewPanoramaView$zzb ahP;

    public StreetViewPanoramaView(Context context) {
        super(context);
        this.ahP = new StreetViewPanoramaView$zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ahP = new StreetViewPanoramaView$zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ahP = new StreetViewPanoramaView$zzb(this, context, null);
    }

    public StreetViewPanoramaView(Context context, StreetViewPanoramaOptions streetViewPanoramaOptions) {
        super(context);
        this.ahP = new StreetViewPanoramaView$zzb(this, context, streetViewPanoramaOptions);
    }

    @Deprecated
    public final StreetViewPanorama getStreetViewPanorama() {
        if (this.ahD != null) {
            return this.ahD;
        }
        this.ahP.zzbpt();
        if (this.ahP.zzbcr() == null) {
            return null;
        }
        try {
            this.ahD = new StreetViewPanorama(((StreetViewPanoramaView$zza) this.ahP.zzbcr()).zzbqa().getStreetViewPanorama());
            return this.ahD;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        zzab.zzhj("getStreetViewPanoramaAsync() must be called on the main thread");
        this.ahP.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }

    public final void onCreate(Bundle bundle) {
        this.ahP.onCreate(bundle);
        if (this.ahP.zzbcr() == null) {
            zza.zzb(this);
        }
    }

    public final void onDestroy() {
        this.ahP.onDestroy();
    }

    public final void onLowMemory() {
        this.ahP.onLowMemory();
    }

    public final void onPause() {
        this.ahP.onPause();
    }

    public final void onResume() {
        this.ahP.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.ahP.onSaveInstanceState(bundle);
    }
}
