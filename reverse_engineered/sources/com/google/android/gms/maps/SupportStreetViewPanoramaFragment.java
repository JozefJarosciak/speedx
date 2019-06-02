package com.google.android.gms.maps;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.maps.internal.IStreetViewPanoramaDelegate;
import com.google.android.gms.maps.internal.IStreetViewPanoramaFragmentDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class SupportStreetViewPanoramaFragment extends Fragment {
    private StreetViewPanorama ahD;
    private final SupportStreetViewPanoramaFragment$zzb ahW = new SupportStreetViewPanoramaFragment$zzb(this);

    public static SupportStreetViewPanoramaFragment newInstance() {
        return new SupportStreetViewPanoramaFragment();
    }

    public static SupportStreetViewPanoramaFragment newInstance(StreetViewPanoramaOptions streetViewPanoramaOptions) {
        SupportStreetViewPanoramaFragment supportStreetViewPanoramaFragment = new SupportStreetViewPanoramaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("StreetViewPanoramaOptions", streetViewPanoramaOptions);
        supportStreetViewPanoramaFragment.setArguments(bundle);
        return supportStreetViewPanoramaFragment;
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
        this.ahW.getStreetViewPanoramaAsync(onStreetViewPanoramaReadyCallback);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        SupportStreetViewPanoramaFragment$zzb.zza(this.ahW, activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ahW.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.ahW.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onDestroy() {
        this.ahW.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.ahW.onDestroyView();
        super.onDestroyView();
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        SupportStreetViewPanoramaFragment$zzb.zza(this.ahW, activity);
        this.ahW.onInflate(activity, new Bundle(), bundle);
    }

    public void onLowMemory() {
        this.ahW.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.ahW.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.ahW.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(SupportStreetViewPanoramaFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.ahW.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }

    protected IStreetViewPanoramaFragmentDelegate zzbpw() {
        this.ahW.zzbpt();
        return this.ahW.zzbcr() == null ? null : ((SupportStreetViewPanoramaFragment$zza) this.ahW.zzbcr()).zzbpw();
    }
}
