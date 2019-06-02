package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

@TargetApi(11)
public class StreetViewPanoramaFragment extends Fragment {
    private final StreetViewPanoramaFragment$zzb ahC = new StreetViewPanoramaFragment$zzb(this);
    private StreetViewPanorama ahD;

    public static StreetViewPanoramaFragment newInstance() {
        return new StreetViewPanoramaFragment();
    }

    public static StreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        StreetViewPanoramaFragment streetViewPanoramaFragment = new StreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        streetViewPanoramaFragment.setArguments(bundle);
        return streetViewPanoramaFragment;
    }

    @Deprecated
    public final StreetViewPanorama getStreetViewPanorama() {
        IStreetViewPanoramaFragmentDelegate zzbpw = zzbpw();
        if (zzbpw == null) {
            return null;
        }
        try {
            IStreetViewPanoramaDelegate streetViewPanorama = zzbpw.getStreetViewPanorama();
            if (streetViewPanorama == null) {
                return null;
            }
            if (this.ahD == null || this.ahD.zzbpv().asBinder() != streetViewPanorama.asBinder()) {
                this.ahD = new StreetViewPanorama(streetViewPanorama);
            }
            return this.ahD;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getStreetViewPanoramaAsync(OnStreetViewPanoramaReadyCallback onStreetViewPanoramaReadyCallback) {
        zzab.zzhj("getStreetViewPanoramaAsync() must be called on the main thread");
        this.ahC.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        StreetViewPanoramaFragment$zzb.zza(this.ahC, activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ahC.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.ahC.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.ahC.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.ahC.onDestroyView();
        super.onDestroyView();
    }

    @SuppressLint({"NewApi"})
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        StreetViewPanoramaFragment$zzb.zza(this.ahC, activity);
        this.ahC.onInflate(activity, new Bundle(), bundle);
    }

    public void onLowMemory() {
        this.ahC.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.ahC.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.ahC.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(StreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.ahC.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    protected IStreetViewPanoramaFragmentDelegate zzbpw() {
        this.ahC.zzbpt();
        return this.ahC.zzbcr() == null ? null : ((StreetViewPanoramaFragment$zza) this.ahC.zzbcr()).zzbpw();
    }
}
