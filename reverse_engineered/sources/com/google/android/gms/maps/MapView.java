package com.google.android.gms.maps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzf;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.IMapViewDelegate;
import com.google.android.gms.maps.internal.MapLifecycleDelegate;
import com.google.android.gms.maps.internal.zzae;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import java.util.ArrayList;
import java.util.List;

public class MapView extends FrameLayout {
    private final zzb aho;

    static class zza implements MapLifecycleDelegate {
        private final ViewGroup ahp;
        private final IMapViewDelegate ahq;
        private View ahr;

        public zza(ViewGroup viewGroup, IMapViewDelegate iMapViewDelegate) {
            this.ahq = (IMapViewDelegate) zzab.zzaa(iMapViewDelegate);
            this.ahp = (ViewGroup) zzab.zzaa(viewGroup);
        }

        public void getMapAsync(final OnMapReadyCallback onMapReadyCallback) {
            try {
                this.ahq.getMapAsync(new com.google.android.gms.maps.internal.zzp.zza(this) {
                    final /* synthetic */ zza ahs;

                    public void zza(IGoogleMapDelegate iGoogleMapDelegate) throws RemoteException {
                        onMapReadyCallback.onMapReady(new GoogleMap(iGoogleMapDelegate));
                    }
                });
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onCreate(Bundle bundle) {
            try {
                this.ahq.onCreate(bundle);
                this.ahr = (View) zze.zzad(this.ahq.getView());
                this.ahp.removeAllViews();
                this.ahp.addView(this.ahr);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            throw new UnsupportedOperationException("onCreateView not allowed on MapViewDelegate");
        }

        public void onDestroy() {
            try {
                this.ahq.onDestroy();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onDestroyView() {
            throw new UnsupportedOperationException("onDestroyView not allowed on MapViewDelegate");
        }

        public void onEnterAmbient(Bundle bundle) {
            try {
                this.ahq.onEnterAmbient(bundle);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onExitAmbient() {
            try {
                this.ahq.onExitAmbient();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            throw new UnsupportedOperationException("onInflate not allowed on MapViewDelegate");
        }

        public void onLowMemory() {
            try {
                this.ahq.onLowMemory();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onPause() {
            try {
                this.ahq.onPause();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onResume() {
            try {
                this.ahq.onResume();
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            try {
                this.ahq.onSaveInstanceState(bundle);
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
        protected zzf<zza> ahm;
        private final List<OnMapReadyCallback> ahn = new ArrayList();
        private final ViewGroup aht;
        private final GoogleMapOptions ahu;
        private final Context mContext;

        zzb(ViewGroup viewGroup, Context context, GoogleMapOptions googleMapOptions) {
            this.aht = viewGroup;
            this.mContext = context;
            this.ahu = googleMapOptions;
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
            if (this.ahm != null && zzbcr() == null) {
                try {
                    MapsInitializer.initialize(this.mContext);
                    IMapViewDelegate zza = zzae.zzdk(this.mContext).zza(zze.zzae(this.mContext), this.ahu);
                    if (zza != null) {
                        this.ahm.zza(new zza(this.aht, zza));
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

    public MapView(Context context) {
        super(context);
        this.aho = new zzb(this, context, null);
        zzbpu();
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.aho = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        zzbpu();
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.aho = new zzb(this, context, GoogleMapOptions.createFromAttributes(context, attributeSet));
        zzbpu();
    }

    public MapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context);
        this.aho = new zzb(this, context, googleMapOptions);
        zzbpu();
    }

    private void zzbpu() {
        setClickable(true);
    }

    public void getMapAsync(OnMapReadyCallback onMapReadyCallback) {
        zzab.zzhj("getMapAsync() must be called on the main thread");
        this.aho.getMapAsync(onMapReadyCallback);
    }

    public final void onCreate(Bundle bundle) {
        this.aho.onCreate(bundle);
        if (this.aho.zzbcr() == null) {
            com.google.android.gms.dynamic.zza.zzb((FrameLayout) this);
        }
    }

    public final void onDestroy() {
        this.aho.onDestroy();
    }

    public final void onEnterAmbient(Bundle bundle) {
        zzab.zzhj("onEnterAmbient() must be called on the main thread");
        this.aho.onEnterAmbient(bundle);
    }

    public final void onExitAmbient() {
        zzab.zzhj("onExitAmbient() must be called on the main thread");
        this.aho.onExitAmbient();
    }

    public final void onLowMemory() {
        this.aho.onLowMemory();
    }

    public final void onPause() {
        this.aho.onPause();
    }

    public final void onResume() {
        this.aho.onResume();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        this.aho.onSaveInstanceState(bundle);
    }
}
