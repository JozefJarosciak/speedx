package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.internal.StreetViewLifecycleDelegate;
import com.google.android.gms.maps.internal.zzab.zza;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.model.RuntimeRemoteException;

class SupportStreetViewPanoramaFragment$zza implements StreetViewLifecycleDelegate {
    private final Fragment Md;
    private final IStreetViewPanoramaFragmentDelegate ahE;

    public SupportStreetViewPanoramaFragment$zza(Fragment fragment, IStreetViewPanoramaFragmentDelegate iStreetViewPanoramaFragmentDelegate) {
        this.ahE = (IStreetViewPanoramaFragmentDelegate) zzab.zzaa(iStreetViewPanoramaFragmentDelegate);
        this.Md = (Fragment) zzab.zzaa(fragment);
    }

    public void getStreetViewPanoramaAsync(final OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        try {
            this.ahE.getStreetViewPanoramaAsync(new zza(this) {
                final /* synthetic */ SupportStreetViewPanoramaFragment$zza ahX;

                public void zza(IStreetViewPanoramaDelegate iStreetViewPanoramaDelegate) throws RemoteException {
                    onStreetViewPanoramaReadyCallback.onStreetViewPanoramaReady(new StreetViewPanorama(iStreetViewPanoramaDelegate));
                }
            });
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onCreate(Bundle bundle) {
        if (bundle == null) {
            try {
                bundle = new Bundle();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        Bundle arguments = this.Md.getArguments();
        if (arguments != null && arguments.containsKey("StreetViewPanoramaOptions")) {
            zzad.zza(bundle, "StreetViewPanoramaOptions", arguments.getParcelable("StreetViewPanoramaOptions"));
        }
        this.ahE.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        try {
            return (View) zze.zzad(this.ahE.onCreateView(zze.zzae(layoutInflater), zze.zzae(viewGroup), bundle));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onDestroy() {
        try {
            this.ahE.onDestroy();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onDestroyView() {
        try {
            this.ahE.onDestroyView();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        try {
            this.ahE.onInflate(zze.zzae(activity), null, bundle2);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onLowMemory() {
        try {
            this.ahE.onLowMemory();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onPause() {
        try {
            this.ahE.onPause();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onResume() {
        try {
            this.ahE.onResume();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        try {
            this.ahE.onSaveInstanceState(bundle);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void onStart() {
    }

    public void onStop() {
    }

    public IStreetViewPanoramaFragmentDelegate zzbpw() {
        return this.ahE;
    }
}
