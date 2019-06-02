package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaViewDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzab.zza;
import com.google.android.gms.maps.model.RuntimeRemoteException;

class StreetViewPanoramaView$zza implements StreetViewLifecycleDelegate {
    private final IStreetViewPanoramaViewDelegate ahQ;
    private View ahR;
    private final ViewGroup ahp;

    public StreetViewPanoramaView$zza(ViewGroup viewGroup, IStreetViewPanoramaViewDelegate iStreetViewPanoramaViewDelegate) {
        this.ahQ = (IStreetViewPanoramaViewDelegate) zzab.zzaa(iStreetViewPanoramaViewDelegate);
        this.ahp = (ViewGroup) zzab.zzaa(viewGroup);
    }

    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        try {
            this.ahQ.getStreetViewPanoramaAsync(new zza(this) {
                final /* synthetic */ StreetViewPanoramaView$zza ahS;

                public void zza(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
                    onStreetViewPanoramaReadyCallback.onStreetViewPanoramaReady(new StreetViewPanorama(iStreetViewPanoramaDelegate));
                }
            });
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onCreate(Bundle bundle) {
        try {
            this.ahQ.onCreate(bundle);
            this.ahR = (View) zze.zzad(this.ahQ.getView());
            this.ahp.removeAllViews();
            this.ahp.addView(this.ahR);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        throw new UnsupportedOperationException("onCreateView not allowed on StreetViewPanoramaViewDelegate");
    }

    public void onDestroy() {
        try {
            this.ahQ.onDestroy();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onDestroyView() {
        throw new UnsupportedOperationException("onDestroyView not allowed on StreetViewPanoramaViewDelegate");
    }

    public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        throw new UnsupportedOperationException("onInflate not allowed on StreetViewPanoramaViewDelegate");
    }

    public void onLowMemory() {
        try {
            this.ahQ.onLowMemory();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onPause() {
        try {
            this.ahQ.onPause();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onResume() {
        try {
            this.ahQ.onResume();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        try {
            this.ahQ.onSaveInstanceState(bundle);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public IStreetViewPanoramaViewDelegate zzbqa() {
        return this.ahQ;
    }
}
