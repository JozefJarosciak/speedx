package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzpz extends GoogleApiClient {
    private final UnsupportedOperationException tl;

    public zzpz(String str) {
        this.tl = new UnsupportedOperationException(str);
    }

    public ConnectionResult blockingConnect() {
        throw this.tl;
    }

    public ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw this.tl;
    }

    public PendingResult<Status> clearDefaultAccountAndReconnect() {
        throw this.tl;
    }

    public void connect() {
        throw this.tl;
    }

    public void disconnect() {
        throw this.tl;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        throw this.tl;
    }

    @NonNull
    public ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        throw this.tl;
    }

    public boolean hasConnectedApi(@NonNull Api<?> api) {
        throw this.tl;
    }

    public boolean isConnected() {
        throw this.tl;
    }

    public boolean isConnecting() {
        throw this.tl;
    }

    public boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks) {
        throw this.tl;
    }

    public boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        throw this.tl;
    }

    public void reconnect() {
        throw this.tl;
    }

    public void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        throw this.tl;
    }

    public void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        throw this.tl;
    }

    public void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        throw this.tl;
    }

    public void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        throw this.tl;
    }

    public void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        throw this.tl;
    }
}
