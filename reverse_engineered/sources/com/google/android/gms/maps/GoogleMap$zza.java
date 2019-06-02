package com.google.android.gms.maps;

import com.google.android.gms.maps.internal.zzb.zza;

final class GoogleMap$zza extends zza {
    private final GoogleMap$CancelableCallback agU;

    GoogleMap$zza(GoogleMap$CancelableCallback googleMap$CancelableCallback) {
        this.agU = googleMap$CancelableCallback;
    }

    public void onCancel() {
        this.agU.onCancel();
    }

    public void onFinish() {
        this.agU.onFinish();
    }
}
