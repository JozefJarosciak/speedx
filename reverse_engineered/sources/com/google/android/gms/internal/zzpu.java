package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;

public class zzpu implements ConnectionCallbacks, OnConnectionFailedListener {
    public final Api<?> pD;
    private final int sV;
    private zzqf sW;

    public zzpu(Api<?> api, int i) {
        this.pD = api;
        this.sV = i;
    }

    private void zzaox() {
        zzab.zzb(this.sW, (Object) "Callbacks must be attached to a GoogleApiClient instance before connecting the client.");
    }

    public void onConnected(@Nullable Bundle bundle) {
        zzaox();
        this.sW.onConnected(bundle);
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzaox();
        this.sW.zza(connectionResult, this.pD, this.sV);
    }

    public void onConnectionSuspended(int i) {
        zzaox();
        this.sW.onConnectionSuspended(i);
    }

    public void zza(zzqf zzqf) {
        this.sW = zzqf;
    }
}
