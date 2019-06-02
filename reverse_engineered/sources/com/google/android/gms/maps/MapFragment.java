package com.google.android.gms.maps;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapFragmentDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzad;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

@TargetApi(11)
public class MapFragment extends Fragment {
    private final zzb ahi = new zzb(this);

    static class zza implements MapLifecycleDelegate {
        private final Fragment Ma;
        private final IMapFragmentDelegate ahj;

        public zza(Fragment fragment, IMapFragmentDelegate iMapFragmentDelegate) {
            this.ahj = (IMapFragmentDelegate) zzab.zzaa(iMapFragmentDelegate);
            this.Ma = (Fragment) zzab.zzaa(fragment);
        }

        public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
            try {
                this.ahj.getMapAsync(new com.google.android.gms.maps.internal.zzp.zza(this) {
                    final /* synthetic */ zza ahl;

                    public void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
                        onMapReadyCallback.onMapReady(new GoogleMap(iGoogleMapDelegate));
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
            Bundle arguments = this.Ma.getArguments();
            if (arguments != null && arguments.containsKey("MapOptions")) {
                zzad.zza(bundle, "MapOptions", arguments.getParcelable("MapOptions"));
            }
            this.ahj.onCreate(bundle);
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) zze.zzad(this.ahj.onCreateView(zze.zzae(layoutInflater), zze.zzae(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroy() {
            try {
                this.ahj.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            try {
                this.ahj.onDestroyView();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onEnterAmbient(Bundle bundle) {
            try {
                this.ahj.onEnterAmbient(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onExitAmbient() {
            try {
                this.ahj.onExitAmbient();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.ahj.onInflate(zze.zzae(activity), (GoogleMapOptions) bundle.getParcelable("MapOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onLowMemory() {
            try {
                this.ahj.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.ahj.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.ahj.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.ahj.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onStart() {
        }

        public void onStop() {
        }
    }

    static class zzb extends com.google.android.gms.dynamic.zza<zza> {
        private final Fragment Ma;
        protected zzf<zza> ahm;
        private final List<OnMapReadyCallback> ahn = new ArrayList();
        private Activity mActivity;

        zzb(Fragment fragment) {
            this.Ma = fragment;
        }

        private void setActivity(Activity activity) {
            this.mActivity = activity;
            zzbpt();
        }

        public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
            if (zzbcr() != null) {
                ((zza) zzbcr()).getMapAsync(onMapReadyCallback);
            } else {
                this.ahn.add(onMapReadyCallback);
            }
        }

        public void onEnterAmbient(Bundle bundle) {
            if (zzbcr() != null) {
                ((zza) zzbcr()).onEnterAmbient(bundle);
            }
        }

        public void onExitAmbient() {
            if (zzbcr() != null) {
                ((zza) zzbcr()).onExitAmbient();
            }
        }

        protected void zza(zzf<zza> zzf) {
            this.ahm = zzf;
            zzbpt();
        }

        public void zzbpt() {
            if (this.mActivity != null && this.ahm != null && zzbcr() == null) {
                try {
                    MapsInitializer.initialize(this.mActivity);
                    IMapFragmentDelegate zzag = zzae.zzdk(this.mActivity).zzag(zze.zzae(this.mActivity));
                    if (zzag != null) {
                        this.ahm.zza(new zza(this.Ma, zzag));
                        for (OnMapReadyCallback mapAsync : this.ahn) {
                            ((zza) zzbcr()).getMapAsync(mapAsync);
                        }
                        this.ahn.clear();
                    }
                } catch (RemoteException e) {
                    throw new RuntimeRemoteException(e);
                } catch (GooglePlayServicesNotAvailableException e2) {
                }
            }
        }
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static MapFragment newInstance(GoogleMapOptions googleMapOptions) {
        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("MapOptions", googleMapOptions);
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        zzab.zzhj("getMapAsync must be called on the main thread.");
        this.ahi.getMapAsync(onMapReadyCallback);
    }

    public void onActivityCreated(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onActivityCreated(bundle);
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.ahi.setActivity(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.ahi.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = this.ahi.onCreateView(layoutInflater, viewGroup, bundle);
        onCreateView.setClickable(true);
        return onCreateView;
    }

    public void onDestroy() {
        this.ahi.onDestroy();
        super.onDestroy();
    }

    public void onDestroyView() {
        this.ahi.onDestroyView();
        super.onDestroyView();
    }

    public final void onEnterAmbient(Bundle bundle) {
        zzab.zzhj("onEnterAmbient must be called on the main thread.");
        this.ahi.onEnterAmbient(bundle);
    }

    public final void onExitAmbient() {
        zzab.zzhj("onExitAmbient must be called on the main thread.");
        this.ahi.onExitAmbient();
    }

    @SuppressLint({"NewApi"})
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.ahi.setActivity(activity);
        Parcelable createFromAttributes = GoogleMapOptions.createFromAttributes(activity, attributeSet);
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("MapOptions", createFromAttributes);
        this.ahi.onInflate(activity, bundle2, bundle);
    }

    public void onLowMemory() {
        this.ahi.onLowMemory();
        super.onLowMemory();
    }

    public void onPause() {
        this.ahi.onPause();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.ahi.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader(MapFragment.class.getClassLoader());
        }
        super.onSaveInstanceState(bundle);
        this.ahi.onSaveInstanceState(bundle);
    }

    public void setArguments(Bundle bundle) {
        super.setArguments(bundle);
    }
}
